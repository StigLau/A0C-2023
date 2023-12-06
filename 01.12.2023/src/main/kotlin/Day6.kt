class Day6 {
    //tine allowed for travel
    //make sure you go farther in each race than the current record holder

    //olding down the button charges the boat
    //releasing the button allows the boat to move

    //holding down the button, the boat's speed increases by one millimeter per millisecond
//Which alternatives (times each other) wins?
    companion object {
        fun calculateBoatDistance(time: Long, timeToBeat: Long): List<Pair<Long, Long>> =
            LongRange(1, time).map { a ->
                Pair(a, a * (time - a))
            }.filter { pear ->
                pear.second > timeToBeat
            }
    }

}