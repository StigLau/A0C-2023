package day2

import java.net.URI
import java.nio.file.Path
import kotlin.io.path.readLines

class Day2(val games:List<String>) {
    val lookup = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun v1( ):Int {
        var tally = 0
        games.map {
            try {
                //Game2

                //Game 1
                parseGame(it, true, lookup) //Fetch colour
                tally += it.split(":")[0].split(" ")[1].toInt()
                println("Game success: $it")

            } catch (e:Exception) {
                println("Game $it failed: ${e.message}")
            }

        }
        return tally

    }
    fun v2():Int {
        var tally2 = 0
        games.map {
            tally2 += evaluateHand2(parseGame(it, false, lookup))
        }

        return tally2
    }

}

fun main() {
    val uriz: URI = ClassLoader.getSystemResource("day2.txt").toURI()
    val lines = Path.of(uriz).readLines()
    val v1 = Day2(lines).v1()
    val v2 = Day2(lines).v2()
    println("final tally: $v1")
    println("final tally2: $v2")
    assert(2683 == v1)
    assert(49710 == v2)
}

private fun parseGame(game: String, exitOnErrors:Boolean, lookup:Map<String, Int>): List<List<Pair<String, Int>>> {
    return game.split(":")[1] //Remove Game ID
        .split(";").map { hand->
         hand.split(",").map {
            val draw = it.trim().split(" ")
            val value = draw[0].toInt()
            val colour = draw[1]
            //Verify by the rules
            if(exitOnErrors && value > lookup.get(colour)!!) {
                println("Colour $colour was $value")
                throw Exception()
            }
            Pair(colour, value)
        }
    }
}

private fun evaluateHand2(gots: List<List<Pair<String, Int>>>): Int {
    val green = extract(gots, "green")
    val red = extract(gots, "red")
    val blue = extract(gots, "blue")
    return green * red * blue
}

private fun extract(resultSet: List<List<Pair<String, Int>>>, colour: String): Int =
    resultSet.flatMap { it }.filter { it.first == colour }.map { it.second }.max()

