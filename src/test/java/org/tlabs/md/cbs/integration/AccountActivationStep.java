package org.tlabs.md.cbs.integration;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tlabs.md.cbs.integration.general.*;
import org.tlabs.md.cbs.integration.util.ClientPasswordCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountActivationStep {

    private static final Logger logger = LoggerFactory.getLogger(AccountActivationStep.class);

    ObjectFactory objectFactory = new ObjectFactory();

    ActivationAccountRequest activationAccountRequest;
    ActivationAccountResponse activationAccountResponse;

    CustomerBaseGeneralN customerBaseWs;


    @Given("Software Agent must activate a user's account")
    public void prepareSoftwareAgent() throws Throwable {

        logger.info("Software Agent Client Preparing");
        customerBaseWs = new CustomerBaseGeneralSN().getCustomerBaseGeneralPN();
    }

    @And("Software Agent prepares the request for activation account by filling it with the following data: temporary token, credential")
    public void prepareRequest() throws Throwable {

        logger.info("SOAP Request Preparing");

        activationAccountRequest = objectFactory.createActivationAccountRequest();
        activationAccountRequest.setActivationCode(UUID.randomUUID().toString());
    }

    @When("Agent software sends the activation request to the system")
    public void sendRequest() throws Throwable {

        logger.info("SOAP Request Sending");

        activationAccountResponse = this.customerBaseWs.accountActivationOpN(activationAccountRequest);
    }

    @Then("The system check the temporary token that carrier expiration time and identity info and returns a response containing the following information: operation result code, message info")
    public void handleResponse() throws Throwable {

        logger.info("Handle Response");

        Assert.assertNotNull(activationAccountResponse);
        Assert.assertTrue("NUR-S01".equals(activationAccountResponse.getOperationCode()));
    }
}
