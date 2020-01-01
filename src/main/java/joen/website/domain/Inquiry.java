package joen.website.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import joen.website.dto.InquiryDto;
import lombok.Data;

@Data
@Table(name="Inquiry")
@Entity
public class Inquiry {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long id;
	
	@Column(name="purpose")
	private String purpose;
	
	@Column(name="equipment")
	private String equipment;
	
	@Column(name="location")
	private String location;
	
	@Column(name="etc")
	private String etc;
	
	@Column(name="phone")
	private String phone;
	
	public Inquiry(InquiryDto.RequestDto dto) {
		this.setPurpose(dto.getPurpose());
		this.setLocation(dto.getLocation());
		this.setEquipment(dto.getEquipment());
		this.setPhone(dto.getPhone());
		this.setEtc(dto.getEtc());
	}
}
