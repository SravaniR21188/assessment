package com.planit.jupitertoys.assessment.utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * 
 * @author Sravani Rayi
 *
 */
public class BaseTest {

	private WebDriver driver;

	/**
	 * Before Class method to create a driver before all the classes
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public void beforeAllClasses() throws Exception {
		driver = WebDriverHelper.createDriver();
	}

	public WebDriver getDriver() {
		return driver;
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
