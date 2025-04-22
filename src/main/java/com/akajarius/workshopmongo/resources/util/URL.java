package com.akajarius.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.expression.ParseException;

public class URL {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			return "";

		}

	}

	public static Date convertDate(String textDate, Date defaultValue) throws java.text.ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		try {
				return sdf.parse(textDate);
			} catch (ParseException e) {
				return defaultValue;
			}

	}

}
