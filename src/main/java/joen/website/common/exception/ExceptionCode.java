package joen.website.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
		//데이터 관련 코드
		INVALID_PARAMETER(1001,"Invalid Parameter"),
		DUPLICATED_DATA(1002,"Duplicated Data"),
		ALREADY_USED(1003, "Already Used"),
		ALREADY_IN_EXISTENCE(1004, "Already In Existence"),
		NOT_FOUND_DATA(1005,"Not Found Data"),
		
		//푸시 관련 코드
		FAIL_TO_SEND_PUSH(2001,"Fail to send push")
		;
		
		Integer code;
		String message;

}
