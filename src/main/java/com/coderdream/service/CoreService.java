package com.coderdream.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.coderdream.bean.Logging;
import com.coderdream.dao.LoggingDao;
import com.coderdream.model.TextMessage;
import com.coderdream.util.MessageUtil;

/**
 * 核心服务类
 */
public class CoreService {

	public static String TAG = "CoreService";

	private Logger logger = Logger.getLogger(CoreService.class);

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public String processRequest(InputStream inputStream) {
		logger.debug(TAG + " #1# processRequest");
		//Timestamp sDate = new Timestamp(Calendar.getInstance().getTime().getTime());
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat f_timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		String logTimestampStr = f_timestamp.format(date);
		Logging logging = new Logging(logTimestampStr, "DEBUG", TAG, "#1# processRequest");
		LoggingDao loggingDao = new LoggingDao();
		loggingDao.addLogging(logging);
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "未知的消息类型！";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(inputStream);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			String logStr = "#2# fromUserName: " + fromUserName + ", toUserName: " + toUserName + ", msgType: "
							+ msgType;
			logger.debug(TAG + logStr);
			logging = new Logging(logTimestampStr, "DEBUG", TAG, logStr);
			loggingDao.addLogging(logging);

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.MESSAGE_TYPE_TEXT);
			logStr = "#3# textMessage: " + textMessage.toString();
			logger.debug(TAG + logStr);
			logging = new Logging(logTimestampStr, "DEBUG", TAG, logStr);
			loggingDao.addLogging(logging);

			// 文本消息
			if (msgType.equals(MessageUtil.MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息！";
			}
			logStr = "#4# respContent: " + respContent;
			logger.debug(TAG + logStr);
			logging = new Logging(logTimestampStr, "DEBUG", TAG, logStr);
			loggingDao.addLogging(logging);

			// 设置文本消息的内容
			textMessage.setContent(respContent);
			// 将文本消息对象转换成xml
			respXml = MessageUtil.messageToXml(textMessage);
			logStr = "#5# respXml: " + respXml;
			logger.debug(TAG + logStr);
			logging = new Logging(logTimestampStr, "DEBUG", TAG, logStr);
			loggingDao.addLogging(logging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}