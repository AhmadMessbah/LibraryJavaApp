package mft.service;

import mft.model.entity.Person;
import mft.model.repository.PersonRepository;

import java.time.LocalDate;
import java.util.List;

public class PersonService {
    public static void save(Person person) throws Exception {
        try(PersonRepository personRepository = new PersonRepository()){
//            قوانین کسب و کار مربوط به ثبت افراد رو قرار می دهیم
            if(LocalDate.now().getYear() - person.getBirthDate().getYear() < 20 ) {
                personRepository.save(person);
            }else{
                throw new Exception("سن برای ثبت نام مجاز نیست");
            }
        }
    }

    public static void edit(Person person) throws Exception {
        try(PersonRepository personRepository = new PersonRepository()){
            personRepository.edit(person);
        }
    }

    public static void delete(int id) throws Exception {
        try(PersonRepository personRepository = new PersonRepository()){
            personRepository.delete (id);
        }
    }

    public static List<Person> findAll() throws Exception {
        try(PersonRepository personRepository = new PersonRepository()){
            return personRepository.findAll();
        }
    }

    public static Person findById(int id) throws Exception {
        try(PersonRepository personRepository = new PersonRepository()){
            return personRepository.findById(id);
        }
    }

    public static List<Person> findByNameAndFamily(String name ,String family) throws Exception {
        try(PersonRepository personRepository = new PersonRepository()){
            return personRepository.findByNameAndFamily(name, family);
        }
    }
}
