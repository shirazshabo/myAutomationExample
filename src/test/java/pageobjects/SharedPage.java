package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

public class SharedPage extends BasePage{

	@FindBy(css="#settings")
	WebElement settingsBtn;
	
	@FindBy(css="#loading")
	WebElement loadingIcon;
	
	public SharedPage(WebDriver driver) {
		super(driver);
	}
	
	@Step("Open Settings page")
	public void openSettings(){
		waitForSettingsBtn();
		click(settingsBtn);
	}
	
	@Step("Wate for loading to end")
	public void waitingForLoading() {
		wait.until(ExpectedConditions.visibilityOf(loadingIcon));
		wait.until(ExpectedConditions.invisibilityOf(loadingIcon));
	}
	
	@Step("Wate for Settings button to be clickable")
	public void waitForSettingsBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(settingsBtn));
	}
}
