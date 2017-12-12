package cn.epi.datasource.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import cn.epi.common.BaseController;
import cn.epi.common.Page;
import cn.epi.datasource.entity.DataSourceEntity;
import cn.epi.datasource.entity.FileSource;
import cn.epi.datasource.service.DatasourceService;
import cn.epi.datasource.service.FDatasourceService;
import cn.epi.util.excel.ShowCSVUtil;
import cn.epi.util.excel.ShowExcelUtil;

@Controller
public class FileSourceController extends BaseController{

	@Autowired
	private FDatasourceService datasourceService;

	/**
	 * 文件上传
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@ResponseBody
	@RequestMapping(value = "/fileupload")
	public String list(MultipartFile file, Model model,
			HttpServletRequest request/* ,FileSource filesource */)
			throws IllegalStateException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String res = sdf.format(new Date());

		// uploads文件夹位置
		String rootPath = request.getSession().getServletContext()
				.getRealPath("resource/uploads/");
		// 原始名称
		String originalFileName = file.getOriginalFilename();
		// 新文件名
		String newFileName = "sliver" + res
				+ originalFileName.substring(originalFileName.lastIndexOf("."));
		// 创建年月文件夹
		Calendar date = Calendar.getInstance();
		File dateDirs = new File(date.get(Calendar.YEAR) + File.separator
				+ (date.get(Calendar.MONTH) + 1));

		// 新文件
		File newFile = new File(rootPath + File.separator + dateDirs
				+ File.separator + newFileName);
		// 判断目标文件所在目录是否存在
		if (!newFile.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			newFile.getParentFile().mkdirs();
		}
		System.out.println(newFile);
		// 将内存中的数据写入磁盘
		file.transferTo(newFile);
		// 完整的url
		String fileUrl = date.get(Calendar.YEAR) + "/"
				+ (date.get(Calendar.MONTH) + 1) + "/" + newFileName;
		/*
		 * filesource.setUrl(fileUrl); filesource.setFilename(newFileName);
		 * model.addAttribute("filesource", filesource);
		 */
		return fileUrl;
	}

	@RequestMapping(value = "/fileremove")
	public String move(Model model, HttpServletRequest request,
			FileSource datasource) throws IllegalStateException,
			IOException {
		File file = new File(datasource.getIP());
		if (file.isFile() && file.exists()) {
			file.delete();
			return "已删除";
		} else {
			return "没有路径";
		}

	}

	@RequestMapping(value = "/showExcel")
	public LinkedHashMap<String, String> showExcel(Model model,
			HttpServletRequest request, FileSource datasource)
			throws IllegalStateException, IOException {
		ShowExcelUtil showExcel = new ShowExcelUtil();
		LinkedHashMap<String, String> excelMap = showExcel.excel2json(datasource);
		return excelMap;
	}

	@RequestMapping(value = "/showCSV")
	public JSONArray showCSV(Model model, HttpServletRequest request,
			FileSource datasource) throws IllegalStateException,
			IOException {
		JSONArray array = new JSONArray();
		String filepath = datasource.getIP();
		ShowCSVUtil util = new ShowCSVUtil();
		array = util.readcsv(filepath);

		return array;
	}
	/**
	 * 文件数据保存
	 * @param datasource
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String upload(FileSource datasource,
                         RedirectAttributes redirectAttributes) {
		//转移文件到其他目录
		String path = datasource.getIP();
		 File afile = new File(path);
		 path = " D:/data/" + afile.getName();
		 afile.renameTo(new File(path));
		 datasource.setIP(path);
			datasourceService.save(datasource);
			
			//addMessage(redirectAttributes, "保存成功");
			//return "redirect:" + adminPath + "/source/update?id="+datasource.getId();
		return "保存成功";
	}		
	/**
	 * 数据源新增保存
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(FileSource datasource, RedirectAttributes redirectAttributes) {
		datasourceService.save(datasource);
		//addMessage(redirectAttributes, "保存成功");
		//return "redirect:" + adminPath + "/source/update?id="+datasource.getId();
		 return "保存成功";
	}
	/**
	 * 数据源修改更新
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequestMapping(value = "/updata", method = RequestMethod.POST)
	public String updata(FileSource datasource, RedirectAttributes redirectAttributes) {
		datasourceService.save(datasource);
		//addMessage(redirectAttributes, "修改成功");
		 //return "redirect:" + adminPath + "/source/update?id="+datasource.getId();
		return "保存成功";
	}
	/**
	 * 数据源删除
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(FileSource datasource, RedirectAttributes redirectAttributes) {
		datasourceService.delete(datasource);
//		addMessage(redirectAttributes, "删除成功");
//		 return "redirect:" + adminPath + "/source/update?id="+datasource.getId();
		return "删除成功";
	}
	/**
	 * 数据源列表详细
	 * @param model
	 * @param page
	 * @return
	 */
	
	@RequestMapping(value = "/listName")
	public @ResponseBody List listName(Model model, Page<DataSourceEntity> page ) {
		List<FileSource> datasourceList = datasourceService.findListName();
//		model.addAttribute("datasourceList", datasourceList);
//		page.setEntity(datasourceList);
//		page.setOrderBy("");
//		model.addAttribute("page", page.setList(datasourceService.findPage(page)));
		return datasourceList;
	}
	
	/**
	 * 数据源详细
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public @ResponseBody List list(FileSource datasource,Model model, Page<DataSourceEntity> page ) {
		List<FileSource> datasourceList = datasourceService.findDesc(datasource.getData_resource_name());
		//model.addAttribute("datasourceList", datasourceList);
		/*page.setEntity(datasource);
		page.setOrderBy("");
		model.addAttribute("page", page.setList(datasourceService.findPage(page)));*/
		return datasourceList;
	}
	
}
