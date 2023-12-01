import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class TestingVersionTwo {
    val eligble = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine" )
    val reversedEligble = listOf("eno", "owt", "eerht", "ruof", "evif", "xis", "neves", "thgie", "enin" )

    @Test
    fun firstTest() {
        val input = "fourfourthreehnbhkmscqxdfksg64bvpppznkh"
        val firstDigit = RoundTwo().findFirstDigit(input, eligble)
        val secondDigit = RoundTwo().findFirstDigit(input.reversed(), reversedEligble)

        assertEquals('4', firstDigit)
        assertEquals('4', secondDigit)
        assertEquals("44", "$firstDigit$secondDigit")
    }

    @Test
    fun secondtest() {
        val input = "7sixvmsrrzqnngonethree"
        val firstDigit = RoundTwo().findFirstDigit(input, eligble)
        val reverseDigit = RoundTwo().findFirstDigit(input.reversed(), reversedEligble)

        assertEquals('7', firstDigit)
        assertEquals('3', reverseDigit)
        assertEquals("73", "$firstDigit$reverseDigit")
    }

    @Test
    fun test3() {
        val input = "dbpjzgdrhnsixfjvs9eightsjdgtckdtsjmhplkjeightwodd"
        val firstDigit = RoundTwo().findFirstDigit(input, eligble)
        val reverseDigit = RoundTwo().findFirstDigit(input.reversed(), reversedEligble)

        assertEquals('6', firstDigit)
        assertEquals('2', reverseDigit)
        assertEquals("62", "$firstDigit$reverseDigit")
    }

    @Test
    fun test4() {
        val input = "ftlpbv55nine"
        val firstDigit = RoundTwo().findFirstDigit(input, eligble)
        val reverseDigit = RoundTwo().findFirstDigit(input.reversed(), reversedEligble)

        assertEquals('5', firstDigit)
        assertEquals('9', reverseDigit)
        assertEquals("59", "$firstDigit$reverseDigit")
    }

    @Test
    fun testWithInput() {
        val result = File("input1.txt").readLines().map {
            val firstDigit = RoundTwo().findFirstDigit(it, eligble)
            println("RESULT 1 was $firstDigit")
            val secondDigit = RoundTwo().findFirstDigit(it.reversed(), reversedEligble)
            println("RESULT REVERSED WAS $secondDigit")

            println("Combined: $firstDigit$secondDigit")
            "$firstDigit$secondDigit".toInt()
        }.sum()

        println("Let there be snow: ${result}")
        assertEquals(54418, result)
    }
}