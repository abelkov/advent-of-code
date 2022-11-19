package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10 {
    @Test
    fun part1() {
        val sequence = apply(40)
        assertEquals(360154, sequence.size)
    }

    @Test
    fun part2() {
        val sequence = apply(50)
        assertEquals(5103798, sequence.size)
    }

    private fun apply(times: Int): List<Int> {
        var sequence = "1113122113".map { it.digitToInt() }
        repeat(times) {
            val newSequence = mutableListOf<Int>()
            var index = 0
            while (index < sequence.size) {
                val digit = sequence[index]
                var number = 0
                while (sequence[index] == digit) {
                    index++
                    number++
                    if (index == sequence.size) {
                        break
                    }
                }
                newSequence.add(number)
                newSequence.add(digit)
            }
            sequence = newSequence
        }
        return sequence
    }
}
