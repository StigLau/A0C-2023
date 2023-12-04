class Day3(val symbolRegex:Regex, val secondAssignment:Boolean) {



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
                    extractContent(symbolRegex, lines.get(it)),
                    parseLines(lines, it)
                )
                    .map { it.first.toInt() }
            }
            .flatMap { it }

    fun parseLines(lines: List<String>, i: Int): List<List<Pair<String, IntRange>>> =
        listOf(
            asPairs(extractContent(digitRegex, lines.get(i - 1))),
            asPairs(extractContent(digitRegex, lines.get(i))),
            asPairs(extractContent(digitRegex, lines.get(i + 1)))
        )


    fun scanLines(symbol: List<Pair<String, IntRange>>, numbers: List<List<Pair<String, IntRange>>>): List<Pair<String, IntRange>> =
        symbol.map { symbol ->
            val found = numbers.flatMap { line -> findInRange(symbol.second, line) }
            if (!secondAssignment)
                found
            else {
                if (found.size == 2) {
                    val newNumber = (found.get(0).first.toInt() * found.get(1).first.toInt()).toString()
                    listOf(Pair(newNumber, symbol.second))
                } else {
                    emptyList()
                }
            }
        }.flatMap { it }


        fun findInRange(symbol: IntRange, lowerRanges: List<Pair<String, IntRange>>): List<Pair<String, IntRange>> =
            lowerRanges.filter { lows ->
                lows.second.contains(symbol.endInclusive)
            }.toList()



    fun extractContent(regex: Regex, input: String): List<Pair<String, IntRange>> =
        regex.findAll(input).map { matchResult ->
            Pair(matchResult.groups[0]!!.value, matchResult.groups[0]!!.range)
        }.toList()
    }

