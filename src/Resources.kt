import java.io.File
import java.lang.Exception
import java.net.URI

internal object Resources {
    fun resourceAsString(fileName: String, delimiter: String = ""): String =
        resourceAsStringList(fileName).reduce { a, b -> "$a$delimiter$b" }

    fun resourceAsStringList(fileName: String): List<String> =
        File(fileName.toURI()).readLines()

    private fun String.toURI(): URI =
        Resources.javaClass.classLoader.getResource(this)?.toURI() ?: throw Exception("Cannot find Resource: $this")
}

