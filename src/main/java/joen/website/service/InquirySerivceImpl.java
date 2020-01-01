package joen.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import joen.website.domain.Inquiry;
import joen.website.dto.InquiryDto;
import joen.website.dto.InquiryDto.RequestDto;
import joen.website.dto.InquiryDto.ResponseDto;
import joen.website.repositories.InquiryRepository;


@Service("InquirySerivce")
public class InquirySerivceImpl implements InquirySerivce {
	
	@Autowired
	InquiryRepository inquiryRepository;
	
	@Override
	public Inquiry insertInquiry(InquiryDto.RequestDto requestDto)  {
		return inquiryRepository.save(new Inquiry(requestDto));
	}

}
