package cn.epi.port.service;

import cn.epi.common.JsonMapper;
import cn.epi.common.PortPage;
import cn.epi.datasource.dao.DataSourceOpDao;
import cn.epi.datasource.dao.IDatabaseDao;
import cn.epi.datasource.entity.DataSourceEntity;
import cn.epi.port.dao.IPortDao;
import cn.epi.port.entity.PortEntity;
import cn.epi.sys.dao.IUserDao;
import cn.epi.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据源
 * 
 * @author 
 */
@Service
@Transactional(readOnly = true)
public class PortalService {

    @Autowired
    private DataSourceOpDao dataSourceOpDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IPortDao portDao;

    @Autowired
    private IDatabaseDao databaseDao;

    /**
     * 查询api使用权限
     * @param username
     * @param apiKey
     * @return user OR null
     */
    public User checkAuth(String username, String apiKey) {
        User user = userDao.getUserByUserName(username);

        if (apiKey.equals(user.getApikey())) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * 查询接口调用权限
     * @param user
     * @param portID
     * @return port OR null
     */
    public PortEntity checkPermission(User user, String portID) {
        PortEntity port = portDao.get(portID);
        DataSourceEntity ds = databaseDao.get(port.getSourceId());

        List<String> roleIds = user.getRoleIds();

        if (roleIds.contains(ds.getRoleId())) {
            return port;
        } else {
            return null;
        }

    }

    /**
     * 执行服务
     * @param port
     * @param values
     * @return int
     */
    public int execPortal(PortEntity port, String[] values) {
        DataSourceEntity ds = databaseDao.get(port.getSourceId());
        int result = -1;

        int valNum = values.length;
        String sql = port.getCommitSql();
        int qusIndex = -1;

        for (int i = 0; i < valNum; i++) {
            qusIndex = sql.indexOf("?"); // Use ? to take spot.
            sql = sql.substring(0, qusIndex) + values[i] + sql.substring(qusIndex + 1, sql.length());
        }

        try {
            result = dataSourceOpDao.update(ds, sql.replaceAll("\r\n", " ").replaceAll("\n", " "));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 查询服务
     * @param port
     * @param values
     * @return JSON数据
     */
    public String queryPortal(PortEntity port, String[] values) {
        DataSourceEntity ds = databaseDao.get(port.getSourceId());
        String result = "EMPTY";

        int valNum = values.length;
        String sql = port.getCommitSql();
        
        int qusIndex = -1;

        for (int i = 0; i < valNum; i++) {
            qusIndex = sql.indexOf("?"); // Use ? to take spot.
            sql = sql.substring(0, qusIndex) + values[i] + sql.substring(qusIndex + 1, sql.length());
        }

        try {
            result = JsonMapper.toJsonString(dataSourceOpDao.select(ds, sql.replaceAll("\r\n", " ").replaceAll("\n", " ")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
   
    
    /**
     * 查询服务
     * @param port
     * @param values
     * @return JSON数据
     */
    public  List<HashMap<String,String>> querybean(PortEntity port,Map<String, String> param,String[] params) {
        DataSourceEntity ds = databaseDao.get(port.getSourceId());
        List<HashMap<String,String>> result =null;
        int valNum = param.size();
        String sql = port.getCommitSql();
        int qusIndex = -1;

        for (int i = 0; i < valNum; i++) {
            qusIndex = sql.indexOf("?"); // Use ? to take spot.
            sql = sql.substring(0, qusIndex) + param.get(param.get(params[i])) + sql.substring(qusIndex + 1, sql.length());
        }
        try {
        	 result = dataSourceOpDao.select(ds, sql.replaceAll("\r\n", " ").replaceAll("\n", " "));
        	
        
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    
    /**
     * 查询服务
     * @param port
     * @param values
     * @return JSON数据
     */
    public Map<String,String> queryPage(PortEntity port, String[] values,PortPage ppage) {
        DataSourceEntity ds = databaseDao.get(port.getSourceId());
        String result = "EMPTY";
        String total = null;
        Map<String ,String > map = new HashMap<String,String>();
        int valNum = values.length;
        String sql = port.getCommitSql();
       
        int qusIndex = -1;
        
        for (int i = 0; i < valNum; i++) {
            qusIndex = sql.indexOf("?"); // Use ? to take spot.
            sql = sql.substring(0, qusIndex) + values[i] + sql.substring(qusIndex + 1, sql.length());
        }
        String countSql="select count(1) as total from "+"("+sql+")"+"as zs";
        sql = sql+"limit "+((ppage.getPagenumber() - 1) * ppage.getPagesize() +1 )+","+ppage.getPagesize();
        try {
        	total = JsonMapper.toJsonString(dataSourceOpDao.select(ds, countSql.replaceAll("\r\n", " ").replaceAll("\n", " ")));
            result = JsonMapper.toJsonString(dataSourceOpDao.select(ds, sql.replaceAll("\r\n", " ").replaceAll("\n", " ")));
          
            map.put("total", total);
            map.put("result", result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }
    
    
   
    
    /**
     * 查询数据库类型
     * @param SourceId
     */
    public String checkdatype(String SourceId) {
    DataSourceEntity ds = databaseDao.get(SourceId);
    return ds.getType();
    }
}
