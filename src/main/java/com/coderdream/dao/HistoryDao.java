package com.coderdream.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderdream.bean.History;
import com.coderdream.util.DBUtil;

public class HistoryDao {

	private static Logger logger = LoggerFactory.getLogger(HistoryDao.class);

	public static List<History> getHistoryList(String dayStr) {
		List<History> list = new ArrayList<History>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 检索数据
		try {
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();
			int number = stmt.executeUpdate("prepare mystmt from 'select day,event from history where day = ?'");
			if (null == dayStr || "".equals(dayStr.trim())) {
				DateFormat df = new SimpleDateFormat("MMdd");
				dayStr = df.format(Calendar.getInstance().getTime());
			}

			stmt.execute("set @day='" + dayStr + "'");
			rs = stmt.executeQuery("execute mystmt using @day");
			System.out.println(number);

			ResultSetMetaData rsmd = rs.getMetaData(); // 表的字段属性变量
			// 按字段属性输出表的数据名
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				logger.info(rsmd.getColumnName(i) + "\t");
			}
			logger.info("\n--------------------------------------\n");
			History history = null;
			while (rs.next()) {
				history = new History();
				String day = rs.getString("day");
				String event = rs.getString("event");
				history.setDay(day);
				history.setEvent(event);
				logger.info("%-8d%-8d%-12s\n", day, event);
				list.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		List<History> list = getHistoryList("0220");
		for (Iterator<History> iterator = list.iterator(); iterator.hasNext();) {
			History history = iterator.next();
			System.out.println(history.getDay() + ":" + history.getEvent());
		}

		List<History> list2 = getHistoryList("");
		for (Iterator<History> iterator = list2.iterator(); iterator.hasNext();) {
			History history = iterator.next();
			System.out.println(history.getDay() + ":" + history.getEvent());
		}
	}
}