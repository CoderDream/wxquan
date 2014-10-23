package com.coderdream.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

public class DBUtil {

	/**
	 * @author help
	 * 
	 *         插入新的一条记录
	 * @return
	 */
	public static Connection getConnection() {
		Connection con = null;
		Properties prop = new Properties();// 属性集合对象
		InputStream fis;
		try {
			fis = DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			prop.load(fis);// 将属性文件流装载到Properties对象中

			String driver = prop.getProperty("driver");

			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			String url = prop.getProperty("url");// 使用从库的域名

			Map<String, String> envMap = System.getenv();

			String os = envMap.get("OS");
			// local
			if (null != os && "Windows_NT".equals(os.trim())) {
				username = prop.getProperty("local.username");
				password = prop.getProperty("local.password");
				url = prop.getProperty("local.url");
			}
			// SAE
			else {
				username = prop.getProperty("sae.username");
				password = prop.getProperty("sae.password");
				url = prop.getProperty("sae.url");
			}

			Class.forName(driver).newInstance();
			// con = DriverManager.getConnection(url + "?useServerPrepStmts=true", username, password);
			// con = DriverManager.getConnection(url + "?useUnicode=true&characterEncoding=utf8", username, password);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void testDB() {
		Statement st = null;
		ResultSet rs = null;

		Properties prop = new Properties();// 属性集合对象
		InputStream fis;
		try {
			fis = DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			prop.load(fis);// 将属性文件流装载到Properties对象中
		} catch (Exception e1) {
			e1.printStackTrace();
		}// 属性文件流

		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String url = prop.getProperty("url");// 使用从库的域名
		String driver = prop.getProperty("driver");
		Connection con = null;
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
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

	public static void close(Connection con) {
		try {
			if (null != con) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement ps) {
		try {
			if (null != ps) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (null != rs) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}