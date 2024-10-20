package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    // локатор заголовка "Вопросы о важном"
    private static final By headerImportantQuestionsLocator = By.xpath(".//div[text()='Вопросы о важном']");
    // локатор кнопки заказать в хэдере
    private static final By headerOrderButtonLocator = By.xpath(".//div[contains(@class, 'Header')]//button[text()='Заказать']");
    // локатор кнопки заказать в середине страницы
    private static final By orderButtonInMiddleLocator = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Скроллим до "Вопросы о важном"
    public void scrollAccordion() {
        WebElement element = driver.findElement(headerImportantQuestionsLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void createOrderButtonInHeader() {
        WebElement orderStatusButton = driver.findElement(headerOrderButtonLocator);
        orderStatusButton.click();
    }

    public void createOrderButtonInFooter() {
        WebElement createOrderBtn = driver.findElement(orderButtonInMiddleLocator);
        createOrderBtn.click();
    }

    public String getAnswer(int accordionIdNumber) {
        //кликаем в вопрос
        driver.findElement(By.id("accordion__heading-" + (accordionIdNumber))).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("accordion__heading-" + (accordionIdNumber)))));
        return driver.findElement(By.id("accordion__panel-" + (accordionIdNumber))).getText();
    }

}