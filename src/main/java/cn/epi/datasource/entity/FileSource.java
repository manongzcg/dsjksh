package cn.epi.datasource.entity;

import java.sql.Date;

import cn.epi.sys.entity.DataEntity;

public class FileSource extends DataEntity<FileSource>{
	private  int data_resource_id; 
    private String data_resource_name;
    private String data_type ;           
    private String operator_name  ;      
    private Date operator_date  ;      
    private String state            ;    
    private String IP   ;                
    private String port   ;              
    private String userid   ;           
    private String password   ;          
    private String SID     ;            
    private String data_name  ;
    
	public int getData_resource_id() {
		return data_resource_id;
	}
	public void setData_resource_id(int data_resource_id) {
		this.data_resource_id = data_resource_id;
	}
	public String getData_resource_name() {
		return data_resource_name;
	}
	public void setData_resource_name(String data_resource_name) {
		this.data_resource_name = data_resource_name;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getOperator_name() {
		return operator_name;
	}
	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}
	public Date getOperator_date() {
		return operator_date;
	}
	public void setOperator_date(Date operator_date) {
		this.operator_date = operator_date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	public String getData_name() {
		return data_name;
	}
	public void setData_name(String data_name) {
		this.data_name = data_name;
	}
	    
}
