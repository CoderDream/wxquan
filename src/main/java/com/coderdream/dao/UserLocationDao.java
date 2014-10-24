package com.coderdream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.coderdream.model.UserLocation;
import com.coderdream.util.DBUtil;

/**
 * 
 */
public class UserLocationDao {

	/**
	 * 保存用户地理位置
	 * 
	 * @param request
	 *          请求对象
	 * @param openId
	 *          用户的OpenID
	 * @param lng
	 *          用户发送的经度
	 * @param lat
	 *          用户发送的纬度
	 * @param bd09_lng
	 *          经过百度坐标转换后的经度
	 * @param bd09_lat
	 *          经过百度坐标转换后的纬度
	 */
	public int saveUserLocation(String openId, String lng, String lat, String bd09_lng, String bd09_lat) {
		String sql = "insert into user_location(open_id, lng, lat, bd09_lng, bd09_lat) values (?, ?, ?, ?, ?)";
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, openId);
			ps.setString(2, lng);
			ps.setString(3, lat);
			ps.setString(4, bd09_lng);
			ps.setString(5, bd09_lat);
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return count;
	}

	/**
	 * 获取用户最后一次发送的地理位置
	 * 
	 * @param request
	 *          请求对象
	 * @param openId
	 *          用户的OpenID
	 * @return UserLocation
	 */
	public UserLocation getLastLocation(String openId) {
		UserLocation userLocation = null;
		String sql = "select open_id, lng, lat, bd09_lng, bd09_lat from user_location where open_id=? order by id desc limit 0,1";
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pre = con.prepareStatement(sql);
			pre.setString(1, openId);
			rs = pre.executeQuery();
			if (rs.next()) {
				userLocation = new UserLocation();
				userLocation.setOpenId(rs.getString("open_id"));
				userLocation.setLng(rs.getString("lng"));
				userLocation.setLat(rs.getString("lat"));
				userLocation.setBd09Lng(rs.getString("bd09_lng"));
				userLocation.setBd09Lat(rs.getString("bd09_lat"));
			}
			// 释放资源
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return userLocation;
	}
}