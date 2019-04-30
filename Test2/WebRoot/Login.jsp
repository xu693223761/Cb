<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
  <title>登录注册</title>
  </head>
  
  <body>
   <script src="http://code.jquery.com/jquery.js"></script>
     <script src="js/bootstrap.min.js"></script>
     <div class="page-header" >
 		 <h1 style="font-family:Simsun;">在线机票预订系统 <small>携程</small></h1>
	</div>
		     <div class="navbar" >
		  <div class="navbar-inner">
		    <a class="brand" href="#">主页</a>
		    <ul class="nav">
		      <li><a href="Mdpwd.jsp">修改密码</a></li>
		      <li><a href="Buyticket.jsp">机票预订</a></li>
		      <li><a href="userlist.jsp">预订信息维护</a></li>
		      <li><a href="Login.jsp">退出系统</a></li>
		    </ul>
		  </div>
		</div>
		
  <s:property value="errorMessage"/>
    <s:form method="post" action="loginAction">
	    <s:textfield name="user.name" label="用户名"/>

	    <s:password name="user.password" label="密码"/>
	    <s:submit name="submit" value="登录" />
	</s:form>
	
	<hr align="left" width="60%" color="RED"/>
	
	<s:form method="post" action="registAction">
	    <s:textfield name="user.name" label="用户名"/>
	    <s:password name="user.password" label="密码"/>
	    <s:submit name="submit" value="注册" />
	    
	</s:form>
    

  </body>
</html>
