package year2015

import kotlin.math.max
import kotlin.math.min
import kotlin.test.Test
import kotlin.test.assertEquals

class Day9 {
    @Test
    fun part1() {
        val regex = """(\w+) to (\w+) = (\d+)""".toRegex()
        val cities = mutableSetOf<String>()
        val map = mutableMapOf<Pair<String, String>, Int>()
        for (line in readInput("year2015/Day9.txt").lines()) {
            val matchResult = regex.matchEntire(line)
            val (from, to, distance) = matchResult!!.groupValues.drop(1)
            cities.add(from)
            cities.add(to)
            map[Pair(from, to)] = distance.toInt()
            map[Pair(to, from)] = distance.toInt()
        }

        var shortest = Int.MAX_VALUE
        for (route: List<String> in cities.permutations()) {
            var total = 0
            for ((index, fromCity) in route.dropLast(1).withIndex()) {
                val toCity = route[index + 1]
                total += map[fromCity to toCity] ?: throw AssertionError()
            }
            shortest = min(shortest, total)
        }

        assertEquals(117, shortest)
    }

    @Test
    fun part2() {
        val regex = """(\w+) to (\w+) = (\d+)""".toRegex()
        val cities = mutableSetOf<String>()
        val map = mutableMapOf<Pair<String, String>, Int>()
        for (line in readInput("year2015/Day9.txt").lines()) {
            val matchResult = regex.matchEntire(line)
            val (from, to, distance) = matchResult!!.groupValues.drop(1)
            cities.add(from)
            cities.add(to)
            map[Pair(from, to)] = distance.toInt()
            map[Pair(to, from)] = distance.toInt()
        }

        var longest = 0
        for (route: List<String> in cities.permutations()) {
            var total = 0
            for ((index, fromCity) in route.dropLast(1).withIndex()) {
                val toCity = route[index + 1]
                total += map[fromCity to toCity] ?: throw AssertionError()
            }
            longest = max(longest, total)
        }

        assertEquals(909, longest)
    }
}
