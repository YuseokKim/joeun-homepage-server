package joen.website.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class InquiryDto {
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RequestDto implements Serializable{
		private String purpose;
		private String equipment;
		private String location;
		private String etc;
		private String phone;
	}
	
	@Data
	@AllArgsConstructor
	public static class ResponseDto implements Serializable{
		private Long id;
	}

}
