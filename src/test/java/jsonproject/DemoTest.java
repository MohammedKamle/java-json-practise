package jsonproject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoTest {
	
	private WebDriver driver ;
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		driver.navigate().to("https://in.linkedin.com");
		driver.manage().window().maximize();
	}
	
	@Test
	public void testDemo() throws InterruptedException {
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		
		WebElement element = driver.findElement(By.linkText("Post a job"));
		//js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		
	}
	
	@AfterClass
	public void tearDown() {
//		driver.close();
//		driver.quit();
	}

}
