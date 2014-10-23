package com.coderdream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.coderdream.bean.Logging;
import com.coderdream.util.DBUtil;

public class LoggingDao {

	private String location;

	public static String TAG = "LoggingDao";

	private Logger logger = Logger.getLogger(LoggingDao.class);

	public LoggingDao() {
	}

	public LoggingDao(String location) {
		this.location = location;
	}

	public int addLogging(Logging logging) {
		logger.debug(TAG + "###0###");

		String sql = "INSERT INTO logging (log_date, log_level, location, message) VALUES (?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			logger.debug(TAG + " ###2### Connection: " + con);
			ps = con.prepareStatement(sql);
			logger.debug(TAG + " ###3### PreparedStatement: " + pre);
			ps.setString(1, logging.getLogDate());
			ps.setString(2, logging.getLogLevel());
			ps.setString(3, logging.getLocation());
			ps.setString(4, logging.getMessage());
			count = ps.executeUpdate();
			logger.debug(TAG + "###4### count: " + count);
		} catch (Exception e) {
			logger.debug(TAG + "###5### Exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return count;
	}

	public int debug(String message) {
		if (!logger.isDebugEnabled()) {
			return 0;
		}

		logger.debug(TAG + "###0###");
		String sql = "INSERT INTO logging (log_date, log_level, location, message) VALUES (?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement pre = null;
		try {
			con = DBUtil.getConnection();
			logger.debug(TAG + " ###2### Connection: " + con);
			pre = con.prepareStatement(sql);
			logger.debug(TAG + " ###3### PreparedStatement: " + pre);
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat f_timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			String logTimestampStr = f_timestamp.format(date);
			pre.setString(1, logTimestampStr);
			pre.setString(2, "DEBUG");
			pre.setString(3, location);
			pre.setString(4, message);
			count = pre.executeUpdate();
			logger.debug(TAG + "###4### count: " + count);
		} catch (Exception e) {
			logger.debug(TAG + "###5### Exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return count;
	}

	public static int debug(String location, String message) {
		if (!Logger.getLogger(LoggingDao.class).isDebugEnabled()) {
			return 0;
		}
		String sql = "INSERT INTO logging (log_date, log_level, location, message) VALUES (?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement pre = null;
		try {
			con = DBUtil.getConnection();
			pre = con.prepareStatement(sql);
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat f_timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			String logTimestampStr = f_timestamp.format(date);
			pre.setString(1, logTimestampStr);
			pre.setString(2, "DEBUG");
			pre.setString(3, location);
			pre.setString(4, message);
			count = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return count;
	}

	public int error(String message) {
		logger.debug(TAG + "###0###");
		String sql = "INSERT INTO logging (log_date, log_level, location, message) VALUES (?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement pre = null;
		try {
			con = DBUtil.getConnection();
			logger.debug(TAG + con);
			pre = con.prepareStatement(sql);
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat f_timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			String logTimestampStr = f_timestamp.format(date);
			pre.setString(1, logTimestampStr);
			pre.setString(2, "ERROR");
			pre.setString(3, location);
			pre.setString(4, message);
			count = pre.executeUpdate();
			logger.debug(TAG + "count: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return count;
	}

	public static int error(String location, String message) {
		String sql = "INSERT INTO logging (log_date, log_level, location, message) VALUES (?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement pre = null;
		try {
			con = DBUtil.getConnection();
			pre = con.prepareStatement(sql);
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat f_timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			String logTimestampStr = f_timestamp.format(date);
			pre.setString(1, logTimestampStr);
			pre.setString(2, "ERROR");
			pre.setString(3, location);
			pre.setString(4, message);
			count = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return count;
	}

	PreparedStatement pre;
	ResultSet rs;

	/**
	 * @author help
	 * 
	 *         显示所有记录
	 * @return
	 */
	public List<Logging> findLoggings() {

		String sql = "select * from logging order by id";
		List<Logging> list = new ArrayList<Logging>();

		// 获取prepareStatement对象
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();

			while (rs.next()) {
				Logging logging = new Logging();
				logging.setId(rs.getInt("id"));
				logging.setLogDate(rs.getString("log_date"));
				logging.setLogLevel(rs.getString("log_level"));
				logging.setLocation(rs.getString("location"));
				logging.setMessage(rs.getString("message"));
				list.add(logging);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return list;
	}

	/**
	 * @author help
	 * 
	 *         显示所有记录
	 * @return
	 */
	public Logging findLogging(int id) {

		String sql = "select * from logging where id=?";
		List<Logging> list = new ArrayList<Logging>();

		// 获取prepareStatement对象
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			pre = con.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();

			while (rs.next()) {
				Logging logging = new Logging();
				logging.setId(rs.getInt("id"));
				logging.setLogDate(rs.getString("log_date"));
				logging.setLogLevel(rs.getString("log_level"));
				logging.setLocation(rs.getString("location"));
				logging.setMessage(rs.getString("message"));
				list.add(logging);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}

		if (null != list && 0 < list.size()) {
			return list.get(0);
		}

		return null;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}