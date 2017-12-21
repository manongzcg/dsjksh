package cn.epi.datasource.dao;

import cn.epi.common.ICrudDao;
import cn.epi.common.annotation.MyBatisDao;
import cn.epi.datasource.entity.DataSourceEntity;
import cn.epi.datasource.entity.FileSource;
import cn.epi.datasource.entity.Files;

/**
 * Created by mwu on 5/11/17 5:05 PM.
 */

@MyBatisDao
public interface FilesDao extends ICrudDao<Files> {
	
}
