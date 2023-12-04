import org.junit.jupiter.api.Test
import java.nio.file.Path
import kotlin.io.path.readLines

class TestingDayFour {

    @Test
    fun parsing() {
        val filz = TestingDayThree::class.java.getResource("day4.txt")
        val lines = Path.of(filz!!.toURI()).readLines()
        val games = lines.map { line ->
            parse(line).map { evaluate(it.toInt()) }
        }.toList()
        println("Result set: $games")
        println("Winning lottery number: " + games.flatMap { it }.sum())

    }

    @Test
    fun mapWinners() {
        val filz = TestingDayThree::class.java.getResource("day4.txt")
        val lines = Path.of(filz!!.toURI()).readLines()

        val lookup = mutableMapOf<Int, List<Int>>()
        lines.mapIndexed { i, line ->
            lookup.put(i, parse(line).map { evaluate(it.toInt()) })
        }.toList()
        println("Gots")

    }
}



