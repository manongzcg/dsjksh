package cn.epi.datasource.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.epi.common.BaseController;
import cn.epi.common.Page;
import cn.epi.datasource.entity.DataSourceEntity;
import cn.epi.datasource.entity.FileSource;
import cn.epi.datasource.service.FDatasourceService;
import cn.epi.util.datasource.DBUtil;
import cn.epi.util.datasource.MasgReturn;
import cn.epi.util.datasource.ShowDB;

@Controller
public class DataSourceController extends BaseController {

	@Autowired
	private FDatasourceService datasourceService;
	private DBUtil dbutil;
	private MasgReturn messageReturn = new MasgReturn();

	/**
	 * 查询数据库和数据库中的表
	 * 
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/showDB")
	public @ResponseBody
	JSONObject showDB(HttpServletResponse response,Model model, HttpServletRequest request, FileSource filesource)
			throws IllegalStateException, IOException {
		// System.out.println(id);
		response.setHeader("Access-Control-Allow-Origin", "*");
		FileSource filesource1 = datasourceService.findAll(filesource.getData_resource_id());
		// System.out.println(filesource.getPassword());
		ShowDB db = new ShowDB();
		JSONArray result = db.showDB(filesource1);
		JSONObject jo = messageReturn.MassageReturn(result);
		return jo;
	}

	@RequestMapping(value = "/showTable")
	public @ResponseBody
	JSONArray showTable(HttpServletResponse response,Model model, HttpServletRequest request, FileSource filesource)
			throws IllegalStateException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = request.getSession();
		FileSource filesource1 = datasourceService.findAll(filesource.getData_resource_id());
		filesource1.setData_name(filesource.getData_name());
		// System.out.println(filesource.getPassword());
		ShowDB db = new ShowDB();
		JSONArray result = db.showTable(filesource1);
		return result;
	}

	/**
	 * 查询表
	 * 
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/showDesc")
	public @ResponseBody
	JSONArray showDesc(HttpServletResponse response,Model model, HttpServletRequest request, String tableName,FileSource filesource)
			throws IllegalStateException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		FileSource filesource1 = datasourceService.findAll(filesource.getData_resource_id());
		filesource1.setData_name(filesource.getData_name());
		ShowDB db = new ShowDB();
		JSONArray result = db.showDesc(filesource1, tableName);
		return result;
	}

	@RequestMapping(value = "/testConn")
	public @ResponseBody
	boolean testConn(Model model, FileSource filesource,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		boolean result = dbutil.testConnection(filesource);
		return result;
	}

	/**
	 * 数据源新增保存
	 * 
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveDB", method = RequestMethod.POST)
	public JSONObject create(FileSource datasource,
			RedirectAttributes redirectAttributes, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		datasourceService.save(datasource);

		JSONObject jo = messageReturn.MassageReturn(true);
		return jo;
	}

	/**
	 * 数据源修改更新
	 * 
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */

	@RequestMapping(value = "/updata", method = RequestMethod.POST)
	public String updata(FileSource datasource,
			RedirectAttributes redirectAttributes, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		datasourceService.save(datasource);

		return "保存成功";
	}

	/**
	 * 数据源删除
	 * 
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(FileSource datasource,
			RedirectAttributes redirectAttributes) {
		datasourceService.delete(datasource);
		// addMessage(redirectAttributes, "删除成功");
		// return "redirect:" + adminPath +
		// "/source/update?id="+datasource.getId();
		return "删除成功";
	}

	/**
	 * 数据源列表详细
	 * 
	 * @param model
	 * @param page
	 * @return
	 */

	@RequestMapping(value = "/listName")
	public @ResponseBody
	List listName(Model model, Page<DataSourceEntity> page,
			HttpServletResponse response) {
		List<FileSource> datasourceList = datasourceService.findListName();
		model.addAttribute("datasourceList", datasourceList);
		response.setHeader("Access-Control-Allow-Origin", "*");
		// page.setEntity(datasourceList);
		// page.setOrderBy("");
		// model.addAttribute("page",
		// page.setList(datasourceService.findPage(page)));
		return datasourceList;
	}

	/**
	 * 数据源详细信息
	 * 
	 * @param model
	 * @param page
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/DBInfo")
	public @ResponseBody
	FileSource list(FileSource datasource, Model model,
			Page<DataSourceEntity> page, HttpServletResponse response,
			HttpServletRequest request, String str) throws IOException {
		FileSource fs = datasourceService.findDesc(datasource
				.getData_resource_id());
		// model.addAttribute("datasourceList", datasourceList);
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println(fs.getData_resource_name());
		/*
		 * page.setEntity(datasource); page.setOrderBy("");
		 * model.addAttribute("page",
		 * page.setList(datasourceService.findPage(page)));
		 */
		return fs;
	}
}
