package cn.epi.datasource.controller;

import java.io.File;
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
import cn.epi.common.utils.CacheUtils;
import cn.epi.common.utils.JStringUtils;
import cn.epi.datasource.entity.DataSourceEntity;
import cn.epi.datasource.entity.FileSource;
import cn.epi.datasource.service.DatasourceService;
import cn.epi.datasource.service.FDatasourceService;
import cn.epi.sys.entity.Role;
import cn.epi.sys.service.RoleService;


@Controller
@RequestMapping("${adminPath}/source")
public class SourceController  extends BaseController{

	@Autowired
	private DatasourceService datasourceService;
	@Autowired
	private RoleService roleService;
	
	
	@ModelAttribute
	public DataSourceEntity get(@RequestParam(required = false) String id) {
		DataSourceEntity entity = null;
		if (JStringUtils.isNotBlank(id)) {
			entity = datasourceService.get(id);
		}
		if (entity == null) {
			entity = new DataSourceEntity();
		}
		return entity;
	}
	
	/**
	 * 数据源
	 * @param model
	 * @param page
	 * @return
	 */
	@RequiresPermissions("source:source:view")
	@RequestMapping(value = "/list")
	public String list(DataSourceEntity datasource,Model model, Page<DataSourceEntity> page ) {
		List<DataSourceEntity> datasourceList = datasourceService.findList(datasource);
		model.addAttribute("datasourceList", datasourceList);
		page.setEntity(datasource);
		page.setOrderBy("");
		model.addAttribute("page", page.setList(datasourceService.findPage(page)));
		return "source/source/list";
	}
	
	/**
	 * 数据源新增
	 * @param model
	 * @return
	 */
	@RequiresPermissions("source:source:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(DataSourceEntity datasource,Role role,Model model) {
		List<Role> roleList = roleService.roleList(role);
		model.addAttribute("role", roleList);
		model.addAttribute("datasource", datasource);
		return "source/source/edit";
	}

	/**
	 * 数据源新增保存
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("source:source:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(DataSourceEntity datasource, RedirectAttributes redirectAttributes) {
		datasourceService.save(datasource);
		addMessage(redirectAttributes, "保存成功");
		 return "redirect:" + adminPath + "/source/update?id="+datasource.getId();
	}
	/**
	 * 数据源修改
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("source:source:update")
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(DataSourceEntity datasource, Role role,Model model) {
		List<Role> roleList = roleService.roleList(role);
		model.addAttribute("role", roleList);
		model.addAttribute("datasource", datasource);
		return "source/source/edit";
	}
	
	
	/**
	 * 数据源修改保存
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("source:source:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(DataSourceEntity datasource, RedirectAttributes redirectAttributes) {
			datasourceService.save(datasource);
			addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/source/update?id="+datasource.getId();
	}
	
	
	
	/**
	 * 数据源删除
	 * @param datasource
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("source:source:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(DataSourceEntity datasource,int pageNo,int pageSize,
                         RedirectAttributes redirectAttributes) {
			datasourceService.delete(datasource);
			CacheUtils.remove(datasource.getDatabaseName());
			addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/source/list?ID="+datasource.getId()+"&pageNo="+pageNo+"&pageSize="+pageSize;
	}
	
	
}

