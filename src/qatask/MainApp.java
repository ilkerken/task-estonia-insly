package qatask;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MainApp {

	// Global variables for excel import
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFCell cell;
	public File src;

	// Selenium  driver variables 
	WebDriver driver;
	WebDriverWait wait1;

	// Variables for excel data
	String companyname;
	String workemail;
	String suggestedpassword;

	// To share data between classes
	TestData testData = new TestData();

	public static void main(String[] args) throws InterruptedException, IOException {

		MainApp testOp = new MainApp();
		testOp.init();
		Thread.sleep(2000);

		// STEP 01
		testOp.checkBeforePageLoad();

		// STEP 02
		testOp.fillFormValues();

		// STEP 03
		testOp.setAdminValues();

		// STEP 04
		testOp.setTermsAndConditions();

		// STEP 05
		testOp.actionsAfterLogin();

		Thread.sleep(2000);
		// Close browser
		testOp.close();

	}

	public void init() {

		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

		driver = new ChromeDriver();
		wait1 = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();

	}

	public void checkBeforePageLoad() {

		// STEP 01

		String baseUrl = "http:\\signup.int.staging.insly.training";
		String expectedTitle = "Insly";
		String actualTitle = "";
		String expectedTag = "Sign up and start using";

		driver.get(baseUrl);
		actualTitle = driver.getTitle();
		if (actualTitle.contentEquals(expectedTitle)) {
			System.out.println("Title check: Passed!");
		} else {
			System.out.println("Title check:  * FAILED *");
		}

		String actualTag = driver.findElement(By.tagName("h1")).getText();
		if (actualTag.contentEquals(expectedTag)) {
			System.out.println("Tag check: Passed!");
		} else {
			System.out.println("Tag check:  * FAILED *");
		}

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("broker_admin_email")));
		System.out.println("Work E-Mail field: Loaded");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("broker_admin_name")));
		System.out.println("Account Manager Name field: Loaded");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("broker_person_password")));
		System.out.println("Password field: Loaded");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("broker_person_password_repeat")));
		System.out.println("Password repeat field: Loaded");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("broker_admin_phone")));
		System.out.println("Phone field: Loaded");

		System.out.println("Administrator account details form: Loaded");

		String termsandconditionsexpected = "I agree to the terms and conditions";
		String termsandconditionsactual = driver
				.findElement(By.xpath("//*[@id=\"field_terms\"]/td[2]/div/div/label[1]")).getText();
		if (termsandconditionsexpected.contentEquals(termsandconditionsactual)) {
			System.out.println("Terms and Conditions field: Loaded");
		} else {
			System.out.println("* FAILED *");
		}

		String privacypolicyexpected = "I agree to the privacy policy";
		String privacypolicyactual = driver.findElement(By.xpath("//*[@id=\"field_terms\"]/td[2]/div/div/label[2]"))
				.getText();
		if (privacypolicyexpected.contentEquals(privacypolicyactual)) {
			System.out.println("Privacy Policy field: Loaded");
		} else {
			System.out.println("* FAILED *");
		}

		String processingpersonaldataexpected = "I agree to the processing and storage of my personal data";
		String processingpersonaldataactual = driver
				.findElement(By.xpath("//*[@id=\"field_terms\"]/td[2]/div/div/label[3]")).getText();
		if (processingpersonaldataexpected.contentEquals(processingpersonaldataactual)) {
			System.out.println("Processing Personal Data field: Loaded");
		} else {
			System.out.println("* FAILED *");
		}
		System.out.println("Terms and conditions form: Loaded");

		System.out.println("- STEP 01: COMPLETE");
	}

	public void fillFormValues() throws IOException, InterruptedException {

		// STEP 02

		// File importing

		src = new File("resources/TestData.xlsx");
		FileInputStream finput = new FileInputStream(src);
		workbook = new XSSFWorkbook(finput);
		sheet = workbook.getSheetAt(0);

		// Data import from excel.

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("broker_name")));

		// Filling Company name
		cell = sheet.getRow(0).getCell(0);
		companyname = cell.getStringCellValue() + "" + System.currentTimeMillis();
		testData.setCompanyname(companyname);
		driver.findElement(By.id("broker_name")).sendKeys(companyname);
		System.out.println("Company name: Filled");

		// Selecting Country
		cell = sheet.getRow(0).getCell(1);
		String country = cell.getStringCellValue();
		driver.findElement(By.id("broker_address_country")).sendKeys(country);
		System.out.println("Country: Selected");

		// Checking web address field
		Thread.sleep(3000);
		WebElement webaddressBox = driver.findElement(By.id("broker_tag"));
		String textWebAddress = webaddressBox.getAttribute("value");

		if (textWebAddress.isEmpty()) {
			System.out.println("Web Address field is EMPTY");
		} else {
			System.out.println("Web Address field is Full");
		}

		// Selecting Company profile
		cell = sheet.getRow(0).getCell(2);
		String companyprofile = cell.getStringCellValue();
		driver.findElement(By.id("prop_company_profile")).sendKeys(companyprofile);
		System.out.println("Company_profile: Selected");

		// Selecting Number of employees
		cell = sheet.getRow(0).getCell(3);
		String numberofemployees = cell.getStringCellValue();
		driver.findElement(By.id("prop_company_no_employees")).sendKeys(numberofemployees);
		System.out.println("Number of employees: Selected");

		// Selecting Describing Yourself
		cell = sheet.getRow(0).getCell(4);
		String persondescription = cell.getStringCellValue();
		driver.findElement(By.id("prop_company_person_description")).sendKeys(persondescription);
		System.out.println("Describing yourself: Selected");

		System.out.println("- STEP 02: COMPLETE");
	}

	public void setAdminValues() throws InterruptedException {

		// STEP 03
		
		// Filling work email on administrator account part
		cell = sheet.getRow(0).getCell(5);
		workemail = cell.getStringCellValue();
		workemail = workemail.replace("@", System.currentTimeMillis()+"@");
		testData.setWorkemail(workemail);
		driver.findElement(By.id("broker_admin_email")).sendKeys(workemail);
		System.out.println("Work e-mail: Entered.");

		// Filling account manager name
		cell = sheet.getRow(0).getCell(6);
		String managername = cell.getStringCellValue();
		driver.findElement(By.id("broker_admin_name")).sendKeys(managername);
		System.out.println("Account manager name: Entered.");


		// suggest a secure password" and remember it
		driver.findElement(By.linkText("suggest a secure password")).click();
		Thread.sleep(2000);
		suggestedpassword = driver.findElement(By.xpath("//*[@id=\"insly_alert\"]/b")).getText();
		
		testData.setSuggestedpassword(suggestedpassword);
		System.out.println("suggestedpassword:" + suggestedpassword);

		// Suggested password pop-up closed
		WebElement close = driver.findElement(By.xpath("/html/body/div[3]/div[1]/a"));
		close.click();
		System.out.println("Suggested password pop-up: closed");

		// Filling phone number
		WebElement broker_admin_phone = driver.findElement(By.id("broker_admin_phone"));
		broker_admin_phone.clear();
		System.out.println("Phone number field: Cleared.");
		cell = sheet.getRow(0).getCell(9);
		String phone = cell.getStringCellValue();
		driver.findElement(By.id("broker_admin_phone")).sendKeys(phone);
		System.out.println("Phone number: Entered.");

		System.out.println("- STEP 03: COMPLETE");
	}

	public void setTermsAndConditions() throws InterruptedException {

		// STEP 04

		// Ticking all Terms and conditions and openning/closing privacy policy
		WebElement privacypolicy = driver.findElement(By.xpath("//*[@id=\"field_terms\"]/td[2]/div/div/label[2]/a"));
		privacypolicy.click();
		Thread.sleep(2000);
		System.out.println("Privacy policy pop-up: opened");

		Thread.sleep(1000);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"document-content\"]/h3[11]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Privacy policy pop-up: scrolled");

		Thread.sleep(1000);
		WebElement closeprivacypolicy = driver.findElement(By.xpath("/html/body/div[4]/div[1]/a"));
		closeprivacypolicy.click();
		System.out.println("Privacy policy pop-up: closed");

		Thread.sleep(2000);
		WebElement chktermsandconditions = driver
				.findElement(By.xpath("//*[@id=\"field_terms\"]/td[2]/div/div/label[1]/span"));
		chktermsandconditions.click();
		System.out.println("Terms and conditions: Marked.");

		WebElement chkprivacypolicy = driver
				.findElement(By.xpath("//*[@id=\"field_terms\"]/td[2]/div/div/label[2]/span"));
		chkprivacypolicy.click();
		System.out.println("Privacy policy: Marked.");

		WebElement chkdataprocessing = driver
				.findElement(By.xpath("//*[@id=\"field_terms\"]/td[2]/div/div/label[3]/span"));
		chkdataprocessing.click();
		System.out.println("Data processing: Marked.");

		System.out.println("- STEP 04: COMPLETE");
	}

	public void actionsAfterLogin() throws InterruptedException {

		// STEP 05

		new AfterLoginTest().init(driver, testData);
	}

	public void close() throws InterruptedException {

		// Closing browser
		driver.quit();
	}
}