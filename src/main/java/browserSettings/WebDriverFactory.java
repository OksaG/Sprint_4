package browserSettings;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class WebDriverFactory {

    public static WebDriver create(WebBrowserName webDriverName) {
        return create(webDriverName, new MutableCapabilities());
    }

    public static WebDriver create(WebBrowserName webDriverName, MutableCapabilities browserOptions) {
        // Установка системного свойства для ChromeDriver (для локального запуска путь до драйвера)
        System.setProperty("webdriver.chrome.driver", "/Users/tochka/Desktop/1/3/chromedriver");
        switch (webDriverName) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                // Добавляем аргумент обхода проблем с безопасностью
                options.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(options.merge(browserOptions));
            case FIREFOX:
                return new FirefoxDriver(new FirefoxOptions().merge(browserOptions));
            // По умолчанию Chrome
            default:
                return new ChromeDriver(new ChromeOptions().merge(browserOptions));
        }
    }
}