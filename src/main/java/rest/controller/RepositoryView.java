package rest.controller;

import java.util.List;

public interface RepositoryView {

	public void create(Book book);
	
	public List<Book> listBooks();
	
	public void editBook(Book book);
	
	public void remove(String id);
}
