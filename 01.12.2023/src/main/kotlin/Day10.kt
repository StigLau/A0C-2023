class Day10

class MyDungeon(val layout: List<String>) {

    val dungeonMap: List<List<Pos>> = layout.mapIndexed { lineNr, line ->
        line.mapIndexed { indent, c ->
            Pos(indent, lineNr, c)
        }
    }

    fun startingPosition() : List<Pos> = dungeonMap.flatMap { it }.filter { it.symbol == 'S'  }

    fun getTile(x:Int, y:Int):Pos {
        val gots = dungeonMap.flatMap { it }.filter { it.x == x && it.y == y }
        if(gots.size > 1) {
            println("OMFG, We shpuld onlyu getz 1")
        }
        return gots.first()
    }

    fun walkToTheBitterEnd(): MutableList<Pos> {
        val startPos = startingPosition().first()
        var nextStep = startPos.directionSurroundingCandidates(this).first()
        val walkTheWalk = mutableListOf(startPos)

        while(nextStep != startPos) {
            val found  = nextStep.directionSurroundingCandidates(this)
                .filter { it != walkTheWalk.last() } //Avoid tracing backwards
            walkTheWalk.add(nextStep)
            if(found.size == 0) {
                nextStep = startPos
            } else {
                nextStep = found.first()
            }

        }
        return walkTheWalk
    }

}


data class Pos(val x:Int, val y:Int, val symbol:Char) {
    fun directionSurroundingCandidates(dung:MyDungeon):List<Pos> {
        val lizzie:MutableList<Pos> = mutableListOf()
        val right = dung.getTile(this.x+1, this.y)
        if(this.isToTheRight(right)) {
            lizzie.add(right)
        }
        val below = dung.getTile(this.x, this.y+1)
        if(this.below(below)) {
            lizzie.add(below)
        }
        val left = dung.getTile(this.x-1, this.y)
        if(this.isToTheLeft(left)) {
            lizzie.add(left)
        }
        val above = dung.getTile(this.x, this.y-1)
        if (this.above(above)) {
            lizzie.add(above)
        }
        return lizzie
    }


    fun below(other: Pos): Boolean =
        this.x == other.x && other.y - 1 == this.y && //isAbove
                (other.symbol == 'L' || other.symbol == '|' || other.symbol == 'J')

    fun above(other:Pos) =
        this.x == other.x && other.y +1 == this.y &&
                (other.symbol == 'F' || other.symbol == '|' || other.symbol == '7')

    fun isToTheLeft(other:Pos) =
        this.x == other.x+1 && other.y == this.y &&
            (other.symbol == 'L' || other.symbol == '-' || other.symbol == 'F')

    fun isToTheRight(other:Pos) =
        this.x == other.x-1 && other.y == this.y &&
            (other.symbol == '7' || other.symbol == '-' || other.symbol == 'J')

}
