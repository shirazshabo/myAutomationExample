package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.NewTaskPage;
import pageobjects.SettingsPage;
import pageobjects.TasksPage;

public class OpenSettingsTest extends BaseTest {

	@Test(description = "Open Settings from Tasks Page")
	@Description("open Tasks Page and press on Settings button, than make sure settings page is open")
	public void tc01_openSettingsFromTasksPage() {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.openSettings();
		SettingsPage settingsPage = new SettingsPage(driver);
		Assert.assertTrue(settingsPage.isThisSettingsPage());
		settingsPage.goBack();
	}
	
	@Test(description = "Open Settings from New Task Page")
	@Description("open New Task Page and press on Settings button, than make sure settings page is open")
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
