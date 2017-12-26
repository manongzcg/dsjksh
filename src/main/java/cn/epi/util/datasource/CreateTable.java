package cn.epi.util.datasource;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;


import com.alibaba.fastjson.JSONObject;

import cn.epi.common.config.JConfig;
import cn.epi.datasource.entity.FileSource;
import cn.epi.util.excel.ShowCSVUtil;
import cn.epi.util.excel.ShowExcelUtil;

public class CreateTable {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
	String res = sdf.format(new Date());
	public boolean createETable(JSONObject json_mate, String tableName) {
		String field = "";
		DBUtil dbutil = new DBUtil();
		     for (Iterator it1 =  json_mate.keySet().iterator();it1.hasNext();)
			   {
			    Object key1 = it1.next();
			  String value = json_mate.getString((String) key1);
			   field = field+(String) key1; 
	             if("num".equals(value)){
	            	 field = field +" "+"numeric";
	             }else{
	            	 field = field +" "+ "text";
	             }
	             field = field + ", \r\n";
			   }
		     field = field.substring(0,field.length()-4);
			String sql = "create table "+tableName +"("+field+")";
		   boolean re = dbutil.connGP(sql);
		return re;
	
	}
	public boolean insertTable(JSONArray jsonArr_file, String tableName,String rootPath,String type) {
		String field = "";
		DBUtil dbutil = new DBUtil();
		String sql = "";
		boolean re =false;
		List<String> list = new  ArrayList<String> ();
		if("csv".equals(type)){
			for (int i = 0; i < jsonArr_file.size(); i++)// 通过循环取出数组里的值
			{
				ShowCSVUtil showCSV = new ShowCSVUtil();
				JSONObject jsonTemp = jsonArr_file.getJSONObject(i);
				String key = jsonTemp.getString("key");
				list = showCSV.csv2json_mate(rootPath+"/"+key);
				for (int j=0; j< list.size(); j++){
					sql = "insert into "+tableName+" values ("+list.get(j)+")";
					re = dbutil.connGP(sql);
				}
			}
		}else{
			
		}
		
		
		   
		return re;
	
	}
//	public void CSVTable(FileSource fs){
//		ShowCSVUtil csvU = new ShowCSVUtil();
//		String filePath = fs.getIP();
//		JSONArray ja = csvU.readcsv(filePath);
//		JSONObject jo = ja.getJSONObject(ja.size()-1);
//		String tableName = "csv_"+res;
//		String sql = "create table "+tableName +"("+CreateSql(jo)+")";
//		
//	}
//	public static String CreateSql(JSONObject jo){
//		String str = jo.toString();
//		 Map mapType = JSON.parseObject(str,Map.class);  
//	        String field =null;
//	        for (Object obj : mapType.keySet()){  
//	             field = field+(String) obj; 
//	             if("num".equals(mapType.get(obj))){
//	            	 field = field +" numeric";
//	             }else{
//	            	 field = field + " text";
//	             }
//	             field = field + ", \r\n";
//	        }  
//	      field = field.substring(0,field.length()-6);
//	      return field;
//	}
}
