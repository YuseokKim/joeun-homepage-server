package joen.website.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
	Integer code;
	String message;
	
	public CustomException(ExceptionCode ec) {
		this.setCode(ec.getCode());
		this.setMessage(ec.getMessage());
	}

}
