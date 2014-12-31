package com.ebay.Ebay_BDD.stepDefinitions;


import com.ebay.Ebay_BDD.constants.Core;
import com.ebay.Ebay_BDD.utils.Util;
import cucumber.api.java.en.*;
import cucumber.api.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vamshi.Ponnam on 05/12/2014.
 */
public class Iphone6SearchTestSteps extends Util {

    @When("^I Enter IPhone6 in the search box$")
    public void I_Enter_IPhone6_in_the_search_box() throws Exception {
         sendKeys(Core.INPUT_SEARCH,"iphone6");
    }


    @Then("^I verify the results of Iphone6$")
    public void I_verify_the_results() throws Exception {
        assertEquals("results for iphone6",getText(Core.TEXT_RESULT));

    }



}
