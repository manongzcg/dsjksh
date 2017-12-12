package cn.epi.datasource.entity;

import cn.epi.sys.entity.DataEntity;

/**
 * Created by mwu on 5/11/17 5:13 PM.、
 *
 * 数据源实体类
 */
public class DataSourceEntity  extends DataEntity<DataSourceEntity>{

    private static final long serialVersionUID = 1L;

    private String type; // 数据源类型
    private String host; // 数据源主机
    private String port; // 数据源服务端口
    private String sourceUser; // 数据源管理用户
    private String password; // 数据源管理用户密码
    private String databaseName; //数据对应数据库名称
    private String project;  //数据对应项目名称
    private String initialSize;  //初始化时建立物理连接的个数
    private String minIdle;  //最小连接池数量
    private String maxActive;  //最大连接池数量
    private String maxWait;  //获取连接时最大等待时间
    private String comment; // 数据源说明
    private String roleId; // 数据源连接角色id

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSourceUser() {
		return sourceUser;
	}

	public void setSourceUser(String sourceUser) {
		this.sourceUser = sourceUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(String initialSize) {
		this.initialSize = initialSize;
	}

	public String getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(String minIdle) {
		this.minIdle = minIdle;
	}

	public String getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(String maxActive) {
		this.maxActive = maxActive;
	}

	public String getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(String maxWait) {
		this.maxWait = maxWait;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
	
}
