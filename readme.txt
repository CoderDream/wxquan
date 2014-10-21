

V002.20141021
这个版本包含以下功能：
1、响应文本消息；
	输入文本，返回：您发送的是文本消息！
2、xstream使用1.4.7的高版本，否则SAE报错；
	详细原因：http://blog.csdn.net/lyq8479/article/details/38878543
3、部署到SAE时要删除3个jar：
	jsp-api-2.1.jar(scope:provide)
	servlet-api-2.4.jar(scope:provide)
	xml-apis-1.0.b2.jar(exclusions方式)

去掉war包中的xml-apis-1.0.b2.jar

V001.20141020
这个版本包含以下功能：
1、微信公众平台接口可以通过验证：
	URL：http://wxquan.sinaapp.com/wechat(在web.xml中设置)
	Token：weixinToken(在SignUtil中设置)