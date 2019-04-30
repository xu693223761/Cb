<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <title>订票信息</title>
    <script type="text/javascript" src="js/js.js" charset="UTF-8">
    </script>
	

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
		
		
		
	<s:form method="post" action="queryAction">
  		<s:submit value="显示所有信息" />
  	</s:form>	
  
 	<table border="1" width="60%"  border="0" cellpadding="3" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px;margin-top:8px;">
		<tr>
			<th>姓名</th>
			<th>性别</th>
			<th>始发城市</th>
			<th>目的城市</th>
			<th>身份证</th>
		</tr>
		<s:iterator value="tickets" status="st" var="ticket">
		<tr align="center" >
			<td><s:property value="#ticket.name"/></td>
			<td><s:property value="#ticket.sex"/></td>
			<td><s:property value="#ticket.Originating_City"/></td>
			<td><s:property value="#ticket.End_City"/></td>
			<td><s:property value="#ticket.idcard"/></td>
			<td>
				<a href="javascript:queryTicketDetails('<s:property value="#ticket.id"/>')">修改</a>
				<a href="javascript:deleteTicket('<s:property value="id"/>')">删除</a>
			</td>
		</tr>
		</s:iterator>
	</table>
	[<a href="queryAction?pageNo=1">首页</a>]
			<c:choose>
				<c:when test="${currentPage>1}">
					[<a href="queryAction?pageNo=${currentPage-1}">上一页</a>]
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${currentPage<totalPage}">
					[<a href="queryAction?pageNo=${currentPage+1}">下一页</a>]
				</c:when>
			</c:choose>
	[<a href="queryAction?pageNo=${totalPage}">尾页</a>]
  
  
     <br>
  </body>
</html>
