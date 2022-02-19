package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class SettingsPage extends SharedPage {

	@FindBy(css="#page_ajax h3")
	WebElement pageTitle;
	
	@FindBy(css="#page_ajax a")
	WebElement backBtn;
	
	public SettingsPage(WebDriver driver) {
		super(driver);
	}
	
	// actions
	// Go Back method
	@Step("Go back to previous page")
	public void goBack() {
		click(backBtn);
	}

	// validation
	// check if I am in the right page
	@Step("Validate this is Settings page")
		public boolean isThisSettingsPage() {
			sleep(300);
			if (getText(pageTitle).equalsIgnoreCase("settings")) {
				return true;
			} else {
				return false;
			}
		}
}
