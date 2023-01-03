package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

class Day17 {
    @Test
    fun part1() {
        val containers = readInput("year2015/Day17.txt").lines().map { it.toInt() }
        val combinations = containers.combinations()
        val answer = combinations.filter { it.sum() == 150 }.size
        assertEquals(4372, answer)
    }

    @Test
    fun part2() {
        val containers = readInput("year2015/Day17.txt").lines().map { it.toInt() }
        val combinations = containers.combinations().filter { it.sum() == 150 }
        val minSize = combinations.minBy { it.size }.size
        val answer = combinations.filter { it.size == minSize }.size
        assertEquals(4, answer)
    }
}