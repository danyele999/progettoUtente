package it.daniele.ExeptionHandler;

public class ReadExeption extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ReadExeption(String msg) {
		super(msg);
	}
}

