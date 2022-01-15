package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1 {
    @Test
    fun test() {
        val input = readInput("year2015/Day1.txt")
        var floor = 0
        var position: Int? = null
        for ((index, char) in input.withIndex()) {
            if (char == '(') {
                floor++
            } else if (char == ')') {
                floor--
            }

            if (position == null && floor == -1) {
                position = index + 1
            }
        }

        assertEquals(74 to 1795, floor to position)
    }
}
