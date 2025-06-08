import mft.model.entity.Book;
import mft.model.entity.Loan;
import mft.model.entity.Person;
import mft.service.LoanService;

import java.time.LocalDate;

public class LoanTest {
    public static void main(String[] args) throws Exception {
        Person person = Person
                .builder()
                .id(4)
                .name("ali")
                .family("alipour")
                .nationalId("1234")
                .birthDate(LocalDate.of(2010, 1, 1))
                .build();


//        Book book = Book
//                .builder()
//                .id(4)
//                .title("Delphi")
//                .author("Delphi")
//                .isbn("4445566")
//                .build();
//
//        Loan newLoan = Loan
//                .builder()
//                .id(4)
//                .person(person)
//                .book(book)
//                .returnDate(LocalDate.now())
//                .build();

//        LoanService.save(newLoan);
//        LoanService.edit(newLoan);

        LoanService.returnLoan(1002);

//        LoanService.findAll().forEach(loan -> System.out.println(loan.getPerson().getFamily() + ":" + loan.getBook().getTitle()));
    }
}
