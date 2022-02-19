package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.NewTaskPage;
import pageobjects.TasksPage;

public class AdvTaskPage extends BaseTest {
	
	@Test(description = "Open Advanced Task Page")
	@Description("Press on New Advanced Tast button and check if New Task Page opens")
	public void tc01_openAdvTaskPage () throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.pressOnNewAdvTaskBtn();
		NewTaskPage newTaskPage = new NewTaskPage(driver);
		Assert.assertTrue(newTaskPage.isThisNewTaskPage());
	}
	
	@Test(description = "Create New Advanced Task")
	@Description("create new advanced task with all the details and check if it was added")
	public void tc02_createNewAdvTask() throws InterruptedException {
		NewTaskPage newTaskPage = new NewTaskPage(driver);
		newTaskPage.createNewAdvTask("2", "15.01.22", "My Task", "no note is needed", "automation practice");
		TasksPage tasksPage = new TasksPage(driver);
		Assert.assertTrue(tasksPage.isTaskAdded("My Task"));
	}
	
	@Test(description = "Cancel the creation of new advanced task")
	@Description("Press on New Advanced Tast button and than cancel the creation")
	public void tc03_cancelCreation() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.pressOnNewAdvTaskBtn();
		NewTaskPage newTaskPage = new NewTaskPage(driver);
		newTaskPage.cancel();
		tasksPage = new TasksPage(driver);
		Assert.assertTrue(tasksPage.isThisTasksPage());
	}
	
	@Test(description = "Press on Go Back button")
	public void tc04_goBack() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.pressOnNewAdvTaskBtn();
		NewTaskPage newTaskPage = new NewTaskPage(driver);
		newTaskPage.goBack();
		tasksPage = new TasksPage(driver);
		Assert.assertTrue(tasksPage.isThisTasksPage());
	}
}
