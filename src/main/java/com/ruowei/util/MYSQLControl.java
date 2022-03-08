package com.ruowei.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

/**
 * We develop this tool based on the java package DbUtils.
 * https://commons.apache.org/proper/commons-dbutils/
 *
 *
 * It provides an easy-to-use interface for users to operating mysql database.
 * This tool supports XML database configuration and Java Bean.
 *
 * Function:  query, update and insert data
 *
 * @author: Yang Qian
 * emiail:qy20115549@126.com
 */
public class MYSQLControl {
    static final Log logger = LogFactory.getLog(MYSQLControl.class);
    public DataSource ds;
    public QueryRunner qr;

    /**
     * the constructor
     *
     * @param connectURI
     * @param databaseName
     * @param userName
     * @param password
     * */
    public MYSQLControl(String connectURI, String databaseName, String userName, String password){
        ds = getDataSource("jdbc:mysql://" + connectURI +"/" + databaseName+"?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", userName, password);
        qr = new QueryRunner(ds);
    }


    public static DataSource getDataSource(String connectURI,String userName, String password){

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername(userName);
        ds.setPassword(password);
        ds.setUrl(connectURI);
        return ds;
    }
    /**
     * update the table based on the sql
     *
     * @param sql
     * */
    public void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
    /**
     * get the result based on sql and JavaBean
     *
     * @param sql
     * @param type
     * */
    public  <T> List<T> getListInfoBySQL (String sql, Class<T> type ){
        List<T> list = null;
        try {
            list = qr.query(sql,new BeanListHandler<T>(type));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * get the one column result based on sql and id
     *
     * @param sql
     * @param id
     * */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> getListOneBySQL (String sql,String id){
        List<Object> list = null;

        try {
            list = (List<Object>) qr.query(sql, new ColumnListHandler(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public  <T> void insertListData(List<T> listData, String tableName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object[][] params =
            new Object[listData.size()][listData.get(0).getClass().getDeclaredFields().length];
        for ( int i = 0; i < listData.size(); i++ ){
            T t = listData.get(i);
            java.lang.reflect.Field[] fields = t.getClass().getDeclaredFields();
            for (int j = 0; j < t.getClass().getDeclaredFields().length; j++) {
                Method m = t.getClass().getMethod("get" + fields[j].getName().substring(0, 1).toUpperCase() + fields[j].getName().substring(1));
                params[i][j] = (String) m.invoke(t);
            }
        }
        System.out.println(params.length);
        String value = "";
        for (int i = 0; i < listData.get(0).getClass().getDeclaredFields().length; i++) {
            value += "?,";
        }
        try {
            qr.batch("INSERT INTO " + tableName + " VALUES ("  + value.substring(0, value.length() - 1) + ")", params);
            System.out.println("insert finish!");
        } catch (SQLException e) {
        }
    }
}
