package com.planit.jupitertoys.assessment.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.planit.jupitertoys.assessment.utils.WebDriverHelper;

public class CartPage extends BasePage {

	static final String _table = "//*[contains(@class,'cart-items')]";
	@FindBy(xpath = _table)
	private WebElement _tableItems;

	@FindBy(xpath = "/html/body/div[2]/div/form/table/tbody/tr")
	private List<WebElement> _listItem;

	@FindBy(xpath = _table + "/tfoot/tr/td")
	private WebElement _total;

	Object[][] cartItems;

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public void getListItem() {
		WebDriverHelper.waitForElement(_table);
		WebElement parentTable = getDriver().findElement(By.xpath(_table));
		String path = "//tbody/tr";
		List<WebElement> count = parentTable.findElements(By.xpath(path));
		cartItems = new Object[count.size()][4];
		int i = 0;
		WebElement listValue;
		for (WebElement element : count) {
			listValue = getDriver().findElement(By.xpath(_table + "/tbody/tr[" + (i + 1) + "]/td[" + 1 + "]"));
			cartItems[i][0] = listValue.getText();
			listValue = getDriver().findElement(By.xpath(_table + "/tbody/tr[" + (i + 1) + "]/td[" + 2 + "]"));
			cartItems[i][1] = listValue.getText();
			listValue = getDriver().findElement(By.xpath(_table + "/tbody/tr[" + (i + 1) + "]/td[" + 3 + "]/input"));
			cartItems[i][2] = listValue.getAttribute("value");
			listValue = getDriver().findElement(By.xpath(_table + "/tbody/tr[" + (i + 1) + "]/td[" + 4 + "]"));
			cartItems[i][3] = listValue.getText();
			i++;
		}
	}

	public void validateCart(Object[][] assertValue) {
		assertEquals(cartItems, assertValue);

	}

	public void validateTotal(String totalValue) {
		String total = _total.getText();
		String message = total.substring(0, total.indexOf(" ") + 1);
		Assert.assertEquals(total, message + totalValue);
	}

}
