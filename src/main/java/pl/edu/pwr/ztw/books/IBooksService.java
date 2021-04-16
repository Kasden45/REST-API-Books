package pl.edu.pwr.ztw.books;

import java.util.Collection;

public interface IBooksService {
    Collection<Book> getBooks();
    Book getBook(int id);
    void createBook(Book book);
    boolean updateBook(int id, Book book);
    boolean deleteBook(int id);
    Collection<Author> getBookAuthors(int id);
}
