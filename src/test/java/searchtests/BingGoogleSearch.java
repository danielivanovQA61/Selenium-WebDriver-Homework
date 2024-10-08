package searchtests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BingGoogleSearch {

    public enum BrowserTypes {
        FIREFOX,
        CHROME,
        EDGE,
    }

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void beforeTest(){

        // Change browser type ENUM below for tests with different browsers
        driver = startBrowser(BrowserTypes.FIREFOX);

        // Configure Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    public void searchBingTelerikAcademyAlpha(){
        String searchTerm = "IT Career Start in 6 Months - Telerik Academy Alpha";

        // Navigate to Bing.com
        driver.get("https://bing.com");
        driver.manage().window().maximize();

        // Accept terms
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bnp_btn_accept")));
        WebElement acceptButton = driver.findElement(By.id("bnp_btn_accept"));
        acceptButton.click();

        // Type Term to search in input
        WebElement input = driver.findElement(By.id("sb_form_q"));
        input.sendKeys("Telerik Academy Alpha" + Keys.ENTER);

        // Assert Result found
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2/a")));
        WebElement resultAnchor = driver.findElement(By.xpath("//h2/a"));
        Assertions.assertTrue(resultAnchor.getText().contains(searchTerm), "expected result not found. Actual text: " + resultAnchor.getText());
    }


    @Test
    public void searchGoogleTelerikAcademyAlpha(){
        String searchTerm = "IT Career Start in 6 Months - Telerik Academy Alpha";
        // Navigate to Google.com
        driver.get("https://google.com");
        driver.manage().window().maximize();

        // Accept Terms
        WebElement acceptButton = driver.findElement(By.id("L2AGLb"));
        acceptButton.click();

        // Type Term to search in input
        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys("Telerik Academy Alpha"+ Keys.ENTER);

        // Assert Result found
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/h3")));
        WebElement resultAnchor = driver.findElement(By.xpath("//a/h3"));
        Assertions.assertTrue(resultAnchor.getText().contains(searchTerm), "expected result not found. Actual text: " + resultAnchor.getText());
    }

    @AfterAll
    public static void afterTest(){
        // Close driver
        driver.close();
    }

    private static WebDriver startBrowser(BrowserTypes browserType) {
        // Setup Browser
        switch (browserType){
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions);
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                return new EdgeDriver(edgeOptions);
        }

        return null;
    }
}
