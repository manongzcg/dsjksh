package cn.epi.port.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.epi.common.BaseController;
import cn.epi.common.Page;
import cn.epi.common.utils.JStringUtils;
import cn.epi.common.utils.ds.ApiKey;
import cn.epi.sys.entity.User;
import cn.epi.sys.service.UserService;


@Controller
@RequestMapping("${adminPath}/apikey")
public class ApiKeyController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public User get(@RequestParam(required = false) String id) {
		User entity = null;
		if (JStringUtils.isNotBlank(id)) {
			entity = userService.get(id);
		}
		if (entity == null) {
			entity = new User();
		}
		return entity;
	}
	
	
	/**
	 * 用户列表
	 * @param model
	 * @param page
	 * @return
	 */
	@RequiresPermissions("apikey:apikey:view")
	@RequestMapping(value = "/list")
	public String list(User user,Model model, Page<User> page) {
		user.setOrderBy("");
        page.setEntity(user);
		model.addAttribute("page", page.setList(userService.findPage(page)));
		return "port/apikey/list";
	}
	
	
	/**
	 * 接口修改保存
	 * @param port
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:update")
	@RequestMapping(value = "/update")
	public String update(User user, RedirectAttributes redirectAttributes) {
			String apikey = ApiKey.getCode(8,5);
			user.setApikey(apikey);
			userService.save(user);
			return "redirect:" + adminPath + "/apikey/list";
	}
}
