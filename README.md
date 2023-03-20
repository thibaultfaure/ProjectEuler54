# ProjectEuler54
A solution to the Project Euler #54 problem: Poker Hands (https://projecteuler.net/problem=54)

## Description
Firstly, all the hands are retrieved from the poker.txt file via the FileCardReader.<br/>
Then both players' hands are parsed as combinations for every poker hand.<br/>
The CombinationFactory factory creates the right combination by analyzing the player's hand.<br/>
In the end, we need to compare the two players' combinations with each other to know the winner of the hand.<br/>
Both players' hands and the winner are printed to the standard console.
