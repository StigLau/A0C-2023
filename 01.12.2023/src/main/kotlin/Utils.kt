import java.nio.file.Path
import kotlin.io.path.readLines

class Utils {
    companion object {
        fun readFile(fileId:String): List<String> {
            println("Trying to read $fileId")
            val filz = Utils::class.java.getResource(fileId)
            return Path.of(filz!!.toURI()).readLines()
        }
    }
}