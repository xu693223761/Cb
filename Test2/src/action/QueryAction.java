package action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import dao.TicketsDao;
import dao.UsersDao;
import model.Ticket;

public class QueryAction extends ActionSupport{
	List<Ticket> tickets;//�־û�����
	TicketsDao tk = new TicketsDao();
	private Ticket ticket;
	private int id; //������ʾ���ݵ�����
	private final int pageSize=5; //ÿҳ��ʾ��¼�ĸ���
	private int pageNo=1; //������,�ӵ�1ҳ��ʼ��ʾ
	private int currentPage; //��ǰҳ
	private int totalPage; //��ҳ��
	public QueryAction(){}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	public TicketsDao getTk() {
		return tk;
	}
	public void setTk(TicketsDao tk) {
		this.tk = tk;
	}
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
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	public String execute()throws Exception{
		tickets=tk.queryTickets();
		if(tickets.size()%pageSize==0){
			totalPage = tickets.size()/pageSize;
		}else{
			totalPage = tickets.size()/pageSize+1;
		}
		if(pageNo<0){
			pageNo=1;
		}else if(pageNo>totalPage){
			pageNo=totalPage;
		}
		//���ݵ�ǰҳ��ѯҪ�ڸ�ҳ����ʾ��5������
		tickets=tk.queryByPage(pageNo,pageSize);
		//���õ�ǰҳ
		currentPage = pageNo;
		if(tickets!=null)
			return SUCCESS;
		else
			return INPUT;
	}
	

}
