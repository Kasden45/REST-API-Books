package pl.edu.pwr.ztw.books;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private List<Integer> authors;
    private String title;
    private int pages;

    public Book(int id, String title, List<Integer> authors, int pages) {
        this.id = id;
        this.authors = new ArrayList<>(authors);
        this.title = title;
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public List<Integer> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Integer> authors) {
        this.authors = authors;
    }

    public void removeAuthor(int authorId)
    {
        authors.removeIf(a -> a==authorId);
    }

    public void addAuthor(int authorId)
    {
        if (!authors.contains(authorId))
            authors.add(authorId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
