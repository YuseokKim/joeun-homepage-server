package joen.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;

import joen.website.common.exception.CustomException;
import joen.website.common.exception.ExceptionCode;
import joen.website.domain.Inquiry;
import joen.website.domain.Result;
import joen.website.dto.InquiryDto;
import joen.website.service.InquirySerivce;
import joen.website.service.PushService;

@RestController
@RequestMapping("/v1.0/")
public class JoenController {
	
	@Autowired
	InquirySerivce inquiryService;
	
	@Autowired
	PushService pushService;
	
	@CrossOrigin(origins={"http://www.joeunjunggi.co.kr"})
	@PostMapping("inquiry")
	public Result insertInquiry(@RequestBody InquiryDto.RequestDto dto){
		if(dto.getPhone() == null || dto.getPhone().equals("")) {
			throw new CustomException(ExceptionCode.INVALID_PARAMETER);
		}
		Inquiry inquiry = inquiryService.insertInquiry(dto);
		try {
			pushService.sendPush(inquiry);
		} catch (FirebaseMessagingException e) {
			return new Result(null, "FAIL", ExceptionCode.FAIL_TO_SEND_PUSH.getCode());
		}
		
		return new Result(new InquiryDto.ResponseDto(inquiry.getId()),"OK",200);
	}
}
