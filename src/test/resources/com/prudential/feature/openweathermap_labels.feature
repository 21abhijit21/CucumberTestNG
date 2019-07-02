#Author: Abhijit Bhattacharyya
#Keywords Summary :

@WebTests
Feature: Validate All Labels on HomePage and Search Functionality


Background: User is on WeatherApp
 Given User navigates to "https://openweathermap.org/"

@WebTests
Scenario: Validate all labels are correctly displayed on the Home Page
Given User is on Home Page for "OpenWeatherMap" App
Then all Header labels should be accurately displayed
And all Body labels should be accurately displayed


@WebTests
Scenario Outline: Title of your scenario outline
Given User is on Home Page for "OpenWeatherMap" App
When I search for an invalid <City>
Then appropriate <Error> should be displayed

Examples:
    | City    |   Error   |
    | Invalid | Not found |
    
@WebTests
Scenario Outline: Search For Invalid City To Validate Error message
Given User is on Home Page for "OpenWeatherMap" App
When I search for an invalid <City>
Then appropriate <Error> should be displayed

Examples:
    | City    |   Error   |
    | Invalid | Not found |
    
@WebTests
Scenario Outline: Search For Valid City To Validate Error message
Given User is on Home Page for "OpenWeatherMap" App
When I search for a valid <City>
Then Reults should be displayed for Search

Examples:
    | City    |
    | London  |
