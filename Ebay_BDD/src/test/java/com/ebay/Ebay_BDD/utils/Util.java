package com.ebay.Ebay_BDD.utils;

import com.ebay.Ebay_BDD.DriverSetup;
import com.ebay.Ebay_BDD.constants.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Util {
    public  double octopusInitial, octopusAmc;
    public WebElement text_InitialAdviserCharge_Per,text_OngoingAdviserCharge_Per,text_OctInitialAdviserCharge_Per,text_AmcAdvised_Per,
            text_BankName_CheckPayment, text_BankName_AboutInvestment, text_BankName_FuturePayments,text_InitialAdviserCharge_Fix,
            text_InitialAdviserCharge_Rebate,text_OctInitialAdviserCharge_Rebate,text_Initial_non_AdviserCharge_Per,text_Initial_non_AdvisedCommission_Rebate,
            text_Ongoing_non_Advised_Commission,text_OctInitial_Non_AdvisedCommission_Per,text_OctInitial_Non_AdvisedCommission_Rebate,text_NonAdvised_Amc_Per,
            text_Aml_Date,text_OctInitial_Direct_Commission_Per,text_OctInitial_Direct_Commission_Rebate,text__Amc_Direct;
    public String octAmcText,octInitialText,specialDealsText = "Special deals are not supported for non-advised and direct applications.";


	public final WebDriver driver = new DriverSetup();

    /**
     * Method to open url
     * @param url
     */
    public void openUrl(String url){
        driver.get(url);
    }
	/**
     * Method to refresh the current Page
     */
	public void refreshPage(){
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
	}
    /**
     * Method to click on an Element
     * @param locator
     */
	public void click(By locator){

      driver.findElement(locator).click();
	}
    /**
     * Method to Send Keys
     * @param locator
     * @param text
     */
    public void sendKeys(By locator,String text){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
    /**
     * Select an element from drop list
     * @param locator
     * @param option
     */
    public void selectElement(By locator,String option) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(option);
    }
    /**
     *Method to verify a message displayed on web page
     * @param locator
     * @param message
     */
	public void verifyDisplayedText(By locator, String message){
		String actualResult = driver.findElement(locator).getText();
		 try{
				assertEquals(message,actualResult); 
//				System.out.println(actualResult);
	         }catch (Exception e){
				System.out.println("Cannot find the Mesaage");
			}
	}
    /**
     * Get inner text from an Element from Attribute
     * @param locator
     * @return
     */
    public String getDisplayedText(By locator) {

        return driver.findElement(locator).getAttribute("innerText");
    }
    /**
     * Method to get the Text
     * @param locator
     * @return
     */
    public String getText(By locator){

        return driver.findElement(locator).getText();
    }
     /**
     * getCss
     * @paramCss
     * @return WebElement
     * @throws InterruptedException
     */
    public  WebElement getCss(By locator) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver.findElement(locator);
    }

    /**
     * Method to verify entered text
     * @param locator
     * @param message
     */
	public void verifyTextPresent(By locator, String message) {
        String actualResult = driver.findElement(locator).getAttribute("value");
        try {
            assertEquals(message, actualResult);
        } catch (Exception e) {
            System.out.println("Text is not present");
        }
    }

    /**
     * Method to check Element  is selected
     * @param locator
     * @return
     */
	public Boolean isSelected(By locator)
    {
        return(driver.findElement(locator).isSelected());
    }
    /**
     * Method to check element is enabled
     * @param locator
     * @return
     */
	public Boolean isEnabled(By locator)
    {
        return(driver.findElement(locator).isEnabled());
    }

    /**
     * Method to check Element  is Displayed
     * @param locator
     * @return
     */
    public Boolean checkIfDisplayed(By locator)
    {
        return driver.findElement(locator).isDisplayed();
    }

    /**
     * Method to mouseOver
     * @param locator
     */
    public void mouseOver(By locator){
        driver.findElement(locator);
        WebElement hoverObject = driver.findElement(locator);

        Actions builder = new Actions(driver);
        builder.moveToElement(hoverObject).build().perform();

    }
    /**
     * Mouse over and Click
     * @param idSelector
     * @param clickidSelector
     * @throws Exception
     */
    public  void mouseOverAndClick(By idSelector, By clickidSelector) throws Exception {
        WebElement item,itemOption;
        //get the element that shows menu with the mouseOver event
        item = driver.findElement(idSelector);
        item.click();
        Thread.sleep(8000);
        //the element that I want to click (hidden)
        itemOption = driver.findElement(clickidSelector);
        itemOption.click();
    }
    /**
     * MouseOver
     * @param idSelector
     */
    public  void mouseOver(String idSelector) {
        //get the element that shows menu with the mouseOver event
        WebElement item = driver.findElement(By.cssSelector(idSelector));

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(driver);
        builder.moveToElement(item).build().perform();
    }
    /**
     * getCss
     * @paramCss
     * @return WebElement
     * @throws InterruptedException
     */
    public  WebElement getCss(String Csskey) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(By.cssSelector(Csskey));
    }

    /**
     * Returns the element when visible
     * Focus to
     * @param Css
     * @return
     */
   /* public WebElement waitForVisible(String css) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
        return element;
    }*/
    /**
     * Returns the element when visible
     * Focus to
     * @param locator
     * @return
     */
    public WebElement waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }


    /**
     * Verify Text Present
     * @param idSelector
     * @param textToBeFound
     * @return
     * @throws Exception
     */
    public  boolean verifyTextPresent(String idSelector, String textToBeFound) throws Exception {
        List<WebElement> listWebElements = driver.findElements(By.cssSelector(idSelector));
        boolean found = false;

        for (WebElement webElement:listWebElements) {
            if (webElement.getText().contains(textToBeFound)) {
                found = true;
            }
        }

        return found;
    }

    /**
     * Drag and Drop
     * @param startpoint
     * @param endpoint
     * @throws InterruptedException
     */
    public  void dragAndDrop(String startpoint, String endpoint) throws InterruptedException {
        WebElement startPoint = getCss(startpoint);
        WebElement endPoint = getCss(endpoint);

        Actions actionMan = new Actions(driver);
        actionMan.clickAndHold(startPoint).moveToElement(endPoint).perform();

        Thread.sleep(1000);
        actionMan.release().perform();
    }

   /* public  boolean isSkip(String item){
        for(int i=1; i<=testData_xls.getRowCount("IHT");i++ ){
            String cellData = testData_xls.getCellData("IHT",0,i);
            System.out.println("Cell Data is "+cellData);
            if(cellData.equals(item))
            {
                String run_mode = testData_xls.getCellData("IHT", 1, i);
                if(run_mode.equals("Run"))
                    return true;
                else
                    return false;
            }

        }

        return false;
    }*/

