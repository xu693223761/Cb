//根据id删除用户
function deleteTicket(userId){
	if(confirm("你确定删除该用户吗?")){
		location.href="deleteTicketAction?id="+userId;
	}
}

//修改，现根据id查询用户详细信息，以供修改
function queryTicketDetails(userId){
	//调用queryActionById
	location.href="queryActionById?id="+userId;
}