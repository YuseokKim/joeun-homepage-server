package joen.website.service;

import joen.website.domain.Inquiry;
import joen.website.dto.InquiryDto;

public interface InquirySerivce {
	
	//InquiryDto.ResponseDto insertInquiry(InquiryDto.RequestDto requestDto);
	Inquiry insertInquiry(InquiryDto.RequestDto requestDto);

}