/*
    // This method would read data from Excel sheet for sheet name provided
    public  Object[][] getTestData(String sheetName){
        if(testData_xls.getColumnCount(sheetName) == -1){
            return new Object[1][0];
        }
        Object[][] data = new Object[testData_xls.getRowCount(sheetName)-1][testData_xls.getColumnCount(sheetName)];
        for(int row=2;row <= testData_xls.getRowCount(sheetName);row++){
            for(int col=0 ; col < testData_xls.getColumnCount(sheetName); col++){
                data[row-2][col] = testData_xls.getCellData(sheetName, col, row);
            }
        }
        return data;
    }*/

  /*  // This method would read data from Excel sheet for sheet name provided	and testnum
    public  Object[][] getTestData(String sheetName,String testNum) {
        if (testData_xls.getColumnCount(sheetName) == -1) {
            return new Object[1][0];
        }
        Object[][] data = new Object[1][testData_xls.getColumnCount(sheetName)];
        for (int row = 2; row <= testData_xls.getRowCount(sheetName); row++) {
            String value = testData_xls.getCellData(sheetName, 1, row);
            if (testNum.equals(value)) {

                for (int col = 0; col < testData_xls.getColumnCount(sheetName); col++) {
                    data[0][col] = testData_xls.getCellData(sheetName, col, row);
                }
            }
        }
        return data;
    }
*/


}