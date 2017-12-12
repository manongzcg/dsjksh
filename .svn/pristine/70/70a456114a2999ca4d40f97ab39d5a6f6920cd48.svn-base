package cn.epi.port.controller;

import cn.epi.common.JsonMapper;
import cn.epi.common.PortPage;
import cn.epi.common.utils.DynamicEngine;
import cn.epi.common.utils.DynamicEnginelast;
import cn.epi.port.entity.PortEntity;
import cn.epi.port.service.PortalService;
import cn.epi.sys.entity.User;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对外接口Action Created by mwu on 5/19/17 10:54 AM.
 */

@Controller
@RequestMapping("${adminPath}/portal")
public class PortalController {

	

	@Autowired
	private PortalService portalService;

	@ResponseBody
	@RequestMapping(value = "/exec/{username}/{apiKey}/{portID}")
	public String execPortal(@PathVariable String username, @PathVariable String apiKey, @PathVariable String portID,
			HttpServletRequest request) {

		User user = portalService.checkAuth(username, apiKey);
		int payload = 0;
		if (null == user) {
			return "501: 请联系管理员重新获取APIKey";
		}

		PortEntity port = portalService.checkPermission(user, portID);
		if (null == port) {
			return "502: 请联系管理员分配接口权限";
		}

		// Get parameters
		String[] params = port.getParams().replace(" ", "").split(",");
		int paramsNum = params.length;
		String[] values = new String[paramsNum];

		for (int i = 0; i < paramsNum; i++) {
			values[i] = StringEscapeUtils.escapeHtml4(request.getParameter(params[i]));
			if (values[i] == null) {
				values[i] = "";
			}else {
				payload += 1;
		}
		}
		
		if(payload==0){
			ServletInputStream is;  
		    try {  
		        is = request.getInputStream(); 
		        int nRead = 1;  
		        int nTotalRead = 0;  
		        byte[] bytes = new byte[10240];  
		        while (nRead > 0) {  
		            nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);  
		            if (nRead > 0)  
		                nTotalRead = nTotalRead + nRead;  
		        }  
		        String str = new String(bytes, 0, nTotalRead, "utf-8");  
		        JSONObject jsStr = JSON.parseObject(str);//fastjson 将String 转成 jsonObject
		       for (int i = 0; i < paramsNum; i++) {
					values[i] = StringEscapeUtils.escapeHtml4((String) jsStr.get(params[i]));
					if (values[i] == null) {
						values[i] = "";
					} 
		       }
		    } catch (IOException e) {  
		        e.printStackTrace();  
		        
		    } 
		}
		int result = portalService.execPortal(port, values);

