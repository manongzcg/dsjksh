package cn.epi.datasource.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mwu on 5/15/17 10:57 AM.
 */

public enum DB_TYPE {
    ORACLE,
    MYSQL,
    SQLSERVER,
    MONGO;

    private static final Map<String, DB_TYPE> stringToEnum = new HashMap<String, DB_TYPE>();
    static {
        for (DB_TYPE dbType : values()) {
            stringToEnum.put(dbType.toString(), dbType);
        }
    }

    public static DB_TYPE enumFromString(String dbType) {
        return stringToEnum.get(dbType);
    }

}

