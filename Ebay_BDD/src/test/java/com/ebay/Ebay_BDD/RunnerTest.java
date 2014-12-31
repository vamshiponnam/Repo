package com.ebay.Ebay_BDD;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@Cucumber.Options(
        features = "src/test/resources/",
        format = { "pretty","html: cucumber-html-reports","json: cucumber-html-reports/cucumber.json" },
        tags = {"@Runme"}
)

public class RunnerTest {



}
