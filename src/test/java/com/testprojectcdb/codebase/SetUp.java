package com.testprojectcdb.codebase;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 *
 * @author Reshmi
 */
public final class SetUp {
    public static final String URL = "http://computer-database.herokuapp.com/computers";
    private static final File pathToBinary = new File("C:\\Firefox\\firefox.exe");
    private static final FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
    private static final FirefoxProfile firefoxProfile = new FirefoxProfile();
    public static final FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile); 
   
    public static FirefoxDriver getDriver() {
       return driver;
    }
    
    public HomeScreen openCDBApplication() {
        getDriver().get(URL);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
        return new HomeScreen();
    }
    
    public static void printLongerTrace(Throwable t){ 
    for(StackTraceElement e: t.getStackTrace()) 
        System.out.println(e); 
    } 
    
    public static Date parseDate(String date) throws ParseException{ 
    	 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
         return formatter.parse(date); 
    } 
}
