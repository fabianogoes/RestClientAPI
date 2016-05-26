package com.programar.rest.api;

import java.util.Arrays;

import org.apache.http.Header;
import org.springframework.http.HttpStatus;

/**
 * Client API Rest
 * 
 * @author Fabiano Góes - 26/05/2016
 * @category REST
 * @version 1.0
 *
 */
public class ResponseEntity {

	private HttpStatus httpStatus;
	private String httpBody;
	private String exceptionMessage;
	private Header[] headers;

	public ResponseEntity() {
	}
	
	public ResponseEntity(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public ResponseEntity(HttpStatus httpStatus, String httpBody) {
		this(httpStatus);
		this.httpBody = httpBody;
	}

	public ResponseEntity(HttpStatus httpStatus, String httpBody, String exceptionMessage) {
		this(httpStatus, httpBody);
		this.exceptionMessage = exceptionMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getHttpBody() {
		return httpBody;
	}

	public void setHttpBody(String httpBody) {
		this.httpBody = httpBody;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}

	@Override
	public String toString() {
		return "RestEntityResponse [httpStatusCode=" + httpStatus + ", httpBody=" + httpBody + ", exceptionMessage="
				+ exceptionMessage + ", headers=" + Arrays.toString(headers) + "]";
	}

}
