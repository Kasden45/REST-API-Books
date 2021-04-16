package pl.edu.pwr.ztw.books;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository("authors")
public class AuthorRepository {
    private static List<Author> authorsRepo = new ArrayList<>();
    static {
        authorsRepo.add(new Author(1, "Henryk", "Sienkiewicz", new Integer[] {1}));
        authorsRepo.add(new Author(2, "Stanisław", "Wyspiański", new Integer[] {2}));
        authorsRepo.add(new Author(3, "Adam", "Mickiewicz", new Integer[] {3}));
    }

    public Collection<Author> getAuthors() {
        return authorsRepo;
    }


    public Author getAuthor(int id) {
        return authorsRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }


    public void createAuthor(Author author) {
        authorsRepo.add(author);
    }


    public boolean updateAuthor(int id, Author author) {
        Author updated = authorsRepo.stream()
                .filter(b -> b.getId() == id).findAny().orElse(null);
        if (updated == null)
            return false;
        int index = authorsRepo.indexOf(updated);
//        booksRepo.remove(id);
        if (index >= 0)
            authorsRepo.set(index, author);
        return true;
    }


    public boolean deleteAuthor(int id) {
        return authorsRepo.removeIf(b -> b.getId() == id);
    }
}
