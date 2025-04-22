@tag
  Feature: Purchase the Order from Ecommerce Website
   Background:
     Given I landed on Ecommerce Page

    @Regression
    Scenario Outline: Positive Test of Submitting the order
      Given Logged in with username <name> and password <password>
      When I add product <productName> to cart
      And checkout <productName> and submit the order
      Then "Thankyou for the order." message is displayed on ConfirmationPage
      Examples:
        | name                  | password    | productName |
        | shetty@gmail.com | Iamking@000 | ZARA COAT 3 |