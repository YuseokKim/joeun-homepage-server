package joen.website.service;

import java.util.List;

import com.google.firebase.messaging.FirebaseMessagingException;

import joen.website.domain.Inquiry;
import joen.website.dto.DeviceDto;

public interface PushService {
	
	DeviceDto.ResponseDto save(DeviceDto.RequestDto dto);
	
	List<String> getTokenList();
	
	void sendPush(Inquiry inquiry) throws FirebaseMessagingException; 

}
