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
        val hands: List<Hand> = day7.parseInput(testdata.lines())
        assertEquals(5, hands.size)
        assertEquals(765, hands.get(0).bid)
        assertEquals(483, hands.get(4).bid)
    }

    @Test
    fun testEvaluatingHand() {
        val hands: List<Hand> = day7.parseInput(testdata.lines())
        assertEquals(10003, hands.get(0).strength())
        assertEquals(30005, hands.get(1).strength())
        assertEquals(21307, hands.get(2).strength())
        assertEquals(21110, hands.get(3).strength())
        assertEquals(30012, hands.get(4).strength())
    }

    @Test
    fun battleHands() {
        val hands: List<Hand> = day7.parseInput(testdata.lines()).sortedBy { it.gotStrength }
        assertEquals(30012, hands.get(4).gotStrength)
        assertEquals(30005, hands.get(3).gotStrength)
        assertEquals(21307, hands.get(2).gotStrength)
        assertEquals(21110, hands.get(1).gotStrength)
        assertEquals(10003, hands.get(0).gotStrength)
    }

    @Test
    fun handAndBid() {
        val hands: List<Hand> = day7.parseInput(testdata.lines()).sortedBy { it.gotStrength }
        assertEquals(483, hands.last().bid)
        assertEquals(765, hands.first().bid)
        val rankedHandsAndBids = hands.mapIndexed { index, hand ->
            (index + 1) * hand.bid
        }
        assertEquals(6440, rankedHandsAndBids.sum())
    }

    @Test
    fun dealWithProd() {
        val unsortedHands = day7.parseInput(readFile("day7.txt"))
        val hands: List<Hand> = unsortedHands.sortedBy { it.gotStrength }
        assertEquals(1000, unsortedHands.size)
        assertEquals(460, unsortedHands.first().bid)
        assertEquals(40910, unsortedHands.first().strength())
        assertEquals(677, unsortedHands.last().bid)
        assertEquals(50012, unsortedHands.last().strength())
        assertEquals(396, hands.last().bid)
        assertEquals(60011, hands.last().gotStrength)
        assertEquals(1, hands.get(1).bid)
        assertEquals(8, hands.get(1).gotStrength)
        assertEquals(556, hands.first().bid)
        assertEquals(7, hands.first().gotStrength)

        val rankedHandsAndBids = hands.mapIndexed { index, hand ->
            (index + 1) * hand.bid
        }
        assertEquals(247411359, rankedHandsAndBids.sum())

        //247411359 was too high!
    }

    @Test
    fun dealWithTheLesserValueCardsALSO() {
        val unsortedHands = day7.parseInput(readFile("day7.txt"))
        val hands: List<Hand> = unsortedHands.sortedBy { it.gotStrength }
    }



    ///////!!!!!!!TODO Husk på å finne ut av hvilken som har de høyeste verdiene basert på neste kort!





}

