package cn.epi.datasource.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.Response;

import cn.epi.common.BaseController;
import cn.epi.common.Page;
import cn.epi.datasource.entity.DataSourceEntity;
import cn.epi.datasource.entity.FileSource;
import cn.epi.datasource.entity.Files;
import cn.epi.datasource.entity.TableDBEntity;
import cn.epi.datasource.service.DatasourceService;
import cn.epi.datasource.service.FDatasourceService;
import cn.epi.datasource.service.FilesService;
import cn.epi.datasource.service.TableDBService;
import cn.epi.util.datasource.CreateTable;
import cn.epi.util.datasource.DBUtil;
import cn.epi.util.excel.ShowCSVUtil;
import cn.epi.util.excel.ShowExcelUtil;

@Controller
public class FileSourceController extends BaseController {

	@Autowired
	private FDatasourceService datasourceService;
	private FilesService fileservice;
	private TableDBService tableDBs;

	/**
	 * 文件上传
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@ResponseBody
	@RequestMapping(value = "/fileupload")
	public JSONObject fileupload(HttpServletResponse response,MultipartFile file, Model model,
			HttpServletRequest request) throws IllegalStateException,
			IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, OPTIONS");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String res = sdf.format(new Date());
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject joA = new JSONObject();
		// uploads文件夹位置
		String rootPath = request.getSession().getServletContext()
				.getRealPath("resource/uploads/");
		// 原始名称
		String originalFileName = file.getOriginalFilename();
		// 新文件名
		String newFileName = res
				+ originalFileName.substring(originalFileName.lastIndexOf("."));
		jo.put("name", originalFileName);
		jo.put("key", newFileName);
		ja.add(jo);

		String fileType = originalFileName.substring(
				originalFileName.lastIndexOf(".") + 1,
				originalFileName.length()).toLowerCase();
		JSONObject show = new JSONObject();
		// 新文件
		File newFile = new File(rootPath + newFileName);
		if ("csv".equals(fileType)) {
		ShowCSVUtil showCSV = new ShowCSVUtil();
		showCSV.readcsv(rootPath + newFileName);
		} else {
			ShowExcelUtil showExcel = new ShowExcelUtil();
			show = showExcel.excel2json(newFile);
		}
		joA.put("files", ja);
		joA.put("options", show);
		HttpSession session = request.getSession();
		session.setAttribute("result", joA);
		// System.out.println(newFile);
		// // 将内存中的数据写入磁盘
		file.transferTo(newFile);
		// 完整的url
		// String fileUrl = date.get(Calendar.YEAR) + "/"
		// + (date.get(Calendar.MONTH) + 1) + "/" + newFileName;
		/*
		 * filesource.setUrl(fileUrl); filesource.setFilename(newFileName);
		 * model.addAttribute("filesource", filesource);
		 */
		return joA;
	}

	@RequestMapping(value = "/fileremove")
	public String move(Model model, HttpServletRequest request,
			FileSource datasource) throws IllegalStateException, IOException {
		File file = new File(datasource.getIP());
		if (file.isFile() && file.exists()) {
			file.delete();
			return "已删除";
		} else {
			return "没有路径";
		}

	}


	/**
	 * 文件数据保存建表
	 * 
	 * @param datasource
	 * @param redirectAttributes
	 * @return
	 */

	@RequestMapping(value = "/saveFile", method = RequestMethod.GET)
	public boolean saveFile(HttpServletResponse response,FileSource datasource,
			RedirectAttributes redirectAttributes, Files files,
			HttpServletRequest request) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		TableDBEntity tableDBe = new TableDBEntity();
		CreateTable createtable = new CreateTable();
		datasourceService.save(datasource);
		int id = datasourceService.findID(datasource.getData_resource_name());
		HttpSession session = request.getSession();
		JSONObject jo = (JSONObject) session.getAttribute("result");
		JSONObject jsonArr_sheet;// 接受json数组
		JSONArray jsonArr_file;// 接受json数组files
		JSONObject json_mate;// 接收json
		jsonArr_file = jo.getJSONArray("files");
		// 获取根路径
		String rootPath = request.getSession().getServletContext()
				.getRealPath("resource/uploads/");
		// 获取时间
		Calendar date = Calendar.getInstance();
		for (int i = 0; i < jsonArr_file.size(); i++)// 通过循环取出数组里的值
		{
			JSONObject jsonTemp = (JSONObject) jsonArr_file.getJSONObject(i);
			String key = jsonTemp.getString("key");
			String name = jsonTemp.getString("name");
			// 创建年月文件夹
			File dateDirs = new File(date.get(Calendar.YEAR) + File.separator
					+ (date.get(Calendar.MONTH) + 1));
			File newFile = new File(rootPath + key);
			// 转移到指定路径
			String path = " D:/data/" + dateDirs + newFile.getName();
			newFile.renameTo(new File(path));
			// 判断目标文件所在目录是否存在
			if (!newFile.getParentFile().exists()) {
				// 如果目标文件所在的目录不存在，则创建父目录
				newFile.getParentFile().mkdirs();
			}
			files.setUrl(path);
			files.setNew_name(key);
			files.setOld_name(name);
			files.setData_resource_id(id);
			fileservice.save(files);
		}
		jsonArr_sheet = jo.getJSONObject("options");
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
	     String res = sdf.format(new Date());
	     boolean result = false ;
		if ("CSV".equals(datasource.getData_type())) {
			 json_mate = jsonArr_sheet.getJSONObject("mate");
			 String  table_name ="C_"+res; 
			 result = createtable.createETable(json_mate,table_name);
		     tableDBe.setTable_name(table_name);
		     tableDBe.setData_resource_id(id);
		     tableDBs.save(tableDBe);
		     
		} else {
			 
			for (Iterator it =  jsonArr_sheet.keySet().iterator();it.hasNext();)
			   {
			    Object key = it.next();
			    JSONObject json_table = (JSONObject) jsonArr_sheet.get(key);
			     json_mate = json_table.getJSONObject("mate");
			     String  table_name = "E_"+res;
			     result =  createtable.createETable(json_mate,table_name);
			     tableDBe.setTable_name(table_name);
			     tableDBe.setData_resource_id(id);
			     tableDBs.save(tableDBe);
			     }
			
		}
		return result;
	}

}
