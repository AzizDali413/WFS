package com.BookStore.pages;

import com.BookStore.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookStorePage {

    public BookStorePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@id='gotoStore']")
    public WebElement goToBookStoreBtn;

    @FindBy(xpath = "//input[@id='searchBox']")
    public WebElement searchBox;

    @FindBy(xpath = "//span[contains(@id, 'Guide')]/a")
    public WebElement firstBookOnList;

    @FindBy(xpath = "//div[@class='text-right fullButton']/button[@id='addNewRecordButton']")
    public WebElement addToYourCollectionBtn;
    //

    @FindBy(xpath = "//span[.='Profile']")
    public WebElement profileBtn;

    @FindBy(xpath = "//div[@class='text-right button di']/button[@id='submit']")
    public WebElement deleteBookBtn;

    @FindBy(id = "closeSmallModal-ok")
    public WebElement okBtn;

    @FindBy(xpath = "//*[.='Log out']")
    public WebElement logOutBtn;

}
