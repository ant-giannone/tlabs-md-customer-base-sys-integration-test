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

public class IdentificationProcedureDoctorProfileStep {

    private static final Logger logger = LoggerFactory.getLogger(AccountActivationStep.class);

    ObjectFactory objectFactory = new ObjectFactory();

    IdentificationProcedureRequest identificationProcedureRequest;
    IdentificationProcedureResponse identificationProcedureResponse;

    CustomerBaseGeneralN customerBaseWs;


    @Given("Software agent must begin the identification procedure as a licensed doctor.")
    public void prepareSoftwareAgent() throws Throwable {

        logger.info("Software Agent Client Preparing");
        customerBaseWs = new CustomerBaseGeneralSN().getCustomerBaseGeneralPN();
    }

    @And("Agent software prepares the registration request by filling it with the following data: main medical qualification, personal identification code as a profile qualified as a doctor, fiscal Code.")
    public void prepareRequest() throws Throwable {

        logger.info("SOAP Request Preparing");

        identificationProcedureRequest = objectFactory.createIdentificationProcedureRequest();

        identificationProcedureRequest.setFiscalCode("FAKE-FISCAL-CODE");
        identificationProcedureRequest.setMainMedicalQualification("Generic");
        identificationProcedureRequest.setPersonalIdentificationCode("FAKE-DOCTOR-P-CODE");
    }

    @And("Agent software also specifies the credentials of the user requesting the identification procedure.")
    public void prepareCredentials() throws Throwable {

        logger.info("SOAP Credentials preparing");
    }

    @When("Agent software sends the update request.")
    public void sendRequest() throws Throwable {

        logger.info("SOAP Request Sending");

        identificationProcedureResponse = this.customerBaseWs.identificationProcedureOpN(identificationProcedureRequest);
    }

    @Then("The system responds with the following information: request result code, request status, message info.")
    public void handleResponse() throws Throwable {

        logger.info("Handle Response");

        Assert.assertNotNull(identificationProcedureResponse);
        Assert.assertTrue("IPR-S01".equals(identificationProcedureResponse.getResultCode()));
    }
}
