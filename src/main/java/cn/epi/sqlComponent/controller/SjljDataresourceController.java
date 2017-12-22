package cn.epi.sqlComponent.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.epi.common.BaseController;
import cn.epi.common.utils.JStringUtils;
import cn.epi.datasource.service.DatasourceService;
import cn.epi.sqlComponent.entity.SjljDataresource;
import cn.epi.sqlComponent.service.SjljDataresourceService;


@Controller
@RequestMapping("${adminPath}/sjljDataresource")
public class SjljDataresourceController extends BaseController{
	
@Autowired
private SjljDataresourceService sjljDataresourceService;
@Autowired
private DatasourceService datasourceService;

@ModelAttribute
public SjljDataresource get(@RequestParam(required = false) String id) {
	SjljDataresource entity = null;
	if (JStringUtils.isNotBlank(id)) {
		entity = sjljDataresourceService.get(id);
	}
	if (entity == null) {
		entity = new SjljDataresource();
	}
	return entity;
}

@ModelAttribute
public SjljDataresource get(SjljDataresource sd) {
	SjljDataresource entity = null;
	if (sd !=null) {
		entity = sjljDataresourceService.get(sd);
	}
	if (entity == null) {
		entity = new SjljDataresource();
	}
	return entity;
}

}
