package action;

import com.opensymphony.xwork2.ActionSupport;

import dao.TicketsDao;
import model.Ticket;

public class QueryActionById extends ActionSupport{
	Ticket ticket;
	int id;
	TicketsDao ticketsDao;
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	public TicketsDao getTicketsDao() {
		return ticketsDao;
	}
	public void setTicketsDao(TicketsDao ticketsDao) {
		this.ticketsDao = ticketsDao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public QueryActionById(){};
	
	public String execute(){
		ticket=ticketsDao.queryTicketById(id);
		if(ticket!=null){
			return SUCCESS;
		}else{
			return INPUT;
		}
	}
}
