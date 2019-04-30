package action;

import com.opensymphony.xwork2.ActionSupport;

import dao.TicketsDao;

public class DeleteTicketAction extends ActionSupport {
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	TicketsDao td = new TicketsDao();
	public DeleteTicketAction(){}
	
	public String execute(){
		if(td.deleteTicketById(id)){
			return SUCCESS;
		}else{
			return INPUT;
		}
		
		
		
	}
}
