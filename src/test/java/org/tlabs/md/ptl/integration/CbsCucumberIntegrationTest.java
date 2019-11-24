package org.tlabs.md.ptl.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", strict = false, tags = {"@cbs-integration"},
        plugin = { "pretty", "html:target/cucumber/report",
                    "json:target/cucumber/reports.json",
                    "rerun:target/cucumber/rerun.txt" })
public class CbsCucumberIntegrationTest {
}
