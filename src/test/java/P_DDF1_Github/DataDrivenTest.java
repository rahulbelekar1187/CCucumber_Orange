package P_DDF1_Github;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest { 
	
WebDriver driver; 
	
	@BeforeClass 
	public void setup() throws InterruptedException { 
		
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); 
	      
	      driver = new ChromeDriver(); 
	      
	      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
	      
	      //driver.get("file:///C:/Users/Rahul%20Belekar/Desktop/HTML/checkbox5.html"); 
	      
	      driver.manage().window().maximize(); 
	      
	      Thread.sleep(2000);
	}
	
	@Test (dataProvider="LoginData")
	public void LoginTest(String user, String pw, String exp) throws InterruptedException { 
		
		driver.get("https://github.com/"); 
		
		WebElement LI = driver.findElement(By.xpath("//a[@class='HeaderMenu-link HeaderMenu-link--sign-in flex-shrink-0 no-underline d-block d-lg-inline-block border border-lg-0 rounded rounded-lg-0 p-2 p-lg-0']"));
		LI.click(); 
		
		WebElement UN = driver.findElement(By.xpath("//input[@class='form-control input-block js-login-field']")); 
		UN.clear();
		UN.sendKeys(user); 
		
		WebElement PW = driver.findElement(By.xpath("//input[@id='password']")); 
		PW.clear(); 
		PW.sendKeys(pw); 
		
		WebElement EN = driver.findElement(By.xpath("//input[@type='submit']")); 
		EN.click(); 

		String exp_title = "GitHub";
		String act_title = driver.getTitle();
		System.out.println(act_title); 
		
		if(exp.equals("Valid")) {
			
			if(act_title.equals(exp_title)) { 
				driver.findElement(By.xpath("(//summary[@class='Header-link'])[2]")).click(); 
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
				Assert.assertTrue(true);
			} 
			else {
				Assert.assertTrue(false);
			}
		} 
		else if(exp.equals("Invalid")) {
			
			if(act_title.equals(exp_title)) {
				driver.findElement(By.xpath("(//summary[@class='Header-link'])[2]")).click(); 
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
				Assert.assertTrue(false);
			} 
			else { 
				
				Assert.assertTrue(true);
			}
		}
		
	} 
	
	@DataProvider (name="LoginData") 
	public String [][] getData() throws IOException 
	{ 
		
		//get the data from excel 
		
		String path = "D:\\Testing Class Notes\\nopcommerce.xlsx"; 
		XLUtility xlutil = new XLUtility(path); 
		int totalrows = xlutil.getRowCount("Sheet1"); 
		int totalcols = xlutil.getCellCount("Sheet1",1); 
		
		String loginData[][]=new String[totalrows][totalcols]; 
		
		for(int i=1;i<=totalrows;i++) 
		 {
			for(int j=0;j<totalcols;j++) 
			{
				loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
	} 
	
	@AfterClass 
	void tearDown() {
		
		driver.close(); 
		
	}


}
