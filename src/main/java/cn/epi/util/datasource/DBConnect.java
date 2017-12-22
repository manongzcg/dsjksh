package cn.epi.util.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 
 * @author damonyang 获取数据库连接
 */
public class DBConnect {
	public  Connection conn = null;

	
	public  Connection getconn(String url,String user,String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);// 获取连接
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 指定连接类型
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return conn;
	}
	
	public  void close(){
		try{
		if(conn!=null){
			conn.close();
		}}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
