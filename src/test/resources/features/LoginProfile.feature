@LoginProfile
Feature: Login Profile
  As an employee of the company
  I want to login my employee profile using my credentials
  In order to collaborate with my colleagues

  Background: User navigates to gmail home page
    Given I am on the "Gmail" page on URL "http://gmail.com"
    Then I should see "Перейти в Gmail" message

  Scenario: Successful login
    When I fill in "email" with "testingangrybambr@gmail.com"
    And I click on the "Далее" button
    And I fill in "password" with "thisIsVeryHardPassword98741"
    And I click on the "Далее" button
    Then I should see "Входящие" message

  Scenario Outline: Failed login using wrong credentials
    When I fill in "email" with "<username>"
    And I click on the "Далее" button
    And I should see "<warning>" message
    Examples:
      | username        | password | warning                                             |
      | asdas@gmail.com | !23      | Не удалось найти аккаунт Google                     |
      |                 | !23      | Введите адрес электронной почты или номер телефона. |
      | Test            |          | Не удалось найти аккаунт Google                     |

  Scenario Outline: Failed login using wrong password
    When I fill in "email" with "<username>"
    And I click on the "Далее" button
    And I should see "<warning>" message
    Examples:
      | username                    | password | warning                                                                                        |
      | testingangrybambr@gmail.com | !23      | Неверный пароль. Повторите попытку или нажмите на ссылку "Забыли пароль?", чтобы сбросить его. |
      | testingangrybambr@gmail.com |          | Введите пароль                                                                                 |
