/*
 * Test script to check the retrieve functionality
 * This script covers the following test cases: TC_RET_01,
 * TC_RET_02,TC_RET_03
 */
package com.testprojectcdb.testscripts;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.testprojectcdb.codebase.HomeScreen;
import com.testprojectcdb.codebase.ScreenShotOnFailure;
import com.testprojectcdb.codebase.SetUp;

/**
 * @author Reshmi
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_Retrieve_Computer extends HomeScreen {
	
	public static final String COM_NAME = "Test_RET_01";
	public static final String COM_NAME_NONEXISTING = "Test_RET_02";
	public static final String COM_NAME_COMMON = "Test";
	public static final String INTR_DATE = "1980-02-21";
	public static final String DIS_DATE = "1990-04-11";
	public static final String COMPANY = "IBM";
	
	@Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(SetUp.getDriver());

    @BeforeClass
    public static void setPreconditionForTest() throws Exception {
    	new HomeScreen().createNewComputer(COM_NAME, INTR_DATE, DIS_DATE, COMPANY);
    }
    
    @Test
    public void testRetrieveComputerFunctionality_TC_RET_01() throws Exception {
        //TC_RET_01
    	new SetUp().openCDBApplication().filterByComputerName(COM_NAME)
    	.verifyFilteredCompDetailsDisplay(COM_NAME,SetUp.parseDate(INTR_DATE),SetUp.parseDate(DIS_DATE),COMPANY);
    }
    @Test
    public void testRetrieveComputerFunctionality_TC_RET_02() throws Exception {
        //TC_RET_02
    	new SetUp().openCDBApplication().filterByComputerName(COM_NAME_NONEXISTING)
        .verifyFilterListEmptyMsg();
    }
    @Test
    public void testRetrieveComputerFunctionality_TC_RET_03() throws Exception {
        //TC_RET_03
    	new SetUp().openCDBApplication().filterByComputerName(COM_NAME_COMMON)
    	.verifyFilteredCompNamesContainGivenName(COM_NAME_COMMON);
    }
    
    @AfterClass
    public static void cleanUp() throws Exception {
    	HomeScreen homeScreen = new HomeScreen().deleteComputer(COM_NAME, INTR_DATE, DIS_DATE, COMPANY);
    	homeScreen.exitApplication();
    }
    
}