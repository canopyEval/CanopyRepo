package com.ajmal.pages.actions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.ajmal.base.Page;
import com.ajmal.pages.locators.AccountHoldingsPageLocators;
import com.ajmal.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class AccountHoldingsPage extends Page {
	
public AccountHoldingsPageLocators accHoldings;
	

	public AccountHoldingsPage(){
		
		this.accHoldings = new AccountHoldingsPageLocators();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,5);
		PageFactory.initElements(factory, this.accHoldings);
		
	}
	
	
	public void Select_ACDC4EVER_Account() throws Exception{
		Utilities.HoverAndEnterValues(accHoldings.accountField, "acdc4ever (335)");
		Thread.sleep(1000);
		click(accHoldings.acdc4everAccount);
	}
	


	public void ApplyFilter(){
		
		click(accHoldings.applyFiltersButton);
	}
	
	
	public String GetNetworthLegend(){
		Utilities.waitForJSAndJQToLoad();
		try {Thread.sleep(5000);} catch (InterruptedException e1) {e1.printStackTrace();}
		if(accHoldings.netWorthLegend.isDisplayed()){
			test.log(LogStatus.PASS, "Validation successfull: Content is displayed  --> "+"\n"+" :"+accHoldings.netWorthLegend.getText());
			return accHoldings.netWorthLegend.getText();
		}else{ 
			test.log(LogStatus.FAIL, "Validation Unsuccessfull: Content is not displayed. ");
			return "Networth as of Date is not displayed";
		}
	}
	
	public void VerifyNetworthisDisplayed(){
		Utilities.waitForJSAndJQToLoad();
		//try {Thread.sleep(5000);} catch (InterruptedException e1) {e1.printStackTrace();}
		if(accHoldings.netWorthLegend.isDisplayed()){
			test.log(LogStatus.PASS, "Validation successfull: Networth is displayed  --> : "+accHoldings.netWorthLegend.getText());
		}else{ 
			test.log(LogStatus.FAIL, "Validation Unsuccessfull: Networth is not displayed. ");
		}
	}
	
	
	public void VerifyCurrentValueUSDisDisplayed(){
		Utilities.waitForJSAndJQToLoad();
		if(accHoldings.currentValueUSD.isDisplayed()){
			test.log(LogStatus.PASS, "Validation successfull: Current Value USD is displayed  --> :"+accHoldings.currentValueUSD.getText());
		}else{ 
			test.log(LogStatus.FAIL, "Validation Unsuccessfull: Current Value USD is not displayed. ");
		}
	}
	
	
	public String GetCurrentValueUSD(){
		Utilities.waitForJSAndJQToLoad();
		Wait(accHoldings.currentValueUSD,10);
		if(accHoldings.currentValueUSD.isDisplayed()){
			test.log(LogStatus.PASS, "Validation successfull: Content is displayed  --> "+"\n"+" :"+accHoldings.currentValueUSD.getText());
			return accHoldings.currentValueUSD.getText();
		}else{ test.log(LogStatus.FAIL, "Validation Unsuccessfull: Content is not displayed. ");
		return "Current value USD is not displayed";
		}
	}
	
	
	
	public String GetHighlightedSections(){
		Utilities.waitForJSAndJQToLoad();
		List<String> sections = new ArrayList<String>();
		StringBuilder bfr = new StringBuilder();
		List<WebElement> ac = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/section[1]/holdings-per-user-component[1]/section[1]/div[2]/ul[@data-role='panelbar']"));
		for  (int i = 0; i < ac.size(); i++) {
	    	  int l = i+1;
	    	  String clasVal =  driver.findElement(By.xpath("//section[1]/holdings-per-user-component[1]/section[1]/div[2]/ul["+l+"]/li[1]")).getAttribute("class");
	    	  if(clasVal.equalsIgnoreCase("k-item k-first k-last highlight")){
	    		 String secName =  driver.findElement(By.xpath("//section[1]/holdings-per-user-component[1]/section[1]/div[2]/ul["+l+"]/li[1]/span[1]/span[1]")).getText();
	    		 sections.add(secName+",\t"); }
	    	  }
		sections.forEach(bfr::append);return bfr.toString();
	 }
	
	
	public void AccountsLoadingTime(){
		int refr = 0, i = 0;
		String ref = "";
		
		while(refr != 0 || i<=40 ){
			ref = accHoldings.refreshIcon.getAttribute("style");
			if(ref.equalsIgnoreCase("display: none;")){
				refr = 1;
				
			}else{
				refr = 0;
				try {
					Thread.sleep(500);i++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		test.log(LogStatus.INFO,"Accounts loading time"+(i*2),"");
		System.out.println("Accounts loading time"+(i*2));
	}
	
	
	
	public void acountHoldingsLoadTime(){
		int refr = 0, i = 0;
		try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();}
		while(refr != 0 || i<=40 ){
			if(accHoldings.currentValueUSD.getAttribute("style").isEmpty()){
				
			refr = 1;	
			}else{
				refr = 0;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
	
					e.printStackTrace();
				}i++;
				
			}
		
		}
		test.log(LogStatus.INFO,"Accounts loading time"+(i*2),"");
	}
	
	
	
	public String GetTotalValueFromHighlightedSections(){
		
		List<String> totalVal = new ArrayList<String>();
		try {Thread.sleep(5000);} catch (InterruptedException e1) {e1.printStackTrace();}
		StringBuilder bfr1 = new StringBuilder();
		List<WebElement> ac = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/section[1]/holdings-per-user-component[1]/section[1]/div[2]/ul[@data-role='panelbar']"));
		for  (int i = 0; i < ac.size(); i++) {
	    	  int l = i+1;
	    	  String clasVal =  driver.findElement(By.xpath("//section[1]/holdings-per-user-component[1]/section[1]/div[2]/ul["+l+"]/li[1]")).getAttribute("class");
	    	  if(clasVal.equalsIgnoreCase("k-item k-first k-last highlight")){
	    		  driver.findElement(By.xpath("//section[1]/holdings-per-user-component[1]/section[1]/div[2]/ul["+l+"]/li[1]/span[1]")).click();
	    		  try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		  WebElement elm1 =  driver.findElement(By.xpath("//section[1]/holdings-per-user-component[1]/section[1]/div[2]/ul["+l+"]/li[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[6]/div[1]/table[1]/tbody[1]/tr[1]/td[contains(text(),'Total: ')]"));
	    		  totalVal.add(elm1.getText()); 
	    	  }
	    	  }
		totalVal.forEach(bfr1::append);return bfr1.toString();
		}
	
	
	
	public String GetTotalValueOfHighlightedSections(){
		
			return Utilities.getTotalValue(GetTotalValueFromHighlightedSections());
		
	}
	
	
	
	public void ValidateSumValueAgainstCurrentValueUSD(String currentValueUSD, String SumOfSections){
	
		String formatVal = Utilities.numberExtractor(currentValueUSD);
		if(currentValueUSD.equalsIgnoreCase(SumOfSections)){
			test.log(LogStatus.PASS, "Validation successfull: Expected data matches Actual data "+"\n"+"Expected :"+formatVal+"\n"+"Actual :"+SumOfSections+"");
		}else{
			test.log(LogStatus.FAIL, "Validation Unsuccessfull: Expected : "+formatVal+", found : "+SumOfSections);
		}
				
	}
	
	
	public void VerifyContentDisplay(WebElement element){
		Utilities.waitForJSAndJQToLoad();
		if(element.isDisplayed()){
			test.log(LogStatus.PASS, "Validation successfull: Content is displayed  --> "+"\n"+" :"+element.getText());
		}else{
			test.log(LogStatus.FAIL, "Validation Unsuccessfull: Content is not displayed. ");
		}
				
	}
	
	
	
	

}
