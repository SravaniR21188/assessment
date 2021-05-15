package com.planit.jupitertoys.assessment.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/***
 * 
 * @author Sravani Rayi
 *
 */

public class WebDriverHelper {
	protected static Properties _userProperties = new Properties();
	static WebDriver driver = null;

	public WebDriverHelper() throws IOException {
		loadProperties();
	}

	private static Properties loadProperties() throws IOException {
		try {
			FileInputStream configStream = new FileInputStream("config.user.properties");
			_userProperties.load(configStream);
			return _userProperties;
		} catch (FileNotFoundException e) {
			System.out.println("No config file Found");
		}
		return _userProperties;

	}

	public static String getStringProperty(String propertyname) throws FileNotFoundException {
		try {
			_userProperties = loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String returnValue = _userProperties.getProperty(propertyname);
		return returnValue;
	}

	public static WebDriver createDriver() throws FileNotFoundException {
		if (driver == null) {
			String browser = getStringProperty("browser");
			if (browser.contains("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/resources/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.contains("Firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/resources/geckodriver.exe");
				driver = new FirefoxDriver();
			} else {
				System.out.println("No Browser found");
			}
			driver.navigate().to(getStringProperty("url"));
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void waitForElement(String element) {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(_userProperties.getProperty("delay")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
	}
	
	public static void waitForLinkElement(String element) {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(_userProperties.getProperty("delay")));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(element)));
	}


	public static boolean isElementDisplay(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
