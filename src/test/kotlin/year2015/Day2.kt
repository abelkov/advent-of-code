package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2 {
    @Test
    fun test() {
        val input = readInput("year2015/Day2.txt")
        var wrappingPaper = 0
        var ribbon = 0
        input.lines().forEach { line ->
            val (x, y, z) = line.split("x").map { it.toInt() }.sorted()
            wrappingPaper += (2 * x * y) + (2 * y * z) + (2 * x * z) + (x * y)
            ribbon += (2 * (x + y)) + (x * y * z)
        }
        assertEquals(1586300 to 3737498, wrappingPaper to ribbon)
    }
}
