import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import org.junit.jupiter.api.Test
import java.lang.foreign.AddressLayout
import kotlin.test.assertEquals

class TestingDay10 {
    val dungeon1 = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
        """.trimIndent()

    @Test
    fun travelDungeon() {
        val dun1 = MyDungeon(dungeon1.lines())
        val startPos = dun1.startingPosition().first()
        val walkTheWalk = mutableListOf(startPos)
        assertEquals(1, startPos.x)
        assertEquals(1, startPos.y)

        val nextStep = startPos.directionSurroundingCandidates(dun1)
        assertEquals(2, nextStep.size)
        assertEquals(Pos(2, 1, '-'), nextStep.first())
        assertEquals(Pos(1, 2, '|'), nextStep.last())

        walkTheWalk.add(nextStep.first())
        val secondStep = nextStep.first().directionSurroundingCandidates(dun1)
        assertEquals(1, secondStep.size)
        assertEquals(Pos(3, 1, '7'), secondStep.first())
    }
    @Test
    fun traveltestDungeonToTheEnd() {
        val dun1 = MyDungeon(dungeon1.lines())
        val rez = dun1.walkToTheBitterEnd()
        assertEquals(8, rez.size)

    }

    val naughtyDungeon = """
            .-...
            .S-77
            .|.||
            .L-JJ
            ...JJ
        """.trimIndent()
    @Test
    fun handleNaughtyDungeon() {
        val dun1 = MyDungeon(dungeon1.lines())
        val rez = dun1.walkToTheBitterEnd()
        assertEquals(8, rez.size)

    }
}


