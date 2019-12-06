@YandexSearch
Feature: Search
  As a man
  I want to use yandex search

  Background: User navigates to yandex home page
    Given I am on the "Яндекс" page on URL "https://ya.ru/"
    Then I should see "Сделать стартовой" message

  Scenario: Successful search
    When I fill text field "text" with "microsoft" and hit enter
    Then I should see "Американская компания" message
    Then I click on the "Википедия" link
    Then I switch to the last tab
    Then I should see "Материал из Википедии — свободной энциклопедии" message
    Then I close last tab
