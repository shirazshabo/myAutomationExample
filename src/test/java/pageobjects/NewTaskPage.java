package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Step;

public class NewTaskPage extends SharedPage{
	@FindBy(css="#duedate")
	WebElement dueDateField;
	
	@FindBy(css=".form-row [name='task']")
	WebElement taskNameField;
	
	@FindBy(css="[name='note']")
	WebElement noteField;
	
	@FindBy(css="#edittags")
	WebElement tagsField;
	
	@FindBy(css="[value='Save']")
	WebElement saveBtn;
	
	@FindBy(css="#mtt_edit_cancel")
	WebElement cancelBtn;
	
	@FindBy(css="#page_taskedit .mtt-inadd")
	WebElement subTitle;
	
	@FindBy(css="#page_taskedit > div > .mtt-back-button")
	WebElement backBtn;
	
	public NewTaskPage(WebDriver driver) {
		super(driver);
	}
	
// Actions
	// create new adv task
	@Step("Create New Advanced Task with priority: {prio}, due date: {dueDate}, task name: {taskName}, note: {note} and tag:{tag}")
	public void createNewAdvTask(String prio, String dueDate, String taskName, String note, String tag) {
		Select select = new Select (driver.findElement(By.cssSelector("[name='prio']")));
		select.selectByValue(prio);
		fillText(dueDateField, dueDate);
		fillText(taskNameField, taskName);
		fillText(noteField, note);
		fillText(tagsField, tag);
		click(saveBtn);
	}
	
	// cancel creation
	@Step("Cancel the creation of new Adv Task")
	public void cancel() {
		click(cancelBtn);
	}
	
	@Step("Go back to previous page")
	public void goBack() throws InterruptedException {
		click(backBtn);
		sleep(1000);
	}
	
	
	// Validation
	@Step("validate this is new task page")
	public boolean isThisNewTaskPage() {
		if (getText(subTitle).equalsIgnoreCase("new task")) {
			return true;
		} else {
			return false;
		}
	}

}
