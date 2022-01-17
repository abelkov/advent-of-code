package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

class Day5 {
    @Test
    fun part1() {
        val niceCount = readInput("year2015/Day5.txt")
            .lines()
            .filter { hasEnoughVowels(it) && hasDoubleLetter(it) && hasNoBadSubstrings(it) }
            .size
        assertEquals(238, niceCount)
    }

    private fun hasEnoughVowels(s: String) = s.count { char -> char in "aeiou" } >= 3

    private fun hasDoubleLetter(s: String): Boolean {
        for ((index, char) in s.withIndex()) {
            if ((index + 2 <= s.length) && char == s[index + 1]) return true
        }
        return false
    }

    private fun hasNoBadSubstrings(s: String) =
        listOf("ab", "cd", "pq", "xy").all { !s.contains(it) }

    @Test
    fun part2() {
        val niceCount = readInput("year2015/Day5.txt")
            .lines()
            .filter { pairAppearsTwice(it) && hasRepeatingLetter(it) }
            .size
        assertEquals(69, niceCount)
    }

    private fun pairAppearsTwice(s: String): Boolean {
        for ((index, char) in s.withIndex()) {
            if ((index + 2 <= s.length) && s.substring(index + 2).contains("$char${s[index + 1]}")) {
                return true
            }
        }
        return false
    }

    private fun hasRepeatingLetter(s: String): Boolean {
        for ((index, char) in s.withIndex()) {
            if ((index + 3 <= s.length) && char == s[index + 2]) {
                return true
            }
        }
        return false
    }
}
