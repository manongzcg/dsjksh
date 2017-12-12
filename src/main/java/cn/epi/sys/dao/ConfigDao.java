package cn.epi.sys.dao;

import cn.epi.common.ICrudDao;
import cn.epi.common.annotation.MyBatisDao;
import cn.epi.sys.entity.Config;

/**
* 公共配置表 DAO接口
* @author iutils.cn
* @version 1.0
*/
@MyBatisDao
public interface ConfigDao extends ICrudDao<Config> {

}
