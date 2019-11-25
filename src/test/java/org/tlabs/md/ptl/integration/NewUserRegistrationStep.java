package org.tlabs.md.ptl.integration;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tlabs.md.ptl.ws.service.*;

public class NewUserRegistrationStep {

    private static final Logger logger = LoggerFactory.getLogger(NewUserRegistrationStep.class);

    ObjectFactory objectFactory = new ObjectFactory();

    NewUserRegistrationRequest newUserRegistrationRequest;
    NewUserRegistrationResponse newUserRegistrationResponse;

    CustomerBaseWs customerBaseWs;


    @Given("Agent software must register a new user.")
    public void prepareSoftwareAgent() throws Throwable {

        logger.info("Software Agent Client Preparing");
        customerBaseWs = new CustomerBaseManagerWs().getCustomerBaseWsImplPort();
    }

    @And("Agent software prepares the registration request by filling it with the following data: Name, Surname, Date of birth, Email, Username and Password.")
    public void prepareRequest() throws Throwable {

        logger.info("SOAP Request Preparing");

        newUserRegistrationRequest = objectFactory.createNewUserRegistrationRequest();

        PersonalDataDTO personalDataDTO = objectFactory.createPersonalDataDTO();
        personalDataDTO.setBirthDate("1982-08-31");
        personalDataDTO.setName("Jon");
        personalDataDTO.setSurname("Doe");

        PersonalContactDTO personalContactDTO = objectFactory.createPersonalContactDTO();
        personalContactDTO.setEmailAddress("jon.doe@example.org");

        PersonalCredentialDTO personalCredentialDTO = objectFactory.createPersonalCredentialDTO();
        personalCredentialDTO.setUsername("j.doe");
        personalCredentialDTO.setPassword("12345678");

        newUserRegistrationRequest.setPersonalData(personalDataDTO);
        newUserRegistrationRequest.setPersonalContact(personalContactDTO);
        newUserRegistrationRequest.setPersonalCredential(personalCredentialDTO);
    }

    @When("Agent software sends the registration request to the system.")
    public void sendRequest() throws Throwable {

        logger.info("SOAP Request Sending");

        newUserRegistrationResponse = customerBaseWs.newUserRegistration(newUserRegistrationRequest);
    }

    @Then("The system returns a response containing the following information: operation result code, account activation link")
    public void handleResponse() throws Throwable {

        logger.info("Handle Response");

        Assert.assertNotNull(newUserRegistrationResponse);
        Assert.assertTrue("NUR-S00".equals(newUserRegistrationResponse.getOperationCode()));
    }
}
