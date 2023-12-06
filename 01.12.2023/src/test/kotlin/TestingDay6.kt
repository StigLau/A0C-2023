import Day6.Companion.calculateBoatDistance
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestingDay6 {
    //Time:        35     93     73     66
    //Distance:   212   2060   1201   1044

    @Test
    fun test1() {
        val time = 35L
        val timeToBeat = 212L
        //function = y = a * (x-a)
        //time = a. distance = y
        val rezz = calculateBoatDistance(time, timeToBeat)
        println(rezz)
        println("We got ${rezz.count()}")
        assertEquals(20, rezz.count())
    }
    @Test
    fun test2() {
        val first = calculateBoatDistance(35, 212).count()
        val second = calculateBoatDistance(93, 2060).count()
        val third = calculateBoatDistance(73, 1201).count()
        val fourth = calculateBoatDistance(66, 1044).count()

        val sumz = first*second*third*fourth
        println("All together now: $sumz")
        assertEquals(114400, sumz)
    }

    @Test
    fun day6Part2TestAssignmentVerification() {
        //Get the same numbers as assignment
        val times = calculateBoatDistance(71530, 940200)
        println(times)
        assertEquals(14, times.first().first)
        assertEquals(71516, times.last().first)
    }
    @Test
    fun day6Part2TestMy() {
        val time = " 35     93     73     66".replace(" ", "").toLong()
        val timesToBeat = "212   2060   1201   1044".replace(" ", "").toLong()
        //Get the same numbers as assignment
        val times = calculateBoatDistance(time, timesToBeat)
        println("Rezult = ${times.first().first} and ${times.last().first}")
        println("Summed up: ${times.count()}")
        assertEquals(7448819, times.first().first)
        assertEquals(28488547, times.last().first)
        assertEquals(21039729, times.count())
    }

}