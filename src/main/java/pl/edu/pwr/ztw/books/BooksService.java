package pl.edu.pwr.ztw.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksService implements IBooksService{

    private final AuthorRepository authors;
    private final BookRepository books;

    @Autowired
    public BooksService(@Qualifier("authors") AuthorRepository authors, @Qualifier("books") BookRepository books){
        this.authors = authors;
        this.books = books;
    }

    @Override
    public Collection<Book> getBooks() {
        return books.getBooks();
    }

    @Override
    public Book getBook(int id) {
        return books.getBook(id);

    }

    @Override
    public void createBook(Book book) {
        if (books.getBooks().size() > 0)
            book.setId(new ArrayList<>(books.getBooks()).get(books.getBooks().size()-1).getId()+1);
        else
            book.setId(1);
        // Zostawić tylko istniejących autorów
        book.setAuthors(
                book.getAuthors().stream()
                        .filter(
                                a -> authors.getAuthors().stream().map(Author::getId).collect(Collectors.toList()).contains(a)
                        )
                        .collect(Collectors.toList()));

        for (Author a:authors.getAuthors())
        {
            // Dodajemy książkę do każdego autora który ją napisał
            if (book.getAuthors().contains(a.getId()))
                a.addBook(book.getId());
        }
        books.createBook(book);
    }

    @Override
    public boolean updateBook(int id, Book book) {
        book.setId(id);
        // Zostawić tylko istniejących autorów
        book.setAuthors(
                book.getAuthors().stream()
                                .filter(
                                        a -> authors.getAuthors().stream().map(Author::getId).collect(Collectors.toList()).contains(a)
                                )
                                .collect(Collectors.toList()));
        if (books.updateBook(id, book))
        {
            for (Author a:authors.getAuthors())
            {
                // Dodajemy książkę do każdego autora który ją napisał
                if (book.getAuthors().contains(a.getId()))
                    a.addBook(book.getId());
                else
                    // Usuwamy książki autorom, którym je 'odebrano'
                    if (a.getBooks().contains(book.getId()))
                        a.removeBook(book.getId());

            }
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deleteBook(int id) {
        for (Author a:authors.getAuthors())
        {
            a.removeBook(id);
        }
        return books.deleteBook(id);
    }

    @Override
    public Collection<Author> getBookAuthors(int id) {
        Book book = books.getBook(id);
        return authors.getAuthors().stream()
                .filter(a -> book.getAuthors().contains(a.getId()))
                .collect(Collectors.toList());
    }

}
