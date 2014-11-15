package rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("InMemoryRepository")

public class InMemoryRepository implements RepositoryView {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryRepository.class);

    public InMemoryRepository() {
		super();
        books.add(new Book("Science Fiction","Pandora\'s star","Peter F. Hamilton",1));
    	books.add(new Book("Science Fiction","Absolution Gap","Alastair Reynolds",2));
	}

    
	ArrayList<Book> books = new ArrayList<>();

	@Override
	public void create(Book book) {
	       LOGGER.debug("Received request to create " + book);
	        book.setId(books.size() + 1);
	        LOGGER.debug("Added the  value " + books.add(book));
	        LOGGER.debug("There are [" + books.size() + "] books");
	}

	@Override
	public List<Book> listBooks() {
    	LOGGER.debug("List all books");
    	return books;
	}

	@Override
	public void editBook(Book book) {
        LOGGER.debug("Received request to edit " + book);
        LOGGER.debug("Replaced the old value " + books.set(book.getId() - 1, book));
        LOGGER.debug("There are [" + books.size() + "] books");
	}

	@Override
	public void remove(String id) {
        LOGGER.debug("Index of value to be Deleted  " + id);
        LOGGER.debug("Deleted  old value " + books.remove(Integer.parseInt(id)));
	}

}
