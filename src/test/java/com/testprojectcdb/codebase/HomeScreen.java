/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testprojectcdb.codebase;

import static com.testprojectcdb.codebase.SetUp.getDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 *
 * @author Reshmi
 */
public class HomeScreen {
	
	//Page objects declaration
    private By homeScreenTitle = By.xpath("//*[@id=\"main\"]/h1");
    private By homeScreenMsgTxt = By.xpath("//*[@id=\"main\"]/div[1]");
    private By filterByNameTxtBox = By.xpath("//*[@id=\"searchbox\"]");
    private By filterByNameBtn = By.xpath("//*[@id=\"searchsubmit\"]");
    private By compNamesColumnRows = By.xpath("//*[@id=\"main\"]/table/tbody/tr/td[1]");
    private By intDateColumnRows = By.xpath("//*[@id=\"main\"]/table/tbody/tr/td[2]");
    private By disConDateColumnRows = By.xpath("//*[@id=\"main\"]/table/tbody/tr/td[3]");
    private By companyColumnRows = By.xpath("//*[@id=\"main\"]/table/tbody/tr/td[4]");
    private By emptyListMsgTxt = By.xpath("//*[@id=\"main\"]/div[2]/em");
    private By addANewCompBtn = By.xpath("//*[@id=\"add\"]");

    //Constants
    public static final String TITLE = "computers found";
    public static final String EMPTY_MSG = "Nothing to display";
    
    FirefoxDriver driver = getDriver();
     
    public HomeScreen verifyHomeScreenDisplay(){
        String titleText = driver.findElement(homeScreenTitle).getText();
        Assert.assertTrue("Home Screen is NOT displayed!", titleText.contains(TITLE));
        return this;
    }
    
    public HomeScreen verifyFilterListEmptyMsg(){
        String listMsgText = driver.findElement(emptyListMsgTxt).getText();
        Assert.assertTrue("The Message Nothing to display is NOT present!", listMsgText.contains(EMPTY_MSG));
        return this;
    }
    
    public HomeScreen filterByComputerName(String name){
        driver.findElement(filterByNameTxtBox).sendKeys(name);
        driver.findElement(filterByNameBtn).click();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return new HomeScreen();    
    }
    
    public int verifyFilteredCompDetailsDisplay(String name, Date introDate, Date disConDate, String company) throws ParseException {
        ArrayList<String> col1RowValues = retrieveAllColRowValues(compNamesColumnRows);
        ArrayList<String> col2RowValues = retrieveAllColRowValues(intDateColumnRows);
        ArrayList<String> col3RowValues = retrieveAllColRowValues(disConDateColumnRows);
        ArrayList<String> col4RowValues = retrieveAllColRowValues(companyColumnRows);
        String introDate1 = parseDateStringToMatchDisplay(introDate);
        String disConDate1 = parseDateStringToMatchDisplay(disConDate);
        int index = 0;
        for (String col1rowval : col1RowValues) {
        	if (col1rowval.matches(name)){
        		index = col1RowValues.indexOf(col1rowval);
        		if((col2RowValues.get(index)).matches(introDate1) &&
        		    (col3RowValues.get(index)).matches(disConDate1) &&
        		    (col4RowValues.get(index)).matches(company)) {
        			Assert.assertTrue("The given computer: " + name + " details is NOT displayed in the filtered list in row :" + (index+1), true);
        			return index+1;
        		}
        	}
        }
        Assert.fail("The given computer: " + name + " details is NOT displayed in the filtered list");
        return 0;
    }
    
    public HomeScreen verifyFilteredCompNamesContainGivenName(String name) {
        ArrayList<String> col1RowValues = retrieveAllColRowValues(compNamesColumnRows);
        for (String col1rowval : col1RowValues) {
        	Assert.assertTrue((col1rowval.toUpperCase()).contains(name.toUpperCase()));
     	}
        return this;
    }
    
    public String parseDateStringToMatchDisplay(Date dateString) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String dateStr1 = sdf.format(dateString);
        Date date = sdf.parse(dateStr1);
        String dateStr2 = sdf.format(date);
        return dateStr2;
    }
    
    public ArrayList<String> retrieveAllColRowValues(By columnRows){
      	List<WebElement> rows = driver.findElements(columnRows);
        ArrayList<String> rowData = new ArrayList<String>();
        for(WebElement row : rows){
       	    rowData.add(row.getText().toString());
        }
        return rowData;
    }
    
    public AddComputerScreen addNewComputer() {
        driver.findElement(addANewCompBtn).click();
        new AddComputerScreen().verifyAddComputerScreenDisplay();
        return new AddComputerScreen();
    }
    
    public HomeScreen verifyNewComputerConfirmMsgDisplay(String name) {
        verifyHomeScreenDisplay();
        String messageText = "Done! Computer " + name + " has been created";
        String titleText = driver.findElement(homeScreenMsgTxt).getText();
        if (titleText.matches(messageText))
            Assert.assertTrue("New computer added confirmation Message is NOT displayed!", true);
        return new HomeScreen();
    }
    
    public EditDeleteComputerScreen navigateToTheEditComputerScreen(String name, Date introDate, Date disConDate, String company) throws ParseException {
    	int rowNum = verifyFilteredCompDetailsDisplay(name,introDate,disConDate,company);
    	driver.findElement(By.xpath(String.format("//*[@id=\"main\"]/table/tbody/tr[%s]/td[1]", rowNum)))
    	.findElement(By.linkText(name)).click(); 
        return new EditDeleteComputerScreen();
    }
    
    public HomeScreen verifyEditComputerConfirmMsgDisplay(String name) {
        String messageText = "Done! Computer " + name + " has been updated";
        String titleText = driver.findElement(homeScreenMsgTxt).getText();
        Assert.assertTrue("Computer details updated confirmation message is NOT displayed!", titleText.contains(messageText));
        return new HomeScreen();
    }
    
    public HomeScreen verifyDeleteComputerConfirmMsgDisplay(String name) {
        verifyHomeScreenDisplay();
        String messageText = "Done! Computer has been deleted";
        String titleText = driver.findElement(homeScreenMsgTxt).getText();
        Assert.assertTrue("Computer deleted confirmation message is NOT displayed!", titleText.contains(messageText));
        return new HomeScreen();
    }
    
    public void createNewComputer(String name, String intrDate, String disDate, String company) {
    	new SetUp().openCDBApplication().addNewComputer()
        	.enterNewComputerDetails(name, intrDate, disDate, company)
        	.verifyNewComputerConfirmMsgDisplay(name);
    }
    
    public HomeScreen deleteComputer(String name, String intrDate, String disDate, String company) throws ParseException {
    	new SetUp().openCDBApplication().filterByComputerName(name)
		.navigateToTheEditComputerScreen(name,SetUp.parseDate(intrDate),SetUp.parseDate(disDate),company)
		.verifyEditComputerScreenDisplay().deleteComputerDetails()
		.verifyDeleteComputerConfirmMsgDisplay(name);
    	return this;
    }
              
    public void closeApplication(){
        driver.close();
    }
    
    public void exitApplication(){
        driver.quit();
    }

}
