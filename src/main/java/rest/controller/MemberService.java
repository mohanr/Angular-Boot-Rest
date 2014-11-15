package rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service("MemberService")
public class MemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

    @Autowired private RepositoryView repository;


	public MemberService(final RepositoryView repository) {
		this.repository = repository;
	}

    public MemberService() {

    }


	public void editBook(Book book) {
        repository.editBook(book);
	}

	public void createBook(Book book){
        repository.create(book);

	}
	
    public @ResponseBody List<Book> listBooks() {
    	return repository.listBooks();
    }


	public void remove(String id) {
		repository.remove(id);
	}

}
