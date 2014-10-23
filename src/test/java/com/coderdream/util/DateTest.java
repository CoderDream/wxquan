package com.coderdream.util;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Test;

public class DateTest {

	@Test
	public void getTime() {
		Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
		System.out.println(timestamp);
		System.out.println("time: " + timestamp.getTime());
	}
}
