package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.NewTaskPage;
import pageobjects.TasksPage;

public class ListTest extends BaseTest{
	@Test(description = "Create new list")
	@Description("Create new list and check it was added")
	public void tc01_createNewList() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.createNewList("shiraz");
		Assert.assertTrue(tasksPage.isNewListAdded("shiraz"));
		sleep(1000);
	}
	
	@Test(description = "Delete List")
	@Description("Delete list and check it was deleted")
	public void tc02_deleteList () throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.deleteList("shiraz");
		Assert.assertTrue(tasksPage.isListDeleted("shiraz"));
		sleep(1000);
	}
	
	@Test(description = "Add new simple task to a specific list")
	@Description("choose specific list and add to it new simple task")
	public void tc03_addSimpleTasksToList() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.createNewList("shiraz");
		Assert.assertTrue(tasksPage.isNewListAdded("shiraz"));
		tasksPage.openList("shiraz");
		for (int i = 1; i <= 5; i++) {
			tasksPage.addNewSimpleTask("My Task " + i);
			Assert.assertTrue(tasksPage.isTaskAdded("my task " + i));
		}
	}
	
	@Test(description = "Add new advanced task (prio2) to a specific list")
	@Description("choose specific list and add to it new advanced task with priority 2")
	public void tc04_addAdvTasksToListPrio2() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		for (int i = 6; i <= 8; i++) {
			tasksPage.pressOnNewAdvTaskBtn();
			NewTaskPage newTaskPage = new NewTaskPage(driver);
			newTaskPage.createNewAdvTask("2", "15.01.22", "My Task " + i, "note", "automation");
			tasksPage = new TasksPage(driver);
			Assert.assertTrue(tasksPage.isTaskAdded("my task " + i));
		}
	}
	
	@Test(description = "Add new advanced task (prio1) to a specific list")
	@Description("choose specific list and add to it new advanced task with priority 1")
	public void tc05_addAdvTasksToListPrio1() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		for (int i = 9; i <= 10; i++) {
			tasksPage.pressOnNewAdvTaskBtn();
			NewTaskPage newTaskPage = new NewTaskPage(driver);
			newTaskPage.createNewAdvTask("1", "07.01.22", "My Task " + i, "note", "QA");
			tasksPage = new TasksPage(driver);
			Assert.assertTrue(tasksPage.isTaskAdded("my task " + i));
		}
	}
	
	@Test
	public void tc06_changeListName() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.changeListName("shiraz", "shirazNew");
		Assert.assertTrue(tasksPage.isNewListAdded("shirazNew"));
	}
	
	@Test
	public void tc07_markTasks() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.openList("shirazNew");
		tasksPage.markTask("My Task 7");
		Assert.assertTrue(tasksPage.isTaskMarked("My Task 7"));
	}
}
