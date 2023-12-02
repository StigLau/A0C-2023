package day2

import java.lang.RuntimeException
import java.net.URI
import java.nio.file.Path
import kotlin.io.path.readLines

class Day2 {

}

fun main() {
    val uriz: URI = ClassLoader.getSystemResource("day2.txt").toURI()
    var tally = 0
    var tally2 = 0

    val result = Path.of(uriz).readLines().map {
        try {
            //Game2
            tally2 += evaluateHand2(parseGame(it, false))

            //Game 1
            val gots = parseGame(it, true) //Fetch colour
            val values =  it.split(":")[0].split(" ")[1].toInt()
            tally+=values
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

val lookup = mapOf("red" to 12, "green" to 13, "blue" to 14)

private fun parseGame(it: String, exitOnErrors:Boolean): List<List<Pair<String, Int>>> {
    val hands = it.split(":")[1] //Remove Game ID
        .split(";")

    val gots = hands.map {hand->
        val foot = hand.split(",").map {
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
        println(foot)
        if(foot.sumOf { it.second } > (12+13+14)) {
            throw Exception ("Too many in one hand")
        }
        foot
    }
    return gots
}

private fun evaluateHand2(gots: List<List<Pair<String, Int>>>): Int {
    val green = extract(gots, "green")
    val red = extract(gots, "red")
    val blue = extract(gots, "blue")

    val summary = green * red * blue
    if (summary == 0) {
        throw RuntimeException("Be warned, shouldn't have an empty colour")
    }
    return summary
}

private fun extract(
    gots: List<List<Pair<String, Int>>>,
    colour: String
): Int = gots.flatMap { it }.filter { it.first == colour }.map { it.second }.max()

