package webConnector;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebConnector {

    public static WebDriver driver;
    public static Properties appProps = new Properties();

    public static void readConfig(){
        try {

            String appConfigPath = "./src/test/java/resource/application.properties";
            appProps.load(new FileInputStream(appConfigPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUpDriver(){
        readConfig();
        String browser = appProps.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver","./src/test/java/resource/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver","./src/test/lib/geckodriver.exe");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
    }

    public static void closeDriver(Scenario scenario){
        if(scenario.isFailed()){
            saveScreenshotsForScenario(scenario);
        }
        driver.close();
    }

    private static void saveScreenshotsForScenario(final Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "name");
    }


}
