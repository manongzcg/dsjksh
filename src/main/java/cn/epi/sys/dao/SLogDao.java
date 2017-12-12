package cn.epi.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.epi.common.ICrudDao;
import cn.epi.common.Page;
import cn.epi.common.annotation.MyBatisDao;
import cn.epi.sys.entity.SLog;

/**
 * 日志记录 DAO接口
 * 
 * @author MyCode
 * @version 1.0
 */
@MyBatisDao
public interface SLogDao extends ICrudDao<SLog> {

	/**
	 * 批量添加记录
	 * 
	 * @param logs
	 * @return
	 */
	public int saveBatch(@Param("logs") List<SLog> logs);

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int deleteAll(@Param("ids") String[] ids);
	
	/**
	 * 查询分页数据
	 * 
	 * @param page
	 * @return
	 */
	public List<SLog> findPage1(@Param("page") Page<SLog> page);
	
	
	/**
	 * 查询总数
	 * 
	 * @return
	 */
	public int count1(@Param("page") Page<SLog> page);
}
