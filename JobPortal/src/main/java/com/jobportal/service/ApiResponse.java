package com.jobportal.service;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
	private String status;
	private String message;
	private LocalDateTime timeStamp;
	
	public ApiResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = LocalDateTime.now();
	}
}
