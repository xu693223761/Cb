package action;

import com.opensymphony.xwork2.ActionSupport;

import dao.TicketsDao;

public class DeleteTicketAction extends ActionSupport {
	int id;
	TicketsDao ticketsDao ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public TicketsDao getTicketsDao() {
		return ticketsDao;
	}
	public void setTicketsDao(TicketsDao ticketsDao) {
		this.ticketsDao = ticketsDao;
	}
	public DeleteTicketAction(){}
	
	public String execute(){
		if(ticketsDao.deleteTicketById(id)){
			return SUCCESS;
		}else{
			return INPUT;
		}
		
		
		
	}
}
