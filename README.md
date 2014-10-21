微信公众平台开发实战
================

V003.2014102102
这个版本包含以下功能：
-----------------------------------
*   1、通过数据库记录log，日志会写到logging表中；
*   2、新增下列文件；
		Logging.java
		LoggingDao.java
		DBUtil.java
		logging.sql
*   3、更新下列文件；
		CoreService.java

V002.2014102101
这个版本包含以下功能：
-----------------------------------
*   1、响应文本消息；
	输入文本，返回：您发送的是文本消息！
*   2、新增下列文件；
		BaseMessage.java
		TextMessage.java
		CoreSerivce.java
		MessageUtil.java
		log4j.properties
*   3、更新下列文件；
		CoreServlet.java
*   4、xstream使用1.4.7的高版本，否则SAE报错；
	详细原因：http://blog.csdn.net/lyq8479/article/details/38878543
*   5、部署到SAE时要删除3个jar：
	jsp-api-2.1.jar(scope:provide)
	servlet-api-2.4.jar(scope:provide)
	xml-apis-1.0.b2.jar(exclusions方式)

V001.20141020
-----------------------------------
这个版本包含以下功能：
*   1、微信公众平台接口可以通过验证：
	URL：http://wxquan.sinaapp.com/wechat(在web.xml中设置)
	Token：weixinToken(在SignUtil中设置)