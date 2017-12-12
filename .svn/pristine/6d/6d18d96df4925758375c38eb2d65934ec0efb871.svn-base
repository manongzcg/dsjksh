package cn.epi.sys.dao;

import cn.epi.common.ICrudDao;
import cn.epi.common.annotation.MyBatisDao;
import cn.epi.sys.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限Dao接口
 * 
 * @author cc
 */
@MyBatisDao
public interface IRoleDao extends ICrudDao<Role> {

    /**
     * 获取权限资源ID
     * @param roleIds
     * @return
     */
    public List<Role> getRoles(@Param("roleIds") String[] roleIds);
    
  
    /**
	 * 查询数据列表
	 * 
	 * @param entity
	 * @return
	 */
	public List<Role> roleList(Role entity);

}
