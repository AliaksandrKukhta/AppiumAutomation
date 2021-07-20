package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testOfSaveTwoArticles() throws InterruptedException {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        NavigationUI navigationUI = new NavigationUI(driver);
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        String firstSubject = "Java";
        String secondSubject = "Appium";
        String articleName = "Java Article";
        String substring = "Object-oriented programming language";

        searchPageObject.initSearchInput();
        searchPageObject.inputInSearchLine(firstSubject);
        Thread.sleep(5000);
        searchPageObject.initSearchChoice();
        articlePageObject.clickMoreOptionsButton();
        myListPageObject.clickAddToReadingListButton();
        myListPageObject.clickOnboardingButton();
        myListPageObject.inputInListLineNaming(articleName);
        myListPageObject.clickOKButton();
        myListPageObject.clickExitButtonFromMyLists();
        searchPageObject.initSearchInput();
        searchPageObject.inputInSearchLine(secondSubject);
        searchPageObject.waitForSearchResultByTitle(secondSubject);
        articlePageObject.clickMoreOptionsButton();
        myListPageObject.clickAddToReadingListButton();
        articlePageObject.clickSomeListButton(articleName);
        myListPageObject.clickExitButtonFromMyLists();
        navigationUI.clickMyListButton();
        navigationUI.openList();
        navigationUI.lestSwipe();
        assertTrue(searchPageObject.waitForSearchResultByTitleIsDisplayed(secondSubject));
    }
}
