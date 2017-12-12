package cn.epi.datasource.dao;

import cn.epi.common.JsonMapper;
import cn.epi.common.utils.ds.DBPooledConnection;
import cn.epi.common.utils.ds.DBUtil;
import cn.epi.datasource.entity.DB_TYPE;
import cn.epi.datasource.entity.DataSourceEntity;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;


/**
 * 数据源动态操作类
 * Created by mwu on 5/15/17 10:28 AM.
 */

@Repository
public class DataSourceOpDao  {

    /**
     * 创建数据源
     * @param ds
     * @param conditions 创建数据库附加条件，为空时传入空字符串
     * @return
     * @throws Exception
     */
    public boolean CreateDataSource(DataSourceEntity ds, String conditions) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection(DB_TYPE.enumFromString(ds.getType()), ds.getHost(),
                    ds.getPort(), ds.getSourceUser(), ds.getPassword());

            String createDBSQL = "CREATE DATABASE " + ds.getDatabaseName() + " " + conditions;
            Statement st = conn.createStatement();

            st.executeUpdate(createDBSQL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        // 测试是否可连接
        boolean connect = DBUtil.testConnection(DB_TYPE.enumFromString(ds.getType()), ds.getHost(),
                ds.getPort(), ds.getDatabaseName(), ds.getSourceUser(), ds.getPassword());

        if (connect == true) {
            DBPooledConnection link = DBPooledConnection.getInstance();
            DruidPooledConnection linkConn = null;

            try {
                linkConn = link.getConn(DB_TYPE.enumFromString(ds.getType()), ds.getHost(),
                        ds.getPort(), ds.getDatabaseName(), ds.getSourceUser(), ds.getPassword(),
                        ds.getInitialSize(), ds.getMinIdle(), ds.getMaxActive(), ds.getMaxWait());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (linkConn != null) {
                    try {
                        linkConn.recycle();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 删除数据源（清空数据库）
     * @param ds
     * @return
     * @throws Exception
     */
    public boolean DropDataSource(DataSourceEntity ds) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection(DB_TYPE.enumFromString(ds.getType()), ds.getHost(),
                    ds.getPort(), ds.getSourceUser(), ds.getPassword());

            String createDBSQL = "DROP DATABASE " + ds.getDatabaseName();
            Statement st = conn.createStatement();

            st.executeUpdate(createDBSQL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 测试是否可连接
        boolean connect = DBUtil.testConnection(DB_TYPE.enumFromString(ds.getType()), ds.getHost(),
                ds.getPort(), ds.getDatabaseName(), ds.getSourceUser(), ds.getPassword());

        if (connect == true) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 处理插入、更新或删除数据
     * @param ds
     * @param sql
     * @return 执行状态
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int update(DataSourceEntity ds, String sql) throws SQLException {
        DBPooledConnection pool = DBPooledConnection.getInstance();
        DruidPooledConnection conn = pool.getConn(DB_TYPE.enumFromString(ds.getType()), ds.getHost(),
                ds.getPort(), ds.getDatabaseName(), ds.getSourceUser(), ds.getPassword(),
                ds.getInitialSize(), ds.getMinIdle(), ds.getMaxActive(), ds.getMaxWait());

        int result = -5;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        result = pstmt.executeUpdate();

        conn.recycle();

        return result;

    }

    /**
     * 处理查询数据
     * @param ds
     * @param sql
     * @return 返回JSON
     * @throws SQLException
     */
    public List<HashMap<String,String>> select(DataSourceEntity ds, String sql) throws SQLException {
        // Get all column names
        int selectIndex = new String("SELECT").length();
        int fromIndex = -2;

        selectIndex += sql.toUpperCase().indexOf("SELECT");
        fromIndex = sql.toUpperCase().indexOf("FROM");

        String rawColumns = sql.substring(selectIndex, fromIndex);
        rawColumns = rawColumns.replace(" ", "");
        String[] columns = rawColumns.split(",");

        for (int i = 0; i < columns.length; i++) {
            int asIndex = columns[i].toUpperCase().indexOf("AS");
            if (asIndex > -1) {
                columns[i] = columns[i].substring(asIndex + 2, columns[i].length());
            }
        }
        return this.doSelect(ds, sql, columns);
        
    }

    private List<HashMap<String,String>> doSelect(DataSourceEntity ds,String sql,String[] columns) throws SQLException {
    	int columnNumber = columns.length;
    	// Do query
        DBPooledConnection pool = DBPooledConnection.getInstance();
        DruidPooledConnection conn = pool.getConn(DB_TYPE.enumFromString(ds.getType()), ds.getHost(),
                ds.getPort(), ds.getDatabaseName(), ds.getSourceUser(), ds.getPassword(),
                ds.getInitialSize(), ds.getMinIdle(), ds.getMaxActive(), ds.getMaxWait());

        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        
        List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        while (rs.next()) {
            HashMap<String, String> record = new HashMap<String, String>();
            for (int i = 0; i < columnNumber; i++) {
                record.put(columns[i], rs.getString(i+1));
            }
            result.add(record);
        }
        DBPooledConnection.releaseAll(rs, pstmt, conn);
    	return result;
    }
}
