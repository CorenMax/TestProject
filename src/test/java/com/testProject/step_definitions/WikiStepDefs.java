package com.testProject.step_definitions;

import com.testProject.pages.WikiSearchPage;
import com.testProject.utilities.ConfigurationReader;
import com.testProject.utilities.Driver;
import com.testProject.utilities.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class WikiStepDefs {
    WikiSearchPage wikiSearchPage = new WikiSearchPage();

    @Given("User is on Wikipedia home page")
    public void user_is_on_wikipedia_home_page() {

        Driver.get().get(Environment.URL);
        //Driver.getDriver().findElement(By.xpath("//button[@id='L2AGLb']")).click();
    }

    @When("User types Steve Jobs in the wiki search box")
    public void user_types_steve_jobs_in_the_wiki_search_box() {
        wikiSearchPage.searchBox.sendKeys("Steve Jobs");

    }

    @When("User clicks wiki search button")
    public void user_clicks_wiki_search_button() {
        wikiSearchPage.searchButton.click();
    }

    @Then("User sees Steve Jobs is in the wiki title")
    public void user_sees_steve_jobs_is_in_the_wiki_title() {

        String actualTitle = Driver.get().getTitle();
        String expectedTitle = "Steve Jobs - Wikipedia";
        Assert.assertEquals("Title is not as expected", expectedTitle, actualTitle);
    }

    @When("User types {string} in the wiki search box")
    public void userTypesInTheWikiSearchBox(String arg0) {
        wikiSearchPage.searchBox.sendKeys(arg0);

    }

    @Then("User sees {string} is in the wiki title")
    public void userSeesJobsIsInTheWikiTitle(String arg0) {
        String actualTitle = Driver.get().getTitle();
        String expectedTitle = arg0 + " - Wikipedia";
        Assert.assertEquals("Title is not as expected", expectedTitle, actualTitle);
    }

    @Then("User sees {string} is in the main header")
    public void userSeesIsInTehMainHeader(String arg0) {

//        String expectedHeader = arg0;
        String actualHeader = wikiSearchPage.mainHeader.getText();

//        Assert.assertEquals(expectedHeader, actualHeader);
        Assert.assertEquals(arg0, actualHeader);


    }

    @Then("User sees {string} is in the image header")
    public void userSeesIsInTheImageHeader(String arg0) {

        String actualImageHeader = wikiSearchPage.imageHeader.getText();
        Assert.assertEquals(arg0, actualImageHeader);
    }
}
