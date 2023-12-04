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
        val day3 = Day3("""[*=/%@+&\-$#]""".toRegex())
        assertEquals(10, day3.foCruftyStuff("""\d+""".toRegex(), line1).size)
        assertEquals(8, day3.foCruftyStuff("""[*=/%@+&\-$]""".toRegex(), line2).size)

        assertEquals(9, day3.foCruftyStuff("""\d+""".toRegex(), line3).size)
        assertEquals(4, day3.foCruftyStuff("""[*=/%@+&\-$]""".toRegex(), line3).size)
    }

    @Test
    fun testThreeLines() {
    val day3 = Day3("""[*=/%@+&\-$#]""".toRegex())
        val lines = listOf(line1, line2, line3)
        val rezzie = day3.extractNumbers(lines)
        println(rezzie)
        assertEquals(12, rezzie.size)
        assertEquals(7366, rezzie.sum())
    }

    @Test
    fun testEntireFile() {

        //TestingDayThree::class.java.getResource
        val filz = TestingDayThree::class.java.getResource("day3.txt")
        val lines = Path.of(filz!!.toURI()).readLines()

        //Start from second line and avoid the last one
        val day3 = Day3("""[*=/%@+&\-$#]""".toRegex())
        println(day3.extractNumbers(lines).sum())
        assertEquals(557705, day3.extractNumbers(lines).sum())

    }

    @Test
    fun testThisLine() {
        val lines = listOf(
            ".444.....560.297...149..............*....*......*...................173...................365............986.$............271...182...26....",
            "....*84....*......................#..471.696.736.107....974...-614............................../...%930..........................+......364",
            "............800...-155..235......446...................*....................286.......822../...199............670-....................%....."
        )
        val day3 = Day3("""[*=/%@+&\-$#]""".toRegex())
        assertEquals(9, day3.extractNumbers(lines).size)
    }
    
    @Test
    fun findGears() {
        val lines = listOf(
            ".444.....560.297...149..............*....*......*...................173...................365............986.$............271...182...26....",
            "....*84....*......................#..471.696.736.107....974...-614............................../...%930..........................+......364",
            "............800...-155..235......446...................*....................286.......822../...199............670-....................%....."
        )
        val day3 = Day3("""[*=/%@+&\-$#]""".toRegex())
        assertEquals(9, day3.extractNumbers(lines).size)
    }


}// 537846 is too low