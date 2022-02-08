package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SharedPage extends BasePage{

	@FindBy(css="#settings")
	WebElement settingsBtn;
	
	@FindBy(css="#loading")
	WebElement loadingIcon;
	
	public SharedPage(WebDriver driver) {
		super(driver);
	}
	
	public void openSettings(){
		waitForSettingsBtn();
		click(settingsBtn);
	}
	
	public void waitingForLoading() {
		wait.until(ExpectedConditions.visibilityOf(loadingIcon));
		wait.until(ExpectedConditions.invisibilityOf(loadingIcon));
	}
	
	public void waitForSettingsBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(settingsBtn));
	}
}
