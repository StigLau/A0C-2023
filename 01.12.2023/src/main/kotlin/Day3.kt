class Day3(val symbolRegex:Regex) {



        val digitRegex = """\d+""".toRegex()

    companion object {
        fun asPairs(lineFinds: List<Pair<String, IntRange>>): List<Pair<String, IntRange>> =
            lineFinds.map { pair ->
                Pair(pair.first, IntRange(pair.second.first - 1, pair.second.last + 1))
            }

    }

        fun extractNumbers(lines: List<String>) = IntRange(1, lines.size - 2)
            .map {
                scanLines(
                    foCruftyStuff(symbolRegex, lines.get(it)),
                    parseLines(lines, it)
                )
                    .map { it.first.toInt() }
            }
            .flatMap { it }

    fun parseLines(lines: List<String>, i: Int): List<List<Pair<String, IntRange>>> =
        listOf(
            asPairs(foCruftyStuff(digitRegex, lines.get(i - 1))),
            asPairs(foCruftyStuff(digitRegex, lines.get(i))),
            asPairs(foCruftyStuff(digitRegex, lines.get(i + 1)))
        )


        fun scanLines(symbol: List<Pair<String, IntRange>>, numbers: List<List<Pair<String, IntRange>>>): List<Pair<String, IntRange>> =
            symbol.map { loff ->
                numbers.map { line -> findInRange(loff.second, line) }
            }.flatMap { it }.flatMap { it }


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

