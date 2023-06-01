package com.testProject.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {
    public static final String URL;
    public static final String BASE_URI;
    public static final String DB_USERNAME;
    public static final String DB_PASSWORD;
    public static final String DB_URL;
    public static final String USER_NAME;
    public static final String USER_PASSWORD;
    public static final String MANAGER_EMAIL;
    public static final String MANAGER_PASSWORD;
    public static final String TEAM_LEADER_EMAIL;
    public static final String TEAM_LEADER_PASSWORD;


    static {
        Properties properties = null;
        String environment = System.getProperty("environment") != null ? environment = System.getProperty("environment") : ConfigurationReader.getProperty("environment");


        try {

            String path = System.getProperty("user.dir") + "/src/test/resources/Environments/" + environment + ".properties";

            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        URL = properties.getProperty("wikiUrl");
        BASE_URI = properties.getProperty("base_URI");
        DB_USERNAME = properties.getProperty("dbUsername");
        DB_PASSWORD = properties.getProperty("dbPassword");
        DB_URL = properties.getProperty("dbUrl");
        USER_NAME = properties.getProperty("username");
        USER_PASSWORD = properties.getProperty("password");
        MANAGER_EMAIL = properties.getProperty("manager_email");
        MANAGER_PASSWORD = properties.getProperty("manager_password");
        TEAM_LEADER_EMAIL = properties.getProperty("team_leader_email");
        TEAM_LEADER_PASSWORD = properties.getProperty("team_leader_password");


    }

}
