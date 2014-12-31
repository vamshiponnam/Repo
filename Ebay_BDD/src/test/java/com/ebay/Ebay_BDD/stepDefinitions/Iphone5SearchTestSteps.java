package com.ebay.Ebay_BDD.stepDefinitions;

import com.ebay.Ebay_BDD.constants.Core;
import com.ebay.Ebay_BDD.utils.Util;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

/**
 * Created by Vamshi.Ponnam on 31/12/2014.
 */
public class Iphone5SearchTestSteps extends Util {


    @When("^I Enter IPhone5 in the search box$")
    public void I_Enter_IPhone5_in_the_search_box() throws Exception {
        sendKeys(Core.INPUT_SEARCH,"iphone5");
    }



    @Then("^I verify the results of Iphone5$")
    public void I_verify_the_results() throws Exception {
        assertEquals("results for iphone5",getText(Core.TEXT_RESULT));

    }


}
