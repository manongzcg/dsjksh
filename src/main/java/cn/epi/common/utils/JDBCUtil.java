package cn.epi.common.utils;

import java.sql.*;

/**
 * Created by mwu on 5/11/17 3:43 PM.
 */



public class JDBCUtil {

    public enum DB_TYPE {
        ORACLE,
        MYSQL,
        MONGO
    }

    public static Connection getConnection(DB_TYPE dbType, String host, String port,
                                           String database, String username, String password) throws ClassNotFoundException, SQLException {
        String url = "";
        String driver = "";

        switch (dbType) {
            case ORACLE:
                url = "jdbc:oracle:thin:" + host + ":" + port;
                if (database != null) {
                    url += ":" + database;
                }
                driver = "oracle.jdbc.driver.OracleDriver";
                break;
            case MYSQL:
                url = "jdbc:mysql://" + host + ":" + port + "/" + database;
                driver = "com.mysql.jdbc.Driver";
                break;
            case MONGO:
                url = "jdbc:mongo://" + host + ":" + port + "/" + database;
                driver = "mongodb.jdbc.MongoDriver";
                break;
        }

        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);

    }

    public static void close(ResultSet rs, Statement st, Connection conn) throws SQLException {
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
                    conn.close();
                }
            }
        }
    }

}
