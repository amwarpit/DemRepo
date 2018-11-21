package packagedatadriven;

import java.io.File;
import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataDriven {
	
public WebDriver driver;

@Test(dataProvider ="testdata")
public void login(String username, String password) throws InterruptedException
{
System.setProperty("webdriver.chrome.driver", "C:\\Users\\AMW40\\Downloads\\chromedriver_win32\\chromedriver.exe");
driver = new ChromeDriver();
driver.get("http://test.medefer.com/Account/Login");
Thread.sleep(3000);
driver.findElement(By.name("UserName")).clear();
driver.findElement(By.name("UserName")).sendKeys(username);
Thread.sleep(2000);
driver.findElement(By.name("Password")).clear();
driver.findElement(By.name("Password")).sendKeys(password);
Thread.sleep(3000);
driver.findElement(By.xpath("//input[@value='Log in']")).click();
Thread.sleep(2000);



/*Assert.assertEquals("Second Factor Authentication - Medefer", driver.getTitle());

String text="Either your login email or password is incorrect or your account has not yet been confirmed.";
String ACT=driver.findElement(By.cssSelector(".validation-summary-errors")).getText();
System.out.println(ACT);
Assert.assertEquals(ACT,text);*/

}
	
	




@DataProvider(name= "testdata")
public Object [] [] readExcel() throws BiffException,IOException
	

{
		
File f = new File("C:/Users/AMW40/Downloads/datadrivensheet.xls");
Workbook w = Workbook.getWorkbook(f);
Sheet s = w.getSheet("Sheet1");
int row = s.getRows();
int column = s.getColumns();
//System.out.println(row);
//System.out.println(column);
String inputData [] [] = new String [row] [column];
for(int i=0; i<row; i++){
	for(int j=0; j<column; j++){
		Cell c = s.getCell(j,i);
		inputData [i] [j] =c.getContents();
		//System.out.println(inputData[i][j]);
	}
}
return inputData;

}
}


