package ua.com.apple_mania;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {

    @BeforeEach
    void openHomePage() {
        open("https://apple-mania.com.ua/");
    }

    @Test
    void searchProductByTitleAndAddToCart() {

        var product_name = "Apple AirPods 3rd generation (MME73)";

        $("[id='search']").val(product_name).pressEnter();
        $(".info-block").shouldHave(exactText(product_name));
        $(".searchautocomlete-inner").shouldHave(text(product_name));
        $(".searchautocomlete-inner").click();
        $(".base").shouldHave(exactText(product_name));
        $("[id='product-addtocart-button']").click();

        $(".block-title").shouldHave(text("Моя корзина"));
        $(".product").shouldHave(text(product_name));
        $("[id='top-cart-btn-checkout']").click();
        sleep(5000);

        $(".product-item-name").shouldHave(exactText(product_name));
        //$(".details-qty").shouldBe(Condition.text("1"));
        $("[name='firstname']").click();
        $("[name='firstname']").val("Yuliia").pressTab();
        $("[name='lastname']").val("Bilan").pressTab();
        $("[name='telephone']").val("+380999999990").pressTab();
        $("[name='username']").val("test@gmail.com").pressTab().pressTab();
        sleep(5000);

        }


    @Test
    void searchProductByTitleTest() {

        var product_name = "Apple AirPods 3rd generation (MME73)";

        new HomePage().searchFor(product_name);
        var actualSearchResultTitle = new SearchResultPage().getSearchResultTitle();

        Assertions.assertEquals(product_name,actualSearchResultTitle);

    }
}
