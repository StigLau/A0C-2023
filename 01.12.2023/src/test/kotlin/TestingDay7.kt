import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestingDay7 {
    val testdata =
        """32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483"""

    //Sorting A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2
    //Five of a kind
    //Four of a kind
    //Full house
    //Three of a kind
    //Two pair
    //One pair
    //High card

    //Dealing with ties
    //Start by comparing the first card in each hand
    //If these cards are different, the hand with the stronger first card is considered stronger. If the first card in each hand have the same label, however, then move on to considering the second card in each hand etc

    //each hand is followed by its bid amount.
    //Each hand wins an amount equal to its bid multiplied by its rank, where the weakest hand gets rank 1, the second-weakest hand gets rank 2, and so on up to the strongest hand. Because there are five hands in this example, the strongest hand will have rank 5 and its bid will be multiplied by 5.

    /*Test Data
    32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483
     */

    val day7 = Day7()
    @Test
    fun testingHandConversion() {
        val hand = day7.convertToHand("T55J5")
        assertEquals(11, hand.first())
        assertEquals(5, hand.last())
        assertEquals(5, hand.size)
    }

    @Test
    fun testInputParsing() {
        val hands: List<Hand> = day7.parseInput(testdata)
        assertEquals(5, hands.size)
        assertEquals(765, hands.get(0).bid)
        assertEquals(483, hands.get(4).bid)
    }

    @Test
    fun testEvaluatingHand() {
        val hands: List<Hand> = day7.parseInput(testdata)
        assertEquals(10003, hands.get(0).strength())
        assertEquals(30005, hands.get(1).strength())
        assertEquals(21307, hands.get(2).strength())
        assertEquals(21110, hands.get(3).strength())
        assertEquals(30012, hands.get(4).strength())
    }

    @Test
    fun battleHands() {
        val hands: List<Hand> = day7.parseInput(testdata).sortedBy { it.gotStrength }
        assertEquals(30012, hands.get(4).gotStrength)
        assertEquals(30005, hands.get(3).gotStrength)
        assertEquals(21307, hands.get(2).gotStrength)
        assertEquals(21110, hands.get(1).gotStrength)
        assertEquals(10003, hands.get(0).gotStrength)
    }

    @Test
    fun handAndBid() {
        val hands: List<Hand> = day7.parseInput(testdata).sortedBy { it.gotStrength }
        assertEquals(483, hands.last().bid)
        assertEquals(765, hands.first().bid)
        val rankedHandsAndBids = hands.mapIndexed { index, hand ->
            (index + 1) * hand.bid
        }
        assertEquals(6440, rankedHandsAndBids.sum())
    }

    @Test
    fun dealWithProd() {

    }





}

