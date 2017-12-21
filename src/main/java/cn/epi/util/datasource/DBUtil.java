package cn.epi.util.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import cn.epi.datasource.entity.DB_TYPE;
import cn.epi.datasource.entity.FileSource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DBUtil {
	// public JSONArray show(FileSource filesource, String sql,String url){
	//
	// PreparedStatement preStatement = null;
	// ResultSet result = null;
	// Connection conn =null;
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	// conn = DriverManager.getConnection(url,filesource.getUserid(),
	// filesource.getPassword());
	// preStatement = conn.prepareStatement(sql);
	// result = preStatement.executeQuery();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// JSONArray array = Tojson(result);
	// System.out.println(array);
	//
	//
	// return array;
	//
	// }
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

	public boolean connGP(String sql) {

		Connection conn = null;
		JSONArray array = new JSONArray();
		ResultSet result = null;
		PreparedStatement preStatement = null;

		try {
			Class.forName("com.pivotal.jdbc.GreenplumDriver");
			 conn = DriverManager.getConnection("jdbc:pivotal:greenplum://192.168.1.123:5432;DatabaseName=testdb", "gpadmin",
					"gpadmin");
			// 3.通过数据库的连接操作数据库，实现增删改查
			preStatement = conn.prepareStatement(sql);
			result = preStatement.executeQuery();
			array = Tojson(result);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
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
		return true;
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

	public static boolean testConnection(FileSource fs) {
		String url = "";
		String driver = "";
		String dbType = fs.getData_type();
		String host = fs.getIP();
		String port = fs.getPort();
		String database = fs.getData_name();
		String username = fs.getUserid();
		String password = fs.getPassword();
		switch (dbType) {
		case "Oracle":
			url = "jdbc:oracle:thin:" + host + ":" + port + ":" + database;
			driver = "oracle.jdbc.driver.OracleDriver";
			break;
		case "Mysql":
			url = "jdbc:mysql://" + host + ":" + port;
			driver = "com.mysql.jdbc.Driver";
			break;
		case "SqlServer":
			url = "jdbc:sqlserver://" + host + ":" + port + ";DatabaseName=" + database;
			;
			driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			break;
		}

		Connection conn = null;

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;

	}
}
