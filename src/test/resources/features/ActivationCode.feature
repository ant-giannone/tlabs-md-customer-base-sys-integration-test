@cbs-integration
Feature: Activation Account

  As a software agent
  I want to be able to request activation account for a specific user
  So That the user can access the services of the system

  Scenario: The user wants to activate his account
    Given Software Agent must activate a user's account
      And Software Agent prepares the request for activation account by filling it with the following data: temporary token, credential
     When Agent software sends the activation request to the system
     Then The system check the temporary token that carrier expiration time and identity info and returns a response containing the following information: operation result code, message info