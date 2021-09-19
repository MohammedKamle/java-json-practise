package jsonproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DDTUsingJson {
	
	WebDriver driver ;
	
	@BeforeClass
	public void setUp() {
		// With the help of webDriver manager class we no need to download chromeDriver separately
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
	}
	
	@Test(dataProvider = "dp")
	public void login(String data) {
		// as data is like {username, password}
		String userCredentials[] = data.split(",");
		driver.get("https://demo.nopcommerce.com/login");
		driver.findElement(By.id("Email")).sendKeys(userCredentials[0]);
		driver.findElement(By.id("Password")).sendKeys(userCredentials[1]);
		driver.findElement(By.xpath("//input[@class = 'button-1 login-button']")).click();
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "nopCommerce demo store";
		Assert.assertEquals(actualTitle	, expectedTitle);
	}
	
	@DataProvider(name = "dp")
	public String[] readData() throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader fileReader = new FileReader(".\\jsonFiles\\testdata.json");
		Object obj = jsonParser.parse(fileReader);
		JSONObject userLoginsjsonObject = (JSONObject)obj;
		//getting "userLogins Json array"
		JSONArray userLoginsArray = (JSONArray) userLoginsjsonObject.get("userlogins");
		
		// getting the data from json array to java array
		String arr[] = new String[userLoginsArray.size()];
		for(int i = 0; i < userLoginsArray.size(); i++) {
			JSONObject user = (JSONObject) userLoginsArray.get(i);
		    String username = 	(String)user.get("username");
			String password = (String)user.get("password");
			
			arr[i] = username+","+password;
			
		}
		
	
		
		return arr;
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	
}
