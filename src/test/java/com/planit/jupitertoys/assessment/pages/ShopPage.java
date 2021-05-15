package com.planit.jupitertoys.assessment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.planit.jupitertoys.assessment.utils.WebDriverHelper;

public class ShopPage extends BasePage {

	static final String _funnyCow = "//*[@id='product-6']/div/p/a";
	@FindBy(xpath = _funnyCow)
	private WebElement _btnBuyFunnyCow;

	static final String _fluffyBunny = "//*[@id='product-4']/div/p/a";
	@FindBy(xpath = _fluffyBunny)
	private WebElement _btnBuyFluffyBunny;
	
	@FindBy(partialLinkText = "Cart")
	private WebElement _lnkCart;

	public ShopPage(WebDriver driver) {
		super(driver);
	}

	public void selectItem(String item, int quantity) {
		int i;
		switch (item) {
		case "Fluffy Bunny":
			WebDriverHelper.waitForElement(_fluffyBunny);
			for (i = 1; i <= quantity; i++) {
				_btnBuyFluffyBunny.click();
			}
			break;
		case "Funny Cow":
			WebDriverHelper.waitForElement(_funnyCow);
			for (i = 1; i <= quantity; i++) {
				_btnBuyFunnyCow.click();
			}
			break;
		default:
			System.out.println(" No requesting item found ");
			break;
		}
	}
	
	public CartPage navigateToCart() {
		_lnkCart.click();
		return new CartPage(getDriver());
	}


}
