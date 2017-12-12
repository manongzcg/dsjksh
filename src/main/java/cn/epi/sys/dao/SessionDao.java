package cn.epi.sys.dao;

import cn.epi.common.ICrudDao;
import cn.epi.common.annotation.MyBatisDao;
import cn.epi.sys.entity.Session;

/**
* session管理 DAO接口
* @author iutils.cn
* @version 1.0
*/
@MyBatisDao
public interface SessionDao extends ICrudDao<Session> {

}
