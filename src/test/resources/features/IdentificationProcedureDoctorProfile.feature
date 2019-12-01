@cbs-integration
Feature: I'm a Doctor

  As a software agent
  I want to be able to update the personal data of a specific user
  So that he can require identification as a doctor qualified to perform medical activity

  Scenario: Identification procedure as a qualified doctor
    Given Software agent must begin the identification procedure as a licensed doctor.
      And Agent software prepares the registration request by filling it with the following data: main medical qualification, personal identification code as a profile qualified as a doctor, fiscal Code.
      And Agent software also specifies the credentials of the user requesting the identification procedure.
     When Agent software sends the update request.
     Then The system responds with the following information: request result code, request status, message info.