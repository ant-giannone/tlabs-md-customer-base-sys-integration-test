package org.tlabs.md.ptl.integration;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tlabs.md.ptl.ws.service.*;

import java.util.UUID;

public class AccountActivationStep {

    private static final Logger logger = LoggerFactory.getLogger(AccountActivationStep.class);

    ObjectFactory objectFactory = new ObjectFactory();

    ActivationAccountRequest activationAccountRequest;
    ActivationAccountResponse activationAccountResponse;

    CustomerBaseWs customerBaseWs;


    @Given("Software Agent must activate a user's account")
    public void prepareSoftwareAgent() throws Throwable {

        logger.info("Software Agent Client Preparing");
        customerBaseWs = new CustomerBaseManagerWs().getCustomerBaseWsImplPort();
    }

    @And("Software Agent prepares the request for activation account by filling it with the following data: temporary token, credential")
    public void prepareRequest() throws Throwable {

        logger.info("SOAP Request Preparing");

        activationAccountRequest = objectFactory.createActivationAccountRequest();
        activationAccountRequest.setActivationCode(UUID.randomUUID().toString());
    }

    @When("When Agent software sends the activation request to the system.")
    public void sendRequest() throws Throwable {

        logger.info("SOAP Request Sending");

        activationAccountResponse = customerBaseWs.accountActivation(activationAccountRequest);
    }

    @Then("The system check the temporary token that carrier expiration time and identity info and returns a response containing the following information: operation result code, message info")
    public void handleResponse() throws Throwable {

        logger.info("Handle Response");

        Assert.assertNotNull(activationAccountResponse);
        Assert.assertTrue("NUR-S01".equals(activationAccountResponse.getOperationCode()));
    }
}
