package com.testProject.utilities;

import java.sql.*;
import java.util.*;

public class DB_Utils {


    // declaring at class level so all methods can access
    private static Connection con;
    private static Statement stm;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;


    /**
     * Create Connection by jdbc url and username , password provided
     *
     * @param url      jdbc url for any database
     * @param username username for database
     * @param password password for database
     */
    public static void createConnection(String url, String username, String password) {


        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (Exception e) {
            System.out.println("CONNECTION HAS FAILED " + e.getMessage());
        }

    }


    /**
     * Create connection method , just checking one connection successful or not
     */
    public static void createConnection() {

        String url = Environment.DB_URL;
        String username = Environment.DB_USERNAME;
        String password = Environment.DB_PASSWORD;

        createConnection(url, username, password);

    }



    public static void destroy() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query
     * @return returns a single cell value. If the results in multiple rows and/or
     * columns of data, only first column of the first row will be returned.
     * The rest of the data will be ignored
     */
    public static Object getCellValue(String query) {
        return getQueryResultList(query).get(0).get(0);
    }

    /**
     * @param query
     * @return returns a list of Strings which represent a row of data. If the query
     * results in multiple rows and/or columns of data, only first row will
     * be returned. The rest of the data will be ignored
     */
    public static List<Object> getRowList(String query) {
        return getQueryResultList(query).get(0);
    }

    /**
     * @param query
     * @return returns a map which represent a row of data where key is the column
     * name. If the query results in multiple rows and/or columns of data,
     * only first row will be returned. The rest of the data will be ignored
     */
    public static Map<String, Object> getRowMap(String query) {
        return getQueryResultMap(query).get(0);
    }

    /**
     * @param query
     * @return returns query result in a list of lists where outer list represents
     * collection of rows and inner lists represent a single row
     */
    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(rs.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @param column
     * @return list of values of a single column from the result set
     */
    public static List<Object> getColumnData(String query, String column) {
        executeQuery(query);
        List<Object> rowList = new ArrayList<>();
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                rowList.add(rs.getObject(column));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @return returns query result in a list of maps where the list represents
     * collection of rows and a map represents represent a single row with
     * key being the column name
     */
    public static List<Map<String, Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @return List of columns returned in result set
     */
    public static List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        try {
            rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    private static void executeQuery(String query) {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getRowCount() throws Exception {
        rs.last();
        int rowCount = rs.getRow();
        return rowCount;
    }
}
