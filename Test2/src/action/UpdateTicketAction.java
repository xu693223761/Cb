package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.TicketsDao;
import model.Ticket;

public class UpdateTicketAction extends ActionSupport{
	Ticket ticket;
	TicketsDao td=new TicketsDao();
	
	public UpdateTicketAction(){}
	
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	
	public String execute(){
		if(td.updateTicket(ticket)){
			return SUCCESS;
		}else{
			return INPUT;
		}
	}

}
