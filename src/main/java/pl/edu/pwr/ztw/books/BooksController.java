package pl.edu.pwr.ztw.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BooksController {
    @Autowired
    IBooksService booksService;

    @RequestMapping(value = "/get/books", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks()
    {
        return new ResponseEntity<>(booksService.getBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable("id") int id){
        return new ResponseEntity<>(booksService.getBook(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/book/{id}/authors", method = RequestMethod.GET)
    public ResponseEntity<Object> getBookAuthors(@PathVariable("id") int id){
        return new ResponseEntity<>(booksService.getBookAuthors(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, @RequestBody Book book) {

        boolean result = booksService.updateBook(id, book);
        if (result)
            return new ResponseEntity<>("Book is updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Book couldn't be updated", HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        boolean result = booksService.deleteBook(id);
        if (result)
            return new ResponseEntity<>("Book is deleted successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Book couldn't be deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<Object> createBook(@RequestBody Book book) {
        booksService.createBook(book);
        return new ResponseEntity<>("Book is created successfully", HttpStatus.CREATED);
    }
}
