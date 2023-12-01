import java.io.File
import java.lang.RuntimeException
class RoundOne {
    fun main(args: Array<String>) {
        val result = File("input1.txt").readLines().map {
            val firstDigit = findFirstDigit(it)
            println("RESULT 1 was $firstDigit")
            val secondDigit = findFirstDigit(it.reversed())
            println("RESULT REVERSED WAS $secondDigit")

            println("Combined: $firstDigit$secondDigit")
            "$firstDigit$secondDigit".toInt()
        }.sum()

        println("Let there be snow: ${result}")

    }

    fun findFirstDigit(input: String): Char {
        println("Looking at $input")
        if (input.isEmpty()) {
            throw RuntimeException("Came to the end without finding anything relevant")
        } else if (input.first().isDigit()) {
            return input.first()
        }
        return findFirstDigit(input.substring(1))
    }
}

/* Oh, we don't need to look for more than a single digi! :)
fun findFirstNum(input:String, foundNumber:Boolean):String {
    println("Looking at $input")
    if(input.isEmpty()) {
        throw RuntimeException("Came to the end without finding anything relevant")
    }
    val first = input.first()
    if(foundNumber && !first.isDigit()) {
        println("Found the end of first number and returning. rest is $input")
        return first.toString()
    }

    var didIFindNumberNow = false
    if(input.first().isDigit()) {
        didIFindNumberNow = true
    }

    println("Seeking further with ${input.substring(1)}")
    val returnStuff = findFirstNum(input.substring(1), didIFindNumberNow)
    if(didIFindNumberNow) {
        println("Adding the number I found here. Resulting in: $first$returnStuff")
        return "$first$returnStuff"
    } else {
        println("Just returning $returnStuff")
        return returnStuff
    }
}
 */