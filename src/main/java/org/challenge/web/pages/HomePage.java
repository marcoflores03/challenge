package org.challenge.web.pages;

import org.assertj.core.api.Fail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(id = "inputImage")
    WebElement inputImage_btn;

    @FindBy(name = "text")
    WebElement description_txt;

    @FindBy(css = ".btn.pull-right.btn-success")
    WebElement createItem_btn;

    @FindBy(xpath = "//button[contains(text(),'Update Item')]")
    WebElement updateItem_btn;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement cancel_btn;

    @FindBy(xpath = "//button[contains(text(),'Yes, delete it!')]")
    WebElement yesDeleteIt_btn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        this.waitUntil(createItem_btn);
    }

    public WebElement getInputImage_btn() {
        return inputImage_btn;
    }

    public WebElement getDescription_txt() {
        return description_txt;
    }

    public void setDescription_txt(String text) {
        this.description_txt.sendKeys(text);
    }

    public WebElement getCreateItem_btn() {
        return createItem_btn;
    }

    public void fillFileToUpload(String path) {
        this.sendKeysById(this.getInputImage_btn().getAttribute("id"), path);
    }

    public WebElement findCardWithText(String text) {
        List<WebElement> list = driver.findElements(By.cssSelector(".media.ng-scope.ui-sortable-handle"));
        int size = list.size();
        boolean existText = false;
        int position = 0;
        for (int i=0;i<size;i++){
            if (list.get(i).findElement(By.tagName("p")).getText().toLowerCase().contains(text.toLowerCase())){
                existText = true;
                position = i;
                break;
            }
        }
        if (existText){
            return list.get(position);
        } else {
            return null;
        }
    }

    public void editCardWithText(String text) {
        WebElement card = this.findCardWithText(text);
        if(card!=null){
            card.findElements(By.tagName("button")).stream().filter(i -> i.getText().equals("Edit")).findFirst().get().click();
        } else {
            Fail.fail("Edit action failed. Text card was not found. text: " + text);
        }
    }

    public void deleteCardWithText(String text) {
        WebElement card = this.findCardWithText(text);
        if(card!=null){
            //card.findElements(By.tagName("button")).get(1).click();
            card.findElements(By.tagName("button")).stream().filter(i -> i.getText().equals("Delete")).findFirst().get().click();
        } else {
            Fail.fail("Delete action failed. Text card was not found. text: " + text);
        }
    }

    public WebElement getUpdateItem_btn() {
        return updateItem_btn;
    }

    public WebElement getCancel_btn() {
        return cancel_btn;
    }

    public void completeCharactersUpTo(WebElement element, int upTo) {
        int size = element.getAttribute("value").length();
        for (int i=0 ; i < upTo - size ; i++) {
            this.setDescription_txt("x");
        }
    }

    public WebElement getYesDeleteIt_btn() {
        return yesDeleteIt_btn;
    }
}