package com.planit.jupitertoys.assessment.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.planit.jupitertoys.assessment.pages.CartPage;
import com.planit.jupitertoys.assessment.pages.ContactPage;
import com.planit.jupitertoys.assessment.pages.HomePage;
import com.planit.jupitertoys.assessment.pages.ShopPage;
import com.planit.jupitertoys.assessment.utils.BaseTest;

public class AssessmentTest extends BaseTest {

	HomePage homePage;
	ContactPage contactPage;
	ShopPage shopPage;
	CartPage cartPage;

	@AfterMethod
	public void navigateToHome() {
		homePage = new HomePage(getDriver());
		homePage.navigateToHome();
	}

	/**
	 * Test case 1: 1. From the home page go to contact page 2. Click submit button
	 * 3. Validate errors 4. Populate mandatory fields 5. Validate errors are gone
	 */
	@Test(enabled = true, priority = 1)
	public void validateContactPage() {
		contactPage = homePage.navigateToContact();
		contactPage.clickOnSubmit();
		contactPage.validateErrors();
		contactPage.enterFieldInfo("Sravani", "Sravani.rayi@gmail.com", "Home Address");
		contactPage.clickOnSubmit();
		contactPage.validateNoErrors();
	}

	/**
	 * Test case 2: 1. From the home page go to contact page 2. Populate mandatory
	 * fields 3. Click submit button 4. Validate successful submission message
	 */
	@Test(enabled = true, priority = 2)
	public void validateSuccessFulContactSubmission() {
		contactPage = homePage.navigateToContact();
		contactPage.enterFieldInfo("Sravani Rayi", "Sravani.rayi@gmail.com", "Home Address");
		contactPage.clickOnSubmit();
		contactPage.validateSuccessMessage("Sravani Rayi");
	}

	/**
	 * Test case 3: 1. From the home page go to contact page 2. Populate mandatory
	 * fields with invalid data 3. Validate errors
	 */
	@Test(enabled = true, priority = 3)
	public void validateContactInvalidData() {
		contactPage = homePage.navigateToContact();
		contactPage.enterFieldInfo("Sravani Rayi", "Sravani.rayi", "Home Address");
		contactPage.validateFieldErrors();
	}

	/**
	 * Test case 4: 1. From the home page go to shop page 2. Click buy button 2
	 * times on “Funny Cow” 3. Click buy button 1 time on “Fluffy Bunny” 4. Click
	 * the cart menu 5. Verify the items are in the cart
	 */
	@Test(enabled = true, priority = 4)
	public void validateItemsInCart() {
		shopPage = homePage.navigateToShop();
		shopPage.selectItem("Funny Cow", 2);
		shopPage.selectItem("Fluffy Bunny", 1);
		cartPage = shopPage.navigateToCart();
		cartPage.getListItem();
		Object[][] assertValue = { { "Funny Cow", "$10.99", "2", "$21.98" },
				{ "Fluffy Bunny", "$9.99", "1", "$9.99" } };
		cartPage.validateCart(assertValue);
		cartPage.validateTotal("31.97");
	}

	@BeforeClass
	public void setup() {
		homePage = new HomePage(getDriver());
	}

}
