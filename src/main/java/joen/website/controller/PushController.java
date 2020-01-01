package joen.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import joen.website.common.exception.CustomException;
import joen.website.common.exception.ExceptionCode;
import joen.website.dto.DeviceDto;
import joen.website.service.PushService;

@RestController
@RequestMapping("/v1.0")
public class PushController {

	@Autowired
	PushService pushService;
	
	@PostMapping("/token")
	public Object insertToken(@RequestBody DeviceDto.RequestDto dto) {
		if(dto == null) {
			throw new CustomException(ExceptionCode.INVALID_PARAMETER);
		}
		DeviceDto.ResponseDto result = pushService.save(dto);
		return result;
	}
}
