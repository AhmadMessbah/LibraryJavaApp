package mft.service;

import mft.model.entity.Book;
import mft.model.repository.BookRepository;

import java.util.List;

public class BookService {
    public static void save(Book book) throws Exception {
        try(BookRepository bookRepository = new BookRepository()){
                bookRepository.save(book);
        }
    }

    public static void edit(Book book) throws Exception {
        try(BookRepository bookRepository = new BookRepository()){
            bookRepository.edit(book);
        }
    }

    public static void delete(int id) throws Exception {
        try(BookRepository bookRepository = new BookRepository()){
            bookRepository.delete (id);
        }
    }

    public static List<Book> findAll() throws Exception {
        try(BookRepository bookRepository = new BookRepository()){
            return bookRepository.findAll();
        }
    }

    public static Book findById(int id) throws Exception {
        try(BookRepository bookRepository = new BookRepository()){
            return bookRepository.findById(id);
        }
    }

    public static List<Book> findByTitleAndAuthor(String title ,String author) throws Exception {
        try(BookRepository bookRepository = new BookRepository()){
            return bookRepository.findByTitleAndAuthor(title, author);
        }
    }
}
