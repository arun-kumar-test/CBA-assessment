package CBA;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class cBA {

		public static void main(String[] args) throws InterruptedException {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.navigate().to("https://www.commbank.com.au/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//Click repayment calculator
			WebElement repaymentcalc = driver.findElement(By.xpath("//a[contains(text(),'Repayments calculator')]"));
			repaymentcalc.click();
			
			//Repayment 1 parameters
			WebElement borrow1 = driver.findElement(By.xpath("//input[@id='amount']"));
			borrow1.clear();
			borrow1.sendKeys("150000");
			WebElement years1 = driver.findElement(By.xpath("//input[@id='term']"));
			years1.clear();
			years1.sendKeys("25");
			WebElement clickCalc = driver.findElement(By.xpath("//button[@id='submit']"));
			clickCalc.click();
			WebElement repaymentValue1 = driver.findElement(By.xpath("//div[contains(@class, 'row repayment-results')]/div/div/div/div/div/p/span/span/span"));
			//WebElement repaymentValue1 = driver.findElement(By.xpath("//div[contains(@class, 'row repayment-results')] and .//span[contains(@class,'h3')]"));
			//WebElement repaymentValue1 = driver.findElement(By.cssSelector("#quote-header-info > div > div > div > span[data-reactid=.0.2.0.0.2.2.0.0.0.2]"));
			//WebElement repaymentValue1 = driver.findElement(By.xpath("//span[contains(@class,'h3')]"));
			String actualValue1 = repaymentValue1.getText();
			
			
			Assert.assertEquals(actualValue1, "$206,211","Repayment 1 calculation is correct");
			
			
			//Repayment 2 parameters
			WebElement borrow2 = driver.findElement(By.xpath("//input[@id='amount']"));
			borrow2.clear();
			borrow2.sendKeys("150000");
			WebElement years2 = driver.findElement(By.xpath("//input[@id='term']"));
			years2.clear();
			years2.sendKeys("25");
			//Select DropDown = new Select(driver.findElement(By.xpath("//select[contains(@name,'products')]")));
			Select dropDown = new Select(driver.findElement(By.id("interestOnly")));
			dropDown.selectByVisibleText("Interest only 4 years");
			dropDown.selectByIndex(1);
			
			WebElement repaymentValue2 = driver.findElement(By.xpath("//span[contains(@class,'h3')]"));
			String actualValue2 = repaymentValue2.getText();
			
			Assert.assertEquals(actualValue2, "$216,000","Repayment 2 calculation is correct");
	}

}
