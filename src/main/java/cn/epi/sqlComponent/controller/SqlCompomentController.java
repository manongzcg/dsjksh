package cn.epi.sqlComponent.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.epi.common.BaseController;
import cn.epi.sqlComponent.dao.SqlCompomentDao;
import cn.epi.sqlComponent.entity.SjljDataresource;
import cn.epi.sqlComponent.service.SjljDataresourceService;


@Controller
@RequestMapping("${adminPath}/sqlCompoment")
public class SqlCompomentController extends BaseController{
	
@Autowired
private SjljDataresourceService sjljDataresourceService;

@RequestMapping(value = "/list")
public @ResponseBody List<Map> getDataList( ) {
	
      StringBuffer sb = new StringBuffer();
      List<Map> array = null;
      sb.append("select * from ");
      // 读取原始json文件并进行操作和输出  
      try {  
          BufferedReader br = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/sqlConpoment.json"));// 读取原始json文件  
          String s = null; 
          while ((s = br.readLine()) != null) {  
              try {  
                  JSONObject dataJson = new JSONObject(s);// 创建一个包含原始json串的json对象  
                  JSONObject feature = dataJson.getJSONObject("config");// 找到features的json数组 
                  JSONArray features = feature.getJSONArray("tables");// 找到features的json数组  
                  
                  for (int i = 0; i < features.length(); i++) {  
                  	 if(i==0){
	                    	 JSONObject info = features.getJSONObject(i);// 获取features数组的第i个json对象  
	                    	 SjljDataresource sd=new SjljDataresource();
	                    	 sd.setOld_table_name(info.getString("tableName"));
	                    	 sd.setData_resource_id(info.getInt("dataConnectionId"));
	                    	 sd.setOld_data_name(info.getString("database"));
							 SjljDataresource entity =  sjljDataresourceService.get(sd);
							 if(entity !=null && entity.getTable_name()!=null){
	                    	 sb.append(entity.getTable_name()+" AS "+info.getString("tableAlias"));
							 }else{
							return null; 
							 }
	                    }else{
                      JSONObject info = features.getJSONObject(i).getJSONObject("join");// 获取features数组的第i个json对象  
                      JSONArray conditions = info.getJSONArray("conditions");// 找到conditions的json数组  
                      for (int j = 0; j < conditions.length(); j++) { 
                      	if(j==0){
		                        sb.append("  "+info.getString("method")+"  JOIN ");
		                        SjljDataresource sd=new SjljDataresource();
		                    	 sd.setOld_table_name(features.getJSONObject(i).getString("tableName"));
		                    	 sd.setData_resource_id(features.getJSONObject(i).getInt("dataConnectionId"));
		                    	 sd.setOld_data_name(features.getJSONObject(i).getString("database"));
								 SjljDataresource entity =  sjljDataresourceService.get(sd);
								 if(entity !=null && entity.getTable_name()!=null){
		                    	 sb.append(entity.getTable_name()+" AS "+features.getJSONObject(i).getString("tableAlias") +" ON ");
								 }else{
									 return null; 
								 }
		                        sb.append("  "+features.getJSONObject(conditions.getJSONObject(j).getInt("leftTableId")).getString("tableAlias")+"."+conditions.getJSONObject(j).getString("left"));
		                        sb.append(" = "+features.getJSONObject(i).getString("tableAlias")+"."+conditions.getJSONObject(j).getString("right"));
                      	}else{
                      		sb.append("  AND "+features.getJSONObject(conditions.getJSONObject(j).getInt("leftTableId")).getString("tableAlias")+"."+conditions.getJSONObject(j).getString("left"));
  	                        sb.append(" = "+features.getJSONObject(i).getString("tableAlias")+"."+conditions.getJSONObject(j).getString("right"));  
                      	}
                      }
                  }  
                  }
                  array= SqlCompomentDao.getInstance().getDataList("jdbc:mysql://182.254.141.145:3367/gz_bigDataShow","root","kjdd$539",sb.toString());  
              } catch (JSONException e) {  
                  // TODO Auto-generated catch block  
                  e.printStackTrace();  
              }  
          }  
          // bw.newLine();  
          br.close();  
      } catch (IOException e) {  
          // TODO Auto-generated catch block  
          e.printStackTrace();  
      }  
	return array;
}

@RequestMapping(value = "/lists")
public @ResponseBody List<Map> getData( ) {
	
      StringBuffer sb = new StringBuffer();
      StringBuffer sql = new StringBuffer();
      List<Map> array = null;
      sb.append("select ");
      // 读取原始json文件并进行操作和输出  
      try {  
          BufferedReader br = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/sqlConpoment.json"));// 读取原始json文件  
          String s = null;  
          while ((s = br.readLine()) != null) {  
              try {  
                  JSONObject dataJson = new JSONObject(s);// 创建一个包含原始json串的json对象  
                  JSONObject feature = dataJson.getJSONObject("config");// 找到features的json数组 
                  JSONArray features = feature.getJSONArray("tables");// 找到features的json数组  
                  JSONArray dimensions = dataJson.getJSONArray("dimensions");// 找到dimensions的json数组  
                 for(int k = 0; k < dimensions.length(); k++){
                	 JSONObject dim = dimensions.getJSONObject(k); 
                	 if(dim.has("ignore")){
                		continue; 
                	 }else{
                		 if(dim.has("expr")){
                			 
                		 }else{
                		  sql.append(dim.getString("tableAlias")+"."+dim.getString("alias")+","); 
                		  }
                	 }
                  }
                 JSONArray measures = dataJson.getJSONArray("measures");// 找到measures的json数组  
                 for(int j = 0; j < measures.length(); j++){
                	 JSONObject meas = measures.getJSONObject(j); 
                	 if(meas.has("ignore")){
                		continue; 
                	 }else{
                       if(meas.has("expr")){
                    	   sql.append(meas.getString("expr")+" AS "+meas.getString("alias")+",");
                		 }else{
                		  sql.append(meas.getString("tableAlias")+"."+meas.getString("alias")+","); 
                		 }
                	 }
                  }
                 sb.append(sql.toString().substring(0,sql.toString().length() - 1)+"  from  "); 
                  for (int i = 0; i < features.length(); i++) {  
                  	 if(i==0){
	                    	 JSONObject info = features.getJSONObject(i);// 获取features数组的第i个json对象  
	                    	 SjljDataresource sd=new SjljDataresource();
	                    	 sd.setOld_table_name(info.getString("tableName"));
	                    	 sd.setData_resource_id(info.getInt("dataConnectionId"));
	                    	 sd.setOld_data_name(info.getString("database"));
							 SjljDataresource entity =  sjljDataresourceService.get(sd);
							 if(entity !=null && entity.getTable_name()!=null){
	                    	 sb.append(entity.getTable_name()+" AS "+info.getString("tableAlias"));
							 }else{
							return null; 
							 }
	                    }else{
                      JSONObject info = features.getJSONObject(i).getJSONObject("join");// 获取features数组的第i个json对象  
                      JSONArray conditions = info.getJSONArray("conditions");// 找到conditions的json数组  
                      for (int j = 0; j < conditions.length(); j++) { 
                      	if(j==0){
		                        sb.append("  "+info.getString("method")+"  JOIN ");
		                        SjljDataresource sd=new SjljDataresource();
		                    	 sd.setOld_table_name(features.getJSONObject(i).getString("tableName"));
		                    	 sd.setData_resource_id(features.getJSONObject(i).getInt("dataConnectionId"));
		                    	 sd.setOld_data_name(features.getJSONObject(i).getString("database"));
								 SjljDataresource entity =  sjljDataresourceService.get(sd);
								 if(entity !=null && entity.getTable_name()!=null){
		                    	 sb.append(entity.getTable_name()+" AS "+features.getJSONObject(i).getString("tableAlias") +" ON ");
								 }else{
									 return null; 
								 }
		                        sb.append("  "+features.getJSONObject(conditions.getJSONObject(j).getInt("leftTableId")).getString("tableAlias")+"."+conditions.getJSONObject(j).getString("left"));
		                        sb.append(" = "+features.getJSONObject(i).getString("tableAlias")+"."+conditions.getJSONObject(j).getString("right"));
                      	}else{
                      		sb.append("  AND "+features.getJSONObject(conditions.getJSONObject(j).getInt("leftTableId")).getString("tableAlias")+"."+conditions.getJSONObject(j).getString("left"));
  	                        sb.append(" = "+features.getJSONObject(i).getString("tableAlias")+"."+conditions.getJSONObject(j).getString("right"));  
                      	}
                      }
                  }  
                  }
                  array= SqlCompomentDao.getInstance().getDataList("jdbc:mysql://182.254.141.145:3367/gz_bigDataShow","root","kjdd$539",sb.toString());  
              } catch (JSONException e) {  
                  // TODO Auto-generated catch block  
                  e.printStackTrace();  
              }  
          }  
          // bw.newLine();  
          br.close();  
      } catch (IOException e) {  
          // TODO Auto-generated catch block  
          e.printStackTrace();  
      }  
	return array;
}


}
