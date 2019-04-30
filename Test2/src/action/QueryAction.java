package action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import dao.TicketsDao;
import dao.UsersDao;
import model.Ticket;

public class QueryAction extends ActionSupport{
	List<Ticket> tickets;//持久化集合
	TicketsDao tk = new TicketsDao();
	private Ticket ticket;
	private int id; //界面显示数据的索引
	private final int pageSize=5; //每页显示记录的个数
	private int pageNo=1; //计数器,从第1页开始显示
	private int currentPage; //当前页
	private int totalPage; //总页数
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
		//根据当前页查询要在该页上显示的5条数据
		tickets=tk.queryByPage(pageNo,pageSize);
		//设置当前页
		currentPage = pageNo;
		if(tickets!=null)
			return SUCCESS;
		else
			return INPUT;
	}
	

}
