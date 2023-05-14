package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import webConnector.WebConnector;

public class ViteApp extends WebConnector {

    @FindBy(id = "base-edit-icon")
    WebElement baseEdit;

    @FindBy(id = "base-value-input")
    WebElement baseInputValue;

    @FindBy(id = "base-check-icon")
    WebElement baseCheck;

    @FindBy(xpath = "//*[@id='app']//span[@class='font-bold']")
    WebElement basePrice;

    @FindBy(id = "ghost-label-input")
    WebElement ghostLabel;

    @FindBy(id = "ghost-value-input")
    WebElement ghostValue;

    @FindBy(xpath = "//*[@id='BasePrice']/following-sibling::div/div[@class='flex-grow flex flex-col']/span")
    WebElement label;

    @FindBy(id = "ghost-check-icon")
    WebElement check;

    @FindBy(xpath = "//div[@class='flex-grow flex flex-col']/input")
    WebElement editlabel;

    @FindBy(xpath = "//input[@type='number']")
    WebElement editValue;

    @FindBy(xpath = "//div[@class='flex-grow flex flex-col']/p")
    WebElement editLabelWarning;

    @FindBy(xpath = "//input[@type='number']//following-sibling::p")
    WebElement editValueWarning;

    @FindBy(xpath = "//div[@class='flex-grow flex flex-col']/input/parent::div/following-sibling::div[@class='w-16']//i[@class='fas fa-check']")
    WebElement editCheck;

    public ViteApp() {
        PageFactory.initElements(driver, this);
    }

    public static String getHomeURl() {
        String homeUrl = appProps.getProperty("url");
        return homeUrl;
    }

    public WebElement getBaseEdit() {return baseEdit;}

    public WebElement getGhostLabel() {return ghostLabel;}

    public WebElement getGhostValue() {return ghostValue;}

    public WebElement getCheck() {return check;}

    public WebElement getBaseInputValue() {
        return baseInputValue;
    }

    public WebElement getBaseCheck(){
        return baseCheck;
    }

    public WebElement getBasePrice(){
        return basePrice;
    }

    public WebElement getEditlabel() {
        return editlabel;
    }

    public WebElement getEditLabelWarning() {
        return editLabelWarning;
    }

    public WebElement getEditCheck() {
        return editCheck;
    }

    public WebElement getLabel() {
        return label;
    }

    public WebElement getEditValue() {
        return editValue;
    }

    public WebElement getEditValueWarning() {
        return editValueWarning;
    }

}
