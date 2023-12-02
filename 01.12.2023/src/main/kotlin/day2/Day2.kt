package day2

import java.net.URI
import java.nio.file.Path
import kotlin.io.path.readLines

class Day2 {

}

fun main() {
    val uriz: URI = ClassLoader.getSystemResource("day2.txt").toURI()
    var tally = 0

    val result = Path.of(uriz).readLines().map {
        try {
            val values = parseGame(it.trim()) //Fetch colour
            tally+=values
            println("Game success: $it")

        } catch (e:Exception) {
            println("Game $it failed: ${e.message}")
        }

    }
    println("final tally: $tally")

}

val lookup = mapOf("red" to 12, "green" to 13, "blue" to 14)

private fun parseGame(it: String): Int {
    val hands = it.split(":")[1] //Remove Game ID
        .split(";")

    val gots = hands.map {hand->
        val foot = hand.split(",").map {
            val draw = it.trim().split(" ")
            val value = draw[0].toInt()
            val colour = draw[1]

            //Verify by the rules
            if(value > lookup.get(colour)!!) {
                println("Colour $colour was $value")
                throw Exception()
            }

            else
                Pair(colour, value)
        }
        println(foot)
        if(foot.sumOf { it.second } > (12+13+14)) {
            throw Exception ("Too many in one hand")
        }
        foot
    }
    println("gots $gots")
    val gameId = it.split(":")[0].split(" ")[1]
    return gameId.toInt()
}