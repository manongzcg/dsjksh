package cn.epi.sqlComponent.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.epi.common.service.CrudService;
import cn.epi.sqlComponent.dao.SjljDataresourceDao;
import cn.epi.sqlComponent.entity.SjljDataresource;


@Service
@Transactional(readOnly = true)
public class SjljDataresourceService extends CrudService<SjljDataresourceDao, SjljDataresource>{

}
