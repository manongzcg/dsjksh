package cn.epi.util.datasource;

import java.sql.*;

import cn.epi.datasource.entity.FileSource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ShowDB {
	private static DBUtil dbutil;
	private static final String classNameM = "com.mysql.jdbc.Driver";
	private static final String classNameO = "oracle.jdbc.driver.OracleDriver";
	private static final String classNameS="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public JSONArray conn(FileSource filesource, String sql, String url, String className) {

		PreparedStatement preStatement = null;
		JSONArray array = null;
		ResultSet result = null;
		Connection conn = null;
		try {
		
				Class.forName(className);
			
			
			conn = DriverManager.getConnection(url, filesource.getUserid(),
					filesource.getPassword());
			preStatement = conn.prepareStatement(sql);
			result = preStatement.executeQuery();
			array = dbutil.Tojson(result);
			System.out.println(array);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (result != null) {
				try {
					result.close();
					result = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preStatement != null) {
				try {
					preStatement.close();
					preStatement = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return array;
	}
	public JSONArray showDB(FileSource filesource) {
		JSONArray result = null;
		String sql = null;
		String url = null;
		ShowDB db = new ShowDB();
		String className = null;
		if ("Mysql".equals(filesource.getData_type())) {
			
				sql = "show databases";
			    url = "jdbc:mysql://" + filesource.getIP() + ":"
						+ filesource.getPort();
			className = classNameM;
			result = db.conn(filesource, sql, url,className);
		} else if ("Oracle".equals(filesource.getData_type())) {
			sql = "select name from v$database";
		   url ="jdbc:oracle:thin:@"+filesource.getIP()+":"+ filesource.getPort();
			className = classNameO;
			result = db.conn(filesource, sql, url,className);
		} else if ("Sqlserver".equals(filesource.getData_type())) {
			JSONObject jo = null;
			jo.put("databasename", filesource.getData_name());
			result.add(jo);
		}
		return result;
	}
	public JSONArray showTable(FileSource filesource) {
		JSONArray result = null;
		String sql = null;
		String url = null;
		ShowDB db = new ShowDB();
		String className = null;
		if ("Mysql".equals(filesource.getData_type())) {
			
				sql = "show tables";
				url = "jdbc:mysql://" + filesource.getIP() + ":"
						+ filesource.getPort() + "/"
						+ filesource.getData_name();
			className = classNameM;
		} else if ("Oracle".equals(filesource.getData_type())) {
			sql = "select table_name from user_tables";
		   url ="jdbc:oracle:thin:@"+filesource.getIP()+":"+ filesource.getPort()+":"+filesource.getData_name();
			className = classNameO;
		} else if ("Sqlserver".equals(filesource.getData_type())) {
			url ="jdbc:sqlserver://"+filesource.getIP()+":"+ filesource.getPort()+";DatabaseName="+filesource.getData_name();
			sql = "select name from sys.objects where type='U'";
			className = classNameS;
		}
		System.out.println(className);
		result = db.conn(filesource, sql, url,className);
		return result;
	}
	
	 public JSONArray showDesc(FileSource filesource,String tableName){
		 JSONArray result = null;
		 String sql = "select * from "+tableName;
			String url = null;
			ShowDB db = new ShowDB();
			String className = null;
			if ("Mysql".equals(filesource.getData_type())) {
					url = "jdbc:mysql://" + filesource.getIP() + ":"
							+ filesource.getPort() + "/"
							+ filesource.getData_name();
				className = classNameM;
			} else if ("Oracle".equals(filesource.getData_type())) {
			   url ="jdbc:oracle:thin:@"+filesource.getIP()+":"+ filesource.getPort()+":"+filesource.getData_name();
				className = classNameO;
			} else if ("Sqlserver".equals(filesource.getData_type())) {
				url ="jdbc:sqlserver://"+filesource.getIP()+":"+ filesource.getPort()+";DatabaseName="+filesource.getData_name();
				className = classNameS;
			}
			result = db.conn(filesource, sql, url,className);
			return result;
	 }
	// public JSONArray showDesc(FileSource filesource,String tableName){
	// String url = "jdbc:mysql://" + filesource.getIP() + ":" +
	// filesource.getPort() + "/"
	// +filesource.getData_name();
	// Connection conn = dbutil.conn(filesource, className, url);
	// String sql = "select * from "+tableName;
	// JSONArray array = dbutil.show(conn, sql);
	// return array;
	// }

}
