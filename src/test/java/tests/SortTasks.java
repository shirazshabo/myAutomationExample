package tests;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageobjects.TasksPage;

public class SortTasks extends BaseTest {

	@Severity(SeverityLevel.NORMAL)
	@Test(description="sort tasks by priority")
	@Description("sort all tasks in the chosen list by priority")
	public void tc01_sortByPriority () throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.sortTasksByPriority("shirazNew");	
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Test(description="sort tasks by due date")
	@Description("sort all tasks in the chosen list by due date")
	public void tc02_sortByDueDate() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.sortTasksByDueDate("shirazNew");
	}
}
