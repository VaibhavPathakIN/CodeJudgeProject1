package org.codejudge.sb.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;

public class Utils {

	/**
	 * Method to return the failure message.
	 * 
	 * @param e
	 * @return
	 */
	public static Map<String, Object> gotFailure(Exception e) {
		Map<String, Object> failure = new HashMap<String, Object>();
		failure.put("status", "failure");
		failure.put("reason", e.getMessage());
		return failure;
	}

	/**
	 * Method to return the HttpStatus based on the exception occurred.
	 * 
	 * @param e
	 * @return
	 */
	public static HttpStatus getStatus(Exception e) {
		if (e instanceof EmptyResultDataAccessException) {
			return HttpStatus.NOT_FOUND;
		}
		return HttpStatus.BAD_REQUEST;
	}
}
