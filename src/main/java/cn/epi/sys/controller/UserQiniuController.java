package cn.epi.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.epi.common.BaseController;
import cn.epi.common.utils.JStringUtils;
import cn.epi.common.utils.UserUtils;
import cn.epi.sys.entity.User;
import cn.epi.sys.entity.UserQiniu;
import cn.epi.sys.service.UserQiniuService;

/**
 * 用户七牛配置 控制器
 * 
 * @author MyCode
 * @version 1.0
 */
@Controller
@RequestMapping("${adminPath}/user/qiniu")
public class UserQiniuController extends BaseController {

	@Autowired
	private UserQiniuService userQiniuService;

	@ModelAttribute
	public UserQiniu get(@RequestParam(required = false) String id) {
		UserQiniu entity = null;
		if (JStringUtils.isNotBlank(id)) {
			entity = userQiniuService.get(id);
		}
		if (entity == null) {
			entity = new UserQiniu();
		}
		return entity;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateForm(Model model) {
		User user = UserUtils.getLoginUser();
		UserQiniu userQiniu = userQiniuService.getByUser(user.getId());
		if (userQiniu == null) {
			userQiniu = new UserQiniu();
		}
		model.addAttribute("userQiniu", userQiniu);
		return "sys/user/config-qiniu";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(UserQiniu userQiniu,
			RedirectAttributes redirectAttributes) {
		userQiniuService.save(userQiniu);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/user/qiniu/create";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(UserQiniu userQiniu,
			RedirectAttributes redirectAttributes) {
		userQiniuService.save(userQiniu);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/user/qiniu/create";
	}

}
