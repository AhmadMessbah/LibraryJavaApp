package mft.model.repository;

import mft.controller.exceptions.PersonValidationException;
import mft.model.entity.Person;
import mft.tools.ConnectionProvider;
import mft.tools.EntityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PersonRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    public void save(Person person) throws SQLException {
        preparedStatement = connection.prepareStatement("select person_seq.nextval from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        person.setId(resultSet.getInt("nextval"));

        preparedStatement = connection.prepareStatement("insert into persons values (?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, person.getNationalId());
        preparedStatement.setDate(5, person.getBirthDate() == null? null:Date.valueOf(person.getBirthDate()));
        preparedStatement.execute();
    }

    public void edit(Person person) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "update persons set name=?, family=?,national_id=?, birth_date=? where id=?"
        );
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setString(3, person.getNationalId());
        preparedStatement.setDate(4, person.getBirthDate() == null? null:Date.valueOf(person.getBirthDate()));
        preparedStatement.setInt(5, person.getId());
        preparedStatement.execute();
    }

    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "delete from persons where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Person> findAll() throws SQLException {
        List<Person> personList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from persons");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            personList.add(EntityMapper.personMapper(resultSet));
        }
        return personList;
    }

    public Person findById(int id) throws SQLException {
        Person person = null;
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from persons where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            person = EntityMapper.personMapper(resultSet);
        }
        return person;
    }

    public List<Person> findByNameAndFamily(String name, String family) throws SQLException {
        List<Person> personList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from persons where name like ? and family like ?");
        preparedStatement.setString(1, name + "%");
        preparedStatement.setString(2, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Person person = EntityMapper.personMapper(resultSet);
            personList.add(person);
        }
        return personList;
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}
