package com.Ecomm.framework.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;


import com.perfecto.reportium.client.ReportiumClient;

import com.Ecomm.framework.constants.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import com.Ecomm.framework.Utils.*;

public class Wrapper implements PerfectoConstants,ApplicationConstants {
	
	public static AppiumDriver getAppiumDriver(String platform, String osversion, String deviceModel,String persona ) throws IOException {

    	AppiumDriver driver = null;
    	String browserName = PERFECTO_BROWSER;
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		capabilities.setCapability("user", PERFECTO_USRNAME);
		capabilities.setCapability("password", PERFECTO_PWD);
		capabilities.setCapability("platformName", platform);
		capabilities.setCapability("platformVersion", osversion);
		capabilities.setCapability("model", deviceModel);
		
		capabilities.setCapability("automationName", "appium");
		PerfectoLabUtils.setExecutionIdCapability(capabilities, PERFECTO_URL);
		
		// One liner to set persona
		capabilities.setCapability("windTunnelPersona", persona);
		
		//Creating Android Driver
		try {
			driver = new IOSDriver(
					new URL("https://"+PERFECTO_URL + "/nexperience/perfectomobile/wd/hub"),capabilities);
		} catch (Exception e) {
			String ErrToRep = e.getMessage().substring(0, e.getMessage().indexOf("Command duration") - 1);
			System.out.println(ErrToRep);
			return (null);
		}
		return driver;
		

        //Change your device ID
        //capabilities.setCapability("deviceName", "12345");
        
        // Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
        //capabilities.setCapability("automationName", "Appium");
        
        // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
       // PerfectoLabUtils.setExecutionIdCapability(capabilities, host);
        
        // Application settings examples.
        // capabilities.setCapability("app", "PRIVATE:applications/Errands.ipa");
        // For Android:
        // capabilities.setCapability("appPackage", "com.google.android.keep");
        // capabilities.setCapability("appActivity", ".activities.BrowseActivity");
        // For iOS:
        // capabilities.setCapability("bundleId", "com.yoctoville.errands");
        
        // Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
        //capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);
        
        // Name your script
        // capabilities.setCapability("scriptName", "AppiumTest");
        
     //   AndroidDriver driver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        // IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	public boolean waitForElement(AppiumDriver<WebElement> driver,By objPath) throws Exception{
		try{
			WebDriverWait wait = new WebDriverWait(driver, 180);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(objPath));
    		return true;
		}
		catch(Exception e){
			Reporter.log("Error occured on waiting for the element to appear  - "+ e);
			driver.quit();
			throw new Exception ("Error occured on waiting for the element to appear  - "+ e);
			
            
		}
	}
	
	public void login(AppiumDriver<WebElement> driver, ReportiumClient reportiumClient) throws Exception {
        String except = "false";
        By username  = By.id("username");
        By password  = By.id("password");
    	By btnSignin = By.className("btsign");
    	try {
	    	driver.findElement(username).sendKeys("karthy@gmail.com");
	    	driver.findElement(password).sendKeys("1234");
	    	System.out.println("Username and possword entered Sucessfully");
	    	reportiumClient.testStep("Username and possword entered Sucessfully");
    	}catch(Exception e) {
    		System.out.println("Unable to input Username/Password : "+e.getMessage());
    		except = "true";
    		Assert.assertEquals(except,true,"Unable to input Username/Password, Reason: "+e.getMessage());	
    	}
    	try {
    		driver.findElement(btnSignin).click();
    		System.out.println("Login button clicked Sucessfully");
    		reportiumClient.testStep("Username and possword entered Sucessfully");
    	}catch(Exception e) {
    		System.out.println("Unable to click the login button : "+e.getMessage());
    		except = "true";
    		Assert.assertEquals(except,true,"Unable to click the login button, Reason: "+e.getMessage());
    	}
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	try {
    		driver.findElement(By.xpath("//a[text()='Logout']")).isDisplayed();
    		System.out.println("Home page loaded Sucessfully");
    		reportiumClient.testStep("Home page loaded Sucessfully");
    		
    	}catch(Exception e) {
    		System.out.println("Unable to load home page : "+e.getMessage());
    		except = "true";
    		Assert.assertEquals(except,true,"Unable to load home page, Reason: "+e.getMessage());
    	}
    }
	
	 private static void switchToContext(RemoteWebDriver driver, String context) {
	        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
	        Map<String,String> params = new HashMap<String,String>();
	        params.put("name", context);
	        executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	    }
	    
	    private static String getCurrentContextHandle(RemoteWebDriver driver) {
	        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
	        String context =  (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
	        return context;
	    }
	    
	    private static List<String> getContextHandles(RemoteWebDriver driver) {
	        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
	        List<String> contexts =  (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
	        return contexts;
	    }

}
