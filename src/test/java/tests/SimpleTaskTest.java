package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.TasksPage;

public class SimpleTaskTest extends BaseTest{
	
	@Test
	public void tc01_addSimpleTask() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.addNewSimpleTask("shirazTask");
		Assert.assertTrue(tasksPage.isTaskAdded("shirazTask"));
	}
	
	@Test
	public void tc02_addSimpleTasks() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.addNewSimpleTask("shiraz1");
		Assert.assertTrue(tasksPage.isTaskAdded("shiraz1"));
		tasksPage.addNewSimpleTask("shiraz2");
		Assert.assertTrue(tasksPage.isTaskAdded("shiraz2"));
	}
	
	@Test
	public void tc03_deleteTask() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.deleteTask("shiraz1");
		driver.navigate().refresh();
		Assert.assertTrue(tasksPage.isTaskDeleted("shiraz1"));
	}
	
	@Test
	public void tc04_search() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.search("shiraz2");
		Assert.assertTrue(tasksPage.isSearchSucceeded(1));
	}
	
	@Test
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
