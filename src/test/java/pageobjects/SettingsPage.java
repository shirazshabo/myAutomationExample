package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	public void goBack() {
		click(backBtn);
	}

	// validation
	// check if I am in the right page
		public boolean isThisSettingsPage() {
			sleep(300);
			if (getText(pageTitle).equalsIgnoreCase("settings")) {
				return true;
			} else {
				return false;
			}
		}
}
