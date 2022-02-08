package tests;

import org.testng.annotations.Test;

import pageobjects.TasksPage;

public class SortTasks extends BaseTest {

	@Test
	public void tc01_sortByPriority () throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.sortTasksByPriority("shirazNew");	
	}
	
	@Test
	public void tc02_sortByDueDate() throws InterruptedException {
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.sortTasksByDueDate("shirazNew");
	}
}
