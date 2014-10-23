package com.coderdream.bean;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class DateTest {
	// `id` int(11) NOT NULL AUTO_INCREMENT,
	private int id;

	private Date logDate;

	private Time logTime;

	private String logMicrosecond;

	private Timestamp logDatetime;

	private Timestamp logTimestamp;

	private String logMillisecondsStr;

	private Long logMilliseconds;

	public DateTest() {

	}

	public DateTest(Date logDate, Time logTime, String logMicrosecond, Timestamp logDatetime, Timestamp logTimestamp,
					String logMillisecondsStr, Long logMilliseconds) {
		super();
		this.logDate = logDate;
		this.logTime = logTime;
		this.logMicrosecond = logMicrosecond;
		this.logDatetime = logDatetime;
		this.logTimestamp = logTimestamp;
		this.logMillisecondsStr = logMillisecondsStr;
		this.logMilliseconds = logMilliseconds;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getLogDatetime() {
		return logDatetime;
	}

	public void setLogDatetime(Timestamp logDatetime) {
		this.logDatetime = logDatetime;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public Time getLogTime() {
		return logTime;
	}

	public void setLogTime(Time logTime) {
		this.logTime = logTime;
	}

	public Timestamp getLogTimestamp() {
		return logTimestamp;
	}

	public void setLogTimestamp(Timestamp logTimestamp) {
		this.logTimestamp = logTimestamp;
	}

	public String getLogMicrosecond() {
		return logMicrosecond;
	}

	public void setLogMicrosecond(String logMicrosecond) {
		this.logMicrosecond = logMicrosecond;
	}

	public String getLogMillisecondsStr() {
		return logMillisecondsStr;
	}

	public void setLogMillisecondsStr(String logMillisecondsStr) {
		this.logMillisecondsStr = logMillisecondsStr;
	}

	public Long getLogMilliseconds() {
		return logMilliseconds;
	}

	public void setLogMilliseconds(Long logMilliseconds) {
		this.logMilliseconds = logMilliseconds;
	}

}