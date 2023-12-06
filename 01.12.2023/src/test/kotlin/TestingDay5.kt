import Day5.Companion.findPears
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestingDay5 {
    //seeds: 79 14 55 13
    //
    //seed-to-soil map:
    //50 98 2
    //52 50 48

    //1
    // Destination Range 50
    //source range start at 98
    //Range length 2//Contains 2 values
    //Både destination og source har 2 verdi . AKA
    //Set 1
    //Dest
    //50, 51
    //90, 99
    //Set two
    // Source 52..(52+48))
    //Dest (50 .. (50+48))
    //seed number 98 corresponds to soil number 50
    //seed number 99 corresponds to soil number 51

    //Set 1

    @Test
    fun testTestData() {


        val seed2Soil = findPears("50 98 2\n52 50 48")
        val soil2Fertilizer = findPears("0 15 37\n37 52 2\n39 0 15")
        val fertilizer2Water = findPears(
            """49 53 8
        0 11 42
        42 0 7
        57 7 4"""
        )
        val water2Light = findPears(
            """88 18 7
        18 25 70"""
        )

        val light2Temperature = findPears(
            """45 77 23
        81 45 19
        68 64 13"""
        )


        val temperature2Humidity = findPears(
            """0 69 1
        1 0 69"""
        )


        val humidity2Location = findPears(
            """60 56 37
        56 93 4"""
        )
        val day5 = Day5(
            seed2Soil,
            soil2Fertilizer,
            fertilizer2Water,
            water2Light,
            light2Temperature,
            temperature2Humidity,
            humidity2Location)

        val seeds = "79 14 55 13".split(" ").toList()

        assertEquals(81, day5.lookupLookup(79, day5.seed2Soil))

        assertEquals(82, day5.findLocationForSeed(79))
        assertEquals(43, day5.findLocationForSeed(14))
        assertEquals(86, day5.findLocationForSeed(55))
        assertEquals(35, day5.findLocationForSeed(13))

        assertEquals(35, day5.findLowest(seeds))
    }

    //!!!!!!! Any source numbers that aren't mapped correspond to the same destination!!!!
    //
    //soil-to-fertilizer map:
    //0 15 37
    //37 52 2
    //39 0 15
    //
    //fertilizer-to-water map:
    //49 53 8
    //0 11 42
    //42 0 7
    //57 7 4
    //
    //water-to-light map:
    //88 18 7
    //18 25 70
    //
    //light-to-temperature map:
    //45 77 23
    //81 45 19
    //68 64 13
    //
    //temperature-to-humidity map:
    //0 69 1
    //1 0 69
    //
    //humidity-to-location map:
    //60 56 37
    //56 93 4



}