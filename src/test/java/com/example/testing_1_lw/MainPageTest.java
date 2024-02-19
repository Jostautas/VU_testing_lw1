package com.example.testing_1_lw;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;


public class MainPageTest {
    //MainPage mainPage = new MainPage();

@BeforeAll    public static void setUpAll() {
        // Configuration.browserSize = "1280x800";
        // SelenideLogger.addListener("allure", new AllureSelenide());
    }

@BeforeEach    public void setUp() {
        // Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        // open("https://demowebshop.tricentis.com/");
        WebDriver driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");

        // Gift Cards:
        driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[4]/div[1]/div[1]/div[2]/ul[1]/li[7]/a[1]\n")).click();

        int i = 1;

        while (true) {
            try {
                var price_element = driver.findElement(By.xpath(String.format("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[3]/div[%d]/div/div[2]/div[3]/div[1]/span", i)));
                String price = price_element.getText();
                price = price.substring(0, Math.min(price.length(), 3));
                try{
                        int price_int = Integer.parseInt(price);
                        if (price_int >= 99) {
                            //add to cart:
                            driver.findElement(By.xpath(String.format("//input[contains(@onclick, \"AjaxCart.addproducttocart_catalog('/addproducttocart/catalog/%d/1/1\")]", i))).click();
                        }
                    } catch(Exception e){
                        System.out.println("invalid number");
                    }
                i++;
            } catch (Exception e) {
                break;
            }
        }


    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    // Enter names:
    driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[4]/div[2]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[5]/div[1]/input[1]")).sendKeys("Jonas");
    driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[4]/div[2]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[5]/div[2]/input[1]")).sendKeys("Jonas");

    // Quantity:
    driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[4]/div[2]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[7]/div[1]/input[1]")).clear();
    driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[4]/div[2]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[7]/div[1]/input[1]")).sendKeys("5000");

    // AddToCard:
    driver.findElement(By.xpath("(//input[@id='add-to-cart-button-4'])[1]")).click();
    // AddToWishList:
    driver.findElement(By.xpath("(//input[@id='add-to-wishlist-button-4'])[1]")).click();
    // Close green confirmation popup
    driver.findElement(By.xpath("(//span[@title='Close'])[1]")).click();
    // Jewelry:
    driver.findElement(By.xpath("(//a[normalize-space()='Jewelry'])[3]")).click();
    //Create your own jewelry:
    driver.findElement(By.xpath("//a[normalize-space()='Create Your Own Jewelry']\n")).click();

    //click on Material dropbox:
    Select drpMaterial = new Select(driver.findElement(By.name("product_attribute_71_9_15")));
    drpMaterial.selectByValue("47");

    //Length:
    driver.findElement(By.xpath("(//input[@id='product_attribute_71_10_16'])[1]")).sendKeys("10");

    //Quantity:
    driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[4]/div[2]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[6]/div[1]/input[1]")).clear();
    driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[4]/div[2]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[6]/div[1]/input[1]")).sendKeys("26");

    // AddToCard:
    driver.findElement(By.xpath("(//input[@id='add-to-cart-button-71'])[1]")).click();
    // AddToWishList:
    driver.findElement(By.xpath("(//input[@id='add-to-wishlist-button-71'])[1]")).click();
    // go to Wishlist:
    driver.findElement(By.xpath("(//span[normalize-space()='Wishlist'])[1]")).click();

    // click Add To Cart checkbox:
    driver.findElement(By.xpath("(//input[@name='addtocart'])[1]")).click();
    driver.findElement(By.xpath("(//input[@name='addtocart'])[2]")).click();
    // Add To Cart button:
    driver.findElement(By.xpath("(//input[@name='addtocartbutton'])[1]")).click();

    // go to Shopping Cart page:
    driver.findElement(By.xpath("(//span[normalize-space()='Shopping cart'])[1]")).click();

    // check whether sub-total is equal to :
    String subtotal = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/span[1]/span[1]")).getText();

    assertEquals(subtotal, "1002600.00");

    try {
        Thread.sleep(1000000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }

    driver.close();
    }

    @Test
    public void search() {
//        mainPage.searchButton.click();
//
//        $("[data-test='search-input']").sendKeys("Selenium");
//        $("button[data-test='full-search-button']").click();
//
//        $("input[data-test='search-input']").shouldHave(attribute("value", "Selenium"));
    }
//
//    @Test
//    public void toolsMenu() {
//        mainPage.toolsMenu.click();
//
//        $("div[data-test='main-submenu']").shouldBe(visible);
//    }
//
//    @Test
//    public void navigationToAllTools() {
//        mainPage.seeDeveloperToolsButton.click();
//        mainPage.findYourToolsButton.click();
//
//        $("#products-page").shouldBe(visible);
//
//assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());    }
}