		return Integer.toString(result);
	}

	@ResponseBody
	@RequestMapping(value = "/query/{username}/{apiKey}/{portID}")
	public String queryPortal(@PathVariable String username, @PathVariable String apiKey, @PathVariable String portID,
			HttpServletRequest request) throws Exception {
		User user = portalService.checkAuth(username, apiKey);
		String result = null;
		List<HashMap<String, String>> lastresult = null;
		PortEntity port = portalService.checkPermission(user, portID);

		if (null == user) {
			return "501: 请联系管理员重新获取APIKey";
		}
		if (null == port) {
			return "502: 请联系管理员分配接口权限";
		}
		String[] params = port.getParams().replace(" ", "").split(",");
		int paramsNum = params.length;
		String[] values = new String[paramsNum];
		int payload = 0;
		for (int i = 0; i < paramsNum; i++) {
			values[i] = StringEscapeUtils.escapeHtml4(request.getParameter(params[i]));
			if (values[i] == null) {
				values[i] = "";
			} else {
				payload += 1;
		}
		}
		if(payload==0){
			ServletInputStream is;  
		    try {  
		        is = request.getInputStream(); 
		        int nRead = 1;  
		        int nTotalRead = 0;  
		        byte[] bytes = new byte[10240];  
		        while (nRead > 0) {  
		            nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);  
		            if (nRead > 0)  
		                nTotalRead = nTotalRead + nRead;  
		        }  
		        String str = new String(bytes, 0, nTotalRead, "utf-8");  
		        JSONObject jsStr = JSON.parseObject(str);//fastjson 将String 转成 jsonObject
		       for (int i = 0; i < paramsNum; i++) {
					values[i] = StringEscapeUtils.escapeHtml4((String) jsStr.get(params[i]));
					if (values[i] == null) {
						values[i] = "";
					} 
		       }
		    } catch (IOException e) {  
		        e.printStackTrace();  
		        
		    } 
		}
		// 判断是否 bean 是bean 加载 获取字段 赋值给values
		// Get parameters
		if (port.getState().equals("1")) {
			result = portalService.queryPortal(port, values);
		} else {
			Map map = new HashMap<>();
			for (int i = 0; i < paramsNum; i++) {
				String params1 = params[i];
				map.put(params[i], values[i]);
			}
			// 调用第一个 反射机制 进行取数据
			String fullName = port.getFirstjavaname();
			StringBuilder src = new StringBuilder();
			src.append(port.getFirstjava());
			DynamicEngine firstde = DynamicEngine.getInstance();
			Map<String, String> param = firstde.javaCodeToObject(fullName, src.toString(), map, port, params);
			// 把取出来的数据进行 sql拼接 并查询

			lastresult = portalService.querybean(port, param, params);

			// 调用第二个 反射机制 进行对数据解析等
			String lastfullName = port.getLastjavaname();
			StringBuilder lastsrc = new StringBuilder();
			lastsrc.append(port.getLastjava());
			DynamicEnginelast lastde = DynamicEnginelast.getInstance();
			result = lastde.javaCodeToObject(lastfullName, lastsrc.toString(), lastresult, port, params, param);

		}

		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/page/{username}/{apiKey}/{portID}")
	public String querypage(@PathVariable String username, @PathVariable String apiKey, @PathVariable String portID,
			HttpServletRequest request) throws Exception {
		User user = portalService.checkAuth(username, apiKey);
		
		
		PortPage ppage = new PortPage();
		String result = null;
		int payload = 0;
		Map<String, String> map = new HashMap<String, String>();
		String lastresult = null;
		if (null == user) {
			return "501: 请联系管理员重新获取APIKey";
		}
		PortEntity port = portalService.checkPermission(user, portID);
		if (null == port) {
			return "502: 请联系管理员分配接口权限";
		}
		String datype = portalService.checkdatype(port.getSourceId());// 获取类型
		ppage.setDbtype(datype);// 给pagebean赋值 所属数据库类型
		ppage.setPagesize(Integer.parseInt(request.getParameter("pagesize")));// 给pagebean赋值
																				// 所属页面大小
		ppage.setPagenumber(Integer.parseInt(request.getParameter("pagenumber")));// 页码

		// 判断是否 bean 是bean 加载 获取字段 赋值给values

		// Get parameters
		String[] params = port.getParams().replace(" ", "").split(",");
		int paramsNum = params.length;
		String[] values = new String[paramsNum];

		for (int i = 0; i < paramsNum; i++) {
			values[i] = StringEscapeUtils.escapeHtml4(request.getParameter(params[i]));
			if (values[i] == null) {
				values[i] = "";
			}else{
				payload+=1;
			}
		}
		if(payload==0){
			ServletInputStream is;  
		    try {  
		        is = request.getInputStream(); 
		        int nRead = 1;  
		        int nTotalRead = 0;  
		        byte[] bytes = new byte[10240];  
		        while (nRead > 0) {  
		            nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);  
		            if (nRead > 0)  
		                nTotalRead = nTotalRead + nRead;  
		        }  
		        String str = new String(bytes, 0, nTotalRead, "utf-8");  
		        JSONObject jsStr = JSON.parseObject(str);//fastjson 将String 转成 jsonObject
		       for (int i = 0; i < paramsNum; i++) {
					values[i] = StringEscapeUtils.escapeHtml4((String) jsStr.get(params[i]));
					if (values[i] == null) {
						values[i] = "";
					} 
		       }
		    } catch (IOException e) {  
		        e.printStackTrace();  
		        
		    } 
		}

		if (datype.equals("MYSQL")) {
			map = portalService.queryPage(port, values, ppage);
		} else {

		}

		if (datype.equals("MYSQL")) {
			result = JsonMapper.toJsonString(map);
			return result.replaceAll("\\\\", "");
		} else {
			return lastresult;
		}

	}

	
	

}
