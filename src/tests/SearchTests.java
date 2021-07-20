package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.inputInSearchLine("Java");
        searchPageObject.waitForSearchResultByXpath("Object-oriented programming language");
    }

    @Test
    public void testCancel() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.inputInSearchLine("Java");
        searchPageObject.clearSearchLine();
        assertTrue(searchPageObject.searchLineIsDisplayed());
    }

    @Test
    public void testOfSomeWordInEveryFindElements() throws InterruptedException {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.inputInSearchLine("Java");
        Thread.sleep(5000);
        List<WebElement> listOfFindElements = searchPageObject.getListOfResults();
        String someText = "Java";
        boolean containsSomeText = false;
        for (int i = 0; i < listOfFindElements.size(); i++) {
            if (listOfFindElements.get(i).getText().contains(someText)) {
                containsSomeText = true;
            }
        }
        assertTrue(containsSomeText);
    }

    @Test
    public void testOfTitlePresent() {
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        String value = "Object-oriented programming language";
        searchPageObject.initSearchInput();
        searchPageObject.inputInSearchLine("Java");
        searchPageObject.waitForSearchResultByXpath("Object-oriented programming language");

        assertEquals(value, articlePageObject.getSomeTitle());
    }
}
