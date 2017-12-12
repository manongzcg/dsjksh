package cn.epi.common.utils.ds;

import cn.epi.datasource.entity.DB_TYPE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC管理连接
 * Created by mwu on 5/11/17 3:43 PM.
 */



public class DBUtil {

    public static Connection getConnection(DB_TYPE dbType, String host, String port,
                                           String username, String password) throws ClassNotFoundException, SQLException {
        String url = "";
        String driver = "";

        switch (dbType) {
            case ORACLE:
                url = "jdbc:oracle:thin:" + host + ":" + port;
                driver = "oracle.jdbc.driver.OracleDriver";
                break;
            case MYSQL:
                url = "jdbc:mysql://" + host + ":" + port + "/";
                driver = "com.mysql.jdbc.Driver";
                break;
            case MONGO:
                url = "jdbc:mongo://" + host + ":" + port + "/";
                driver = "mongodb.jdbc.MongoDriver";
                break;
        }

        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);

    }

    public static boolean testConnection(DB_TYPE dbType, String host, String port,
                                           String database, String username, String password) {
        String url = "";
        String driver = "";

        switch (dbType) {
            case ORACLE:
                url = "jdbc:oracle:thin:" + host + ":" + port + ":" + database;
                driver = "oracle.jdbc.driver.OracleDriver";
                break;
            case MYSQL:
                url = "jdbc:mysql://" + host + ":" + port + "/" + database;
                driver = "com.mysql.jdbc.Driver";
                break;
            case MONGO:
                url = "jdbc:mongo://" + host + ":" + port + "/" + database;;
                driver = "mongodb.jdbc.MongoDriver";
                break;
        }

        Connection conn = null;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
             conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;

    }
}
