package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.TasksPage;

public class SimpleTaskTest extends BaseTest{
	
	@Test(description="add new simple task")
	@Description("add new simple task and make sure it was added")
	public void tc01_addSimpleTask() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.addNewSimpleTask("shirazTask");
		Assert.assertTrue(tasksPage.isTaskAdded("shirazTask"));
	}
	
	@Test(description="add 2 new simple tasks")
	@Description("add two new simple tasks and make sure they were added")
	public void tc02_addSimpleTasks() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.addNewSimpleTask("shiraz1");
		Assert.assertTrue(tasksPage.isTaskAdded("shiraz1"));
		tasksPage.addNewSimpleTask("shiraz2");
		Assert.assertTrue(tasksPage.isTaskAdded("shiraz2"));
	}
	
	@Test(description = "delete task")
	@Description("delete a task and make sure it was deleted")
	public void tc03_deleteTask() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.deleteTask("shiraz1");
		driver.navigate().refresh();
		Assert.assertTrue(tasksPage.isTaskDeleted("shiraz1"));
	}
	
	@Test(description = "search a task")
	@Description("search a task and make sure you get the right amount of results")
	public void tc04_search() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.search("shiraz2");
		Assert.assertTrue(tasksPage.isSearchSucceeded(1));
	}
	
	@Test(description = "search an unexisted task")
	@Description("search an unexisted task and make sure you get 0 results")
	public void tc05_search() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.search("shiraz1");
		Assert.assertTrue(tasksPage.isSearchSucceeded(0));
	}
	
	@Test
	public void tc06_markTask() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.markTask("shirazTest");
		Assert.assertTrue(tasksPage.isTaskDeleted("shirazTest"));
	}
}
