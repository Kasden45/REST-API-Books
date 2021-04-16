package pl.edu.pwr.ztw.books;

import java.util.Collection;

public interface IAuthorsService {
    Collection<Author> getAuthors();
    Author getAuthor(int id);
    void createAuthor(Author author);
    boolean updateAuthor(int id, Author author);
    boolean deleteAuthor(int id);
    Collection<Book> getAuthorBooks(int id);
}
