# ProjectEuler54
A solution to the Project Euler #54 problem: Poker Hands (https://projecteuler.net/problem=54)

## High-level description
Firstly, all the hands are retrieved from the poker.txt file via the FileCardReader.
Then both players' hands are parsed as combinations for every poker hand.
The CombinationFactory factory creates the right combination by analyzing the player's hand.
In the end, we need to compare the two players' combinations with each other to know the winner of the hand.
Both players' hands and the winner are printed to the standard console.
