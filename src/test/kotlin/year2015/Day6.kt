package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6 {
    @Test
    fun part1() {
        val field = Array(1000) { Array(1000) { false } }
        val regex = """(?>turn )?(\w+) (\d+),(\d+) \w+ (\d+),(\d+)""".toRegex()
        readInput("year2015/Day6.txt").lines().forEach { line: String ->
            val matchResult = regex.matchEntire(line)
            val (command, x1, y1, x2, y2) = matchResult!!.groupValues.drop(1)
            for (x in x1.toInt()..x2.toInt()) {
                for (y in y1.toInt()..y2.toInt()) {
                    when (command) {
                        "on" -> field[x][y] = true
                        "off" -> field[x][y] = false
                        "toggle" -> field[x][y] = !field[x][y]
                        else -> IllegalStateException()
                    }
                }
            }
        }

        val count = field.sumOf { row -> row.sumOf { light -> if (light) 1L else 0 } }
        assertEquals(543903, count)
    }

    @Test
    fun part2() {
        val field = Array(1000) { Array(1000) { 0 } }
        val regex = """(?>turn )?(\w+) (\d+),(\d+) \w+ (\d+),(\d+)""".toRegex()
        readInput("year2015/Day6.txt").lines().forEach { line: String ->
            val matchResult = regex.matchEntire(line)
            val (command, x1, y1, x2, y2) = matchResult!!.groupValues.drop(1)
            for (x in x1.toInt()..x2.toInt()) {
                for (y in y1.toInt()..y2.toInt()) {
                    when (command) {
                        "on" -> field[x][y]++
                        "off" -> {
                            field[x][y]--
                            field[x][y] = field[x][y].coerceAtLeast(0)
                        }
                        "toggle" -> field[x][y] += 2
                        else -> IllegalStateException()
                    }
                }
            }
        }

        val count = field.sumOf { row -> row.sumOf { level -> level } }
        assertEquals(14687245, count)
    }
}
