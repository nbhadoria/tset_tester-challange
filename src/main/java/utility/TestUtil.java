package utility;


import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.ViteApp;
import webConnector.WebConnector;

import java.math.BigDecimal;
import java.util.*;


import static org.junit.Assert.*;

public class TestUtil extends WebConnector {

    static HashMap<String, String> testData = new HashMap<>();

    public static void initialization() {
        driver.get(ViteApp.getHomeURl());
    }

    public static void hoverClick() {
        ViteApp viteApp = new ViteApp();
        scriptExecutor(viteApp.getBaseEdit());
    }

    public static void enterValue(String value) {
        ViteApp viteApp = new ViteApp();
        viteApp.getBaseInputValue().clear();
        viteApp.getBaseInputValue().sendKeys(value);
        viteApp.getBaseCheck().click();
    }

    public static void addingNewValues(DataTable dataTable) {
        testData = parsingDataTable(dataTable);
        enterTestData(testData);
    }

    public static HashMap<String, String> parsingDataTable(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);

        for (List<String> columns : rows) {
            testData.put(columns.get(0), columns.get(1));
        }
        return testData;
    }

    public static void enterTestData(HashMap testData) {
        ViteApp viteApp = new ViteApp();

        Set<Map.Entry<String, String>> entries = testData.entrySet();
        for (Map.Entry<String, String> entry : entries
        ) {
            viteApp.getGhostLabel().click();
            viteApp.getGhostLabel().clear();
            viteApp.getGhostLabel().sendKeys(entry.getKey());
            viteApp.getGhostValue().clear();
            viteApp.getGhostValue().sendKeys(entry.getValue());
            viteApp.getCheck().click();

        }
    }

    public static void deleteEntryAndVerify(String label) {
        String currentTotalSum = getTotal();
        String newPrice = Double.toString(Double.parseDouble(currentTotalSum) - Double.parseDouble(getEntryPrice(label)));
        WebElement entry = deleteNewEntry(label);

        scriptExecutor(entry);
        assertEquals(getTotal(), newPrice);
    }

    public static void editEntryAndVerifyWarning(String label, String newLable, String warningMessage) {
        ViteApp viteApp = new ViteApp();

        WebElement element = editNewEntry(label);
        WebElement editLabel = viteApp.getEditlabel();
        WebElement check = viteApp.getEditCheck();

        scriptExecutor(element);
        clickAndSend(editLabel, newLable);
        checkForWarning(viteApp.getEditLabelWarning(), warningMessage);
        scriptExecutor(check);

        assertEquals(viteApp.getLabel().getText(), label);
    }

    public static void editEntryValueAndVerifyWarning(String label, String newValue, String warningMessage) {
        ViteApp viteApp = new ViteApp();

        WebElement editElement = editNewEntry(label);
        WebElement editValueElement = viteApp.getEditValue();
        WebElement checkElement = viteApp.getEditCheck();
        String expectedValue = getEntryPrice(label);

        scriptExecutor(editElement);
        clickAndSend(editValueElement, newValue);
        checkForWarning(viteApp.getEditValueWarning(), warningMessage);
        scriptExecutor(checkElement);

        String actualValue = getEntryPrice(label);
        assertEquals(actualValue, expectedValue);
    }

    public static void editEntryValueAndVerifySum(String label, String newValue) {

        double currentTotalSum = Double.valueOf(getTotal());
        double expectedSum = currentTotalSum + Double.valueOf(newValue) - Double.valueOf(getEntryPrice(label));
        double roundOff = Math.round(expectedSum * 100.0) / 100.0;
        String expected = Double.toString(roundOff);

        editEntry(label, newValue);
        verifyBasePrice(expected);
    }

    public static void verifyEnteredValues(){
       List<WebElement> list;
       list = driver.findElements(By.xpath("//*[@id='BasePrice']/following-sibling::div/div[@class='w-1/3 flex flex-col']/div"));
       for(int i=0; i<list.size()-1;i++){
           String priceOfLabel=list.get(i).getText();
           try {
               double price = Double.valueOf(priceOfLabel);
               assertTrue((BigDecimal.valueOf(price).scale() <3));
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }
    }

    public static void verifyBasePrice(String expectedValue) {
        String actualValue = getTotal();
        assertEquals(expectedValue, actualValue);
    }

    public static void checkForWarning(WebElement element, String warningMessage) {
        String warning = element.getText();
        assertEquals(warningMessage, warning);
    }


    public static void clickAndSend(WebElement element, String data) {
        element.click();
        element.clear();
        element.sendKeys(data);
    }

    public static WebElement editNewEntry(String entry) {
        return driver.findElement(By.xpath("//span[contains(text(),'" + entry + "')]/parent::div/preceding-sibling::div/span"));
    }

    public static WebElement deleteNewEntry(String entry) {
        return driver.findElement(By.xpath("//span[contains(text(),'" + entry + "')]/parent::div/following-sibling::div[@class='w-16']//i[@class='fas fa-trash-alt']"));
    }

    public static String getEntryPrice(String entry) {
        return driver.findElement(By.xpath("//span[contains(text(),'" + entry + "')]/parent::div/following-sibling::div/div[@class='text-right']")).getText();
    }

    public static void scriptExecutor(WebElement entry) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", entry);
    }

    public static String getTotal() {
        ViteApp viteApp = new ViteApp();
        return viteApp.getBasePrice().getText();
    }

    public static void editEntry(String label, String newValue) {
        ViteApp viteApp = new ViteApp();

        WebElement element = editNewEntry(label);
        WebElement editValue = viteApp.getEditValue();
        WebElement check = viteApp.getEditCheck();

        scriptExecutor(element);
        clickAndSend(editValue, newValue);
        scriptExecutor(check);
    }

}
