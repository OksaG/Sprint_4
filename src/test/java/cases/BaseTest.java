package cases;

import browserSettings.WebBrowserName;
import browserSettings.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;

    private final String SITE_URL = "https://qa-scooter.praktikum-services.ru/";

    public void goToWebsite() {
        driver.get(SITE_URL);
    }

    public void cookiesAссept() {
        driver.findElement(By.xpath(".//button[contains(@class, 'App_CookieButton')]")).click();
    }

    @Before
    public void setUp() {
        driver = WebDriverFactory.create(WebBrowserName.CHROME);//меняем на FIREFOX
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
