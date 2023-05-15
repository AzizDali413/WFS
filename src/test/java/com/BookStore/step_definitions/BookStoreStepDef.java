package com.BookStore.step_definitions;

import com.BookStore.pages.BookStorePage;
import com.BookStore.pages.LoginPage;
import com.BookStore.utilities.BrowserUtils;
import com.BookStore.utilities.ConfigurationReader;
import com.BookStore.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookStoreStepDef {

    LoginPage loginPage = new LoginPage();
    BookStorePage bookStorePage = new BookStorePage();

    @Given("User navigates to the login page")
    public void user_navigates_to_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }
    @When("user loging in using username and password")
    public void user_loging_in_using_username_and_password() {
        loginPage.inputUsername.sendKeys(ConfigurationReader.getProperty("username"));
        loginPage.inputPassword.sendKeys(ConfigurationReader.getProperty("password"));
        loginPage.loginBtn.click();
    }
    @Then("navigates to BookStore")
    public void navigates_to_book_store() {
        Assert.assertEquals(Driver.getDriver().getTitle(),"DEMOQA");

        try {
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", bookStorePage.goToBookStoreBtn);
        } catch (ElementClickInterceptedException e){
            System.out.println(e);
        }

        bookStorePage.goToBookStoreBtn.click();



    }
    @Then("filter the listed books using the word: {string}")
    public void filter_the_listed_books_using_the_word_(String string) {
        bookStorePage.searchBox.sendKeys(string);
    }

    @When("clicks on the book and add it to your collection")
    public void clicks_on_the_book_and_add_it_to_your_collection() throws InterruptedException {
        try {
        bookStorePage.firstBookOnList.click();
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(bookStorePage.addToYourCollectionBtn);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", bookStorePage.addToYourCollectionBtn);
        bookStorePage.addToYourCollectionBtn.click();
       // Thread.sleep(10000);

            Wait<WebDriver> wait = new WebDriverWait(Driver.getDriver(), 3);
            wait.until(ExpectedConditions.alertIsPresent());
//if(checkNativeAlert())
{
    System.out.println(Driver.getDriver().switchTo().alert().getText());
    Driver.getDriver().switchTo().alert().accept();}

//
//            Alert alert = Driver.getDriver().switchTo().alert();
//
//            String actualMessage = alert.getText();
//            Assert.assertEquals("Book has not been ADDED to the collection", "Book added to your collection.", Driver.getDriver().switchTo().alert().getText());

        } catch (NoAlertPresentException e){
            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public boolean checkNativeAlert() {
        boolean exist = false;

        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 3);
            if(!(wait.until(ExpectedConditions.alertIsPresent()) == null)) {
                exist = true;
            }
        }
        catch (Exception ignore) {
            System.out.println(ignore);

        }

        return exist;
    }
    @When("Check that the book was added on your profile page")
    public void check_that_the_book_was_added_on_your_profile_page()  {
        Wait<WebDriver> wait = new WebDriverWait(Driver.getDriver(),10);
        wait.until(ExpectedConditions.elementToBeClickable(bookStorePage.profileBtn));
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", bookStorePage.profileBtn);
        bookStorePage.profileBtn.click();

        try {
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", bookStorePage.deleteBookBtn);
        } catch (ElementClickInterceptedException e){
            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }
        bookStorePage.deleteBookBtn.click();
        wait.until(ExpectedConditions.visibilityOf(bookStorePage.okBtn));
        bookStorePage.okBtn.click();

        try {
            Wait<WebDriver> wait2 = new WebDriverWait(Driver.getDriver(), 10);
            wait2.until(ExpectedConditions.alertIsPresent());
            Alert alert = Driver.getDriver().switchTo().alert();
            String actualMessage = alert.getText();
            Assert.assertEquals("Book has not been DELETED from the collection", "All Books deleted.", actualMessage);
            alert.accept();
        } catch (NoAlertPresentException e){
            System.out.println(e);
        } catch (Exception e){
            System.out.println(e);
        }



    }
    @Then("Log out")
    public void log_out() {
        bookStorePage.logOutBtn.click();
    }
}
