package it.daniele.ExeptionHandler;

import org.springframework.http.HttpStatus;

import lombok.Data;
@Data
public class ApiError {
	
	private String message;
	private HttpStatus status;
	private final String developer_reference = "Developer ID: Daniele.Gab";
	
	
	@SuppressWarnings("unused")
	public ApiError(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
		String res = developer_reference;
		
	}

}
