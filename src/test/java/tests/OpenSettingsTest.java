package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.NewTaskPage;
import pageobjects.SettingsPage;
import pageobjects.TasksPage;

public class OpenSettingsTest extends BaseTest {

	@Test
	public void tc01_openSettingsFromTasksPage() {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.openSettings();
		SettingsPage settingsPage = new SettingsPage(driver);
		Assert.assertTrue(settingsPage.isThisSettingsPage());
		settingsPage.goBack();
	}
	
	@Test
	public void tc02_openSettingsFromNewTaskPage () throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.pressOnNewAdvTaskBtn();
		NewTaskPage newTaskPage = new NewTaskPage(driver);
		newTaskPage.openSettings();
		SettingsPage settingsPage = new SettingsPage(driver);
		Assert.assertTrue(settingsPage.isThisSettingsPage());
		settingsPage.goBack();
	}
	
}
