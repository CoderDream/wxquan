<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Welcome,home</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body onload="disptime();">
	<FORM NAME="myform">
		<DIV align="center">

			<SCRIPT language="JavaScript">
			<!--
				function disptime() {

					var time = new Date(); //获得当前时间
					var year = time.getYear();//获得年、月、日
					var month = time.getMonth();
					var day = time.getDay();
					var hour = time.getHours(); //获得小时、分钟、秒 
					var minute = time.getMinutes();
					var second = time.getSeconds();
					var apm = "AM"; //默认显示上午: AM 

					if (hour > 12) //按12小时制显示 
					{
						hour = hour - 12;
						apm = "PM";
					}
					if (minute < 10) //如果分钟只有1位，补0显示 
						minute = "0" + minute;
					if (second < 10) //如果秒数只有1位，补0显示 
						second = "0" + second;
					/*设置文本框的内容为当前时间*/
					document.myform.myclock.value = year + "年" + month + "月"
							+ day + "日   " + hour + ":" + minute + ":" + second
							+ " " + apm;
					/*设置定时器每隔1秒（1000毫秒），调用函数disptime()执行，刷新时钟显示*/
					var myTime = setTimeout("disptime()", 1000);

				}
			//-->
			</SCRIPT>
			动态显示时间<INPUT name="myclock" type="text" value="" size="25"> <br>
			静态显示时间
			<%=new java.util.Date()%>
		</DIV>
	</FORM>
</body>
</html>