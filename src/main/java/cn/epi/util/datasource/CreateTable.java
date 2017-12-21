package cn.epi.util.datasource;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;


import com.alibaba.fastjson.JSONObject;

import cn.epi.datasource.entity.FileSource;
import cn.epi.util.excel.ShowCSVUtil;
import cn.epi.util.excel.ShowExcelUtil;

public class CreateTable {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
	String res = sdf.format(new Date());
	public boolean createETable(JSONObject jsonArr_sheet, String tableName) {
		String field = null;
		DBUtil dbutil = new DBUtil();
		     for (Iterator it1 =  jsonArr_sheet.keySet().iterator();it1.hasNext();)
			   {
			    Object key1 = it1.next();
			  String value = (String) jsonArr_sheet.get(key1);
			   field = field+(String) key1; 
	             if("num".equals(value)){
	            	 field = field +" numeric";
	             }else{
	            	 field = field + " text";
	             }
	             field = field + ", \r\n";
			   }
		     field = field.substring(0,field.length()-6);
		    
			String sql = "create table "+tableName +"("+field+")";
		   boolean re = dbutil.connGP(sql);
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
