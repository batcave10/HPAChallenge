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

public class Challenge_Final {

	static WebDriver driver;
	static WebDriverWait wait;
	
	public static void main(String[] args) {
		String projectpath = System.getProperty("user.dir");
		System.out.println("Projectpath is " + projectpath);

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
		System.out.println(optionVal);
		String i = "1";
		String j = "2";
		
		if(optionVal.equals(i)) {
			driver.findElement(By.xpath("//*[@id=\"Box2\"]/input[1]")).click();
		}
		else if(optionVal.equals(j)){
			driver.findElement(By.xpath("//*[@id=\"Box2\"]/input[2]")).click();
		}
		
		isAlertPresent();
		
		String drpdowntxt = driver.findElement(By.id("selectionVal")).getText();
		System.out.println(drpdowntxt);
		
		Select drpCountry = new Select(driver.findElement(By.xpath("//*[@id=\"BoxParagraph4\"]/select")));
		drpCountry.selectByVisibleText(drpdowntxt);
		isAlertPresent();
		
		driver.findElement(By.id("formDate")).sendKeys("2017-05-04");
		driver.findElement(By.id("formCity")).sendKeys("Nashville");
		driver.findElement(By.id("formState")).sendKeys("Tennessee");
		driver.findElement(By.id("formCountry")).sendKeys("USA");
		
		driver.findElement(By.xpath("//tbody//tr[5]//td[1]//input[1]")).sendKeys("2009-08-26");
		driver.findElement(By.xpath("//tbody//tr[6]//td[1]//input[1]")).sendKeys("Seattle");
		driver.findElement(By.xpath("//tbody//tr[7]//td[1]//input[1]")).sendKeys("Washington");
		driver.findElement(By.xpath("//tbody//tr[8]//td[1]//input[1]")).sendKeys("USA");
		driver.findElement(By.xpath("//tbody//tr[9]//td[1]//input[1]")).sendKeys("2007-10-10");
		
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		isAlertPresent();
		
		String result = driver.findElement(By.id("formResult")).getText();
		System.out.println(result);
		
		String linenum = driver.findElement(By.xpath("//*[@id=\"lineNum\"]")).getText();
		System.out.println(linenum);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(By.xpath("//tbody//tr['"+linenum+"']//td[1]"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
		 
		 driver.findElement(By.xpath("//tbody//tr["+linenum+"]//td[2]//input[1]")).clear();
		 driver.findElement(By.xpath("//tbody//tr["+linenum+"]//td[2]//input[1]")).sendKeys(result);
		 driver.findElement(By.xpath("//tbody//tr["+linenum+"]//td[2]//input[1]")).sendKeys(Keys.TAB);
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