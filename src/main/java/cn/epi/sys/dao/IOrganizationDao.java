package cn.epi.sys.dao;

import cn.epi.common.ICrudDao;
import cn.epi.common.annotation.MyBatisDao;
import cn.epi.sys.entity.Organization;

/**
 * 组织机构Dao
 * 
 * @author cc
 */
@MyBatisDao
public interface IOrganizationDao extends ICrudDao<Organization> {

    /**
     * 查询是否存在下级节点
     * @param organization
     * @return
     */
    public int findNext(Organization organization);

}
