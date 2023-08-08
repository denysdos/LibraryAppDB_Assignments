package com.library.steps;

import com.github.javafaker.Book;
import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class us02_StepDef {


    LoginPage loginPage = new LoginPage();
    BorrowedBooksPage borrowedBooksPageUI = new BorrowedBooksPage();
    String BorrowUI;


    @Given("the {string} on the home page")
    public void the_on_the_home_page(String username) {
        loginPage.login(username);
        BrowserUtil.waitFor(2);
    }

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        BorrowUI =  borrowedBooksPageUI.BorrowedBooksNumber.getText();
    }


    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

         String query = "select count(*) from book_borrow  where is_returned = 0";

         DB_Util.runQuery(query);

        String expectedBorrowedBooksNumbers  = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(BorrowUI,expectedBorrowedBooksNumbers);

        System.out.println("Number borrow books on the WebPage: " + BorrowUI);
        System.out.println("Number borrow books on the DB: " + expectedBorrowedBooksNumbers);



        DB_Util.destroy();

    }



    }





