package com;


import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Class file for complete flow of the test case of Stackoverflow
 * @author biraj
 *
 */
public class TestStackOverFlow{
    WebDriver driver;
    StackOverFlowHomePage objStackOverFlowHomePage;
    StackOverFlowQuestionsPage objStackOverFlowQuestions; 
    /**
     * Test tear up
     */
    @BeforeTest
    public void setup(){
    	/*
    	 * Its expected that Chromedriver is already set in path, so we dont need to set it up here
    	 */
    	driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://stackoverflow.com/");
    }

	/**
	 * In this test, we are using page objects to extract max count of 
	 * questions and its corresponding tag name
	 * @throws InterruptedException 
	 */
	@Test
	public void testStackOverFlow() throws InterruptedException {
		Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
		objStackOverFlowHomePage = new StackOverFlowHomePage(driver);
		objStackOverFlowHomePage.navigateToQuestionsPage();
		objStackOverFlowQuestions = new StackOverFlowQuestionsPage(driver);
		objStackOverFlowQuestions.clickOnTags();
		objStackOverFlowQuestions.clickOnName();
		String maxQuestion = objStackOverFlowQuestions.getMaxQuestionCount();
		String tagName = objStackOverFlowQuestions.getTagName(maxQuestion);
		Reporter.log("The Tag Name: "+tagName+" has maximum question count of: "+maxQuestion, true);
	}
	/**
	 * Suite tear down
	 */
	@AfterTest
	public void teardown() {
		driver.close();
	}

}
