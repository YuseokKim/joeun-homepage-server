package joen.website.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import joen.website.dto.DeviceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="device")
@NoArgsConstructor
@AllArgsConstructor
public class Device {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="fcm_token")
	private String fcmToken;
	
	@Column(name="model")
	private String model;
	
	public Device(DeviceDto.RequestDto dto) {
		this.setFcmToken(dto.getToken());
		this.setModel(dto.getModel());
		
	}

}
