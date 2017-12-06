package com.Ecomm.framework;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.*;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;

import com.Ecomm.framework.Pages.*;
import com.Ecomm.framework.constants.*;
import com.Ecomm.framework.Utils.*;
import com.Ecomm.framework.Utils.*;

@Listeners(com.Ecomm.framework.Utils.TestNG_Listener.class)
public class OrderFlow_Mobile implements ApplicationConstants,PerfectoConstants {
	Wrapper wp = new Wrapper();
	AppiumDriver driver;
	Homepage hp = new Homepage();
	ConfirmOrderPage cop = new ConfirmOrderPage();
	
	By Verify_HomeScreen = By.id("username");

	@Parameters({ "mobileOS", "OSVersion", "deviceModel", "persona"})
	@Test(priority=1)
	public void Addtocart_mob(String mobileOS,String OSVersion, String deviceModel,String persona) throws MalformedURLException, IOException {
        System.out.println("Run started");
        driver = wp.getAppiumDriver(mobileOS,OSVersion, deviceModel, persona);
 
        // Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
        PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
        .withProject(new Project("Ecommerce Project", "1.0"))
        .withJob(new Job("My Job", 45))
        .withContextTags("tag1")
        .withWebDriver(driver)
        .build();
        ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
        
        try {
            reportiumClient.testStart("Addtocart_mob", new TestContext("tag2", "tag3"));
            reportiumClient.stepStart("Loading Application URL");
            driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
            driver.get(APP_URL);
    		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    		
			if(wp.waitForElement(driver, Verify_HomeScreen)){
				System.out.println("Home page loaded sucessfully");
				Reporter.log("Home page loaded sucessfully");
				reportiumClient.reportiumAssert("Home page loaded sucessfully", wp.waitForElement(driver, Verify_HomeScreen));
				reportiumClient.stepEnd("Home page loaded sucessfully");
			}
			else {
				System.out.println("Home page not loaded or application is down");
				reportiumClient.reportiumAssert("Home page not loaded or application is down", !wp.waitForElement(driver, Verify_HomeScreen));
				reportiumClient.testStop(TestResultFactory.createFailure("Home page not loaded or application is down"));
			}
			reportiumClient.stepStart("Login");
			wp.login(driver, reportiumClient);
			reportiumClient.stepEnd("Login");
			
			reportiumClient.stepStart("Add to cart");
			hp.Addtocart(driver, reportiumClient);
			reportiumClient.stepEnd("Add to cart");
    		
            reportiumClient.testStop(TestResultFactory.createSuccess());
        } catch (Exception e) {
            reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
            e.printStackTrace();
        } finally {
            try {
                driver.quit();
                
                // Retrieve the URL to the DigitalZoom Report (= Reportium Application) for an aggregated view over the execution
                String reportURL = reportiumClient.getReportUrl();
                
                // Retrieve the URL to the Execution Summary PDF Report
                String reportPdfUrl = (String)(driver.getCapabilities().getCapability("reportPdfUrl"));
                // For detailed documentation on how to export the Execution Summary PDF Report, the Single Test report and other attachments such as
                // video, images, device logs, vitals and network files - see http://developers.perfectomobile.com/display/PD/Exporting+the+Reports
                System.out.println(reportPdfUrl);
                System.out.println(reportURL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
     }
        
        
		@Parameters({ "mobileOS", "OSVersion", "deviceModel", "persona"})
		@Test(priority=2)
    	public void Checkoutcart_mob(String mobileOS,String OSVersion, String deviceModel,String persona) throws MalformedURLException, IOException {
            System.out.println("Run started");
            driver = wp.getAppiumDriver(mobileOS,OSVersion, deviceModel, persona);
     
            // Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
            PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
            .withProject(new Project("Ecommerce Project", "1.0"))
            .withJob(new Job("My Job", 45))
            .withContextTags("tag1")
            .withWebDriver(driver)
            .build();
            ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
            
            try {
                reportiumClient.testStart("Checkoutcart_mob", new TestContext("tag2", "tag3"));
                reportiumClient.stepStart("Loading Application URL");
                driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
                driver.get(APP_URL);
        		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        		
    			if(wp.waitForElement(driver, Verify_HomeScreen)){
    				System.out.println("Home page loaded sucessfully");
    				Reporter.log("Home page loaded sucessfully");
    				reportiumClient.reportiumAssert("Home page loaded sucessfully", wp.waitForElement(driver, Verify_HomeScreen));
    				reportiumClient.stepEnd("Home page loaded sucessfully");
    			}
    			else {
    				System.out.println("Home page not loaded or application is down");
    				reportiumClient.reportiumAssert("Home page not loaded or application is down", !wp.waitForElement(driver, Verify_HomeScreen));
    				reportiumClient.testStop(TestResultFactory.createFailure("Home page not loaded or application is down"));
    			}
    			reportiumClient.stepStart("Login");
    			wp.login(driver, reportiumClient);
    			reportiumClient.stepEnd("Login");
    			
    			reportiumClient.stepStart("Add to cart");
    			hp.Addtocart(driver, reportiumClient);
    			reportiumClient.stepEnd("Add to cart");
    			
    			reportiumClient.stepStart("checkout cart");
    			hp.Checkoutcart(driver, reportiumClient);
    			reportiumClient.stepEnd("Checkout cart");
    			
    			Thread.sleep(8000);
    			
    			
        		
                reportiumClient.testStop(TestResultFactory.createSuccess());
            } catch (Exception e) {
                reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
                e.printStackTrace();
            } finally {
                try {
                    driver.quit();
                    
                    // Retrieve the URL to the DigitalZoom Report (= Reportium Application) for an aggregated view over the execution
                    String reportURL = reportiumClient.getReportUrl();
                    
                    // Retrieve the URL to the Execution Summary PDF Report
                    String reportPdfUrl = (String)(driver.getCapabilities().getCapability("reportPdfUrl"));
                    // For detailed documentation on how to export the Execution Summary PDF Report, the Single Test report and other attachments such as
                    // video, images, device logs, vitals and network files - see http://developers.perfectomobile.com/display/PD/Exporting+the+Reports
                    System.out.println(reportPdfUrl);
                    System.out.println(reportURL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        
        System.out.println("Run ended");
    }
		
		@Parameters({ "mobileOS", "OSVersion", "deviceModel", "persona"})
		@Test(priority=3)
    	public void PlaceOrder_mob(String mobileOS,String OSVersion, String deviceModel,String persona) throws MalformedURLException, IOException {
            System.out.println("Run started");
            driver = wp.getAppiumDriver(mobileOS,OSVersion, deviceModel, persona);
     
            // Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
            PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
            .withProject(new Project("Ecommerce Project", "1.0"))
            .withJob(new Job("My Job", 45))
            .withContextTags("tag1")
            .withWebDriver(driver)
            .build();
            ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
            
            try {
                reportiumClient.testStart("PlaceOrder_mob", new TestContext("tag2", "tag3"));
                reportiumClient.stepStart("Loading Application URL");
                driver.get(APP_URL);
                driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        		
        		
    			if(wp.waitForElement(driver, Verify_HomeScreen)){
    				System.out.println("Home page loaded sucessfully");
    				Reporter.log("Home page loaded sucessfully");
    				reportiumClient.reportiumAssert("Home page loaded sucessfully", wp.waitForElement(driver, Verify_HomeScreen));
    				reportiumClient.stepEnd("Home page loaded sucessfully");
    			}
    			else {
    				System.out.println("Home page not loaded or application is down");
    				reportiumClient.reportiumAssert("Home page not loaded or application is down", !wp.waitForElement(driver, Verify_HomeScreen));
    				reportiumClient.testStop(TestResultFactory.createFailure("Home page not loaded or application is down"));
    			}
    			reportiumClient.stepStart("Login");
    			wp.login(driver, reportiumClient);
    			reportiumClient.stepEnd("Login");
    			
    			reportiumClient.stepStart("Add to cart");
    			hp.Addtocart(driver, reportiumClient);
    			reportiumClient.stepEnd("Add to cart");
    			
    			reportiumClient.stepStart("checkout cart");
    			hp.Checkoutcart(driver, reportiumClient);
    			reportiumClient.stepEnd("Checkout cart");
    			
    			reportiumClient.stepStart("Place order");
    			cop.PlaceOrder(driver, reportiumClient);
    			reportiumClient.stepEnd("Place order");
    			
    			Thread.sleep(8000);
    			
        		
                reportiumClient.testStop(TestResultFactory.createSuccess());
            } catch (Exception e) {
                reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
                e.printStackTrace();
            } finally {
                try {
                    driver.quit();
                    
                    // Retrieve the URL to the DigitalZoom Report (= Reportium Application) for an aggregated view over the execution
                    String reportURL = reportiumClient.getReportUrl();
                    
                    // Retrieve the URL to the Execution Summary PDF Report
                    String reportPdfUrl = (String)(driver.getCapabilities().getCapability("reportPdfUrl"));
                    // For detailed documentation on how to export the Execution Summary PDF Report, the Single Test report and other attachments such as
                    // video, images, device logs, vitals and network files - see http://developers.perfectomobile.com/display/PD/Exporting+the+Reports
                    System.out.println(reportPdfUrl);
                    System.out.println(reportURL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        
        System.out.println("Run ended");
    }
    
   
    
}
