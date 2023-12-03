class Day3 {

    companion object {
        fun asPairs(lineFinds: List<Pair<String, IntRange>>): List<Pair<String, IntRange>> =
            lineFinds.map { pair ->
                Pair(pair.first, IntRange(pair.second.first - 1, pair.second.last + 1))
            }

        fun findInRange(symbol: IntRange, lowerRanges: List<Pair<String, IntRange>>): MutableList<Pair<String, IntRange>> {
            val tempList = mutableListOf<Pair<String, IntRange>>()
            lowerRanges.filter { lows ->
                lows.second.contains(symbol.endInclusive)
            }.forEach {
                tempList.add(it)
            }
            return tempList
        }


        fun foCruftyStuff(regex: Regex, input: String): List<Pair<String, IntRange>> {
            val rez = mutableListOf<Pair<String, IntRange>>()
            regex.findAll(input).forEach { matchResult ->
                rez.add(Pair(matchResult.groups[0]!!.value, matchResult.groups[0]!!.range))
            }
            return rez
        }
    }
}

