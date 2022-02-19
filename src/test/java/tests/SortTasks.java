package tests;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.TasksPage;

public class SortTasks extends BaseTest {

	@Test(description="sort tasks by priority")
	@Description("sort all tasks in the chosen list by priority")
	public void tc01_sortByPriority () throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.sortTasksByPriority("shirazNew");	
	}
	
	@Test(description="sort tasks by due date")
	@Description("sort all tasks in the chosen list by due date")
	public void tc02_sortByDueDate() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.sortTasksByDueDate("shirazNew");
	}
}
