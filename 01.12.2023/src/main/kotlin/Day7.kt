import java.lang.Exception

class Day7

data class Hand(val originalInput:String, val cards: List<Int>, val bid: Int) {

    //val gotStrength:Long = this.strength()

    companion object {
        fun parseInput(lines:List<String>): List<Hand> = lines.map {
            val line = it.split(" ")
            Hand(line[0], convertToHand(line[0]), line[1].toInt())
        }
        fun convertToHand(input: String): List<Int> =
            input.map { labels.getValue(it) }.sortedDescending()

        val labels: Map<Char, Int> = mapOf(
            'A' to 14,
            'K' to 13,
            'Q' to 12,
            'J' to 11,
            'T' to 10,
            '9' to 9,
            '8' to 8,
            '7' to 7,
            '6' to 6,
            '5' to 5,
            '4' to 4,
            '3' to 3,
            '2' to 2
        )

        private fun howManyInAGroup(groupCunts: List<Pair<Int, Int>>, howMany: Int): List<Int> {
            return groupCunts.filter { it.second == howMany }.map { it.first }
        }

        fun strength(cards: List<Int>): Int {
            val grouped = cards.groupBy { it }
            val groupCunts = grouped.map { it.key to it.value.size }

            return if (howManyInAGroup(groupCunts, 5).isNotEmpty()) { //Five of a kind
                7
            } else if (howManyInAGroup(groupCunts, 4).isNotEmpty()) {//Four of a kind
                6
            } else if (howManyInAGroup(groupCunts, 3).isNotEmpty() && howManyInAGroup(groupCunts, 2).isNotEmpty()) { //Full house
                5
            } else if (howManyInAGroup(groupCunts, 3).isNotEmpty()) { //Three of a kind
                4
            } else if (howManyInAGroup(groupCunts, 2).size == 2) { //Two pair
                3
            } else if (howManyInAGroup(groupCunts, 2).size == 1) { //One pair
                2
            } else 1
        }

        val handComparator = object : Comparator<Hand> {
            override fun compare(hmd1: Hand, hmd2: Hand): Int {
                val diff = strength(hmd1.cards) - strength(hmd2.cards)
                return if (diff != 0) {
                    diff
                } else {
                    compareTwoBiatches(hmd1.originalInput, hmd2.originalInput)
                }
            }
        }

        private fun compareTwoBiatches(hmd1: String, hmd2: String): Int {
            hmd1.forEachIndexed { index, i ->
                val comparison = labels.get(hmd1[index])!! - labels.get(hmd2[index])!!
                if (comparison != 0) {
                    return comparison
                }
            }
            throw Exception("Data says no")
        }    }
}