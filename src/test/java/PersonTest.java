import mft.model.entity.Person;
import mft.service.PersonService;

import java.time.LocalDate;

public class PersonTest {
    public static void main(String[] args) throws Exception {
        Person person = Person
                .builder()
                .name("ali")
                .family("alipour")
                .nationalId("1234")
                .birthDate(LocalDate.of(2010, 1, 1))
                .build();

//        PersonService.save(person);
        System.out.println(PersonService.findAll());
    }
}
