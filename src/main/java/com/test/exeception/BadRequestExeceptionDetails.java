package com.test.exeception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadRequestExeceptionDetails {

	private String title;
	private int status;
	private String details;
	private String developerMessage;
	private LocalDateTime timestamp;
	
}
