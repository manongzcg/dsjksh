package cn.epi.util.datasource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MasgReturn {
public JSONObject MassageReturn(Object object){
	JSONObject jo = new JSONObject();
	jo.put("code", 200);
	if(object==null){
		jo.put("result", new JSONArray());
	}else{
	
	jo.put("result", object);
	}
	return jo;
}
}
