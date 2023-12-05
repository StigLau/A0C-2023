fun evaluate(winnings: Int): Int =
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
    winners.filter { winner ->
        numbers.contains(winner)
    }


class Moro(val lookup: Map<Int, List<String>>) {

    companion object {
        fun createLookup(lines: List<String>): Map<Int, List<String>> {
            val lookup = mutableMapOf<Int, List<String>>()
            lines.mapIndexed { i, line ->
                lookup.put(i, parse(line).map { it })
            }.toList()
            return lookup
        }
    }

    fun searchInLines(lineNr:Int, lines: List<String>): List<String> {
        val whatWeGot = lines.mapIndexed { outerIndex, line ->
            //println("Child $lineNr $outerIndex")
            //parse(line).mapIndexed { index, number ->
            val rangez = IntRange(lineNr + 1, lineNr + (lines.size+1))
            rangez.filter { it < 198 } //TODO Avoid going past 198!
                .map { tryThisOne ->
                    findNext(tryThisOne ) }
        }
        val withCurrent = mutableListOf("$lineNr")
        withCurrent.addAll(whatWeGot.flatMap { it }.flatMap { it })
        return withCurrent
    }

    fun findNext(nextLineToTry: Int): List<String> {
        val found = lookup.get(nextLineToTry)!!
            .filter { it.toInt() > nextLineToTry }
            .filter { it.toInt() < 70 } //TODO This is where we control size of stuff
        //50 762
        //60 843

        if(found.size == 0) {
            //println("Found $nextLineToTry")
            return listOf(nextLineToTry.toString())
        }else { //Todo To be removed
            //println("Founds $found")
            return found.map { current ->
                searchInLines(current.toInt(), lookup.get(current.toInt())!!
                )//.filter { it > current })
            }.flatMap { it }
        }
    }
}
