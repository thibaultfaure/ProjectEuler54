# ProjectEuler54
Solution to the Project Euler #54 problem: Poker Hands (https://projecteuler.net/problem=54)

## High-level description
Firstly, all the hands are retrieved from the poker.txt file via the FileCardReader.
Then, for every poker hand, both players' hands are parsed as combinations.
The CombinationFactory factory creates the right combination by analyzing the player's hand.
In the end, we just need to compare the two players' combinations with each other to know the winner of the hand.
Both players' hands and the winner are printed to the standard console.

## Concepts used
All combination classes implement the Combination interface. 
Combination extends the Comparable interface, and each Combination can define its own ordering method.
Apart from that, the factory method design pattern is used to create the Combination.

The deck package contains classes describing cards in a vacuum.
I am not 100% satisfied with the comparator's name.
The CardStrengthComparator defines an order that allows ordering cards by rank in a hand.
However, the HandStrengthComparator recursively compares the ranks of the cards in a hand.
My opinion is that it can be confusing that they share the same naming pattern even if they do different things, so I'd like to find another name in the future.

## Remarks
This solution tries to focus on readability.
The solution is extensively tested.
The drawback of this solution is that it needs a lot of files to be created, worrying me about class explosion.
Also, if we'd focus on time efficiency to compute the winner of the hand, we don't necessarily need to completely parse both hands.
For instance, if the first player has a straight flush and the second hand isn't, we already know that the first player won.
My solution doesn't intend to optimize computing time.

Also, let's note that I've not been 100% consistent in the way I treat the preconditions set in the problem description.
For instance, I refuse files with some wrong formats.
However, I haven't checked that both hands aren't overlapping. 
Neither did I handle ties.

## Latest introduction
In the end, I followed my Sonarlint suggestion to convert the Card class into a Record.
I never had used them before.
I'm wondering if the whole code could be simplified via Records, and maybe even reduce the number of classes by having a special Record directly used for hand comparison.

## Evolutions
In the future, I might come back to this project and try to implement a solution focusing on time efficiency.
If I do that, I'd like to benchmark both solutions using JMH.