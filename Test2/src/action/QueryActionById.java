package action;

import com.opensymphony.xwork2.ActionSupport;

import dao.TicketsDao;
import model.Ticket;

public class QueryActionById extends ActionSupport{
	Ticket ticket;
	int id;
	TicketsDao td = new TicketsDao();
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public QueryActionById(){};
	
	public String execute(){
		ticket=td.queryTicketById(id);
		if(ticket!=null){
			return SUCCESS;
		}else{
			return INPUT;
		}
	}
}
