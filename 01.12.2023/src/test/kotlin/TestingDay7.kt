import Utils.Companion.readFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestingDay7 {
    val testdata =
        """32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483"""

val testdata2 =
        """AAAAA 2
22222 3
AAAAK 5
22223 7
AAAKK 11
22233 13
AAAKQ 17
22234 19
AAKKQ 23
22334 29
AAKQJ 31
22345 37
AKQJT 41
23456 43"""

    val day7 = Day7()
    @Test
    fun testingHandConversion() {
        val hand = Hand.convertToHand("T55J5")
        assertEquals(11, hand.first())
        assertEquals(5, hand.last())
        assertEquals(5, hand.size)
    }

    @Test
    fun testInputParsing() {
        val hands: List<Hand> = Hand.parseInput(testdata.lines())
        assertEquals(5, hands.size)
        assertEquals(765, hands.get(0).bid)
        assertEquals(483, hands.get(4).bid)
    }

    @Test
    fun testEvaluatingHand() {
        val hands: List<Hand> = Hand.parseInput(testdata.lines())
        /*
        assertEquals(20003131002L, hands.get(0).strength())
        assertEquals(40000051110, hands.get(1).strength())
        assertEquals(30000130706, hands.get(2).strength())
        assertEquals(30000111013, hands.get(3).strength())
        assertEquals(40000121411, hands.get(4).strength())

         */
    }

    @Test
    fun battleHands() {
        /*
        val hands: List<Hand> = day7.parseInput(testdata.lines()).sortedBy { it.gotStrength }
        assertEquals(40000121411, hands.get(4).gotStrength)
        assertEquals(40000051110, hands.get(3).gotStrength)
        assertEquals(30000130706, hands.get(2).gotStrength)
        assertEquals(30000111013, hands.get(1).gotStrength)
        assertEquals(20003131002, hands.get(0).gotStrength)

         */
    }
/*
    @Test
    fun handAndBid() {
        val hands: List<Hand> = day7.parseInput(testdata.lines()).sortedBy { it.strength(it.cards) }
        assertEquals(483, hands.last().bid)
        assertEquals(765, hands.first().bid)
        val rankedHandsAndBids = hands.mapIndexed { index, hand ->
            (index + 1) * hand.bid
        }
        assertEquals(6440, rankedHandsAndBids.sum())
    }

 */

    @Test
    fun testWithTestData2() {
        val hands = Hand.parseInput(testdata2.lines())
        val sortedHands = hands.sortedWith(Hand.handComparator)
        sortedHands.forEach { println(it) }
        val rankedHandsAndBids = hands.sortedWith(Hand.handComparator).mapIndexed { index, hand ->
            (index + 1) * hand.bid }

        assertEquals(1343, rankedHandsAndBids.sum())
    }


    @Test
    fun newTestWithSuperTestData() {
        val hands = Hand.parseInput(testdata2.lines())

        val sortedStuff = hands.sortedWith(Hand.handComparator)
        val rankedHandsAndBids = sortedStuff.mapIndexed { index, hand ->
            (index + 1) * hand.bid }
        assertEquals(1343, rankedHandsAndBids.sum())
    }

    @Test
    fun newProdTest() {
        val hands = Hand.parseInput(readFile("day7.txt"))
        val sortedStuff = hands.sortedWith(Hand.handComparator)
        sortedStuff.forEach { println(it) }
        val rankedHandsAndBids = sortedStuff.mapIndexed { index, hand ->
            (index + 1) * hand.bid }
        assertEquals(246795406, rankedHandsAndBids.sum())
    }



}

