class Day7 {
    fun parseInput(testdata: String): List<Hand> = testdata.lines().map {
        val line = it.split(" ")
        Hand(convertToHand(line[0]), line[1].toInt())
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
}

data class Hand(val cards: List<Int>, val bid: Int) {

    val gotStrength = this.strength()

    fun strength(): Int {
        val grouped = cards.groupBy { it }
        val groupCunts = grouped.map { it.key to  it.value.size }

        val fiveOfAKind = howManyInAGroup(groupCunts, 5)
        val four = howManyInAGroup(groupCunts, 4)
        val three = howManyInAGroup(groupCunts, 3)
        val pairs = howManyInAGroup(groupCunts, 2).sortedDescending()

        return if(fiveOfAKind.isNotEmpty()) { //Five of a kind
            60000 + fiveOfAKind.first()
        } else if(four.isNotEmpty()) {//Four of a kind
            50000 + four.first()
        } else if(three.isNotEmpty() && pairs.isNotEmpty()) { //Full house
            40000 + (three.first() * 100) + pairs.first()
        } else if(three.isNotEmpty()) { //Three of a kind
            30000 + three.first()
        } else if(pairs.size == 2) { //Two pair
            20000+ (pairs.first() * 100) + pairs.last()
        } else if(pairs.size == 1) { //One pair
            10000+pairs.first()
        } else //Highest
            cards.first()

    }

    private fun howManyInAGroup(groupCunts: List<Pair<Int, Int>>, howMany: Int): List<Int> {
        val fiveOfAKind = groupCunts.filter { it.second == howMany }.map { it.first }
        return fiveOfAKind
    }

    fun haveYouSeenThisCard(thisCard:Int, rest:List<Int>):MutableList<Int> {
        if(rest.isEmpty())
            return mutableListOf()
        else {
            val filteredRest: MutableList<Int> = haveYouSeenThisCard(thisCard, rest.subList(1, rest.size))
            if (rest.first() == thisCard) {
                filteredRest.add(rest.first())
            }
            return filteredRest
        }
    }


}