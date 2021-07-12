package it.daniele.ExeptionHandler;

public class FindExeption extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public FindExeption(String msg) {
		super(msg);
	}
}

