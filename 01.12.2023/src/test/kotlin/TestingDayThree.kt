import kotlin.test.Test
import kotlin.test.assertEquals

class TestingDayThree {
    @Test
    fun testParsingLine() {
        val line1 = "..172..............................454..46.......507..........809......923.778..................793..............137.............238........"
        val line2 = "............*.........712........=.......*................515.*...........*.......690.........../..........658.........=.........*.........."
        val line3 = ".........823.835........%.........710.....749........134..%............................#812...&.....925.../..........276.......386.........."

        assertEquals(10, foCruftyStuff("""\d+""".toRegex(), line1).size)
        assertEquals(8, foCruftyStuff("""[*=/%@+&\-$]""".toRegex(), line2).size)

        assertEquals(9, foCruftyStuff("""\d+""".toRegex(), line3).size)
        assertEquals(4, foCruftyStuff("""[*=/%@+&\-$]""".toRegex(), line3).size)
    }

    private fun foCruftyStuff(regex: Regex, input: String): MutableList<Pair<String, IntRange>> {
        val rez = mutableListOf<Pair<String, IntRange>>()
        regex.findAll(input).forEach { matchResult ->
            rez.add(Pair(matchResult.groups[0]!!.value, matchResult.groups[0]!!.range))
        }
        return rez
    }

    fun testThreeLines() {

    }
}