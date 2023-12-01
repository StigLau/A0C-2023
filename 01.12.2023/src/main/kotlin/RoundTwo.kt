import java.io.File
import java.lang.RuntimeException

class RoundTwo {
    val eligble = listOf("one", "two", "three", "four", "five", "six", "seven", "eight" )



    fun findFirstDigit(input:String):Char {
        println("Looking at $input")
        if(input.isEmpty()) {
            throw RuntimeException("Came to the end without finding anything relevant")
        } else if(input.first().isDigit()) {
            return input.first()
        }
        try{
            println("SingleDigit not found. Evaluating against StringDigits")
            return evaluateStringDigits(input, eligble).digitToChar()
        } catch (notFound:NoSuchElementException) {
            return findFirstDigit(input.substring(1))
        }
    }

    fun evaluateStringDigits(input:String, compare:List<String>):Int {
        if(compare.isEmpty()) {
            throw NoSuchElementException()
        }

        return if(findAlternative(input, compare.last())) {
            val foundAt = compare.size
            println("Hooray, found as String: $foundAt")
            foundAt
        } else {
            evaluateStringDigits(input, compare.dropLast(1))
        }
    }

    fun findAlternative(input:String, verifyAgainst:String):Boolean {
        println("Testing $input against $verifyAgainst")
        if(input.length < verifyAgainst.length)
            return false
        else
            return verifyAgainst == input.subSequence(0, verifyAgainst.length)
    }


}

fun main(args: Array<String>) {
    val result = File("input2.txt").readLines().map {
    //val result = File("input1.txt").readLines().map {
        val firstDigit = RoundTwo().findFirstDigit(it)
        println("RESULT 1 was $firstDigit")
        val secondDigit = RoundTwo().findFirstDigit(it.reversed())
        println("RESULT REVERSED WAS $secondDigit")

        println("Combined: $firstDigit$secondDigit")
        "$firstDigit$secondDigit".toInt()
    }.sum()

    println("Let there be snow: ${result}")

}
