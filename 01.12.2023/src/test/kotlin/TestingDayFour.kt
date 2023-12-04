import org.junit.jupiter.api.Test
import java.nio.file.Path
import kotlin.io.path.readLines

class TestingDayFour {

    @Test
    fun parsing() {
        val filz = TestingDayThree::class.java.getResource("day4.txt")
        val lines = Path.of(filz!!.toURI()).readLines()
        val games = lines.map { line ->
            parse(line).map { translate(it.toInt()) }
        }.toList()
        println(games)
        println(games.flatMap { it }.sum())

    }

    @Test
    fun mapWinners() {
        val filz = TestingDayThree::class.java.getResource("day4.txt")
        val lines = Path.of(filz!!.toURI()).readLines()

    }
}



    fun translate(winnings: Int): Int =
        when (winnings) {
            0 -> 0
            1 -> 1
            2 -> 2
            3 -> 4
            4 -> 8
            5 -> 16
            6 -> 32
            7 -> 64
            8 -> 128
            9 -> 256
            10 -> 512
            11 -> 1024
            else -> -1
        }

        fun parse(line: String): List<String> {
            val splut = line.split("|")
            val winners = splut[0].split(":")[1].trim().split(" ")
            val numbers = splut[1].trim().replace("  ", " ").split(" ")
            return worth(numbers, winners)
        }

        fun worth(numbers: List<String>, winners: List<String>): List<String> =
            winners.filter {winner->
                numbers.contains(winner)
            }

