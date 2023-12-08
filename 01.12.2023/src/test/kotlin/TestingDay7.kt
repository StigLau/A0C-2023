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
        assertEquals(20003131002L, hands.get(0).strength())
        assertEquals(40000051110, hands.get(1).strength())
        assertEquals(30000130706, hands.get(2).strength())
        assertEquals(30000111013, hands.get(3).strength())
        assertEquals(40000121411, hands.get(4).strength())
    }

    @Test
    fun battleHands() {
        val hands: List<Hand> = day7.parseInput(testdata.lines()).sortedBy { it.gotStrength }
        assertEquals(40000121411, hands.get(4).gotStrength)
        assertEquals(40000051110, hands.get(3).gotStrength)
        assertEquals(30000130706, hands.get(2).gotStrength)
        assertEquals(30000111013, hands.get(1).gotStrength)
        assertEquals(20003131002, hands.get(0).gotStrength)
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
    fun testWithTestData2() {
        val hands = day7.parseInput(testdata2.lines())
        val rankedHandsAndBids = hands.mapIndexed { index, hand ->
            (index + 1) * hand.bid }

        assertEquals(1343, rankedHandsAndBids.sum())
        //
    }

    @Test
    fun dealWithProd() {
        val unsortedHands = day7.parseInput(readFile("day7.txt"))
        val hands: List<Hand> = unsortedHands.sortedBy { it.gotStrength }
        assertEquals(1000, unsortedHands.size)
        assertEquals(460, unsortedHands.first().bid)
        assertEquals(50000000910, unsortedHands.first().strength())
        assertEquals(677, unsortedHands.last().bid)
        assertEquals(60000001211, unsortedHands.last().strength())
        assertEquals(396, hands.last().bid)
        assertEquals(70000000011, hands.last().gotStrength)
        assertEquals(1, hands.get(1).bid)
        assertEquals(10807040302, hands.get(1).gotStrength)
        assertEquals(556, hands.first().bid)
        assertEquals(10706050302, hands.first().gotStrength)

        var sumItALL:Long = 0
        val rankedHandsAndBids = hands.mapIndexed { index, hand ->
            sumItALL += (index + 1) * hand.bid
            (index + 1) * hand.bid

        }
        assertEquals(247255339, rankedHandsAndBids.sum())
        assertEquals(247255339, sumItALL)

        //247411359 was too high!
        //247375415 was wrong
        //247374953 too high!
        //250382021 was wrong
        //250390839 <- Any better?
        //253745161
        //NOT 247255339
    }

    @Test
    fun dealWithTheLesserValueCardsALSO() {
        val hands: List<Hand> = day7.parseInput(readFile("day7.txt")).sortedByDescending { it.gotStrength }
        //Read pears to verify then against each other
        val filteredPairs = hands.filter {
            //it.gotStrength > 100000000 &&
                    it.gotStrength < 20000000000L }
        println(filteredPairs)

        //House fucks
        // bids 85. 811 109
        // 9, 10, 11
        val rankedHandsAndBids = hands.mapIndexed { index, hand ->
            (index + 1) * hand.bid
        }
        assertEquals(253745161, rankedHandsAndBids.sum())
    }



    ///////!!!!!!!TODO Husk på å finne ut av hvilken som har de høyeste verdiene basert på neste kort!





}

