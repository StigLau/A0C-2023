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
                scanLines(lines, it)
                    .map { it.first.toInt() }
            }
            .flatMap { it }

        fun scanLines(lines: List<String>, i: Int): MutableSet<Pair<String, IntRange>> {
            val foundSymbolsIneMiddle = foCruftyStuff(symbolRegex, lines.get(i))
            val upperRanges = asPairs(foCruftyStuff(digitRegex, lines.get(i - 1)))
            val sameLineRanges = asPairs(foCruftyStuff(digitRegex, lines.get(i)))
            val lowerRanges = asPairs(foCruftyStuff(digitRegex, lines.get(i + 1)))

            val rezzie = mutableSetOf<Pair<String, IntRange>>()
            foundSymbolsIneMiddle.forEach {
                rezzie.addAll(findInRange(it.second, lowerRanges))
                rezzie.addAll(findInRange(it.second, sameLineRanges))
                rezzie.addAll(findInRange(it.second, upperRanges))
            }
            return rezzie
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

