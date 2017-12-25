package cn.epi.datasource.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.epi.common.service.CrudService;
import cn.epi.datasource.dao.IDatabaseDao;
import cn.epi.datasource.entity.DataSourceEntity;
import cn.epi.datasource.entity.FileSource;
import cn.epi.sys.dao.IOrganizationDao;
import cn.epi.sys.entity.Organization;

/**
 * 数据源
 * 
 * @author 
 */
@Service
@Transactional(readOnly = true)
public class DatasourceService extends CrudService<IDatabaseDao, DataSourceEntity> {
	
	
}
