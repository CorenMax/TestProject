package com.testProject.step_definitions;

import com.testProject.utilities.DB_Utils;
import com.testProject.utilities.Environment;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBTesting {

    @Test
    public void test1() {

        DB_Utils.createConnection();

        String query = "Select * From employees";
        List<Map<String, Object>> queryData = DB_Utils.getQueryResultMap(query);

        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        DB_Utils.destroy();
    }

    @Test
    public void test2() {
        DB_Utils.createConnection();

        String query = "Select firstName,lastName,email, jobTitle From employees Limit 2";
        Map<String, Object> rowMap = DB_Utils.getRowMap(query);

        System.out.println(rowMap);

        DB_Utils.destroy();
    }
}
