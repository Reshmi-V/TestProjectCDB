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
public class Test_Delete_Computer extends HomeScreen {
	
    private static final String COM_NAME = "Test_DEL_01";
    private static final String INTR_DATE = "1980-04-11";
    private static final String DIS_DATE = "1985-10-21";
    private static final String COMPANY = "IBM";
    
    @Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(SetUp.getDriver());
   
    @BeforeClass
    public static void setPreconditionForTest() throws Exception {
    	new HomeScreen().createNewComputer(COM_NAME, INTR_DATE, DIS_DATE, COMPANY);
    }
    
    @Test
    public void testCreateComputerFunctionality_TC_DEL_01() throws Exception {
        //TC_DEL_01
    	new SetUp().openCDBApplication().filterByComputerName(COM_NAME)
			.navigateToTheEditComputerScreen(COM_NAME,SetUp.parseDate(INTR_DATE),SetUp.parseDate(DIS_DATE),COMPANY)
			.verifyEditComputerScreenDisplay().deleteComputerDetails()
			.verifyDeleteComputerConfirmMsgDisplay(COM_NAME)
			.filterByComputerName(COM_NAME)
			.verifyFilterListEmptyMsg().closeApplication();
    }
    
    @AfterClass
    public static void cleanUp(){
    	new HomeScreen().exitApplication();
    }
    
}