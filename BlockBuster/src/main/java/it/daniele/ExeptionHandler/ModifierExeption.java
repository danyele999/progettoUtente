package it.daniele.ExeptionHandler;

public class ModifierExeption extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ModifierExeption(String msg) {
		super(msg);
	}
}

