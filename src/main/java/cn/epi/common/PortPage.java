package cn.epi.common;

public class PortPage {
	
	
	
	private int pagenumber;//当前页
	private int pagesize;//分页条数    页面大小
	private int startrom;//开始行数  orcal
	private int endrom;//结束行数 orcal
	private String dbtype;//数据库类型
	private int total;//总数
	
	
	public int getPagenumber() {
		return pagenumber;
	}
	public void setPagenumber(int pagenumber) {
		this.pagenumber = pagenumber;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getStartrom() {
		return startrom;
	}
	public void setStartrom(int startrom) {
		this.startrom = startrom;
	}
	public int getEndrom() {
		return endrom;
	}
	public void setEndrom(int endrom) {
		this.endrom = endrom;
	}
	public String getDbtype() {
		return dbtype;
	}
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	

}
