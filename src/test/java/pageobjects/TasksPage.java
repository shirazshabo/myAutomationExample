package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class TasksPage extends SharedPage {
	@FindBy(css = "#task")
	WebElement newSimpleTaskField;
	
	@FindBy(css="#newtask_submit")
	WebElement addSimpleTaskBtn;
	
	@FindBy(css=".task-container")
	List <WebElement> list;
	
	@FindBy(css="#cmenu_delete")
	WebElement deleteTaskBtn;
	
	@FindBy(css=".task-title")
	List <WebElement> titlesList;
	
	@FindBy(css="#newtask_adv")
	WebElement addNewAdvTaskBtn;
	
	@FindBy(css=".btnstr")
	WebElement subTitle;
	
	@FindBy(css="#search")
	WebElement searchField;
	
	@FindBy(css="#total")
	WebElement totalTasks;
	
	@FindBy(css=".mtt-tabs-add-button span")
	WebElement newList;
	
	@FindBy(css=".mtt-tabs.ui-sortable li")
	List <WebElement> listsList;
	
	@FindBy(css="#btnDeleteList")
	WebElement deleteListBtn;
	
	@FindBy(css="#btnRenameList")
	WebElement renameListBtn;
	
	@FindBy(css="#sortByPrio")
	WebElement sortByPrioBtn;
	
//	@FindBy(css=".task-prio.prio-pos")
//	List<WebElement> taskPrioList;

	@FindBy(css="#sortByDueDate")
	WebElement sortByDueDateBtn;
	
	@FindBy(css="#loading")
	WebElement loadingIcon;
	
	
	// constructor
	public TasksPage(WebDriver driver) {
		super(driver);
	}

// ACTIONS	
	// Add new simple task
	@Step("Add new simple task: {taskName}")
	public void addNewSimpleTask (String taskName) throws InterruptedException {
		fillText(newSimpleTaskField, taskName);
		click(addSimpleTaskBtn);
		waitingForLoading();
	}

	// delete task
	@Step("Delete task: {taskName}")
	public void deleteTask(String taskName) {
		for (WebElement el : list) {
			if (getText(el).equalsIgnoreCase(taskName)) {
				WebElement taskActionsBtn = el.findElement(By.cssSelector(".task-actions"));
				click(taskActionsBtn);
				click(deleteTaskBtn);
				driver.switchTo().alert().accept();
				break;
			}
		}
	}
	
	// open new advanced task window
	@Step("Press on new Adv task button")
	public void pressOnNewAdvTaskBtn() throws InterruptedException {
		click(addNewAdvTaskBtn);
		sleep(500);
	}
	
	// search
	@Step("Search in the search field: {text}")
	public void search(String text) throws InterruptedException {
		fillText(searchField, text);
		sleep(1000);
		System.out.println("For the search: " + text + " There are " + getText(totalTasks) + " Results");
	}
	
	// create new list
	@Step("Create new list: {listName}")
	public void createNewList(String listName) throws InterruptedException {
		click(newList);
		driver.switchTo().alert().sendKeys(listName);
		driver.switchTo().alert().accept();
		sleep(1000);
	}
	
	// Delete list
	@Step("Delete list: {listName}")
	public void deleteList (String listName) throws InterruptedException {
		openList(listName);
		WebElement listAction = driver.findElement(By.cssSelector("[title='" +listName + "'] > .list-action"));
		click(listAction);
		click(deleteListBtn);
		driver.switchTo().alert().accept();
		sleep(1000);
	}
	
	// open list
	@Step("Open list: {listName}")
	public void openList(String listName) throws InterruptedException {
		sleep(1000);
		WebElement listTab = driver.findElement(By.cssSelector("[title='" +listName + "']"));
		click(listTab);
	}
	
	// change list name
	@Step("Change list name from: {listName} to: {newName}")
	public void changeListName(String listName, String newName) throws InterruptedException {
		openList(listName);
		WebElement listAction = driver.findElement(By.cssSelector("[title='" +listName + "'] > .list-action"));
		click(listAction);
		click(renameListBtn);
		driver.switchTo().alert().sendKeys(newName);
		driver.switchTo().alert().accept();
		sleep(1000);
	}
	
	// sort tasks by priority
	@Step("sort all tasks of the list: {listName} by priority")
	public void sortTasksByPriority (String listName) throws InterruptedException {
		openList(listName);
		WebElement listAction = driver.findElement(By.cssSelector("[title='" +listName + "'] > .list-action"));
		click(listAction);
		click(sortByPrioBtn);
	}
	
	// sort tasks by due date
	@Step("sort all tasks of the list: {listName} by due date")
	public void sortTasksByDueDate (String listName) throws InterruptedException {
		openList(listName);
		WebElement listAction = driver.findElement(By.cssSelector("[title='" +listName + "'] > .list-action"));
		click(listAction);
		click(sortByDueDateBtn);
	}
	
	
	// Mark task
	@Step("Mark the task: {taskName}")
	public void markTask(String taskName) {
		for (WebElement el : list) {
			if (getText(el).contains(taskName)) {
				WebElement taskCheckbox = el.findElement(By.cssSelector("[type='checkbox']"));
				click(taskCheckbox);
				break;	
			}
		}
	}

// VALIDATIONS
	// validation for add task
	@Step("validate the task: {taskName} was added")
	public boolean isTaskAdded(String taskName) throws InterruptedException {
		sleep(1000);
		for (WebElement el : titlesList) {
			if (getText(el).equalsIgnoreCase(taskName)) {
				return true;
			}
		}
		return false;
	}
	
	// validation for delete task
	@Step("validate the task: {taskName} was deleted")
	public boolean isTaskDeleted(String taskName) throws InterruptedException {
		sleep(1000);
		for (WebElement el : titlesList) {
			if (getText(el).equalsIgnoreCase(taskName)) {
				return false;
			}
		}
		return true;
	}
	
	// validation - check if I am in the right page
	@Step("Validate this is Tasks Page")
	public boolean isThisTasksPage() {
		if (getText(subTitle).toLowerCase().contains("tasks")) {
			return true;
		} else {
			return false;
		}
	}
	
	// validation for search
	@Step("Validate that the search has succeeded and shows the right amount of results: {num}")
	public boolean isSearchSucceeded(int num) {
		String total = getText(totalTasks);
		int totalInt = Integer.valueOf(total);
		if (totalInt == num) {
			return true;
		} else {
			return false;
		}
	}
	
	// validation for add new list
	@Step("validate the list: {name} was added")
	public boolean isNewListAdded (String name) {
		for (WebElement el : listsList) {
			String taskName = getText(el);
			if (taskName.equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	// validation for delete list
	@Step("validate the list: {name} was deleted")
	public boolean isListDeleted (String name) {
		for (WebElement el : listsList) {
			String taskName = getText(el);
			if (taskName.equalsIgnoreCase(name)) {
				return false;
			}
		}
		return true;
	}
	
	// validation for marking a task in a list
	@Step("Validate the task: {taskName} was marked")
	public boolean isTaskMarked (String taskName) throws InterruptedException {
		driver.navigate().refresh();
		for (WebElement el : list) {
			WebElement taskCheckbox = el.findElement(By.cssSelector("[type='checkbox']"));
			if (getText(el).contains(taskName)) {
				sleep(1000);
				if (taskCheckbox.getAttribute("checked") == null) {
					return false;
				}
			}
		}
		return true;
	}
	
}
