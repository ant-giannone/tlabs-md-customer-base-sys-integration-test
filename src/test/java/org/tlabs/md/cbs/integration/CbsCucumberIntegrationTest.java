package org.tlabs.md.cbs.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
        strict = false,
        tags = {
            "@cbs-integration-registration-user",
            "@cbs-integration-activation-code",
            "@cbs-integration-identification-procedure"
        },
        plugin = { "pretty", "html:target/cucumber/report",
                    "json:target/cucumber/reports.json",
                    "rerun:target/cucumber/rerun.txt" })
public class CbsCucumberIntegrationTest {
}
