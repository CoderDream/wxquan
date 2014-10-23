package com.coderdream.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;

import com.coderdream.bean.Logging;

public class EntitiesHelper {

	public static void assertLogging(Logging expected, Logging actual) {
		Assert.assertNotNull(expected);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getLogDate(), actual.getLogDate());
		Assert.assertEquals(expected.getLogLevel(), actual.getLogLevel());
		Assert.assertEquals(expected.getLocation(), actual.getLocation());
		Assert.assertEquals(expected.getMessage(), actual.getMessage());
	}

	public static void assertLogging(Logging expected) {
		String logStr = "InitLogging";
		// Timestamp sDate = new Timestamp(1413993830024l);
		// 2014-10-23 00:03:50.024
		// time: 1413993830024
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String time = df.format(new Date(1413993830024l));
		Logging baseLogging = new Logging(1, time, "DEBUG", "LoggingDaoTest", logStr);
		assertLogging(expected, baseLogging);
	}
}