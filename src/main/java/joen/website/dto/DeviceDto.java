package joen.website.dto;

import java.io.Serializable;

import joen.website.domain.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DeviceDto {
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RequestDto implements Serializable{
		private String token;
		private String model;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ResponseDto implements Serializable{
		private String token;
		private String model;
		
		public ResponseDto(Device device) {
			this.setToken(device.getFcmToken());
			this.setModel(device.getModel());
		}
	}

}
