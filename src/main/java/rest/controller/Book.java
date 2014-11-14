package rest.controller;

import java.io.Serializable;

public class Book implements Serializable{


	public Book(String genre, String title, String author, int id) {
		super();
		this.genre = genre;
		this.title = title;
		this.author = author;
		this.id = id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7443226200578666264L;

	private String genre;
	
	private String title;
	
	private String author;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Book(){
		
	}
	
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("ID [" + id + "] genre[" + genre + "] Title [")
		  .append(title + "] Author [" + author + "]");
		return sb.toString();
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
