package org.tlabs.md.cbs.integration;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tlabs.md.cbs.integration.registration.*;

import java.time.Instant;
import java.util.UUID;

public class NewUserRegistrationStep {

    private static final Logger logger = LoggerFactory.getLogger(NewUserRegistrationStep.class);

    ObjectFactory objectFactory = new ObjectFactory();

    NewUserRegistrationRequest newUserRegistrationRequest;
    NewUserRegistrationResponse newUserRegistrationResponse;

    CustomerBaseRegistrationN customerBaseWs;


    @Given("Agent software must register a new user.")
    public void prepareSoftwareAgent() throws Throwable {

        logger.info("Software Agent Client Preparing");
        customerBaseWs = new CustomerBaseRegistrationSN().getCustomerBaseRegistrationPN();
    }

    @And("Agent software prepares the registration request by filling it with the following data: Name, Surname, Date of birth, Email, Username and Password.")
    public NewUserRegistrationRequest prepareRequest() throws Throwable {

        logger.info("SOAP Request Preparing");

        newUserRegistrationRequest = objectFactory.createNewUserRegistrationRequest();

        PersonalDataDTO personalDataDTO = objectFactory.createPersonalDataDTO();
        personalDataDTO.setBirthDate("1982-08-31");
        personalDataDTO.setName("Jon");
        personalDataDTO.setSurname("Doe");

        PersonalContactDTO personalContactDTO = objectFactory.createPersonalContactDTO();
        personalContactDTO.setEmailAddress("jon.doe."+ UUID.randomUUID().toString()+"@example.org");

        PersonalCredentialDTO personalCredentialDTO = objectFactory.createPersonalCredentialDTO();
        personalCredentialDTO.setUsername("j.doe." + UUID.randomUUID().toString());
        personalCredentialDTO.setPassword("12345678");

        newUserRegistrationRequest.setPersonalData(personalDataDTO);
        newUserRegistrationRequest.setPersonalContact(personalContactDTO);
        newUserRegistrationRequest.setPersonalCredential(personalCredentialDTO);

        return newUserRegistrationRequest;
    }

    @When("Agent software sends the registration request to the system.")
    public void sendRequest() throws Throwable {

        logger.info("SOAP Request Sending");

        newUserRegistrationResponse = customerBaseWs.newRegistration(newUserRegistrationRequest);
    }

    @Then("The system returns a response containing the following information: operation result code, account activation link")
    public String handleResponse() throws Throwable {

        logger.info("Handle Response");

        Assert.assertNotNull(newUserRegistrationResponse);
        Assert.assertTrue("NUR-S00".equals(newUserRegistrationResponse.getOperationCode()));

        return newUserRegistrationResponse.getAccountActivationCode();
    }
}
