package com.planit.jupitertoys.assessment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.planit.jupitertoys.assessment.constants.ContactConstants;
import com.planit.jupitertoys.assessment.utils.WebDriverHelper;

public class ContactPage extends BasePage {

	public ContactPage(WebDriver driver) {
		super(driver);
	}

	static final String _submit = "//*[text()='Submit']";
	@FindBy(xpath = _submit)
	private WebElement _btnSubmit;

	// Header Error Message
	@FindBy(xpath = "//*[@id='header-message']/div")
	private WebElement _msgHeader;

	// Forename Input
	@FindBy(id = "forename")
	private WebElement _inputForename;

	// Fore Name Error Message
	@FindBy(id = "forename-err")
	private WebElement _msgForeName;

	// Email Input
	@FindBy(id = "email")
	private WebElement _inputEmail;

	// Email Error Message
	@FindBy(id = "email-err")
	private WebElement _msgEmail;

	// Message Input
	@FindBy(id = "message")
	private WebElement _inputMessage;

	// Message Error Message
	@FindBy(id = "message-err")
	private WebElement _msgMessage;

	// Success Message
	static final String _success = "//*[@ui-if='contactValidSubmit']/div";
	@FindBy(xpath = _success)
	private WebElement _msgSuccess;
	
	// Back Button 
	static final String _back = "//*[contains(text(),'Back')]";
	@FindBy(xpath = _back)
	private WebElement _btnBack;

	public void clickOnSubmit() {
		WebDriverHelper.waitForElement(_submit);
		_btnSubmit.click();
	}

	public void validateErrors() {
		Assert.assertEquals(_msgHeader.getText(), ContactConstants.HEADER_ERR_MESSAGE);
		Assert.assertEquals(_msgForeName.getText(), ContactConstants.FORENAME_ERR_MESSAGE);
		Assert.assertEquals(_msgEmail.getText(), ContactConstants.EMAIL_ERR_MESSAGE);
		Assert.assertEquals(_msgMessage.getText(), ContactConstants.MESSAGE_ERR_MESSAGE);
	}

	public void validateNoErrors() {
		Assert.assertFalse(WebDriverHelper.isElementDisplay(_msgForeName));
		Assert.assertFalse(WebDriverHelper.isElementDisplay(_msgEmail));
		Assert.assertFalse(WebDriverHelper.isElementDisplay(_msgMessage));
		WebDriverHelper.waitForElement(_back);
	}

	public void enterFieldInfo(String forename, String email, String message) {
		WebDriverHelper.waitForElement(_submit);
		_inputForename.sendKeys(forename);
		_inputEmail.sendKeys(email);
		_inputMessage.sendKeys(message);
	}

	public void validateSuccessMessage(String forename) {
		WebDriverHelper.waitForElement(_success);
		String message = _msgSuccess.getText();		
		String thanks = message.substring(0,7);
		String feedback = message.substring(message.indexOf(","),message.length());
		Assert.assertEquals(message, thanks+forename+feedback);
	}

	public void validateFieldErrors() {
		Assert.assertFalse(WebDriverHelper.isElementDisplay(_msgForeName));
		Assert.assertTrue(WebDriverHelper.isElementDisplay(_msgEmail));
		Assert.assertFalse(WebDriverHelper.isElementDisplay(_msgMessage));	
	}
}
