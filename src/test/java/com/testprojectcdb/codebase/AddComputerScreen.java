/*
 * This class covers the page objects and related methods on Add a Computer Screen
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
public class AddComputerScreen {
    
    //Page objects declaration
    private By addScreenTitle = By.xpath("//*[@id=\"main\"]/h1");
    private By nameTxtBox = By.xpath("//*[@id=\"name\"]");
    private By introDateTxtBox = By.xpath("//*[@id=\"introduced\"]");
    private By discontDateTxtBox = By.xpath("//*[@id=\"discontinued\"]");
    private By companyDropdown = By.xpath("//*[@id=\"company\"]");
    private By createthiscompBtn = By.xpath("//*[@id=\"main\"]/form/div/input");
    private By cancelBtn = By.xpath("//*[@id=\"main\"]/form[1]/div/a");
    //Page errors/field errors highlight
    private By computerNameFieldError = By.xpath("//*[@id=\"main\"]/form/fieldset/div[1]");
    private By introDateFieldError = By.xpath("//*[@id=\"main\"]/form/fieldset/div[2]");
    private By discontDateFieldError = By.xpath("//*[@id=\"main\"]/form/fieldset/div[3]");
    //Constants
    public static final String TITLE = "Add a computer";
     
    FirefoxDriver driver = getDriver();
       
    public AddComputerScreen verifyAddComputerScreenDisplay(){
        String titleText = driver.findElement(addScreenTitle).getText();
        Assert.assertTrue("Add a computer screen is NOT displayed!", titleText.contains(TITLE));
        return this;
    }
    
    public HomeScreen enterNewComputerDetails(String name, String intrDate, String disDate, String company){
        driver.findElement(nameTxtBox).sendKeys(name);
        if (intrDate != null){
            driver.findElement(introDateTxtBox).sendKeys(intrDate);
        }
        if (disDate != null){
            driver.findElement(discontDateTxtBox).sendKeys(disDate);
        }
        if (company != null){
            Select dropdown = new Select(driver.findElement(companyDropdown));
            dropdown.selectByVisibleText(company);
        }
        driver.findElement(createthiscompBtn).click();
        return new HomeScreen();    
    }
    
    public AddComputerScreen createExistingComputerDetailsVerifySaveDisabled(String name, String intrDate, String disDate, String company) {
        driver.findElement(nameTxtBox).sendKeys(name);
        if (intrDate != null){
            driver.findElement(introDateTxtBox).sendKeys(intrDate);
        }
        if (disDate != null){
            driver.findElement(discontDateTxtBox).sendKeys(disDate);
        }
        if (company != null){
            Select dropdown = new Select(driver.findElement(companyDropdown));
            dropdown.selectByVisibleText(company);
        }
        Boolean state = driver.findElement(createthiscompBtn).isEnabled();
        if (state.equals(false))
        	Assert.assertTrue(true);
        else
        	Assert.fail("FAIL: The save button is enabled permitting duplicate computer creation!");
        return this;
    }

    
    public AddComputerScreen addComputerInvalidNameVerifyError(String name){
        driver.findElement(nameTxtBox).sendKeys(name);
        driver.findElement(createthiscompBtn).click();
        Boolean error = driver.findElement(computerNameFieldError).isDisplayed();
        Assert.assertTrue("Computer name field is NOT mandatory", error);
        return this;    
    }
    
    public AddComputerScreen addComputerWithInvalidDatesVerifyError(String name, String intrDate, String disDate){
        driver.findElement(nameTxtBox).sendKeys(name);
        driver.findElement(introDateTxtBox).sendKeys(intrDate);
        driver.findElement(discontDateTxtBox).sendKeys(disDate);
        driver.findElement(createthiscompBtn).click();
        Boolean error1 = driver.findElement(introDateFieldError).isDisplayed();
        Boolean error2 = driver.findElement(discontDateFieldError).isDisplayed();    
        Assert.assertTrue("Invalid Introduced Date error does not occur!", error1);
        Assert.assertTrue("Invalid Introduced Date error does not occur!", error2);
        return this;    
    }
    
    public HomeScreen cancelAddNewComputer(){
        driver.findElement(cancelBtn).click();
        new HomeScreen().verifyHomeScreenDisplay();
        return new HomeScreen();
    } 
    
}
