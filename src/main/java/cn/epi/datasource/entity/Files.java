package cn.epi.datasource.entity;

import cn.epi.sys.entity.DataEntity;

public class Files extends DataEntity<Files> {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private  int file_resource_id ;
private String old_name;
private String new_name;
private String url;
private  int data_resource_id ;    
private String csv_separator  ;      
private String withheader   ;
public int getFile_resource_id() {
	return file_resource_id;
}
public void setFile_resource_id(int file_resource_id) {
	this.file_resource_id = file_resource_id;
}

public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public int getData_resource_id() {
	return data_resource_id;
}
public void setData_resource_id(int data_resource_id) {
	this.data_resource_id = data_resource_id;
}
public String getCsv_separator() {
	return csv_separator;
}
public void setCsv_separator(String csv_separator) {
	this.csv_separator = csv_separator;
}
public String getWithheader() {
	return withheader;
}
public void setWithheader(String withheader) {
	this.withheader = withheader;
}

public String getNew_name() {
	return new_name;
}
public void setNew_name(String new_name) {
	this.new_name = new_name;
}
public String getOld_name() {
	return old_name;
}
public void setOld_name(String old_name) {
	this.old_name = old_name;
}     

}
