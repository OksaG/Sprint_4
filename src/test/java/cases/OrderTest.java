package cases;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.HomePage;
import pages.OrderPage;


@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String name;
    private final String lastName;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final String date;
    private final String rentPeriod;
    private final String colorOfScooter;
    private final String comment;

    public OrderTest(String name, String lastName, String address, String subwayStation,
                     String phoneNumber, String date, String rentPeriod, String colorOfScooter, String comment) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentPeriod = rentPeriod;
        this.colorOfScooter = colorOfScooter;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Оксана", "Грабовская", "ул. 26 Бакинских Комиссаров, д 4к2,кв 26", "Юго-Западная", "89111112111", "20.10.2024", "шестеро суток", "чёрный жемчуг", "Домофон не работает"},
                {"Максим", "Максимов", "Ленинский пр-т., 117, Москва", "Строгино", "89221222222", "30.10.2024", "трое суток", "серая безысходность", "Оставить у охранника"},
        };
    }

    @Test
    public void createOrderWithButtonInHeader() {

        goToWebsite();
        cookiesAccept();
        HomePage homePage = new HomePage(driver);
        homePage.createOrderButtonInHeader();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstPathOrderForm(name, lastName, address, subwayStation, phoneNumber);
        orderPage.clickButtonNext();
        orderPage.fillSecondPathOrderForm(date, rentPeriod, colorOfScooter, comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmOrder();
        orderPage.checkSuccessOrderModal();
        boolean checkOrderModal = orderPage.checkSuccessOrderModal();
        Assert.assertTrue("Не появилось модальное окно успеха заказа", checkOrderModal);

    }

    @Test
    public void createOrderWithButtonInFooter() {

        goToWebsite();
        cookiesAccept();
        HomePage homePage = new HomePage(driver);
        homePage.createOrderButtonInFooter();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstPathOrderForm(name, lastName, address, subwayStation, phoneNumber);
        orderPage.clickButtonNext();
        orderPage.fillSecondPathOrderForm(date, rentPeriod, colorOfScooter, comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmOrder();
        orderPage.checkSuccessOrderModal();
        boolean checkOrderModal = orderPage.checkSuccessOrderModal();
        Assert.assertTrue("Не появилось модальное окно успеха заказа", checkOrderModal);

    }
}
