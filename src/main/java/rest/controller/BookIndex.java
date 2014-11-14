package rest.controller;

import java.io.Serializable;

public class BookIndex  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6254295237956257320L;

	public BookIndex() {
	}

	private int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
