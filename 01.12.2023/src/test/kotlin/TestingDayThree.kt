import Day3.Companion.asPairs
import Day3.Companion.digitRegex
import Day3.Companion.extractNumbers
import Day3.Companion.findInRange
import Day3.Companion.foCruftyStuff
import Day3.Companion.scanLines
import Day3.Companion.symbolRegex
import java.nio.file.Path
import kotlin.io.path.readLines
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

        val foundSymbolsIneMiddle = foCruftyStuff(symbolRegex, line2)

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

    @Test
    fun testEntireFile() {
        //TestingDayThree::class.java.getResource
        val filz = TestingDayThree::class.java.getResource("day3.txt")
        val lines = Path.of(filz!!.toURI()).readLines()

        //Start from second line and avoid the last one
        println(extractNumbers(lines).sum())
        assertEquals(557705, extractNumbers(lines).sum())

    }

    @Test
    fun testThisLine() {
        val lines = listOf(
            ".444.....560.297...149..............*....*......*...................173...................365............986.$............271...182...26....",
            "....*84....*......................#..471.696.736.107....974...-614............................../...%930..........................+......364",
            "............800...-155..235......446...................*....................286.......822../...199............670-....................%....."
        )
        assertEquals(9, extractNumbers(lines).size)
    }


}// 537846 is too low