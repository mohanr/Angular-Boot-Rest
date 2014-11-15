package rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		
	    @Autowired private MemberService service;


	    
	    public ItemManagementController(final MemberService service) {
	        this.service = service;
	    }

	    public ItemManagementController() {

	    }

	    /* No Validator is configured */
	    /**http://www.amundsen.com/blog/archives/1063
	     * Mapping CRUD operation to HTTP is not intended by the Rest
	     * specification. So POST is acceptable.
	     * */
	    @RequestMapping(value = "/editbook", method = RequestMethod.POST)
	    public  void editBook(@RequestBody @Valid final Book book) {
	    	service.editBook(book);
	    }
	    
	    /* No Validator is configured */
	    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
	    public  void createBook(@RequestBody @Valid final Book book) {
	    	service.createBook(book);
	    }

	    @RequestMapping(value = "/books", method = RequestMethod.GET)
	    public @ResponseBody List<Book> listBooks() {
	    	return service.listBooks();
	    }

	    /* No Validator is configured */
	    @RequestMapping(value = "/deletebook/{id}", method = RequestMethod.DELETE)
	    public void  deleteBook(@PathVariable String id) {
	        service.remove(id);
	    }

	    @ExceptionHandler
	    @ResponseStatus(HttpStatus.CONFLICT)
	    public String flagAsRedundant(RedundantBookException e) {
	        return e.getMessage();
	    }

	

}
