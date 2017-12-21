package cn.epi.common;

import org.apache.ibatis.annotations.Param;

import cn.epi.datasource.entity.FileSource;
import cn.epi.datasource.entity.Files;

import java.util.List;

/**
 * DAO支持类实现
 * 
 * @author cc
 * @param <T>
 */
public interface ICrudDao<T> extends IBaseDao {

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public T get(String id);

	/**
	 * 获取单条数据
	 * 
	 * @param entity
	 * @return
	 */
	public T get(T entity);
	/**
	 * 查询数据源列表
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findListName();

	/**
	 * 查询数据列表
	 * 
	 * @param entity
	 * @return
	 */
	public FileSource findDesc(Object id);

	/**
	 * 查询所有数据
	 * 
	 * @param entity
	 * @return
	 */
	public FileSource findAll(Object id);
	/**
	 * 查询数据列表
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	/**
	 * 查询数据列表
	 * 
	 * @param entity
	 * @return
	 */
	public int findID(String name);
	/**
	 * 查询总数
	 * 
	 * @return
	 */
	public int count(@Param("page") Page<T> page);

	/**
	 * 查询分页数据
	 * 
	 * @param page
	 * @return
	 */
	public List<T> findPage(@Param("page") Page<T> page);

	
	/**
	 * 插入数据
	 * 
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * 更新数据
	 * 
	 * @param entity
	 * @return
	 */
	public int update(T entity);

	/**
	 * 删除数据
	 * 
	 * @param entity
	 * @return
	 */
	public int delete(T entity);

	/**
	 * 删除数据
	 * 
	 * @param id
	 * @return
	 */
	public int delete(String id);

}