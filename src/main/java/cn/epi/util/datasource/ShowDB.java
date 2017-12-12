package cn.epi.util.datasource;

import java.sql.*;

import cn.epi.datasource.entity.FileSource;

import com.alibaba.fastjson.JSONArray;

public class ShowDB {
	private static DBUtil dbutil;

	public JSONArray showDatabase(FileSource filesource, String sql, String url, String className) {

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
	// public JSONArray showTable(FileSource filesource){
	// String url = "jdbc:mysql://" + filesource.getIP() + ":" +
	// filesource.getPort() + "/"
	// +filesource.getData_name();
	// Connection conn = dbutil.conn(filesource, className, url);
	// String sql = "show tables";
	// JSONArray array = dbutil.show(conn, sql);
	// return array;
	// }
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
