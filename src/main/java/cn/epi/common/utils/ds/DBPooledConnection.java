package cn.epi.common.utils.ds;

import cn.epi.datasource.entity.DB_TYPE;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库连接池管理连接
 * Created by mwu on 5/15/17 11:20 PM.
 */
public class DBPooledConnection {

    private static DBPooledConnection dbConn = null;
    private static Map<String, DruidDataSource> dsMap = new HashMap<String, DruidDataSource>();

    public static synchronized DBPooledConnection getInstance() {
        if (dbConn == null) {
           dbConn = new DBPooledConnection();
        }

        return dbConn;
    }

    public DruidPooledConnection getConn(DB_TYPE dbType, String host, String port,
                                         String database, String username, String password,
                                         String initialSize, String minIdle, String maxActive, String maxWait) {
        String url = "";

        switch (dbType) {
            case ORACLE:
                url = "jdbc:oracle:thin:" + host + ":" + port + ":" + database;
                break;
            case MYSQL:
                url = "jdbc:mysql://" + host + ":" + port + "/" + database;
                break;
        }

        // Check connection existence.
        String key = url + username + password;
        DruidDataSource ds = dsMap.get(key);
        if (ds != null) {
            try {
                return ds.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        Map<String, String> connInfo = new HashMap<String, String>();
        connInfo.put("url", url);
        connInfo.put("username", username);
        connInfo.put("password", password);
        connInfo.put("initialSize", initialSize);
        connInfo.put("minIdle", minIdle);
        connInfo.put("maxActive", maxActive);
        connInfo.put("maxWait", maxWait);

        DruidDataSource newDS = null;
        try {
            newDS = (DruidDataSource) DruidDataSourceFactory.createDataSource(connInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dsMap.put(key, newDS);

        try {
            return newDS.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void releaseAll(ResultSet rs, Statement st, DruidPooledConnection conn) throws SQLException {
        try {
            if (rs != null) {
                rs.close();
            }
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } finally {
                if (conn != null) {
                    conn.recycle();
                }
            }
        }
    }
}
