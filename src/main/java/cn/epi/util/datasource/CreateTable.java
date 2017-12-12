package cn.epi.util.datasource;

import java.io.IOException;
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
	public void ExcleTable(FileSource fs) {
      ShowExcelUtil excleU = new ShowExcelUtil();
      LinkedHashMap lhm = null ;
      try {
		lhm = excleU.excel2json(fs);
		  for (Iterator it =  lhm.keySet().iterator();it.hasNext();)
		   {
		    Object key = it.next();
		    JSONArray ja = (JSONArray) lhm.get(key);
		    JSONObject jo = ja.getJSONObject(ja.size()-1);
		   }
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void CSVTable(FileSource fs){
		ShowCSVUtil csvU = new ShowCSVUtil();
		String filePath = fs.getIP();
		JSONArray ja = csvU.readcsv(filePath);
		JSONObject jo = ja.getJSONObject(ja.size()-1);
	}
	public void CreateSql(JSONObject jo){
		String str = jo.toString();
		 Map mapType = JSON.parseObject(str,Map.class);  
	        String field =null;
	        for (Object obj : mapType.keySet()){  
	             field = (String) obj; 
	             field = field + ", \r\n";
	        }  
	}
}
