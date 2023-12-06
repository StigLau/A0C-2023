class Day5(
    val seed2Soil: List<Pair<MyRange, MyRange>>,
    val soil2Fertilizer: List<Pair<MyRange, MyRange>>,
    val fertilizer2Water: List<Pair<MyRange, MyRange>>,
    val water2Light: List<Pair<MyRange, MyRange>>,
    val light2Temperature: List<Pair<MyRange, MyRange>>,
    val temperature2Humidity: List<Pair<MyRange, MyRange>>,
    val humidity2Location: List<Pair<MyRange, MyRange>>,
) {

    fun findLocationForSeed(seedNr: Long): Long {
        val soilId = lookupLookup(seedNr, seed2Soil)
        val fertilizerId = lookupLookup(soilId, soil2Fertilizer)
        val waterId = lookupLookup(fertilizerId, fertilizer2Water)
        val lightId = lookupLookup(waterId, water2Light)
        val temperature = lookupLookup(lightId, light2Temperature)
        val humidity = lookupLookup(temperature, temperature2Humidity)
        val location = lookupLookup(humidity, humidity2Location)

        return location
    }

    //First is destination
    //Second is source
    fun lookupLookup(sourceId: Long, lookupMap: List<Pair<MyRange, MyRange>>): Long {
        lookupMap.filter { it.second.contains(sourceId) }.forEach {
            val index = sourceId-it.second.first
            return it.first.first + index
        }//Alternative
        return sourceId

    }

    //fun findLowest(seeds: List<String>): Long = seeds.map { findLocationForSeed(it.toLong()) }.min()
    fun findLowest(seeds: List<Long>): Long = seeds.map { findLocationForSeed(it) }.min()
    fun findLowest(seeds: LongRange): Long = seeds.map { findLocationForSeed(it) }.min()

    fun findLowest(seeds: MyRange): Long  {
        val start = System.currentTimeMillis()
        var lowest = Long.MAX_VALUE
        var current = seeds.first
        while (current < seeds.to) {
            val rez = findLocationForSeed(current)
            if(rez < lowest) {
                println("${(System.currentTimeMillis() - start)} ms:  $rez")
                lowest = rez
            }
            current++
        }
        return lowest
    }


    companion object {
        fun pearHunt(longerString: String) = longerString.lines().map { findPear(it) }
        private fun findPear(input: String): Pair<MyRange, MyRange> {
            //example Input = 50 98 2
            val numbers = input.trim().split(" ")
            val dest = numbers[0].toLong()
            val src = numbers[1].toLong()
            val range = numbers[2].toLong()
            return Pair(MyRange(dest, dest + range - 1), MyRange(src, src + range - 1))
        }
    }
}


data class MyRange(val first:Long, val to:Long) {

    fun contains(doWeHaveThis:Long):Boolean = (first <= doWeHaveThis && doWeHaveThis <= to)

    companion object {
        fun fromRange(from:Long, range:Long):MyRange =
            MyRange(from, (from + range -1))
    }
}