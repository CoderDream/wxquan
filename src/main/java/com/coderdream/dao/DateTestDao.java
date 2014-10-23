package com.coderdream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.coderdream.bean.DateTest;
import com.coderdream.util.DBUtil;

public class DateTestDao {

	private String location;

	public static String TAG = "DateTestDao";

	private Logger logger = Logger.getLogger(DateTestDao.class);

	public DateTestDao() {
	}

	public DateTestDao(String location) {
		this.location = location;
	}

	public int addDateTest(DateTest dateTest) {
		logger.debug(TAG + "###0###");
		String sql = "INSERT INTO date_test (log_date, log_time, log_microsecond, log_datetime, log_timestamp, log_milliseconds_str, log_milliseconds) VALUES (?,?,?,?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			logger.debug(TAG + con);
			ps = con.prepareStatement(sql);
			ps.setDate(1, dateTest.getLogDate());
			ps.setTime(2, dateTest.getLogTime());
			ps.setString(3, dateTest.getLogMicrosecond());
			ps.setTimestamp(4, dateTest.getLogDatetime());
			ps.setTimestamp(5, dateTest.getLogTimestamp());
			ps.setString(6, dateTest.getLogMillisecondsStr());
			ps.setLong(7, dateTest.getLogMilliseconds());
			count = ps.executeUpdate();
			logger.debug(TAG + "count: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return count;
	}

	// public int debug(String message) {
	// if (!logger.isDebugEnabled()) {
	// return 0;
	// }
	//
	// logger.debug(TAG + "###0###");
	// String sql = "INSERT INTO date_test (log_date, log_level, location, message) VALUES (?,?,?,?)";
	//
	// int count = 0;
	// Connection con = null;
	// PreparedStatement pre = null;
	// try {
	// con = DBUtil.getConnection();
	// logger.debug(TAG + " ###2### Connection: " + con);
	// pre = con.prepareStatement(sql);
	// logger.debug(TAG + " ###3### PreparedStatement: " + pre);
	// pre.setTimestamp(1, new Timestamp(Calendar.getInstance().getTime().getTime()));
	// pre.setString(2, "DEBUG");
	// pre.setString(3, location);
	// pre.setString(4, message);
	// count = pre.executeUpdate();
	// logger.debug(TAG + "###4### count: " + count);
	// } catch (Exception e) {
	// logger.debug(TAG + "###5### Exception: " + e.getMessage());
	// e.printStackTrace();
	// } finally {
	// DBUtil.close(pre);
	// DBUtil.close(con);
	// }
	// return count;
	// }
	//
	// public static int debug(String location, String message) {
	// if (!Logger.getLogger(DateTestDao.class).isDebugEnabled()) {
	// return 0;
	// }
	// String sql = "INSERT INTO date_test (log_date, log_level, location, message) VALUES (?,?,?,?)";
	//
	// int count = 0;
	// Connection con = null;
	// PreparedStatement ps = null;
	// try {
	// con = DBUtil.getConnection();
	// ps = con.prepareStatement(sql);
	// ps.setTimestamp(1, new Timestamp(Calendar.getInstance().getTime().getTime()));
	// ps.setString(2, "DEBUG");
	// ps.setString(3, location);
	// ps.setString(4, message);
	// count = ps.executeUpdate();
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// DBUtil.close(ps);
	// DBUtil.close(con);
	// }
	// return count;
	// }
	//
	// public int error(String message) {
	// logger.debug(TAG + "###0###");
	// String sql = "INSERT INTO date_test (log_date, log_level, location, message) VALUES (?,?,?,?)";
	//
	// int count = 0;
	// Connection con = null;
	// PreparedStatement pre = null;
	// try {
	// con = DBUtil.getConnection();
	// logger.debug(TAG + con);
	// pre = con.prepareStatement(sql);
	// pre.setTimestamp(1, new Timestamp(Calendar.getInstance().getTime().getTime()));
	// pre.setString(2, "ERROR");
	// pre.setString(3, location);
	// pre.setString(4, message);
	// count = pre.executeUpdate();
	// logger.debug(TAG + "count: " + count);
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// DBUtil.close(pre);
	// DBUtil.close(con);
	// }
	// return count;
	// }
	//
	// public static int error(String location, String message) {
	// String sql = "INSERT INTO date_test (log_date, log_level, location, message) VALUES (?,?,?,?)";
	//
	// int count = 0;
	// Connection con = null;
	// PreparedStatement ps = null;
	// try {
	// con = DBUtil.getConnection();
	// ps = con.prepareStatement(sql);
	// ps.setTimestamp(1, new Timestamp(Calendar.getInstance().getTime().getTime()));
	// ps.setString(2, "ERROR");
	// ps.setString(3, location);
	// ps.setString(4, message);
	// count = ps.executeUpdate();
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// DBUtil.close(ps);
	// DBUtil.close(con);
	// }
	// return count;
	// }
	//
	// PreparedStatement pre;
	// ResultSet rs;
	//
	// /**
	// * @author help
	// *
	// * 显示所有记录
	// * @return
	// */
	// public List<DateTest> query() {
	//
	// String sql = "select * from date_test order by id";
	// List<DateTest> list = new ArrayList<DateTest>();
	//
	// // 获取prepareStatement对象
	// Connection con = null;
	// try {
	// con = DBUtil.getConnection();
	// pre = con.prepareStatement(sql);
	// rs = pre.executeQuery();
	//
	// while (rs.next()) {
	// DateTest dateTest = new DateTest();
	// dateTest.setId(rs.getInt("id"));
	// dateTest.setLogDate(rs.getTimestamp("log_date"));
	// dateTest.setLogLevel(rs.getString("log_level"));
	// dateTest.setLocation(rs.getString("location"));
	// dateTest.setMessage(rs.getString("message"));
	// list.add(dateTest);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// DBUtil.close(pre);
	// DBUtil.close(con);
	// }
	// return list;
	// }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public static void main(String[] args) {
		DateTestDao dateTestDao = new DateTestDao();

		// SimpleDateFormat f_datetime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date date = Calendar.getInstance().getTime();
		// String logDateStr = f_datetime.format(date);
		// Date logDate = Date.
		// java.sql.Date logDatetime2 =
		// java.sql.Date logDate = new java.sql.Date(date.getTime());

		// SimpleDateFormat f_date = new SimpleDateFormat("yyyy-MM-dd");
		// String logDateStr = f_date.format(date);
		// Date logDate = Date.
		// java.sql.Date logDatetime2 =
		java.sql.Date logDate = new java.sql.Date(date.getTime());

		SimpleDateFormat f_sqlTime = new SimpleDateFormat("hh:mm:ss");
		String logTimeStr = f_sqlTime.format(date);
		Time logTime = Time.valueOf(logTimeStr);

		SimpleDateFormat f_timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		String logTimestampStr = f_timestamp.format(date);
		String logMicrosecondStr = logTimestampStr.substring(20, logTimestampStr.length());
		System.out.println("logTimestampStr: " + logTimestampStr + ", logMicrosecondStr: " + logMicrosecondStr);

		Timestamp logTimestamp = Timestamp.valueOf(logTimestampStr);

		String logMillisecondsStr = logTimestampStr;
		Long logMilliseconds = date.getTime();
		System.out.println("logMillisecondsStr: " + logMillisecondsStr + ", logMilliseconds: " + logMilliseconds);

		DateTest dateTest = new DateTest(logDate, logTime, logMicrosecondStr, logTimestamp, logTimestamp,
						logMillisecondsStr, logMilliseconds);
		dateTestDao.addDateTest(dateTest);
	}

}