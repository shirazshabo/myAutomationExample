package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.NewTaskPage;
import pageobjects.TasksPage;

public class AdvTaskPage extends BaseTest {
	
	@Test
	// open
	public void tc01_openAdvTaskPage () throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.pressOnNewAdvTaskBtn();
		NewTaskPage newTaskPage = new NewTaskPage(driver);
		Assert.assertTrue(newTaskPage.isThisNewTaskPage());
	}
	
	@Test
	public void tc02_createNewAdvTask() throws InterruptedException {
		NewTaskPage newTaskPage = new NewTaskPage(driver);
		newTaskPage.createNewAdvTask("2", "15.01.22", "My Task", "no note is needed", "automation practice");
		TasksPage tasksPage = new TasksPage(driver);
		Assert.assertTrue(tasksPage.isTaskAdded("My Task"));
	}
	
	@Test
	public void tc03_cancelCreation() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.pressOnNewAdvTaskBtn();
		NewTaskPage newTaskPage = new NewTaskPage(driver);
		newTaskPage.cancel();
		tasksPage = new TasksPage(driver);
		Assert.assertTrue(tasksPage.isThisTasksPage());
	}
	
	@Test
	public void tc04_goBack() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.pressOnNewAdvTaskBtn();
		NewTaskPage newTaskPage = new NewTaskPage(driver);
		newTaskPage.goBack();
		tasksPage = new TasksPage(driver);
		Assert.assertTrue(tasksPage.isThisTasksPage());
	}
}
