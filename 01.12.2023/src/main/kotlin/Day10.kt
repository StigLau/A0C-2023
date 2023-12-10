import com.sun.org.apache.xpath.internal.operations.Bool

class Day10

class MyDungeon(val layout: List<String>) {

    val dungeonMap: List<List<Pos>> = layout.mapIndexed { lineNr, line ->
        line.mapIndexed { indent, c ->
            Pos(indent, lineNr, c)
        }
    }

    fun startingPosition() : List<Pos> = dungeonMap.flatMap { it }.filter { it.symbol == 'S'  }



    fun surroundings(here:Pos):List<Pos> =
        dungeonMap
            .flatMap { it }
            .filter {
                here.candidate(it)
            }

    fun walkToTheBitterEnd(): MutableList<Pos> {
        val startPos = startingPosition().first()
        var nextStep = surroundings(startPos).first()
        val walkTheWalk = mutableListOf(startPos)

        while(nextStep != startPos) {
            walkTheWalk.add(nextStep)
            val found  = surroundings(nextStep)
            nextStep = found.first()
            if(found.size != 1)
                println("OMFG")

        }
        return walkTheWalk
    }

}


data class Pos(val x:Int, val y:Int, val symbol:Char) {
    fun candidate(other:Pos):Boolean {
        //TODO Remember to take into account who YOU are!
        //isAbove
        return if(this == other)
            false
        else if(this.x == other.x && other.y -1 == this.y && //isAbove
            (other.symbol == 'F' || other.symbol == '|' || other.symbol == '7'))
            true
        //Or Below
        else if(this.x == other.x && other.y +1 == this.y &&
            (other.symbol == 'L' || other.symbol == '|' || other.symbol == 'J'))
            true
        //Or to the left
        else if(this.x == other.x+1 && other.y == this.y &&
            (other.symbol == 'L' || other.symbol == '-' || other.symbol == 'F'))
            true
        else if(this.x == other.x-1 && other.y == this.y &&
            (other.symbol == '7' || other.symbol == '-' || other.symbol == 'J'))
            true
        else
            false

    }
}
