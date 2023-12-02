package day2

import java.nio.file.Path
import kotlin.io.path.readLines

class Day2(val games: List<String>, val lookup: Map<String, Int>) {

    fun puzzle1(): Int = games.sumOf {
        try {
            parseGame(it, true, lookup) //Fetch colour
            it.split(":")[0].split(" ")[1].toInt()
        } catch (e: Exception) {
            0
        }
    }

    fun puzzle2(): Int = games.sumOf { evaluateHand2(parseGame(it, false, lookup)) }

    private fun parseGame(game: String, exitOnErrors: Boolean, lookup: Map<String, Int>): List<List<Pair<String, Int>>> =
        game.split(":")[1] //Remove Game ID
            .split(";").map { hand ->
                hand.split(",").map {
                    val draw = it.trim().split(" ")
                    val value = draw[0].toInt()
                    val colour = draw[1]
                    //Verify by the rules
                    if (exitOnErrors && value > lookup.get(colour)!!) {
                        //println("Colour $colour was $value")
                        throw Exception()
                    }
                    Pair(colour, value)
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
}

fun main() {
    val lines = Path.of(ClassLoader.getSystemResource("day2.txt").toURI()).readLines()
    val maxNrOfCubes = mapOf("red" to 12, "green" to 13, "blue" to 14)
    val v1 = Day2(lines, maxNrOfCubes).puzzle1()
    val v2 = Day2(lines, maxNrOfCubes).puzzle2()
    println("Puzzle 1: $v1")
    println("Puzzle 2: $v2")
    assert(2683 == v1)
    assert(49710 == v2)
}
