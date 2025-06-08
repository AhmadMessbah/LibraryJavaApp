package mft.tools;

import mft.model.entity.Book;
import mft.model.entity.Loan;
import mft.model.entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper {
    public static Person personMapper(ResultSet resultSet) throws SQLException {
        return Person
                .builder()
                .id(resultSet.getInt("ID"))
                .name(resultSet.getString("NAME"))
                .family(resultSet.getString("FAMILY"))
                .nationalId(resultSet.getString("NATIONAL_ID"))
                .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                .build();
    }

    public static Book bookMapper(ResultSet resultSet) throws SQLException {
        return Book
                .builder()
                .id(resultSet.getInt("ID"))
                .title(resultSet.getString("TITLE"))
                .author(resultSet.getString("AUTHOR"))
                .isbn(resultSet.getString("ISBN"))
                .build();
    }

    public static Loan loanMapper(ResultSet resultSet) throws SQLException {
        Person person =
                Person
                        .builder()
                        .id(resultSet.getInt("PERSON_ID"))
                        .name(resultSet.getString("PERSON_NAME"))
                        .family(resultSet.getString("PERSON_FAMILY"))
                        .nationalId(resultSet.getString("PERSON_NATIONAL_ID"))
                        .birthDate(resultSet.getDate("PERSON_BIRTH_DATE").toLocalDate())
                        .build();

        Book book = Book
                .builder()
                .id(resultSet.getInt("BOOK_ID"))
                .title(resultSet.getString("BOOK_TITLE"))
                .author(resultSet.getString("BOOK_AUTHOR"))
                .isbn(resultSet.getString("BOOK_ISBN"))
                .build();

        return Loan
                .builder()
                .id(resultSet.getInt("LOAN_ID"))
                .person(person)
                .book(book)
                .loanDate(resultSet.getDate("LOAN_DATE").toLocalDate())
                .returnDate(resultSet.getDate("RETURN_DATE").toLocalDate())
                .build();
    }
}
