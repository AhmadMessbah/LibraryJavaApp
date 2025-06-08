import mft.model.entity.Book;
import mft.service.BookService;

public class BookTest {
    public static void main(String[] args) throws Exception {
        Book book = Book
                .builder()
                .title("Delphi")
                .author("Delphi")
                .isbn("4445566")
                .build();

        BookService.save(book);

        System.out.println(BookService.findAll());
    }
}
