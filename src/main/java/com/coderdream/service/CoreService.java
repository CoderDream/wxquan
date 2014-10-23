package com.coderdream.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.coderdream.bean.Logging;
import com.coderdream.dao.LoggingDao;
import com.coderdream.model.Article;
import com.coderdream.model.NewsMessage;
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
		SimpleDateFormat f_timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Logging logging = new Logging(f_timestamp.format(Calendar.getInstance().getTime()), "DEBUG", TAG,
						"#1# processRequest");
		LoggingDao loggingDao = new LoggingDao();
		loggingDao.addLogging(logging);
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "未知的消息类型！";
		try {
			MessageUtil messageUtil = new MessageUtil();
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
			logging = new Logging(f_timestamp.format(Calendar.getInstance().getTime()), "DEBUG", TAG, logStr);
			loggingDao.addLogging(logging);

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.MESSAGE_TYPE_TEXT);
			logStr = "#3# textMessage: " + textMessage.toString();
			logger.debug(TAG + logStr);
			logging = new Logging(f_timestamp.format(Calendar.getInstance().getTime()), "DEBUG", TAG, logStr);
			loggingDao.addLogging(logging);

			// 文本消息
			if (msgType.equals(MessageUtil.MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息！";

				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");

				// 创建图文消息
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.MESSAGE_TYPE_NEWS);
				// newsMessage.setFuncFlag(0);

				List<Article> articleList = new ArrayList<Article>();
				// 单图文消息
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("微信公众帐号开发教程Java版");
					article.setDescription("柳峰，80后，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
					article.setPicUrl("http://wxquan.sinaapp.com/meal.jpg");
					article.setUrl("http://blog.csdn.net/lyq8479?toUserName=" + fromUserName + "&toUserName="
									+ toUserName);
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					return messageUtil.messageToXml(newsMessage);
				}
				// 单图文消息---不含图片
				else if ("2".equals(content)) {
					Article article = new Article();
					article.setTitle("微信公众帐号开发教程Java版");
					// 图文消息中可以使用QQ表情、符号表情
					article.setDescription("柳峰，80后，"
					// + emoji(0x1F6B9)
									+ "，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列连载教程，也希望借此机会认识更多同行！\n\n目前已推出教程共12篇，包括接口配置、消息封装、框架搭建、QQ表情发送、符号表情发送等。\n\n后期还计划推出一些实用功能的开发讲解，例如：天气预报、周边搜索、聊天功能等。");
					// 将图片置为空
					article.setPicUrl("");
					article.setUrl("http://blog.csdn.net/lyq8479?toUserName=" + fromUserName + "&toUserName="
									+ toUserName);
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					return messageUtil.messageToXml(newsMessage);
				}
				// 多图文消息
				else if ("3".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("微信公众帐号开发教程\n引言");
					article1.setDescription("");
					article1.setPicUrl("http://wxquan.sinaapp.com/meal.jpg");
					article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622?toUserName=" + fromUserName
									+ "&toUserName=" + toUserName);

					Article article2 = new Article();
					article2.setTitle("第2篇\n微信公众帐号的类型");
					article2.setDescription("");
					article2.setPicUrl("http://wxquan.sinaapp.com/meal.jpg");
					article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577?toUserName=" + fromUserName
									+ "&toUserName=" + toUserName);

					Article article3 = new Article();
					article3.setTitle("关注页面");
					article3.setDescription("关注页面");
					article3.setPicUrl("http://wxquan.sinaapp.com/meal.jpg");
					article3.setUrl("http://wxquan.sinaapp.com/follow.jsp?toUserName=" + fromUserName + "&toUserName="
									+ toUserName);

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					return messageUtil.messageToXml(newsMessage);
				}
				// 多图文消息---首条消息不含图片
				else if ("4".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("微信公众帐号开发教程Java版");
					article1.setDescription("");
					// 将图片置为空
					article1.setPicUrl("");
					article1.setUrl("http://blog.csdn.net/lyq8479");

					Article article2 = new Article();
					article2.setTitle("第4篇\n消息及消息处理工具的封装");
					article2.setDescription("");
					article2.setPicUrl("http://wxquan.sinaapp.com/meal.jpg");
					article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088?toUserName=" + fromUserName
									+ "&toUserName=" + toUserName);

					Article article3 = new Article();
					article3.setTitle("第5篇\n各种消息的接收与响应");
					article3.setDescription("");
					article3.setPicUrl("http://wxquan.sinaapp.com/meal.jpg");
					article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173?toUserName=" + fromUserName
									+ "&toUserName=" + toUserName);

					Article article4 = new Article();
					article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");
					article4.setDescription("");
					article4.setPicUrl("http://wxquan.sinaapp.com/meal.jpg");
					article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824?toUserName=" + fromUserName
									+ "&toUserName=" + toUserName);

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					articleList.add(article4);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					return messageUtil.messageToXml(newsMessage);
				}
				// 多图文消息---最后一条消息不含图片
				else if ("5".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("第7篇\n文本消息中换行符的使用");
					article1.setDescription("");
					article1.setPicUrl("http://wxquan.sinaapp.com/meal.jpg");
					article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467?toUserName=" + fromUserName
									+ "&toUserName=" + toUserName);

					Article article2 = new Article();
					article2.setTitle("第8篇\n文本消息中使用网页超链接");
					article2.setDescription("");
					article2.setPicUrl("http://wxquan.sinaapp.com/meal.jpg");
					article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455?toUserName=" + fromUserName
									+ "&toUserName=" + toUserName);

					Article article3 = new Article();
					article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");
					article3.setDescription("");
					// 将图片置为空
					article3.setPicUrl("");
					article3.setUrl("http://blog.csdn.net/lyq8479");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					//respContent = messageUtil.messageToXml(newsMessage);
					return messageUtil.messageToXml(newsMessage);
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息！";
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫描带参数二维码事件
				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				}
				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 处理菜单点击事件
				}
			}

			logStr = "#4# respContent: " + respContent;
			logger.debug(TAG + logStr);
			logging = new Logging(f_timestamp.format(Calendar.getInstance().getTime()), "DEBUG", TAG, logStr);
			loggingDao.addLogging(logging);

			// 设置文本消息的内容
			textMessage.setContent(respContent);
			// 将文本消息对象转换成xml
			respXml = MessageUtil.messageToXml(textMessage);
			logStr = "#5# respXml: " + respXml;
			logger.debug(TAG + logStr);
			logging = new Logging(f_timestamp.format(Calendar.getInstance().getTime()), "DEBUG", TAG, logStr);
			loggingDao.addLogging(logging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}