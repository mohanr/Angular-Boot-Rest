package rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemManagementController {
	

	    private static final Logger LOGGER = LoggerFactory.getLogger(ItemManagementController.class);
		
	    private MemberService service;//Not injected and used now

        ArrayList<Book> books = new ArrayList<>();

	    
	    public ItemManagementController(final MemberService service) {
	        this.service = service;
	    }

	    public ItemManagementController() {
	        books.add(new Book("Science Fiction","Pandora\'s star","Peter F. Hamilton",1));
	    	books.add(new Book("Science Fiction","Absolution Gap","Alastair Reynolds",2));

	    }

	    /* No Validator is configured */
	    @RequestMapping(value = "/editbook", method = RequestMethod.POST)
	    public  void editBook(@RequestBody @Valid final Book book) {
	        LOGGER.debug("Received request to edit " + book);
	        LOGGER.debug("Replaced the old value " + books.set(book.getId() - 1, book));
	        LOGGER.debug("There are [" + books.size() + "] books");
	    }
	    
	    /* No Validator is configured */
	    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
	    public  void createBook(@RequestBody @Valid final Book book) {
	        LOGGER.debug("Received request to create " + book);
	        book.setId(books.size() + 1);
	        LOGGER.debug("Added the  value " + books.add(book));
	        LOGGER.debug("There are [" + books.size() + "] books");
	    }

	    @RequestMapping(value = "/books", method = RequestMethod.GET)
	    public @ResponseBody List<Book> listBooks() {
	        LOGGER.debug("List all books");
	        return books;
	    }

	    /* No Validator is configured */
	    @RequestMapping(value = "/deletebook/{id}", method = RequestMethod.DELETE)
	    public void  deleteBook(@PathVariable String id) {
	        LOGGER.debug("Index of value to be Deleted  " + id);
	        LOGGER.debug("Deleted  old value " + books.remove(Integer.parseInt(id)));
	    }

	    @ExceptionHandler
	    @ResponseStatus(HttpStatus.CONFLICT)
	    public String flagAsRedundant(RedundantBookException e) {
	        return e.getMessage();
	    }

	

}
