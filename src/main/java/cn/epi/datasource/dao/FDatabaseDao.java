package cn.epi.datasource.dao;

import cn.epi.common.ICrudDao;
import cn.epi.common.annotation.MyBatisDao;
import cn.epi.datasource.entity.DataSourceEntity;
import cn.epi.datasource.entity.FileSource;

/**
 * Created by mwu on 5/11/17 5:05 PM.
 */

@MyBatisDao
public interface FDatabaseDao extends ICrudDao<FileSource> {
	
	/**
	 * 重命名                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	 * 
	 * @param entity
	 * @return
	 */
	public int changeName(FileSource filesource);
	/**
	 * 更新文件数据源                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	 * 
	 * @param entity
	 * @return
	 */
	public int updateFile(FileSource filesource);
}
