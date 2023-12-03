import kotlin.test.Test
import kotlin.test.assertEquals

class TestingDayThree {
    val line1 =
        "..172..............................454..46.......507..........809......923.778..................793..............137.............238........"
    val line2 =
        "............*.........712........=.......*................515.*...........*.......690.........../..........658.........=.........*.........."
    val line3 =
        ".........823.835........%.........710.....749........134..%............................#812...&.....925.../..........276.......386.........."

    @Test
    fun testParsingLine() {
        assertEquals(10, foCruftyStuff("""\d+""".toRegex(), line1).size)
        assertEquals(8, foCruftyStuff("""[*=/%@+&\-$]""".toRegex(), line2).size)

        assertEquals(9, foCruftyStuff("""\d+""".toRegex(), line3).size)
        assertEquals(4, foCruftyStuff("""[*=/%@+&\-$]""".toRegex(), line3).size)
    }

    @Test
    fun testThreeLines() {
        val digitRegex = """\d+""".toRegex()
        val foundSymbolsIneMiddle = foCruftyStuff("""[*=/%@+&\-$]""".toRegex(), line2)

        val upperRanges = asPairs(foCruftyStuff(digitRegex, line1))
        val sameLineRanges = asPairs(foCruftyStuff(digitRegex, line2))
        val lowerRanges = asPairs(foCruftyStuff(digitRegex, line3))
        //val ranges = listOf(lowerRanges, sameLineRanges, upperRanges)
        val rezzie = mutableSetOf<Pair<String, IntRange>>()
        foundSymbolsIneMiddle.forEach {
            //rezzie.addAll(ranges.map {ut -> extractOst(it.second, ut) })
            rezzie.addAll(findInRange(it.second, lowerRanges))
            rezzie.addAll(findInRange(it.second, upperRanges))
            rezzie.addAll(findInRange(it.second, sameLineRanges))
        }
        println(rezzie)
        assertEquals(12, rezzie.size)
        assertEquals(7366, rezzie.map { it.first.toInt() }.sum())
    }

    private fun asPairs(lineFinds: List<Pair<String, IntRange>>): List<Pair<String, IntRange>> =
        lineFinds.map { pair ->
            Pair(pair.first, IntRange(pair.second.first - 1, pair.second.last + 1))
    }

    private fun findInRange(symbol: IntRange, lowerRanges: List<Pair<String, IntRange>>): MutableList<Pair<String, IntRange>> {
        val tempList = mutableListOf<Pair<String, IntRange>>()
        lowerRanges.filter { lows ->
            lows.second.contains(symbol.endInclusive)
        }.forEach {
            tempList.add(it)
        }
        return tempList
    }


    private fun foCruftyStuff(regex: Regex, input: String): List<Pair<String, IntRange>> {
        val rez = mutableListOf<Pair<String, IntRange>>()
        regex.findAll(input).forEach { matchResult ->
            rez.add(Pair(matchResult.groups[0]!!.value, matchResult.groups[0]!!.range))
        }
        return rez
    }

}