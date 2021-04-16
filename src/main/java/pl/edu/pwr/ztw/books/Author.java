package pl.edu.pwr.ztw.books;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private List<Integer> books;


    public Author(int id, String firstName, String lastName, Integer[] books) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = new ArrayList<>(Arrays.asList(books));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Integer> getBooks() {
        return books;
    }

    public void removeBook(int bookId)
    {
        books.removeIf(b -> b==bookId);
    }

    public void addBook(int bookId)
    {
        if (!books.contains(bookId))
            books.add(bookId);
    }

    public void setBooks(List<Integer> books) {
        this.books = books;
    }
}
