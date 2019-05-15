package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.TicketsDao;
import model.Ticket;

public class UpdateTicketAction extends ActionSupport{
	Ticket ticket;
	TicketsDao ticketsDao;
	
	

	public TicketsDao getTicketsDao() {
		return ticketsDao;
	}

	public void setTicketsDao(TicketsDao ticketsDao) {
		this.ticketsDao = ticketsDao;
	}

	public UpdateTicketAction(){}
	
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	
	public String execute(){
		if(ticketsDao.updateTicket(ticket)){
			return SUCCESS;
		}else{
			return INPUT;
		}
	}

}
