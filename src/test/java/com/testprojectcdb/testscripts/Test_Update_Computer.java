/*
 * Test script to check the update functionality
 * This script covers the following test cases: TC_UPD_01,
 * TC_UPD_02,TC_UPD_03
 */
package com.testprojectcdb.testscripts;

import com.testprojectcdb.codebase.SetUp;
import com.testprojectcdb.codebase.HomeScreen;
import com.testprojectcdb.codebase.ScreenShotOnFailure;

import org.junit.runners.MethodSorters;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Reshmi
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_Update_Computer extends HomeScreen {
	
    private static final String COM_NAME = "Test_UPD_01";
    private static final String INTR_DATE = "1980-04-11";
    private static final String DIS_DATE = "1985-10-21";
    private static final String COMPANY = "IBM";
    private static final String COM_NAME_1 = "Test_UPD_01_New";
    private static final String INTR_DATE_1 = "2000-04-11";
    private static final String DIS_DATE_1 = "2005-10-21";
    private static final String COMPANY_1 = "Sony";
    private static final String COM_NAME_2 = "Test_UPD_02";
    private static final String COM_NAME_3 = "Test_UPD_03";
    
    @Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(SetUp.getDriver());
    
    @BeforeClass
    public static void setPreconditionForTest() throws Exception {
    	new HomeScreen().createNewComputer(COM_NAME, INTR_DATE, DIS_DATE, COMPANY);
    }
    
    @Test
    public void testCreateComputerFunctionality_TC_UPD_01() throws Exception {
        //TC_UPD_01
    	new SetUp().openCDBApplication().filterByComputerName(COM_NAME)
			.navigateToTheEditComputerScreen(COM_NAME,SetUp.parseDate(INTR_DATE),SetUp.parseDate(DIS_DATE),COMPANY)
			.verifyEditComputerScreenDisplay().editComputerDetails(COM_NAME_1, INTR_DATE_1, DIS_DATE_1, COMPANY_1)
			.saveUpdatedComputerDetails().verifyEditComputerConfirmMsgDisplay(COM_NAME_1);
    }
    @Test
    public void testCreateComputerFunctionality_TC_UPD_02() throws Exception {
        //Precondition
    	new HomeScreen().createNewComputer(COM_NAME_2, INTR_DATE, DIS_DATE, COMPANY);
    	//TC_UPD_02
    	new SetUp().openCDBApplication().filterByComputerName(COM_NAME_2)
    		.navigateToTheEditComputerScreen(COM_NAME_2,SetUp.parseDate(INTR_DATE),SetUp.parseDate(DIS_DATE),COMPANY)
    		.verifyEditComputerScreenDisplay().verifySaveButtonEnabledForEditComputerDetails(false);
    }
    @Test
    public void testCreateComputerFunctionality_TC_UPD_03() throws Exception {
        //TC_UPD_03
    	new SetUp().openCDBApplication().filterByComputerName(COM_NAME_2)
    		.navigateToTheEditComputerScreen(COM_NAME_2,SetUp.parseDate(INTR_DATE),SetUp.parseDate(DIS_DATE),COMPANY)
    		.verifyEditComputerScreenDisplay().editComputerDetails(COM_NAME_3, INTR_DATE_1, DIS_DATE_1, COMPANY_1)
    		.cancelEditComputerDetails();
    }
    
    @AfterClass
    public static void cleanUp() throws Exception{
    	HomeScreen homeScreen = new HomeScreen().deleteComputer(COM_NAME_1, INTR_DATE_1, DIS_DATE_1, COMPANY_1)
    			.deleteComputer(COM_NAME_2, INTR_DATE, DIS_DATE, COMPANY);
    	homeScreen.exitApplication();
    }
    
}