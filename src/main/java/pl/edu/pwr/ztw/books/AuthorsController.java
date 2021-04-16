package pl.edu.pwr.ztw.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorsController {
    @Autowired
    IAuthorsService authorsService;

    @RequestMapping(value = "/get/authors", method = RequestMethod.GET)

    public ResponseEntity<Object> getAuthors()
    {
        return new ResponseEntity<>(authorsService.getAuthors(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthor(@PathVariable("id") int id){
        return new ResponseEntity<>(authorsService.getAuthor(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/author/{id}/books", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthorBooks(@PathVariable("id") int id){
        return new ResponseEntity<>(authorsService.getAuthorBooks(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") int id, @RequestBody Author author) {

        boolean result = authorsService.updateAuthor(id, author);
        if (result)
            return new ResponseEntity<>("Author is updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Author couldn't be updated", HttpStatus.OK);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") int id) {
        boolean result = authorsService.deleteAuthor(id);
        if (result)
            return new ResponseEntity<>("Author is deleted successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Author couldn't be deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public ResponseEntity<Object> createAuthor(@RequestBody Author author) {
        authorsService.createAuthor(author);
        return new ResponseEntity<>("Author is created successfully", HttpStatus.CREATED);
    }
}
