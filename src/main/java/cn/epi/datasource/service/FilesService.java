package cn.epi.datasource.service;

import java.util.List;

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


	 /**
    * 获取文件名
    *
    * @param id
    * @return
    */
   public List<Files> getName(int id) {
       return dao.getName(id);
   }
}
