@cbs-integration
Feature: You can register as a new user to find a doctor or be able to propose yourself as a

  As a software agent
  I want to be able to register a new user
  So That the user can execute activation account procedure

  Scenario: Register new user as generic User
    Given Agent software must register a new user.
      And Agent software prepares the registration request by filling it with the following data: Name, Surname, Date of birth, Email, Username and Password.
     When Agent software sends the registration request to the system.
     Then The system returns a response containing the following information: operation result code, account activation link