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

public class Challenge3 {

	static WebDriver driver;
	static WebDriverWait wait;

	public static void main(String[] args) {
		String projectpath = System.getProperty("user.dir");

		//chrome driver initialization
		System.setProperty("webdriver.chrome.driver", projectpath +"//drivers//chromedriver//chromedriver.exe");
		driver = new ChromeDriver();

		//wait and size
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,50);

		//get url
		String url = "http://hpa.services/automation-challenge/";
		driver.get(url);

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
		
		//String date = "2017-05-04";
		//driver.findElement(By.id("formDate")).sendKeys("2017-05-04");
		WebElement frmdate1= driver.findElement(By.xpath("(//input[@id='formDate'])[1]"));
		String frmdate = frmdate1.getAttribute("placeholder");
		frmdate1.sendKeys(frmdate);
		String s=driver.findElement(By.xpath("(//input[@id='formDate'])[1]")).getAttribute("placeholder"); 
		System.out.println(s);
		driver.findElement(By.xpath("(//input[@id='formDate'])[1]")).sendKeys(s);
		
		driver.findElement(By.id("formCity")).sendKeys("Nashville");
		driver.findElement(By.id("formState")).sendKeys("Tennessee");
		driver.findElement(By.id("formCountry")).sendKeys("USA");
		driver.findElement(By.xpath("//input[@type='text' and @id='formDate' and @placeholder='2009-08-26']")).sendKeys("2009-08-26");
		driver.findElement(By.xpath("//input[@type='text' and @id='formCity' and @placeholder='Seattle']")).sendKeys("Seattle");
		driver.findElement(By.xpath("//input[@type='text' and @id='formState' and @placeholder='Washington']")).sendKeys("Washington");
		driver.findElement(By.xpath("(//input[@id='formCountry'])[2]")).sendKeys("USA");
		driver.findElement(By.xpath("//input[@type='text' and @id='formDate' and @placeholder='2007-10-10']")).sendKeys("2007-10-10");
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		isAlertPresent();

		String result = driver.findElement(By.id("formResult")).getText();

		String linenum = driver.findElement(By.xpath("//*[@id=\"lineNum\"]")).getText();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(By.xpath("//tbody//tr['"+linenum+"']//td[1]"));
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