package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	public void addNewSimpleTask (String taskName) throws InterruptedException {
		fillText(newSimpleTaskField, taskName);
		click(addSimpleTaskBtn);
		waitingForLoading();
	}

	// delete task
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
	public void pressOnNewAdvTaskBtn() throws InterruptedException {
		click(addNewAdvTaskBtn);
		sleep(500);
	}
	
	// search
	public void search(String text) throws InterruptedException {
		fillText(searchField, text);
		sleep(1000);
		System.out.println("For the search: " + text + " There are " + getText(totalTasks) + " Results");
	}
	
	// create new list
	public void createNewList(String listName) throws InterruptedException {
		click(newList);
		driver.switchTo().alert().sendKeys(listName);
		driver.switchTo().alert().accept();
		sleep(1000);
	}
	
	// Delete list
	public void deleteList (String listName) throws InterruptedException {
		openList(listName);
		WebElement listAction = driver.findElement(By.cssSelector("[title='" +listName + "'] > .list-action"));
		click(listAction);
		click(deleteListBtn);
		driver.switchTo().alert().accept();
		sleep(1000);
	}
	
	// open list
	public void openList(String listName) throws InterruptedException {
		sleep(1000);
		WebElement listTab = driver.findElement(By.cssSelector("[title='" +listName + "']"));
		click(listTab);
	}
	
	// change list name
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
	public void sortTasksByPriority (String listName) throws InterruptedException {
		openList(listName);
		WebElement listAction = driver.findElement(By.cssSelector("[title='" +listName + "'] > .list-action"));
		click(listAction);
		click(sortByPrioBtn);
	}
	
	// sort tasks by due date
	public void sortTasksByDueDate (String listName) throws InterruptedException {
		openList(listName);
		WebElement listAction = driver.findElement(By.cssSelector("[title='" +listName + "'] > .list-action"));
		click(listAction);
		click(sortByDueDateBtn);
	}
	
	
	// Mark task
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
	public boolean isThisTasksPage() {
		if (getText(subTitle).toLowerCase().contains("tasks")) {
			return true;
		} else {
			return false;
		}
	}
	
	// validation for search
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
