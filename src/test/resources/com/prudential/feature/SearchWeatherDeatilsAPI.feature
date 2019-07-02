#Author: Abhijit Bhattacharyya
#Keywords Summary :
@API
Feature: Validate Weather Deatils via API

@API
Scenario Outline: Search for weather details via API
Given I search for Weather Deatils for <Country> <ZIP> on the WeatherApp api
Then correct  <status> <ZIP> and <Country> should be displayed

Examples:
    | Country  |ZIP   | status |
    | US       |94040 | OK     |
