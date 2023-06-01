package com.testProject.step_definitions;

import com.testProject.utilities.ConfigurationReader;
import com.testProject.utilities.Environment;
import io.cucumber.java.en.Given;

public class EnvironmentStepDefs {


    @Given("I get related environment information")
    public void i_get_related_environment_information() {


        System.out.println("Environment.URL = " + Environment.URL);
        System.out.println("Environment.BASE_URL = " + Environment.BASE_URI);
        System.out.println("Environment.DB_URL = " + Environment.DB_URL);


    }

    @Given("I get related field data from maven")
    public void i_get_related_field_data_from_maven() {
        String user_name =
                System.getProperty("user_email") != null ? user_name = System.getProperty("user_email")
                        : Environment.USER_NAME;

        System.out.println("Environment.USER_NAME =" + user_name);


        String browser =
                System.getProperty("browser") != null ? browser = System.getProperty("browser")
                        : ConfigurationReader.getProperty("browser");

        System.out.println("browser = " + browser);

    }

}
