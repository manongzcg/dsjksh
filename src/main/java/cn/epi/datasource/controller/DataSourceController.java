package cn.epi.datasource.controller;


import java.io.IOException;


import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSONArray;

import cn.epi.common.BaseController;

import cn.epi.datasource.entity.FileSource;

import cn.epi.datasource.service.FDatasourceService;
import cn.epi.util.datasource.ShowDB;


@Controller
public class DataSourceController extends BaseController {

	@Autowired
	private FDatasourceService datasourceService;
	private static final String classNameM = "com.mysql.jdbc.Driver";
	private static final String classNameO = "oracle.jdbc.driver.OracleDriver";
	private static final String classNameS="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	@RequestMapping(value = "/showDB")
	public @ResponseBody
	JSONArray showDB(Model model, HttpServletRequest request,
			FileSource filesource) throws IllegalStateException, IOException {
		JSONArray result = null;
		String sql = null;
		String url = null;
		ShowDB db = new ShowDB();
		String className = null;
		if ("Mysql".equals(filesource.getData_type())) {
			if (null == filesource.getData_name()) {
				sql = "show databases";
			    url = "jdbc:mysql://" + filesource.getIP() + ":"
						+ filesource.getPort();
			} else {
				sql = "show tables";
				url = "jdbc:mysql://" + filesource.getIP() + ":"
						+ filesource.getPort() + "/"
						+ filesource.getData_name();
			}
			className = classNameM;
		} else if ("Oracle".equals(filesource.getData_type())) {
			sql = "select table_name from user_tables";
		   url ="jdbc:oracle:thin:@"+filesource.getIP()+":"+ filesource.getPort()+":"+filesource.getData_name();
			className = classNameO;
		} else if ("Sqlserver".equals(filesource.getData_type())) {
			url ="jdbc:sqlserver://"+filesource.getIP()+":"+ filesource.getPort()+";DatabaseName="+filesource.getData_name();
			sql = "select name from sys.objects where type='U'";
			className = classNameS;
		}
		result = db.showDatabase(filesource, sql, url,className);
		return result;
	}

//	@RequestMapping(value = "/showDB")
//	public @ResponseBody
//	JSONArray showTable(Model model, HttpServletRequest request,
//			FileSource filesource) throws IllegalStateException, IOException {
//		JSONArray array = new JSONArray();
//		ShowMysql mysql = new ShowMysql();
//		String sql = "show databases";
//		String url = "jdbc:mysql://" + filesource.getIP() + ":"
//				+ filesource.getPort();
//		array = mysql.showDatabase(filesource, sql, url);
//		return array;
//	}
	
	 @RequestMapping(value = "/showTable")
	 public @ResponseBody JSONArray showDesc(Model model, HttpServletRequest request,
	 FileSource filesource,String tableName) throws IllegalStateException,
	 IOException {
		 JSONArray result = null;
			String sql = "select * from "+tableName;
			String url = null;
			ShowDB db = new ShowDB();
			String className = null;
			if ("Mysql".equals(filesource.getData_type())) {
					url = "jdbc:mysql://" + filesource.getIP() + ":"
							+ filesource.getPort() + "/"
							+ filesource.getData_name();
				className = classNameM;
			} else if ("Oracle".equals(filesource.getData_type())) {
			   url ="jdbc:oracle:thin:@"+filesource.getIP()+":"+ filesource.getPort()+":"+filesource.getData_name();
				className = classNameO;
			} else if ("Sqlserver".equals(filesource.getData_type())) {
				url ="jdbc:sqlserver://"+filesource.getIP()+":"+ filesource.getPort()+";DatabaseName="+filesource.getData_name();
				className = classNameS;
			}
			result = db.showDatabase(filesource, sql, url,className);
			return result;
 }
}
