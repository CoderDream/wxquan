package com.coderdream.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static String username = "oymzyxonwk";// SaeUserInfo.getAccessKey();
	public static String password = "j4j0w3mhw31i1jmw1zjxl1mjh3iw01y2wiw4j11m";// SaeUserInfo.getSecretKey();
	public static String urlW = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_wxquan";// 使用从库的域名
	public static String urlR = "jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_wxquan";// 使用从库的域名
	public static String driver = "com.mysql.jdbc.Driver";

	/**
	 * @author help
	 * 
	 *         插入新的一条记录
	 * @return
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(urlW, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	public static void testDB() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(urlW, username, password);
			st = con.createStatement();
			String sql = "select * from logging";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.print("id=" + rs.getInt("id"));
				System.out.print(", log_date=" + rs.getDate("log_date"));
				System.out.print(", log_level=" + rs.getString("log_level"));
				System.out.print(", location=" + rs.getString("location"));
				System.out.print(", message=" + rs.getString("message"));
				System.out.println("");
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