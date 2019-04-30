<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>在线机票预订</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
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
		
  <!-- <div align="center" > -->
   	<s:form  method="post" action="buyticketAction">
   		<s:textfield name="ticket.name" label="姓名" />
   		<s:radio name="ticket.sex" list="%{#{'男':'男','女':'女'}}" label="性别"/> 
   		<s:textfield name="ticket.Originating_City" label="始发城市"/>
   		<s:textfield name="ticket.End_City"  label="目的城市"/>
   		<s:textfield name = "ticket.idcard" label="身份证"/>
   		<s:submit name="submit"  value="预定"/>  	
   </s:form>
  <!-- </div>	 --> 	
   		
  </body>
</html>
