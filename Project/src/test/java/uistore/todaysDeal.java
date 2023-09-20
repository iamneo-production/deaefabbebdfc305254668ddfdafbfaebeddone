package uistore;

import org.openqa.selenium.By;

public class todaysDeal {

  public todaysDeal()  {
	PageFactory.initElements(driver, this);
		
	}

  
   // public static By username = By.name("username"); -- REFERENCE
   
   @FindBy(xpath = "//a[contains(text(),'Today')]")
	public WebElement todaysDeals_label;

    @FindBy(xpath = "//h1[contains(text(),'Today')]")
	public WebElement todaysDeals_title;

}