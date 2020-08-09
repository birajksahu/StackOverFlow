package com;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StackOverFlowHomePage {
    WebDriver driver;
    @FindBy(xpath="//a[contains(text(),'Browse questions')]")
    WebElement browseQuestions;    

    public StackOverFlowHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this); //This initElements method will create all WebElements
    }
    
    /**
     * We are using javascriptexecutor to identify and click "browseQuestions" as 
     * selenium fails to perform any operation on it
     * @throws InterruptedException
     */
    public void navigateToQuestionsPage() throws InterruptedException{
    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", browseQuestions);
        Thread.sleep(5000);
        }

}
