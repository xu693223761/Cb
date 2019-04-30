package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.TicketsDao;
import model.Ticket;

public class Buyticketaction extends ActionSupport {
		Ticket ticket;

		TicketsDao td=new TicketsDao();
		
		public Buyticketaction(){}
		public Ticket getTicket() {
			return ticket;
		}


		public void setTicket(Ticket ticket) {
			this.ticket = ticket;
		}


		public TicketsDao getTd() {
			return td;
		}


		public void setTd(TicketsDao td) {
			this.td = td;
		}


		

		public String execute(){
			if(null==ticket.getName() || "".equals(ticket.getName())){
	            this.addFieldError("ticket.name", "用户名不能为空!");
	            return INPUT;
	        }
	        if(null==ticket.getOriginating_City()|| "".equals(ticket.getOriginating_City())){
	            this.addFieldError("ticket.name", "始发城市不能为空!");
	            return INPUT;
	        }
	        if(null==ticket.getEnd_City() || "".equals(ticket.getEnd_City())){
	            this.addFieldError("ticket.password", "目的城市不能为空!");
	            return INPUT;
	        }if(null==ticket.getIdcard()|| "".equals(ticket.getIdcard())){
	            this.addFieldError("ticket.name", "身份证不能为空!");
	            return INPUT;
	        }
	       
			
			
			if(td.insertTicket(ticket)>0){
			System.out.println("购票成功");
			return SUCCESS;
			}else{
				return INPUT;
			}
			
			}
		}
		
