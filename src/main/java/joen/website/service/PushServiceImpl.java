package joen.website.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.gson.JsonObject;

import joen.website.domain.Device;
import joen.website.domain.Inquiry;
import joen.website.dto.DeviceDto;
import joen.website.dto.DeviceDto.RequestDto;
import joen.website.dto.DeviceDto.ResponseDto;
import joen.website.repositories.DeviceRepository;

@Service("PushService")
public class PushServiceImpl implements PushService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	GoogleCredentials googleCredentials;
	
	@Autowired
	FirebaseOptions firebaseOptions;
	
	@Autowired
	DeviceRepository deviceRepository;
	
	@Override
	public List<String> getTokenList() {
		return deviceRepository.findFcmTokenList();
	}
	
	@Override
	public ResponseDto save(RequestDto dto) {
		Device device = deviceRepository.findByModel(dto.getModel());
		
		if(device != null) {
			device.setFcmToken(dto.getToken());
			deviceRepository.flush();
		}else {
			device = deviceRepository.save(new Device(dto));
		}
		
		return new DeviceDto.ResponseDto(device);
	}
	
	
	@Override
	public void sendPush(Inquiry inquiry) throws FirebaseMessagingException {
		
		JsonObject data = new JsonObject();
		
		data.addProperty("purpose",inquiry.getPurpose() );
		data.addProperty("equipment",inquiry.getEquipment());
		data.addProperty("phone",inquiry.getPhone());
		data.addProperty("etc",inquiry.getEtc() );
		data.addProperty("location",inquiry.getLocation() );
		List<String> tokens = getTokenList();
		
		for(String token:tokens) {
			Message message = Message.builder()
					.setNotification(new Notification("견적 문의 알람", inquiry.toString()))
					.putData("data", data.toString())
					.setToken(token)
					.build();
			
			FirebaseMessaging.getInstance().send(message);
		}
		
		
	}
	

}
