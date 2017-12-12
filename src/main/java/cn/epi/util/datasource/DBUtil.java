package cn.epi.util.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import cn.epi.datasource.entity.FileSource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DBUtil {
//	public JSONArray show(FileSource filesource, String sql,String url){
//		
//		PreparedStatement preStatement = null;
//		ResultSet result = null;
//		Connection conn =null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			 conn = DriverManager.getConnection(url,filesource.getUserid(),
//						filesource.getPassword());
//			preStatement = conn.prepareStatement(sql);
//			result = preStatement.executeQuery();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		JSONArray array = Tojson(result);
//		System.out.println(array);
//			
//			
//		return array;
//		
//	}
	public JSONArray show(Connection conn, String sql) {
		JSONArray array = new JSONArray();
		ResultSet result = null;
		PreparedStatement preStatement = null;
		try {
			// 3.通过数据库的连接操作数据库，实现增删改查
			preStatement = conn.prepareStatement(sql);
			result = preStatement.executeQuery();
			array = Tojson(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
		
	}

	public static Connection conn(FileSource filesource, String classname,
			String url) {

		Connection conn = null;

		try {
			Class.forName(classname);
			conn = DriverManager.getConnection(url, filesource.getUserid(),
					filesource.getPassword());
			// 3.通过数据库的连接操作数据库，实现增删改查

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static JSONArray Tojson(ResultSet result) {
		JSONArray array = new JSONArray();
		ResultSetMetaData metaData = null;
		int columnCount = 0;

		try {
			metaData = result.getMetaData();
			columnCount = metaData.getColumnCount();
			while (result.next()) {

				JSONObject jsonObj = new JSONObject();

				// 遍历每一列

				for (int i = 1; i <= columnCount; i++) {

					String columnName = metaData.getColumnLabel(i);

					String value = result.getString(columnName);

					jsonObj.put(columnName, value);

				}

				array.add(jsonObj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;
	}
}
