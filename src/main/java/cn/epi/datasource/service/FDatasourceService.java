package cn.epi.datasource.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.epi.common.service.CrudService;
import cn.epi.datasource.dao.FDatabaseDao;
import cn.epi.datasource.entity.FileSource;

/**
 * 数据源
 * 
 * @author 
 */
@Service
@Transactional(readOnly = true)
public class FDatasourceService extends CrudService<FDatabaseDao, FileSource> {

	  /**
     * 重命名
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public int changeName(FileSource entity) {
            return dao.insert(entity);
       
    }
    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public int update(FileSource entity) {
    
            return dao.update(entity);
        
    }
    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public int updateFile(FileSource entity) {
    
            return dao.updateFile(entity);
        
    }
}
