package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

import java.math.BigInteger
import java.security.MessageDigest

class Day4 {
    @Test
    fun part1() {
        val prefix = readInput("year2015/Day4.txt")
        var suffix = 1
        while(!md5(prefix + suffix).startsWith("00000")) {
            suffix++
        }
        assertEquals(254575, suffix)
    }

    @Test
    fun part2() {
        val prefix = readInput("year2015/Day4.txt")
        var suffix = 1
        while(!md5(prefix + suffix).startsWith("000000")) {
            suffix++
        }
        assertEquals(1038736, suffix)
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}
