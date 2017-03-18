/*
 * Test script to check the create functionality
 * This script covers the following test cases: TC_CR_01,
 * TC_CR_02,TC_CR_03,TC_CR_04,TC_CR_05,TC_CR_06
 */
package com.testprojectcdb.testscripts;

import com.testprojectcdb.codebase.SetUp;
import com.testprojectcdb.codebase.HomeScreen;
import com.testprojectcdb.codebase.ScreenShotOnFailure;

import org.junit.runners.MethodSorters;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Reshmi
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_Create_Computer extends HomeScreen {
	
    public static final String COM_NAME = "Test_Com_01";
    public static final String MAX_NAME = "akjshdakjshfjakhfjakshfjasgfjasgfajsgfjagfjkagfakjsgfjfgajxxy";
    public static final String INTR_DATE = "1980-04-11";
    public static final String DIS_DATE = "1985-10-21";
    public static final String INTR_DATE_INVALID = "1980-14-11";
    public static final String DIS_DATE_INVALID = "1985-11-34";
    public static final String COMPANY = "IBM";
    
    @Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(SetUp.getDriver());
    
    @Test
    public void testCreateComputerFunctionality_TC_CR_01() throws Exception {
        //TC_CR_01
    	new SetUp().openCDBApplication().addNewComputer()
             .enterNewComputerDetails(COM_NAME, INTR_DATE, DIS_DATE, COMPANY)
             .verifyNewComputerConfirmMsgDisplay(COM_NAME);
    }
    @Test
    public void testCreateComputerFunctionality_TC_CR_02() throws Exception {
        //TC_CR_02 and TC_CR_06
    	new SetUp().openCDBApplication().addNewComputer()
        .addComputerInvalidNameVerifyError("")
        .cancelAddNewComputer();
    }
    @Test
    public void testCreateComputerFunctionality_TC_CR_03() throws Exception {
        //TC_CR_03
    	new SetUp().openCDBApplication().addNewComputer()
    	.addComputerWithInvalidDatesVerifyError(COM_NAME, INTR_DATE_INVALID, DIS_DATE_INVALID)
        .cancelAddNewComputer();
    }
    @Test
    public void testCreateComputerFunctionality_TC_CR_04() throws Exception {
        //TC_CR_04
    	new SetUp().openCDBApplication().addNewComputer()
    	.createExistingComputerDetailsVerifySaveDisabled(COM_NAME, INTR_DATE, DIS_DATE, COMPANY)
        .cancelAddNewComputer();
    }
    @Test
    public void testCreateComputerFunctionality_TC_CR_05() throws Exception {
        //TC_CR_05
    	new SetUp().openCDBApplication().addNewComputer()
    	.enterNewComputerDetails(MAX_NAME, INTR_DATE, DIS_DATE, COMPANY)
        .verifyNewComputerConfirmMsgDisplay(MAX_NAME)
        .closeApplication();
    }
    
    @AfterClass
    public static void cleanUp() throws Exception {
    	HomeScreen homeScreen = new HomeScreen()
    			.deleteComputer(COM_NAME, INTR_DATE, DIS_DATE, COMPANY);
    	homeScreen.exitApplication();
    }
    
}