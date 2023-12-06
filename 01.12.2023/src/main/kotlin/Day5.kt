class Day5(
    val seed2Soil: List<Pair<IntRange, IntRange>>,
    val soil2Fertilizer: List<Pair<IntRange, IntRange>>,
    val fertilizer2Water: List<Pair<IntRange, IntRange>>,
    val water2Light: List<Pair<IntRange, IntRange>>,
    val light2Temperature: List<Pair<IntRange, IntRange>>,
    val temperature2Humidity: List<Pair<IntRange, IntRange>>,
    val humidity2Location: List<Pair<IntRange, IntRange>>,
) {

    //seed-to-soil map:
    //50 98 2
    //52 50 48


    fun findLocationForSeed(seedNr: Int): Int {
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
    fun lookupLookup(sourceId: Int, lookupMap: List<Pair<IntRange, IntRange>>): Int {
        lookupMap.filter { it.second.contains(sourceId) }.forEach {
            //println(it)
            val index:Int = it.second.indexOf(sourceId)
            return it.first.first + index
        }//Alternative
        println("Didn't find $sourceId")
        return sourceId

    }

    fun findLowest(seeds: List<String>): Int = seeds.map { findLocationForSeed(it.toInt()) }.min()


    companion object {
        fun findPears(longerString: String) = longerString.lines().map { findPear(it) }
        private fun findPear(input: String): Pair<IntRange, IntRange> {
            //example Input = 50 98 2
            val numbers = input.trim().split(" ")
            val dest = numbers[0].toInt()
            val src = numbers[1].toInt()
            val range = numbers[2].toInt()
            return Pair(IntRange(dest, dest + range - 1), IntRange(src, src + range - 1))
        }
    }
}