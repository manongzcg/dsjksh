package cn.epi.sqlComponent.entity;


import cn.epi.sys.entity.DataEntity;

public class SjljDataresource extends DataEntity<SjljDataresource>{
	
	
	private static final long serialVersionUID = 1L;
	
	
	private  int relationID;
	private  int data_resource_id; 
    private String table_name;
    private String state;    
    private String old_table_name;                
    private String old_data_name;              
    private String type;           
    private String is_Timing_execution;
    
    
    
	public int getRelationID() {
		return relationID;
	}
	public void setRelationID(int relationID) {
		this.relationID = relationID;
	}
	public int getData_resource_id() {
		return data_resource_id;
	}
	public void setData_resource_id(int data_resource_id) {
		this.data_resource_id = data_resource_id;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOld_table_name() {
		return old_table_name;
	}
	public void setOld_table_name(String old_table_name) {
		this.old_table_name = old_table_name;
	}
	public String getOld_data_name() {
		return old_data_name;
	}
	public void setOld_data_name(String old_data_name) {
		this.old_data_name = old_data_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIs_Timing_execution() {
		return is_Timing_execution;
	}
	public void setIs_Timing_execution(String is_Timing_execution) {
		this.is_Timing_execution = is_Timing_execution;
	}          
   

}
