package P_DDF_Orange;

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

import P_DDF1_Github.XLUtility;

public class DataDrivenTest_Orange { 
	
	WebDriver driver; 
	@BeforeClass 
	public void set() throws InterruptedException { 
		
		System.setProperty("webdriver.chrome.driver", "D:\\\\drivers\\\\chromedriver-win64\\\\chromedriver-win64\\\\chromedriver.exe"); 
		
		driver = new ChromeDriver(); 
		
		driver.manage().window().maximize(); 
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
		
		//driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); 
		
		Thread.sleep(2000); 
		
	} 
	
	@Test (dataProvider="dataprovider")
	public void logintest(String UN, String PW, String exp) throws InterruptedException { 
		
		Thread.sleep(2000);
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); 
		
		Thread.sleep(2000);
		
		WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']")); 
		username.clear();
		username.sendKeys(UN); 
		
		Thread.sleep(2000);
		
	    WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));  
	    username.clear();
	    password.sendKeys(PW); 
	    
	    Thread.sleep(2000);
	    
	    WebElement enter = driver.findElement(By.xpath("//button[normalize-space()='Login']")); 
		enter.click(); 
		
		Thread.sleep(2000);
		
		//String exp_title = "OrangeHRM";
		//String act_title = driver.getTitle();
		//System.out.println(act_title); 
		
		if(exp.equals("Valid")) {
			
			if(driver.getPageSource().contains("Dashboard")) { 
				
				Assert.assertTrue(true);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click(); 
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click(); 
				Thread.sleep(2000);
	
			} 
			else {
				Assert.assertTrue(false);
			}
		} 
		else if(exp.equals("Invalid")) {
			
			if(driver.getPageSource().contains("Dashboard")) { 
				
				Assert.assertTrue(false);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click(); 
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//a[@class='oxd-userdropdown-link'])[4]")).click(); 
				Thread.sleep(2000);
				
			} 
			else { 
				
				Assert.assertTrue(true);
			}
		}
	} 
	
	@DataProvider (name="dataprovider") 
	public String [] [] getData() throws IOException { 
		
		//get the data from excel 
		
		String path = "D:\\Testing Class Notes\\OrangeHRM.xlsx"; 
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
	void tearDown() throws InterruptedException {
		
	driver.close(); 
		
	Thread.sleep(2000);
	}

}
