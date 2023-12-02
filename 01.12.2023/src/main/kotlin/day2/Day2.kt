package day2

import java.net.URI
import java.nio.file.Path
import kotlin.io.path.readLines

class Day2 {

    fun v1() {

    }

}

fun main() {
    val uriz: URI = ClassLoader.getSystemResource("day2.txt").toURI()
    var tally = 0
    var tally2 = 0
    val lookup = mapOf("red" to 12, "green" to 13, "blue" to 14)
    val result = Path.of(uriz).readLines().map {
        try {
            //Game2
            tally2 += evaluateHand2(parseGame(it, false, lookup))

            //Game 1
            parseGame(it, true, lookup) //Fetch colour
            tally += it.split(":")[0].split(" ")[1].toInt()
            println("Game success: $it")

        } catch (e:Exception) {
            println("Game $it failed: ${e.message}")
        }

    }
    println("final tally: $tally")
    println("final tally2: $tally2")
    assert(2683 == tally)
    assert(49710 == tally2)
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

