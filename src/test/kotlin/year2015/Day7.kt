package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

class Day7 {
    @Test
    fun part1() {
        val wires = mutableMapOf<String, Int>()
        fun getValue(source: String): Int? = source.toIntOrNull() ?: wires[source]
        val instructions = readInput("year2015/Day7.txt").lines().toMutableSet()
        val regex = """([a-z]+|\d+)?\s?([A-Z]+)?\s?([a-z]+|\d+)?\s?->\s([a-z]+)""".toRegex()
        while (true) {
            val iterator = instructions.iterator()
            while (iterator.hasNext()) {
                val matchResult = regex.matchEntire(iterator.next())
                val (source1, op, source2, destination) = matchResult!!.groupValues.drop(1)
                val value1 = getValue(source1)
                val value2 = getValue(source2)
                when {
                    op.isBlank() && source2.isBlank() && value1 != null -> {
                        wires[destination] = value1
                        iterator.remove()
                    }
                    source1.isBlank() && value2 != null -> {
                        wires[destination] = value2.inv() // NOT
                        iterator.remove()
                    }
                    value1 != null && value2 != null -> {
                        wires[destination] = when (op) {
                            "AND" -> value1 and value2
                            "OR" -> value1 or value2
                            "LSHIFT" -> value1 shl value2
                            "RSHIFT" -> value1 shr value2
                            else -> throw IllegalStateException()
                        }
                        iterator.remove()
                    }
                }
            }

            if (wires.contains("a")) break
        }

        assertEquals(16076, wires["a"])
    }
}
