package org.challenge;

import org.challenge.general.DateManager;
import org.challenge.general.Tools;
import org.challenge.web.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestFrontEnd extends BaseTest {

    HomePage homePage;

    final String newCard = "Stranger Things - Simpsons Style " + DateManager.getDateId();
    final String exitingCard = "mike";

    @BeforeMethod
    public void login() {
        driver.get(environment.getUrl());
    }

    /**
     * Test Case: Create an Item.
     * Steps:                           | Expected Results:
     * ------------------------------------------------------------
     *  1) Go to StrangerList Page      | Input Image button, Description text box and Create Item button should are displayed
     *  2) Add file to upload           | Should be able to fill file to upload
     *  3) Fill text description        | Should be able type a text description
     *  4) Click on Create Item         | Should be able click Create Item button and
     *  5) Verify Item created          | New Item should be created
     */

    @Test
    public void test_createItem() {
        homePage = new HomePage(driver);
        assertTrue(homePage.getInputImage_btn().isDisplayed(),"Input Image button is not displayed.");
        assertTrue(homePage.getInputImage_btn().isDisplayed(),"Input Image button is not displayed.");
        assertTrue(homePage.getCreateItem_btn().isDisplayed(),"Create Item button is not displayed.");

        assertTrue(homePage.getInputImage_btn().isEnabled(),"Input Image button is not enabled.");
        homePage.fillFileToUpload("src\\test\\resources\\st-simpsons.jpg");

        assertTrue(homePage.getInputImage_btn().isEnabled(),"Input Image button is not enabled.");
        homePage.setDescription_txt(newCard);

        assertTrue(homePage.getCreateItem_btn().isEnabled(),"Create Item button is not enabled.");
        homePage.getCreateItem_btn().click();

        assertNotNull(homePage.findCardWithText(newCard),"Not found the card with text: " + newCard);
    }

    /**
     * Test Case: Edit another existing item.
     * Steps:                             | Expected Results:
     * ------------------------------------------------------------
     *  1) Go to StrangerList Page        |
     *  2) Find an item with text "mike"  | Should be able an item with text "mike"
     *  3) Click on Edit in "mike" item   | Cancel and Update Item buttons should be able and the text box should be updated with item text
     *  4) Fill new text description      | Should be able update text description
     *  5) Click on Update Item           | New text should be displayed in the Item
     */

    @Test
    public void test_editItem() {
        homePage = new HomePage(driver);
        assertNotNull(homePage.findCardWithText(exitingCard),"Not found the card with text: " + exitingCard);

        homePage.editCardWithText(exitingCard);

        assertTrue(homePage.getUpdateItem_btn().isDisplayed(),"Update Item button is not displayed.");
        assertTrue(homePage.getCancel_btn().isDisplayed(),"Cancel Update button is not displayed.");
        assertTrue(Tools.containsIgnoreCase(homePage.getDescription_txt().getAttribute("value"),exitingCard));

        String newText = "New Text " + DateManager.getDateId();
        homePage.setDescription_txt(newText);
        assertTrue(Tools.containsIgnoreCase(homePage.getDescription_txt().getAttribute("value"),newText));

        homePage.getUpdateItem_btn().click();
        assertTrue(homePage.findCardWithText(newText).isEnabled(),"Card is not updated.");
    }

    /**
     * Test Case: Delete the item created.
     * Steps:                              | Expected Results:
     * ------------------------------------------------------------
     *  1) Go to StrangerList Page         |
     *  2) Find an item created previously | Should be able item created previously
     *  3) Click on Delete button          | Warning delete popup should be displayed
     *  4) Click on Yes Delete It button   | Warning delete popup should be disappear
     *  5) Verify item deleted in the list | Item should be deleted in the list
     */

    @Test
    public void test_deleteItem() {
        homePage = new HomePage(driver);
        assertNotNull(homePage.findCardWithText(newCard),"Not found the card to delete with text: " + newCard);

        homePage.deleteCardWithText(newCard);
        assertTrue(homePage.getYesDeleteIt_btn().isDisplayed(),"Warning delete message is not displayed");

        homePage.getYesDeleteIt_btn().click();
        assertFalse(homePage.getYesDeleteIt_btn().isDisplayed(),"Warning delete message is still displayed");

        assertNull(homePage.findCardWithText(newCard),"New card: '" + newCard + "' was not deleted.");
    }

    /**
     * Test Case: Check max long in description.
     * Steps:                                         | Expected Results:
     * ----------------------------------------------------------------------
     *  1) Go to StrangerList Page                    |
     *  2) Edit an existing item and get maximum      |
     *        length of description text box          |
     *  3) Fill description up to maximum length      | Update Item button should be enable
     *  4) Add a character to description text        | Update Item button should be not enable
     */

    @Test
    public void test_maxDescription() {
        homePage = new HomePage(driver);

        homePage.editCardWithText(exitingCard);
        int maxLength = Tools.getIntFromString(homePage.getDescription_txt().getAttribute("ng-maxlength"));
        homePage.completeCharactersUpTo(homePage.getDescription_txt(),maxLength);
        assertTrue(homePage.getUpdateItem_btn().isEnabled(),
                "Update button is not enabled for max length (" + maxLength + ").");

        maxLength = maxLength + 1;
        homePage.completeCharactersUpTo(homePage.getDescription_txt(),maxLength);
        assertFalse(homePage.getUpdateItem_btn().isEnabled(),
                "Update button is enabled for a greater value of max length (" + maxLength + ").");
    }

    /**
     * Test Case: Find and Item with a text.
     * Steps:                                         | Expected Results:
     * ----------------------------------------------------------------------
     *  1) Go to StrangerList Page                    |
     *  2) Find an item with following text:          | Item should be in the list of Items
     *      'Creators: Matt Duffer, Ross Duffer'      |
     */

    @Test
    public void test_findItemWithText() {
        homePage = new HomePage(driver);
        final String textToFind = "Creators: Matt Duffer, Ross Duffer";
        assertNotNull(homePage.findCardWithText(textToFind),"Not found the card with text: " + textToFind);
    }
}