package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3 {
    @Test
    fun part1() {
        data class Point(val x: Int, val y: Int)

        var x = 0
        var y = 0
        val houses = mutableSetOf(Point(x, y))
        for (c in readInput("year2015/Day3.txt")) {
            when (c) {
                '>' -> x++
                '<' -> x--
                '^' -> y++
                'v' -> y--
                else -> throw IllegalStateException()
            }
            houses.add(Point(x, y))
        }

        assertEquals(2081, houses.size)
    }

    @Test
    fun part2() {
        data class Point(var x: Int, var y: Int)
        val couriers = listOf(
            Point(0,0), // Santa
            Point(0,0)  // Robo-Santa
        )
        val houses = mutableSetOf(Point(0, 0))
        for ((index, char) in readInput("year2015/Day3.txt").withIndex()) {
            val courier = couriers[index % couriers.size]
            when (char) {
                '>' -> courier.x++
                '<' -> courier.x--
                '^' -> courier.y++
                'v' -> courier.y--
                else -> throw IllegalStateException()
            }
            houses.add(courier.copy())
        }

        assertEquals(2341, houses.size)
    }
}
