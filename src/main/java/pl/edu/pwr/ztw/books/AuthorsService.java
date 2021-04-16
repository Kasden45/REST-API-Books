package pl.edu.pwr.ztw.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorsService implements IAuthorsService {

    private final AuthorRepository authors;
    private final BookRepository books;

    @Autowired
    public AuthorsService(@Qualifier("authors") AuthorRepository authors, @Qualifier("books") BookRepository books){
        this.authors = authors;
        this.books = books;
    }


    @Override
    public Collection<Author> getAuthors() {
        return authors.getAuthors();
    }

    @Override
    public Author getAuthor(int id) {
        return authors.getAuthor(id);
    }

    @Override
    public void createAuthor(Author author) {
        if (authors.getAuthors().size() > 0)
            author.setId(new ArrayList<>(authors.getAuthors()).get(authors.getAuthors().size()-1).getId()+1);
        else
            author.setId(1);
        // Zostawić tylko istniejące książki
        author.setBooks(
                author.getBooks().stream()
                        .filter(
                                b -> books.getBooks().stream().map(Book::getId).collect(Collectors.toList()).contains(b)
                        )
                        .collect(Collectors.toList()));
        // Dodajemy nowego autora do każdej książki którą napisał
        for (Book b:books.getBooks())
        {

            if (author.getBooks().contains(b.getId()))
                // Napisał książkę
                b.addAuthor(author.getId());
        }
        authors.createAuthor(author);
    }

    @Override
    public boolean updateAuthor(int id, Author author) {
        author.setId(id);
        // Zostawić tylko istniejące książki
        author.setBooks(
                author.getBooks().stream()
                        .filter(
                                b -> books.getBooks().stream().map(Book::getId).collect(Collectors.toList()).contains(b)
                        )
                        .collect(Collectors.toList()));

        if (authors.updateAuthor(id, author))
        {
            for (Book b:books.getBooks())
            {

                // Dodajemy nowego autora do każdej książki którą napisał
                if (author.getBooks().contains(b.getId()))
                    b.addAuthor(author.getId());
                else
                    // Usuwamy autora z książek, które mu 'odebrano'
                    if (b.getAuthors().contains(author.getId()))
                        b.removeAuthor(author.getId());

            }
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deleteAuthor(int id) {
        for (Book b:books.getBooks())
        {
            b.removeAuthor(id);
        }
        return authors.deleteAuthor(id);
    }

    @Override
    public Collection<Book> getAuthorBooks(int id) {
        Author author = authors.getAuthor(id);
        return books.getBooks().stream()
                .filter(b -> author.getBooks().contains(b.getId()))
                .collect(Collectors.toList());
    }
}
