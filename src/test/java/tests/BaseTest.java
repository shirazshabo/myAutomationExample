package tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.TasksPage;
import utils.Utils;

public class BaseTest {
	WebDriver driver;

	public void sleep(long mils) throws InterruptedException {
		Thread.sleep(mils);
	}

	@BeforeClass
	public void setup(ITestContext testContext) throws InterruptedException {
		// define the driver 
		//System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver.exe");
		driver = WebDriverManager.chromedriver().driverVersion("97").create(); 
		testContext.setAttribute("WebDriver", this.driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//		Utils u = new Utils();
		//		driver.get(u.readProperty("url"));
		driver.get(Utils.readProperty("url"));

		// open the site
		// driver.get("http://www.mytinytodo.net/demo/");
		TasksPage tasksPage = new TasksPage(driver);
		tasksPage.waitingForLoading();
	}

	@AfterClass 
	public void tearDown() throws InterruptedException {
		sleep(2000);
		driver.quit();
	}

//	@AfterMethod
//	public void failedTest(ITestResult result) {
//		//check if the test failed
//		if (result.getStatus() == ITestResult.FAILURE ){
//			TakesScreenshot ts = (TakesScreenshot)driver;
//			File srcFile = ts.getScreenshotAs(OutputType.FILE);
//			try {
//				FileUtils.copyFile(srcFile, new File("./ScreenShots/"+result.getName()+".jpg"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			//result.getname() method will give you current test case name. 
//			//./ScreenShots/ tell you that, in your current directory, create folder ScreenShots. dot represents current directory
//		}
//	}
}
