package com.ebay.Ebay_BDD.stepDefinitions;

import com.ebay.Ebay_BDD.constants.Core;
import com.ebay.Ebay_BDD.utils.Util;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * Created by Vamshi.Ponnam on 31/12/2014.
 */
public class CommonSteps extends Util {
    @Given("^The landing page is opened$")
    public void The_landing_page_is_opened() throws Exception {
        openUrl("http://ebay.co.uk");
    }
    @Then("^I click the search button$")
    public void I_click_the_search_button() throws Exception {
        click(Core.SEARCH_BUTTON);

    }

}
