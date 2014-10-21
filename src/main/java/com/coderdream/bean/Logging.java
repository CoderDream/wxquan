package com.coderdream.bean;

import java.sql.Date;

public class Logging {

	// `id` int(11) NOT NULL AUTO_INCREMENT,
	private int id;
	// `log_date` datetime DEFAULT NULL,
	private Date logDate;
	// `log_level` varchar(64) DEFAULT NULL,
	private String logLevel;
	// `location` varchar(256) DEFAULT NULL,
	private String location;
	// `message` varchar(1024) DEFAULT NULL,
	private String message;

	public Logging() {

	}

	public Logging(int id, Date logDate, String logLevel, String location, String message) {
		this.id = id;
		this.logDate = logDate;
		this.logLevel = logLevel;
		this.location = location;
		this.message = message;
	}

	public Logging(Date logDate, String logLevel, String location, String message) {
		this.logDate = logDate;
		this.logLevel = logLevel;
		this.location = location;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}