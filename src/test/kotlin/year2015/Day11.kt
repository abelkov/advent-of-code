package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11 {
    @Test
    fun part1() {
        var password = "cqjxjnds"
        do {
            password = increment(password)
        } while (!password.isAcceptable())
        assertEquals("cqjxxyzz", password)
    }

    @Test
    fun part2() {
        var password = "cqjxxyzz"
        do {
            password = increment(password)
        } while (!password.isAcceptable())
        assertEquals("cqkaabcc", password)
    }

    private fun String.isAcceptable(): Boolean =
        condition1(this) && condition2(this) && condition3(this)

    // Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to xyz.
    // They cannot skip letters; abd doesn't count.
    private fun condition1(password: String): Boolean {
        for ((index, firstLetter) in password.withIndex()) {
            if (password.length - index < 3) break
            val secondLetter = password[index + 1]
            val thirdLetter = password[index + 2]
            if (firstLetter + 1 == secondLetter && secondLetter + 1 == thirdLetter) {
                return true
            }
        }
        return false
    }

    // Passwords may not contain the letters i, o, or l, as these letters can be mistaken
    // for other characters and are therefore confusing.
    private fun condition2(password: String): Boolean =
        !password.contains("i") &&
                !password.contains("o") &&
                !password.contains("l")

    // Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.
    private fun condition3(password: String): Boolean {
        for ((index, firstLetter) in password.withIndex()) {
            if (password.length - index < 2) break
            val secondLetter = password[index + 1]

            if (firstLetter == secondLetter) {
                var nextIndex = index + 2
                while (nextIndex < password.length - 1) {
                    val nextFirstLetter = password[nextIndex]
                    val nextSecondLetter = password[nextIndex + 1]
                    if (nextFirstLetter == nextSecondLetter && nextFirstLetter != firstLetter) {
                        return true
                    }
                    nextIndex++
                }
            }
        }
        return false
    }

    private fun increment(password: String): String {
        val list = password.toMutableList()
        var index = list.size - 1
        while (index >= 0) {
            if (list[index] == 'z') {
                list[index] = 'a'
                index--
            } else {
                list[index]++
                break
            }
        }
        return list.joinToString(separator = "")
    }
}