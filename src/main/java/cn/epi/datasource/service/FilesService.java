package cn.epi.datasource.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.epi.common.service.CrudService;
import cn.epi.datasource.dao.FilesDao;
import cn.epi.datasource.entity.Files;

/**
 * 数据源
 * 
 * @author 
 */
@Service
@Transactional(readOnly = true)
public class FilesService extends CrudService<FilesDao, Files> {


}
