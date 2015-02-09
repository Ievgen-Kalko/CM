Narrative:
In order to calculate coin's price according to its parameters
As a development team
I would like to implement decision table to visually present price matrix


Scenario: Price calculation scenario 1 whenUSGold1907VeryFineCoinReceived_thenPrice2750000

Given an empty coin
And its parameter country is US
And its parameter composition is GOLD
And its parameter year is 1907
And its parameter grade is VERY_FINE
When the price is calculated
Then the price should be 2750000

Scenario:  Stack search

Given an empty stack
When the string Java is added
And the string C++ is added
And the string PHP is added
And the element Java is searched for
Then the position returned should be 3