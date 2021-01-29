package com.example.webfluxdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReactiveRateLimitException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public ReactiveRateLimitException(String reason) {
		super(HttpStatus.TOO_MANY_REQUESTS, reason);
	}

}
