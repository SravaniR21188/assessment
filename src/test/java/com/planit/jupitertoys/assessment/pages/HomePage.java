package com.planit.jupitertoys.assessment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.planit.jupitertoys.assessment.utils.WebDriverHelper;

public class HomePage extends BasePage {
	static final String _contact = "Contact";
	@FindBy(linkText = _contact)
	private WebElement _lnkContact;
	
	static final String _home = "Home";
	@FindBy(linkText = _home)
	private WebElement _lnkHome;

	@FindBy(linkText = "Shop")
	private WebElement _lnkShop;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public ContactPage navigateToContact() {
		WebDriverHelper.waitForLinkElement(_contact);
		_lnkContact.click();
		return new ContactPage(getDriver());
	}

	public ShopPage navigateToShop() {
		_lnkShop.click();
		return new ShopPage(getDriver());
	}

	public void navigateToHome() {
		WebDriverHelper.waitForLinkElement(_home);
		_lnkHome.click();
		
	}

}
