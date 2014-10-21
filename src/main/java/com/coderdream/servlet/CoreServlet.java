package com.coderdream.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.coderdream.service.CoreService;
import com.coderdream.util.SignUtil;

/**
 * 请求处理的核心类
 * 
 */
public class CoreServlet extends HttpServlet {

	public static String TAG = "CoreServlet";

	private Logger logger = Logger.getLogger(CoreServlet.class);

	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 请求校验（确认请求来自微信服务器）
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 请求校验与处理
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date utilDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = sdf.format(utilDate);
		logger.debug(TAG + " ###### log4j debug" + str);
		logger.info(TAG + " ###### log4j info" + str);
		logger.trace(TAG + " ######  log4j trace" + str);
		logger.warn(TAG + " ###### log4j warn" + str);
		logger.fatal(TAG + " ###### log4j fatal" + str);
		logger.error(TAG + " ###### log4j error" + str);

		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 接收参数微信加密签名、 时间戳、随机数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		PrintWriter out = response.getWriter();
		// 请求校验
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			// 从request中取得输入流
			InputStream inputStream = request.getInputStream();
			CoreService coreService = new CoreService();
			// 调用核心服务类接收处理请求
			String respXml = coreService.processRequest(inputStream);
			out.print(respXml);
		}
		out.close();
		out = null;
	}
}