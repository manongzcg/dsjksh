package cn.epi.sqlComponent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;


import cn.epi.util.datasource.DBConnect;



public class SqlCompomentDao {
	private static final String String = null;
	private static SqlCompomentDao insertDao;

	private SqlCompomentDao() {
	}

	/**
	 * 线程安全
	 */
	private static synchronized void syncInit() {
		if (insertDao == null) {
			insertDao = new SqlCompomentDao();
		}
	}

	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static SqlCompomentDao getInstance() {
		syncInit();
		return insertDao;
	}


	
		public List<Map> getDataList(String url,String user,String password,String sql){
			Connection con = new DBConnect().getconn(url,user,password);
			PreparedStatement pst = null;
			//JSONArray array = null;
			List<Map> dataList=null;
			try {
				con = new DBConnect().getconn(url,user,password);
				pst = con.prepareStatement(sql);
				ResultSet ret = pst.executeQuery();
			   // array = Tojson(ret);
			    dataList = Tojson(ret);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(pst!=null)pst.close();
					if(con!=null)con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//return array;
			return dataList;
		}
		
		public static List<Map> Tojson(ResultSet result) {
			//JSONArray array = new JSONArray();
			List<Map> dataList=new ArrayList<Map>();
			ResultSetMetaData metaData = null;
			int columnCount = 0;

			try {
				metaData = result.getMetaData();
				columnCount = metaData.getColumnCount();
				while (result.next()) {

					//JSONObject jsonObj = new JSONObject();
                     Map<String,String>  map=new HashedMap();
					// 遍历每一列

					for (int i = 1; i <= columnCount; i++) {

						String columnName = metaData.getColumnLabel(i);

						String value = result.getString(columnName);
                         
						map.put(columnName, value);
						//jsonObj.put(columnName, value);

					}
					dataList.add(map);
					//array.add(jsonObj);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//return array;
			return dataList;
		}
}
