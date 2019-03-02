package com.usuarios.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public static Date dt(String strDate) {

		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
		} catch (ParseException e) {
			System.err.println("Not valid format date!!");
		}

		return date;
	}
	
	
}
