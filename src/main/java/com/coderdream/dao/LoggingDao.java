package com.coderdream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.coderdream.bean.Logging;
import com.coderdream.util.DBUtil;

public class LoggingDao {

	public static String TAG = "LoggingDao";

	private Logger logger = Logger.getLogger(LoggingDao.class);

	// add
	public int add(Logging logging) {
		query();

		logger.debug(TAG + "###0###");
		String sql = "INSERT INTO logging (log_date, log_level, location, message) VALUES (?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement pre = null;
		try {
			con = DBUtil.getConnection();
			logger.debug(TAG + con);
			pre = con.prepareStatement(sql);
			pre.setDate(1, logging.getLogDate());
			pre.setString(2, logging.getLogLevel());
			pre.setString(3, logging.getLocation());
			pre.setString(4, logging.getMessage());
			count = pre.executeUpdate();
			logger.debug(TAG + "count: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != pre && !pre.isClosed()) {
					pre.close();
				}
				if (null != con && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public void query() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			st = con.createStatement();
			String sql = "select * from logging";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// System.out.print("id=" + rs.getInt("id"));
				// System.out.print(", log_date=" + rs.getDate("log_date"));
				// System.out.print(", log_level=" + rs.getString("log_level"));
				// System.out.print(", location=" + rs.getString("location"));
				// System.out.print(", message=" + rs.getString("message"));
				// System.out.println("");

				logger.debug(TAG + "id=" + rs.getInt("id"));
				logger.debug(TAG + ", log_date=" + rs.getDate("log_date"));
				logger.debug(TAG + ", log_level=" + rs.getString("log_level"));
				logger.debug(TAG + ", location=" + rs.getString("location"));
				logger.debug(TAG + ", message=" + rs.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}

			try {
				st.close();
			} catch (Exception e) {
			}

			try {
				con.close();
			} catch (Exception e) {
			}
		}

	}
}
