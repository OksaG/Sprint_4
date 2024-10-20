package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы на странице заказа,раздел "Для кого самокат"

    // Локатор для ввода имени
    private By nameInputLocator = By.xpath(".//input[@placeholder='* Имя']");
    // Локатор для ввода фамилии
    private By surnameInputLocator = By.xpath(".//input[@placeholder='* Фамилия']");
    // Локатор для ввода адреса
    private By addressInputLocator = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Локатор для  выбора станции метро
    private By subwayInputLocator = By.xpath(".//input[@placeholder='* Станция метро']");
    // Локатор для выбора для станции метро из ниспадающего списка
    private final String stationMenuItemLocator = ".//div[contains(@class, 'Order_Text') and text()='%s']";
    // Локатор для ввода  номера телефона
    private By phoneInputLocator = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка далее
    private By nextButtonLocator = By.xpath(".//button[text()='Далее']");

    // Локаторы на странице заказа,раздел "Про аренду"

    // Локатор для ввода  выбора даты получения самоката
    private By dateInputLocator = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Локатор для  выбора срока аренды самоката
    private By rentPeriodInputLocator = By.xpath(".//div[text()='* Срок аренды']");
    // Локатор для выпадающего меню выбора срока аренды
    private final String rentPeriodItemLocator = ".//div[contains(@class, 'Dropdown-option') and text()='%s']";
    // Локатор для выбора цвета на чекбоксах
    private final String colorOfScooterCheckboxLocator = ".//label[contains(@class, 'Checkbox') and text()='%s']";
    // Локатор для ввода комментария для курьера
    private By commentImputLocator = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Локатор нопки 'Заказать'
    private By createOrderButtonLocator = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");
    // Локатор кнопки 'Да' для подтверждения заказа
    private By confirmOrderButtonLocator = By.xpath(".//button[text()='Да']");
    // Локатор заголовка 'Заказа оформлен' на модальном окне
    private By successOrderModalLocator = By.xpath(".//div[contains(@class, 'Order_ModalHeader') and text()='Заказ оформлен']");


    public void fillFirstPathOrderForm(String name, String lastName, String address, String subwayStation, String phoneNumber) {
        setInputName(name);
        setInputLastName(lastName);
        setInputAddress(address);
        setInputSubwayStation(subwayStation);
        setInputPhoneNumber(phoneNumber);
    }

    public void setInputName(String name) {
        WebElement nameInput = driver.findElement(nameInputLocator);
        nameInput.sendKeys(name);
    }

    public void setInputLastName(String lastName) {
        WebElement surnameInput = driver.findElement(surnameInputLocator);
        surnameInput.sendKeys(lastName);
    }

    public void setInputAddress(String address) {
        WebElement addressInput = driver.findElement(addressInputLocator);
        addressInput.sendKeys(address);
    }

    public void setInputSubwayStation(String subwayStation) {
        WebElement subwayInput = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(subwayInputLocator));
        subwayInput.click();
        WebElement subwayStationLocator = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(stationMenuItemLocator, subwayStation))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", subwayStationLocator);
        subwayStationLocator.click();
    }


    public void setInputPhoneNumber(String phoneNumber) {
        WebElement phoneInput = driver.findElement(phoneInputLocator);
        phoneInput.sendKeys(phoneNumber);
    }

    public void clickButtonNext() {
        WebElement nextButton = driver.findElement(nextButtonLocator);
        nextButton.click();
    }

    public void fillSecondPathOrderForm(String date, String rentPeriod, String colorOfScooter, String comment) {
        setInputDate(date);
        chooseRentPeriod(rentPeriod);
        chooseColorOfScooter(colorOfScooter);
        setInputComment(comment);
    }

    public void setInputDate(String data) {
        WebElement dateInput = driver.findElement(dateInputLocator);
        //Keys.ENTER: После ввода даты происходит имитация нажатия клавиши "Enter"
        dateInput.sendKeys(data, Keys.ENTER);
    }

    public void chooseRentPeriod(String rentPeriod) {
        WebElement rentPeriodInput = driver.findElement(rentPeriodInputLocator);
        rentPeriodInput.click();

        WebElement rentPeriodItem = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(rentPeriodItemLocator, rentPeriod))));
        rentPeriodItem.click();
    }

    public void chooseColorOfScooter(String colorOfScooter) {
        WebElement colorOfScooterCheckbox = driver.findElement(By.xpath(String.format(colorOfScooterCheckboxLocator, colorOfScooter)));
        colorOfScooterCheckbox.click();
    }

    public void setInputComment(String commentInput) {
        WebElement colorOfScooterCheckbox = driver.findElement(commentImputLocator);
        colorOfScooterCheckbox.sendKeys(commentInput);
    }

    public void clickOrderButton() {
        WebElement createOrderButton = driver.findElement(createOrderButtonLocator);
        createOrderButton.click();
    }

    public void clickConfirmOrder() {
        WebElement confirmButton = driver.findElement(confirmOrderButtonLocator);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(confirmOrderButtonLocator));
        confirmButton.click();
    }

    public boolean checkSuccessOrderModal() {
        try {
            // Пытаемся найти модальное окно с текстом "Заказ оформлен"
            driver.findElement(successOrderModalLocator);
            // Ожидаем, что модальное окно появится
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(successOrderModalLocator));
            // Возвращаем результат видимости модального окна
            return true;
        } catch (NoSuchElementException e) {
            // Если модального окна с таким текстом не дождались, возвращаем false
            return false;
        }
    }
}








