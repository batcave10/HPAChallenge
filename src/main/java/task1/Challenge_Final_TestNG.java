package task1;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Challenge_Final_TestNG {

	static WebDriver driver;
	static WebDriverWait wait;
	
	@BeforeTest
	public void setup() {
	String projectpath = System.getProperty("user.dir");

		//chrome driver initialization
		System.setProperty("webdriver.chrome.driver", projectpath +"//drivers//chromedriver//chromedriver.exe");
		driver = new ChromeDriver();

		//wait and size
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,50);

		//get url
		String url = "http://hpa.services/automation-challenge/";
		driver.get(url);
	}
	@Test
	public void Test1() {
		driver.findElement(By.id("Box1")).click();
		isAlertPresent();

		driver.findElement(By.id("Box3")).sendKeys(Keys.TAB);
		isAlertPresent();

		String optionVal = driver.findElement(By.id("optionVal")).getText();
		String i = "1";
		String j = "2";

		if(optionVal.equals(i)) {
			driver.findElement(By.xpath("//input[@value='1']")).click();

		}
		else if(optionVal.equals(j)){
			driver.findElement(By.xpath("//input[@value='2']")).click();
		}

		isAlertPresent();

		String drpdowntxt = driver.findElement(By.id("selectionVal")).getText();

		Select drpCountry = new Select(driver.findElement(By.xpath("//select[@onchange='Step4(this);']")));
		drpCountry.selectByVisibleText(drpdowntxt);
		isAlertPresent();
		
		WebElement fromDate= driver.findElement(By.id("formDate"));
		String strFromDate = fromDate.getAttribute("placeholder");
		fromDate.sendKeys(strFromDate);
		
		WebElement fromCity = driver.findElement(By.id("formCity"));
		String strfromcity = fromCity.getAttribute("placeholder");
		fromCity.sendKeys(strfromcity);
		
		WebElement fromState = driver.findElement(By.id("formState"));
		String fromstate = fromState.getAttribute("placeholder");
		fromState.sendKeys(fromstate);
		
		WebElement fromCountry = driver.findElement(By.id("formCountry"));
		String strfromCountry = fromCountry.getAttribute("placeholder");
		fromCountry.sendKeys(strfromCountry);
		
		WebElement fromDate1 = driver.findElement(By.xpath("(//input[@id='formDate'])[2]"));
		String strfromDate1 = fromDate1.getAttribute("placeholder");
		fromDate1.sendKeys(strfromDate1);
		
		WebElement fromCity1 = driver.findElement(By.xpath("(//input[@id='formCity'])[2]"));
		String strfromCity1 = fromCity1.getAttribute("placeholder");
		fromCity1.sendKeys(strfromCity1);
		
		WebElement fromState1 = driver.findElement(By.xpath("(//input[@id='formState'])[2]"));
		String strfromState1 = fromState1.getAttribute("placeholder");
		fromState1.sendKeys(strfromState1);
		
		WebElement formCountry1 = driver.findElement(By.xpath("(//input[@id='formCountry'])[2]"));
		String strformCountry1 = formCountry1.getAttribute("placeholder");
		formCountry1.sendKeys(strformCountry1);
		
		WebElement fromdate2 = driver.findElement(By.xpath("(//input[@id='formDate'])[3]"));
		String strfromdate2 = fromdate2.getAttribute("placeholder");
		fromdate2.sendKeys(strfromdate2);
		
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		isAlertPresent();

		String result = driver.findElement(By.id("formResult")).getText();

		String linenum = driver.findElement(By.xpath("//*[@id=\"lineNum\"]")).getText();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		//WebElement Element = driver.findElement(By.xpath("//tbody//tr['"+linenum+"']//td[1]"));
		WebElement Element = driver.findElement(By.xpath("(//input[@type='text'])['"+linenum+"']"));
		//(//input[@type='text'])[35]
		js.executeScript("arguments[0].scrollIntoView();", Element);

		WebElement element = driver.findElement(By.xpath("(//input[@type='text'])["+linenum+"]"));
		element.clear();
		element.sendKeys(result);
		element.sendKeys(Keys.TAB);

		isAlertPresent();

		driver.findElement(By.id("Box7")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		isAlertPresent();

		driver.findElement(By.id("Box8")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		isAlertPresent();

		driver.findElement(By.id("Box9")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		isAlertPresent();

		driver.findElement(By.id("Box10")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		isAlertPresent();
	}
	@AfterTest
	public void tearDown() {
		System.out.println("Completed");

		driver.close();
		driver.quit();
	}

	public static boolean isAlertPresent(){

		boolean presentFlag = false;

		try {
			Alert alert = driver.switchTo().alert();
			presentFlag = true;
			alert.accept();

		} catch (NoAlertPresentException ex) {
			ex.printStackTrace();
		}
		return presentFlag;
	}
}