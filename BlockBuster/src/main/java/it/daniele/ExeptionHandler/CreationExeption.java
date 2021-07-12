package it.daniele.ExeptionHandler;

public class CreationExeption extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public CreationExeption(String msg) {
		super(msg);
	}
}

