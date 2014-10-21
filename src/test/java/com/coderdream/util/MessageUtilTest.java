package com.coderdream.util;

import org.junit.Before;
import org.junit.Test;

import com.coderdream.model.TextMessage;

public class MessageUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * <pre>
	 *  <xml>
	 *  <ToUserName><![CDATA[toUser]]></ToUserName>
	 *  <FromUserName><![CDATA[fromUser]]></FromUserName> 
	 *  <CreateTime>1348831860</CreateTime>
	 *  <MsgType><![CDATA[text]]></MsgType>
	 *  <Content><![CDATA[this is a test]]></Content>
	 *  <MsgId>1234567890123456</MsgId>
	 *  </xml>
	 * </pre>
	 */
	@Test
	public void testMessageToXml() {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName("toUser");
		textMessage.setFromUserName("fromUser");
		textMessage.setCreateTime(1348831860l);
		textMessage.setMsgType(MessageUtil.MESSAGE_TYPE_TEXT);
		textMessage.setContent("this is a test");
		textMessage.setMsgId(1234567890123456l);

		String xml = MessageUtil.messageToXml(textMessage);
		System.out.println(xml);
		// fail("Not yet implemented");
	}

}
