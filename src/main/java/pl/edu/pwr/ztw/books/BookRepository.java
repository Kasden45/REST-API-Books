package pl.edu.pwr.ztw.books;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository("books")
public class BookRepository {
    private static List<Book> booksRepo = new ArrayList<>();
    static {
        booksRepo.add(new Book(1,"Potop", Arrays.asList(1), 936));
        booksRepo.add(new Book(2,"Wesele", Arrays.asList(2), 150));
        booksRepo.add(new Book(3,"Dziady", Arrays.asList(3), 292));
    }

    public Collection<Book> getBooks() {
        return booksRepo;
    }


    public Book getBook(int id) {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }


    public void createBook(Book book) {
        booksRepo.add(book);
    }


    public boolean updateBook(int id, Book book) {
        Book updated = booksRepo.stream()
                .filter(b -> b.getId() == id).findAny().orElse(null);
        if (updated == null)
            return false;
        int index = booksRepo.indexOf(updated);
        if (index >= 0)
            booksRepo.set(index, book);
        return true;
    }


    public boolean deleteBook(int id) {
        return booksRepo.removeIf(b -> b.getId() == id);
    }
}
