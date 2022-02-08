package pageobjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;


	//constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		js =(JavascriptExecutor)driver;
	}

	// click method
	public void click(WebElement el) {
		highlightElement(el, "black", "RoyalBlue");
		try {
			el.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//fillText method
	public void fillText(WebElement el, String text) {
		highlightElement(el, "black", "RoyalBlue");
		try {
			el.clear();
		} catch (Exception e) {
			
		}
		el.sendKeys(text);
	}

	//get element's text method
	public String getText(WebElement el) {
		highlightElement(el, "black", "orange");
		return el.getText();
	}
	
	// sleep method
	public void sleep(long mils) {
		try {
			Thread.sleep(mils);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void refresh() {
		driver.navigate().refresh();
	}
	
	private void highlightElement(WebElement element, String borderColor, String backgroundColor) {
		//keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background:" + backgroundColor + "; border: 1px solid " + borderColor + ";" + originalStyle;
		
		// Change the style 
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
				element);

		// Change the style back after few milliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + originalStyle + "');},400);", element);

	}
}
