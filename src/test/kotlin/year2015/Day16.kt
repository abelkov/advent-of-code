package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

class Day16 {
    private val compounds = mapOf(
        "children" to 3,
        "cats" to 7,
        "samoyeds" to 2,
        "pomeranians" to 3,
        "akitas" to 0,
        "vizslas" to 0,
        "goldfish" to 5,
        "trees" to 3,
        "cars" to 2,
        "perfumes" to 1,
    )

    @Test
    fun part1() {
        var auntNumber = -1
        // Sue 1: goldfish: 6, trees: 9, akitas: 0
        val regex = """Sue (\d+): (.+)""".toRegex()
        aunt@ for (aunt in readInput("year2015/Day16.txt").lines()) {
            val matchResult = regex.matchEntire(aunt)
            val (currentAuntNumber, compoundsInfo) = matchResult!!.groupValues.drop(1)

            for (compoundInfo in compoundsInfo.split(", ")) {
                val (compoundName, compoundNumber) = compoundInfo.split(": ")
                if (compoundNumber.toInt() != compounds[compoundName]) {
                    continue@aunt
                }
            }

            auntNumber = currentAuntNumber.toInt()
            break
        }

        assertEquals(103, auntNumber)
    }

    @Test
    fun part2() {
        var auntNumber = -1
        // Sue 1: goldfish: 6, trees: 9, akitas: 0
        val regex = """Sue (\d+): (.+)""".toRegex()
        aunt@ for (aunt in readInput("year2015/Day16.txt").lines()) {
            val matchResult = regex.matchEntire(aunt)
            val (currentAuntNumber, compoundsInfo) = matchResult!!.groupValues.drop(1)

            for (compoundInfo in compoundsInfo.split(", ")) {
                val (compoundName, compoundNumber) = compoundInfo.split(": ")
                when (compoundName) {
                    "cats", "trees" -> {
                        if (compoundNumber.toInt() <= compounds.getValue(compoundName)) {
                            continue@aunt
                        }
                    }
                    "pomeranians", "goldfish" -> {
                        if (compoundNumber.toInt() >= compounds.getValue(compoundName)) {
                            continue@aunt
                        }
                    }
                    else -> {
                        if (compoundNumber.toInt() != compounds.getValue(compoundName)) {
                            continue@aunt
                        }
                    }
                }
            }

            auntNumber = currentAuntNumber.toInt()
            break
        }

        assertEquals(405, auntNumber)
    }
}