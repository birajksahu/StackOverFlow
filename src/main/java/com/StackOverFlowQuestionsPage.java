package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StackOverFlowQuestionsPage {
	WebDriver driver;
    @FindBy(xpath="//a[@id='nav-tags']")
    WebElement tags;
    @FindBy(xpath="//a[contains(text(),'Name')]")
    WebElement name;
    @FindBy(xpath=("//div[@id='tags-browser']//"
			+ "div[@class='s-card js-tag-cell grid fd-column']"
			+ "//div[@class='mt-auto grid jc-space-between fs-caption fc-black-300']"
			+ "//div[@class='grid--cell']"))
    List<WebElement> taglist; 
    /*Captures all web element having each tag's number 
    of questions in string as"123 questions" format*/
    
	public StackOverFlowQuestionsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this); //This initElements method will create all WebElements
    }
	
	public void clickOnTags() {
		tags.click();
	}
	
	public void clickOnName() {
		name.click();
	}
	
	/**
	 * @return
	 * This method treats the taglist string and converts it into a List Integer type
	 * and returns maximum question count
	 */
	public String getMaxQuestionCount() {
		List<Integer> tagInt = new ArrayList<>();
		for (int i =0; i<taglist.size(); i++) {
			tagInt.add(Integer.parseInt((taglist.get(i).getText().
					replace(" questions", "").replace("question", "").trim())));
		}
		return String.valueOf(Collections.max(tagInt));
	}
	
	/**
	 * @param maxCount
	 * @return
	 * Using maxCount, we return the tag name of its parent.
	 */
	public String getTagName(String maxCount) { 
		WebElement maxtagname = driver.findElement(By.xpath("//div[text()='"
				+maxCount+" questions']/../../div/..//div"
						+ "[@class='grid jc-space-between ai-center mb12']/div/a"));
		return maxtagname.getText();
	}
}
