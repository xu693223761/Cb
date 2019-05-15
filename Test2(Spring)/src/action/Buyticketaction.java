package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.TicketsDao;
import model.Ticket;

public class Buyticketaction extends ActionSupport {
		Ticket ticket;

		TicketsDao ticketsDao;
		
		public Buyticketaction(){}
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
		public String execute(){
			if(null==ticket.getName() || "".equals(ticket.getName())){
	            this.addFieldError("ticket.name", "�û�������Ϊ��!");
	            return INPUT;
	        }
	        if(null==ticket.getOriginating_City()|| "".equals(ticket.getOriginating_City())){
	            this.addFieldError("ticket.name", "ʼ�����в���Ϊ��!");
	            return INPUT;
	        }
	        if(null==ticket.getEnd_City() || "".equals(ticket.getEnd_City())){
	            this.addFieldError("ticket.password", "Ŀ�ĳ��в���Ϊ��!");
	            return INPUT;
	        }if(null==ticket.getIdcard()|| "".equals(ticket.getIdcard())){
	            this.addFieldError("ticket.name", "����֤����Ϊ��!");
	            return INPUT;
	        }
	       
			
			
			if(ticketsDao.insertTicket(ticket)>0){
			System.out.println("��Ʊ�ɹ�");
			return SUCCESS;
			}else{
				return INPUT;
			}
			
			}
		}
		