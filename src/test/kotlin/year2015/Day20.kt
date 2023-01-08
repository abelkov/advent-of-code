package year2015

import kotlin.math.ceil
import kotlin.test.Test
import kotlin.test.assertEquals

class Day20 {
    @Test
    fun part1() {
        val presentsTarget = readInput("year2015/Day20.txt").toInt()
//        var houseNumber = 1
        var houseNumber = 550000
        outer@
        while (true) {
            var presents = 0

            for (elfNumber in 1..(houseNumber / 2)) {
                if (houseNumber % elfNumber == 0) {
                    presents += elfNumber * 10
                }
            }
            // don't forget about elfNumber == houseNumber
            presents += houseNumber * 10

            if (presents >= presentsTarget) {
                break@outer
            }

            if (presents > 20000000) {
                println("House $houseNumber got $presents presents.")
            }

            houseNumber++
        }

        assertEquals(665280, houseNumber)
    }

    @Test
    fun part2() {
        val presentsTarget = readInput("year2015/Day20.txt").toInt()
//        var houseNumber = 1
        var houseNumber = 630000
        outer@
        while (true) {
            var presents = 0
            val minimumElfNumber = ceil(houseNumber / 50.0).toInt()
            for (elfNumber in minimumElfNumber..(houseNumber / 2)) {
                if (houseNumber % elfNumber == 0) {
                    presents += elfNumber * 11
                }
            }
            // don't forget about elfNumber == houseNumber
            presents += houseNumber * 11

            if (presents >= presentsTarget) {
                break@outer
            }

            if (presents > 20000000) {
                println("House $houseNumber got $presents presents.")
            }

            houseNumber++
        }

        assertEquals(705600, houseNumber)
    }
}