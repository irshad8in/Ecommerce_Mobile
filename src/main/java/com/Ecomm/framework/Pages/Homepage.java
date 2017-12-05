package com.Ecomm.framework.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import com.perfecto.reportium.client.ReportiumClient;

import io.appium.java_client.AppiumDriver;


public class Homepage {
	String except = "false";
	
	public void Addtocart(AppiumDriver<WebElement> driver, ReportiumClient reportiumClient) throws Exception {
    	try {
    		driver.findElement(By.xpath("//table/tbody/tr/td[text()='Youth watches']/following-sibling::td/input[@id='viewdetail']")).click();
    		System.out.println("Clicked Add to cart button Successfully.");
    		reportiumClient.testStep("Clicked Add to cart button Successfully.");
    	}catch(Exception e) {
    		System.out.println("Unable to click add to cart button : "+e.getMessage());
    		except = "true";
    		Assert.assertEquals(except,true,"Unable to click add to cart button, Reason: "+e.getMessage());	
    	}
    	try {
    		driver.findElement(By.xpath("//font[contains(text(), 'cart successfully')]")).isDisplayed();
    		System.out.println("Product Added to cart Successfully.");
    		reportiumClient.testStep("Product Added to cart Successfully.");
    	}catch(Exception e) {
    		System.out.println("Product not added to cart : "+e.getMessage());
    		except = "true";
    		Assert.assertEquals(except,true,"Product not added to cart, Reason: "+e.getMessage());		
    	}
    	
    	
    }
	
	
	public void Checkoutcart(AppiumDriver<WebElement> driver, ReportiumClient reportiumClient) throws Exception {
    	try {
    		driver.findElement(By.id("checkoutproduct")).click();
    		System.out.println("Clicked Checkout product button Successfully.");
    		reportiumClient.testStep("Clicked Checkout product button Successfully.");
    		Thread.sleep(8000);
    	}catch(Exception e) {
    		System.out.println("Unable to click Checkout product button : "+e.getMessage());
    		except = "true";
    		Assert.assertEquals(except,true,"Unable to click Checkout product button, Reason: "+e.getMessage());		
    	}
    	
    }

}
