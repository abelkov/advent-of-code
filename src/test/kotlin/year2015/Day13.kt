package year2015

import kotlin.math.max
import kotlin.test.*

class Day13 {
    @Test
    fun part1() {
        val map = mutableMapOf<String, MutableMap<String, Int>>()
        // Alice would gain 54 happiness units by sitting next to Bob.
        val regex = """(\w+) would (\w+) (\d+) happiness units by sitting next to (\w+).""".toRegex()
        for (line in readInput("year2015/Day13.txt").lines()) {
            val matchResult = regex.matchEntire(line)
            val (person1, sign, units, person2) = matchResult!!.groupValues.drop(1)
            map.putIfAbsent(person1, mutableMapOf())
            val signedUnits = if (sign == "gain") units.toInt() else -units.toInt()
            map.getValue(person1)[person2] = signedUnits
        }

        var optimal = Int.MIN_VALUE
        for (guests: List<String> in map.keys.permutations()) {
            var sum = 0
            for ((index, guest) in guests.withIndex()) {
                val prevIndex = (index - 1).mod(guests.size)
                val nextIndex = (index + 1).mod(guests.size)
                val prevGuest = guests[prevIndex]
                val nextGuest = guests[nextIndex]
                sum += map.getValue(guest).getValue(prevGuest)
                sum += map.getValue(guest).getValue(nextGuest)
            }
            optimal = max(optimal, sum)
        }

        assertEquals(709, optimal)
    }

    @Test
    fun part2() {
        val map = mutableMapOf<String, MutableMap<String, Int>>()
        // Alice would gain 54 happiness units by sitting next to Bob.
        val regex = """(\w+) would (\w+) (\d+) happiness units by sitting next to (\w+).""".toRegex()
        for (line in readInput("year2015/Day13.txt").lines()) {
            val matchResult = regex.matchEntire(line)
            val (person1, sign, units, person2) = matchResult!!.groupValues.drop(1)
            map.putIfAbsent(person1, mutableMapOf())
            val signedUnits = if (sign == "gain") units.toInt() else -units.toInt()
            map.getValue(person1)[person2] = signedUnits
        }

        map["me"] = mutableMapOf()
        for (person in map.keys) {
            map.getValue(person)["me"] = 0
            map.getValue("me")[person] = 0
        }

        var optimal = Int.MIN_VALUE
        for (guests: List<String> in map.keys.permutations()) {
            var sum = 0
            for ((index, guest) in guests.withIndex()) {
                val prevIndex = (index - 1).mod(guests.size)
                val nextIndex = (index + 1).mod(guests.size)
                val prevGuest = guests[prevIndex]
                val nextGuest = guests[nextIndex]
                sum += map.getValue(guest).getValue(prevGuest)
                sum += map.getValue(guest).getValue(nextGuest)
            }
            optimal = max(optimal, sum)
        }

        assertEquals(668, optimal)
    }
}