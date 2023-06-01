@ui
Feature: Wikipedia search functionality
  As a user, I should be able to search terms and see relevant information
  Case: Search for "celebrity full name", the title, the header, and the image

  Background:Assuming user is on the Wikipedia home page
    Given User is on Wikipedia home page

  Scenario: Wikipedia Search Functionality Title Verification
    When User types "Steve Jobs" in the wiki search box
    And User clicks wiki search button
    Then User sees "Steve Jobs" is in the wiki title


  Scenario: Wikipedia Search Functionality Header Verification
    When User types "Michael Jackson" in the wiki search box
    And User clicks wiki search button
    Then User sees "Michael Jackson" is in the main header


  Scenario: Wikipedia Search Functionality Image Header Verification
    When User types "Pablo Picasso" in the wiki search box
    And User clicks wiki search button
    Then User sees "Pablo Picasso" is in the image header

  @wip @ui
    @scenarioOutline
  Scenario Outline: Wikipedia Search Functionality Image Header Verification
    When User types "<searchValue>" in the wiki search box
    And User clicks wiki search button
    Then User sees "<expectedImageHeader>" is in the image header
    Then User sees "<expectedTitle>" is in the wiki title

    Examples: Search terms we are going to use in this template is as below
      | searchValue       | expectedImageHeader | expectedTitle     |
      | Jack Ma           | Jack Ma             | Jack Ma           |
      | Albert Einstein   | Albert Einstein     | Albert Einstein   |
      | Nikola Tesla      | Nikola Tesla        | Nikola Tesla      |
      | Muhammad Ali      | Muhammad Ali        | Muhammad Ali      |
      | Chuck Norris      | Chuck Norris        | Chuck Norris      |
      | Dwayne Johnson    | Dwayne Johnson      | Dwayne Johnson    |
      | Marie Curie       | Marie Curie         | Marie Curie       |
      | Elvis Presley     | Elvis Presley       | Elvis Presley     |
      | Leonardo da Vinci | Leonardo da Vinci   | Leonardo da Vinci |
      | Thomas Edison     | Thomas Edison       | Thomas Edison     |
      | Indira Gandhi     | Indira Gandhi       | Indira Gandhi     |
      | Henry Ford        | Henry Ford          | Henry Ford        |