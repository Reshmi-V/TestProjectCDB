/*
 * This class covers the page objects and related methods on Edit Computer Screen
 */
package com.testprojectcdb.codebase;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import static com.testprojectcdb.codebase.SetUp.getDriver;

/**
 *
 * @author Reshmi
 */
public class EditDeleteComputerScreen extends HomeScreen {
	
	//Page objects declaration
    private By EditScreenTitle = By.xpath("//*[@id=\"main\"]/h1"); 
    private By nameTxtBox = By.xpath("//*[@id=\"name\"]");
    private By introDateTxtBox = By.xpath("//*[@id=\"introduced\"]");
    private By discontDateTxtBox = By.xpath("//*[@id=\"discontinued\"]");
    private By companyDropdown = By.xpath("//*[@id=\"company\"]"); 
    private By savethiscompBtn = By.xpath("//*[@id=\"main\"]/form[1]/div/input");
    private By cancelBtn = By.xpath("//*[@id=\"main\"]/form[1]/div/a");
    private By deleteBtn = By.xpath("//*[@id=\"main\"]/form[2]/input"); 
    //Page errors/field errors highlight
    private By computerNameFieldError = By.xpath("//*[@id=\"main\"]/form/fieldset/div[1]");
    //Constants
    public static final String TITLE = "Edit computer";
     
    FirefoxDriver driver = getDriver();
       
    public EditDeleteComputerScreen verifyEditComputerScreenDisplay(){
        String titleText = driver.findElement(EditScreenTitle).getText();
        Assert.assertTrue("Edit computer screen is NOT displayed!", titleText.contains(TITLE));
        return this;
    }
    
    public EditDeleteComputerScreen editComputerDetails(String name, String intrDate, String disDate, String company){
    	driver.findElement(nameTxtBox).clear();
        driver.findElement(nameTxtBox).sendKeys(name);
        if (intrDate != null){
        	driver.findElement(introDateTxtBox).clear();
            driver.findElement(introDateTxtBox).sendKeys(intrDate);
        }
        if (disDate != null){
        	driver.findElement(discontDateTxtBox).clear();
            driver.findElement(discontDateTxtBox).sendKeys(disDate);
        }
        if (company != null){
            Select dropdown = new Select(driver.findElement(companyDropdown));
            dropdown.selectByVisibleText(company);
        }
        return this;    
    }
    
    public HomeScreen saveUpdatedComputerDetails(){
        driver.findElement(savethiscompBtn).click();
        return new HomeScreen();    
    }
    
    public EditDeleteComputerScreen editComputerWithInvalidName(String name){
        driver.findElement(nameTxtBox).sendKeys(name);
        driver.findElement(savethiscompBtn).click();
        Boolean error = driver.findElement(computerNameFieldError).isDisplayed();
        Assert.assertTrue("Computer name field is NOT mandatory!", error);
        return this;    
    }
   
    public HomeScreen cancelEditComputerDetails(){
        driver.findElement(cancelBtn).click();
        new HomeScreen().verifyHomeScreenDisplay();
        return new HomeScreen();
    }
    
    public EditDeleteComputerScreen verifySaveButtonEnabledForEditComputerDetails(Boolean buttonIsEnabled){
        Boolean state = driver.findElement(savethiscompBtn).isEnabled();
        Assert.assertEquals(buttonIsEnabled, state);
        return new EditDeleteComputerScreen();
    }
    
    public HomeScreen deleteComputerDetails(){
    	driver.findElement(deleteBtn).click();
        return new HomeScreen();
    }
}
