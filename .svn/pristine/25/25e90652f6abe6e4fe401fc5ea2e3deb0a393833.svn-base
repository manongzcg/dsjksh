package cn.epi.port.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.epi.common.BaseController;
import cn.epi.common.Page;
import cn.epi.common.utils.JStringUtils;
import cn.epi.common.utils.UserUtils;
import cn.epi.datasource.entity.DataSourceEntity;
import cn.epi.datasource.service.DatasourceService;
import cn.epi.port.entity.PortEntity;
import cn.epi.port.service.PortService;

@Controller
@RequestMapping("${adminPath}/port")
public class PortController extends BaseController{
		
	@Autowired
	private PortService portService;
	@Autowired
	private DatasourceService datasourceService;
	
	@ModelAttribute
	public PortEntity get(@RequestParam(required = false) String id) {
		PortEntity entity = null;
		if (JStringUtils.isNotBlank(id)) {
			entity = portService.get(id);
		}
		if (entity == null) {
			entity = new PortEntity();
		}
		return entity;
	}
	
	
	/**
	 * 接口
	 * @param model
	 * @param page
	 * @return
	 */
	@RequiresPermissions("port:port:view")
	@RequestMapping(value = "/list")
	public String list(PortEntity port,Model model, Page<PortEntity> page ) {
		List<PortEntity> portList = portService.findList(port);
		port.setUserName(UserUtils.getLoginUser().getUsername());
		model.addAttribute("portList", portList);
		page.setEntity(port);
		page.setOrderBy("");
		model.addAttribute("page", page.setList(portService.findPage(page)));
		return "port/port/list";
	}
	
	
	
	/**
	 * 接口新增
	 * @param model
	 * @return
	 */
	@RequiresPermissions("port:port:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(PortEntity port,Model model,DataSourceEntity datasource) {
		List<DataSourceEntity> datasourceList = datasourceService.findList(datasource);
		model.addAttribute("datasour", datasourceList);
		model.addAttribute("port", port);
		return "port/port/edit";
	}

	/**
	 * 接口新增保存
	 * @param port
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("port:port:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(PortEntity port, RedirectAttributes redirectAttributes) {
		port.setCreateDate(new Date());
		port.setUserName(UserUtils.getLoginUser().getUsername());
		if(port.getState().equals("1")){
			port.setFirstjava("");
			port.setFirstjavaname("");
			port.setLastjava("");
			port.setLastjavaname("");
		}
		portService.save(port);
		addMessage(redirectAttributes, "保存成功");
		 return "redirect:" + adminPath + "/port/update?id="+port.getId();
	}
	/**
	 * 接口修改
	 * @param port
	 * @param model
	 * @return
	 */
	@RequiresPermissions("port:port:update")
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(PortEntity port, Model model,DataSourceEntity datasource) {
		List<DataSourceEntity> datasourceList = datasourceService.findList(datasource);
		model.addAttribute("datasour", datasourceList);
		model.addAttribute("port", port);
		return "port/port/edit";
	}
	
	
	/**
	 * 接口修改保存
	 * @param port
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("port:port:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(PortEntity port, RedirectAttributes redirectAttributes) {
			port.setUpdateDate(new Date());
			if(port.getState().equals("1")){
				port.setFirstjava("");
				port.setFirstjavaname("");
				port.setLastjava("");
				port.setLastjavaname("");
			}
			portService.save(port);
			addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/port/update?id="+port.getId();
	}
	
	/**
	 * 接口删除
	 * @param port
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("port:port:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(PortEntity port,int pageNo,int pageSize,
                         RedirectAttributes redirectAttributes) {
				portService.delete(port);
			//CacheUtils.remove(datasource.getDatabaseName());
			addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/port/list?ID="+port.getId()+"&pageNo="+pageNo+"&pageSize="+pageSize;
	}
}
