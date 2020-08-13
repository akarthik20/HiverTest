package com.HiverTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class HiverBasics {
	
	WebDriver driver;
	
	@BeforeSuite
	public void browserSetup() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromeDriverFolder\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("gmail_hiver.crx"));
		driver = new ChromeDriver();	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void Setup() throws InterruptedException, IOException {
		
		String filePath = "C:\\Users\\dell\\eclipse-workspace\\HiverTest\\src\\com\\HiverTest\\dataSrc.xlsx";
		
	FileInputStream fileinput = new FileInputStream(filePath);
	
	HSSFWorkbook workbook = new HSSFWorkbook(fileinput);
	
	HSSFSheet sheet = workbook.getSheet("sheet1");
	
	driver.navigate().to("https://www.gmail.com");
	
	WebElement username = driver.findElement(By.id("identifierId"));
	username.sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
	
	WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/div[2]"));
	nextBtn.click();								
	
	WebElement passwordAtr = driver.findElement(By.name("password"));
	passwordAtr.sendKeys(sheet.getRow(1).getCell(1).getStringCellValue());
	
	WebElement nextBtnPass = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/div[2]"));
	nextBtnPass.click();
	
	WebElement hiverAdmin = driver.findElement(By.xpath("//*[@id=\"hq-accounts-icon\"]/svg/g/path[1]"));
	hiverAdmin.click();

	WebElement AdminPanel = driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div[1]/div[5]/div/div[3]/div[1]/div[1]/text()"));
	AdminPanel.click();
	
	WebElement SharedEmailBox = driver.findElement(By.xpath("//*[@id=\"circle\"]"));
	SharedEmailBox.click();
	
	WebElement SharedMail = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div/div[2]/div/div/div[2]/div[2]/div/span[1]"));
	SharedMail.click();
	
	WebElement autoResponder = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div/div[2]/div/div/div[1]/div[2]/div/a[4]/div/span"));
	autoResponder.click();
	
	Thread.sleep(5000);
	
	driver.switchTo().frame("//*[@id=\"cke_1_contents\"]/iframe");
	
	driver.findElement(By.xpath("//*[@id=\"cke_1_contents\"]/iframe")).sendKeys("KarthikTest");
			
	WebElement responderSwitch = driver.findElement(By.id("checkbox-default-3"));
	responderSwitch.click();
	
	Thread.sleep(2000);
	
	responderSwitch.click();	
	
	WebElement toastMessage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div"));
	String successMessage = toastMessage.getText();
	
	System.out.println(successMessage);
	
	}
	
	@AfterSuite
	public void closeBrowser() {
		driver.quit();
	}
	
	
}
