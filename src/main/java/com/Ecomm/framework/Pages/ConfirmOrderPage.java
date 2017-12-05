package com.Ecomm.framework.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.perfecto.reportium.client.ReportiumClient;

import io.appium.java_client.AppiumDriver;



public class ConfirmOrderPage {
	String except = "false";
	
	 public void PlaceOrder(AppiumDriver<WebElement> driver, ReportiumClient reportiumClient) throws Exception {
	    	try {
	    		driver.findElement(By.id("address1")).sendKeys("Irving");
	    		System.out.println("Address1 entered Sucessfully");
	    		reportiumClient.testStep("Address1 entered Sucessfully");
	    	}catch(Exception e) {
	    		System.out.println("Unable to input Address1 : "+e.getMessage());
	    		except = "true";
	    		Assert.assertEquals(except,true,"Unable to input Address1, Reason: "+e.getMessage());	
	    	}
	    	try {
	    		driver.findElement(By.xpath("//input[@value='OneClickPay']")).click();
	    		System.out.println("Clicked OneClickPay Sucessfully.");
	    		reportiumClient.testStep("Clicked OneClickPay Sucessfully.");
	    	}catch(Exception e) {
	    		System.out.println("Unable to clic OneClickPay : "+e.getMessage());
	    		except = "true";
	    		Assert.assertEquals(except,true,"Unable to clic OneClickPay, Reason: "+e.getMessage());		
	    	}
	    	try {
	    		driver.findElement(By.xpath("//font[contains(text(),'Order Created')]")).isDisplayed();
	    		System.out.println("Order Created Sucessfully.");
	    		reportiumClient.testStep("Order Created Sucessfully.");
	    	}catch(Exception e) {
	    		System.out.println("Order not created : "+e.getMessage());
	    		except = "true";
	    		Assert.assertEquals(except,true,"Order not created , Reason: "+e.getMessage());		
	    	}
	    	
	    	
	    }

}
