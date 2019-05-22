package qatask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AfterLoginTest {

	WebDriver driver;



	TestData testData;

// to do

	private WebDriverWait wait1;

	public void init(WebDriver driver, TestData testData) throws InterruptedException {

		this.driver = driver;
		this.testData = testData;
		
		wait1 = new WebDriverWait(driver, 30);
		
		test();
	}

	public void test() throws InterruptedException {

		driver.findElement(By.id("submit_save")).click();
		System.out.println("Sign Up button clicked");

		Thread.sleep(15000);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Forgot my password")));
		System.out.println("Insly demo instance start to work");

		String companyname2 = testData.getCompanyname().toLowerCase();
		String expectedurl = ("https://" + companyname2 + ".int.staging.insly.training/login");
		String actualurl = driver.getCurrentUrl();
		if (actualurl.contentEquals(expectedurl)) {
			System.out.println("Url is the same as was entered in the step 2");
		} else {
			System.out.println("* FAILED *");
		}

		driver.findElement(By.id("login_username")).sendKeys(testData.getWorkemail());
		driver.findElement(By.id("login_password")).sendKeys(testData.getSuggestedpassword());
		driver.findElement(By.id("login_submit")).click();
		Thread.sleep(10000);

		String expectedurl2 = ("https://" + companyname2 + ".int.staging.insly.training/dashboard");
		String actualurl2 = driver.getCurrentUrl();
		if (actualurl2.contentEquals(expectedurl2)) {
			System.out.println("Logged in as user: Successfully");
			System.out.println("Dashboard page opened: Successfully");
			System.out.println("- STEP 05: COMPLETE");
		} else {
			System.out.println("* FAILED *");
		}
	}
}
