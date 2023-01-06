package year2015

import kotlin.math.min
import kotlin.test.Test
import kotlin.test.assertEquals

class Day19 {
    @Test
    fun part1() {
        val input = readInput("year2015/Day19.txt").lines()
        val molecule = input.last()
        val replacements = mutableListOf<Pair<String, String>>()
        for (pair in input.dropLast(2)) {
            val (before, after) = pair.split(" => ")
            replacements += Pair(before, after)
        }

        val nextMolecules = mutableSetOf<String>()
        replacement@ for ((before, after) in replacements) {
            var startIndex = 0
            while (startIndex < molecule.length) {
                val nextIndex = molecule.indexOf(before, startIndex)
                if (nextIndex == -1) continue@replacement
                val endIndex = nextIndex + before.length
                nextMolecules += molecule.replaceRange(nextIndex, endIndex, after)
                startIndex = endIndex
            }
        }

        assertEquals(509, nextMolecules.size)
    }

    // Doesn't work: brute force is too slow
    @Test
    fun part2() {
        val input = readInput("year2015/Day19.txt").lines()
        val targetMolecule = input.last()
        val replacements = mutableListOf<Pair<String, String>>()
        // replacements minus epsilon pairs
        for (pair in input.dropLast(5)) {
            val (before, after) = pair.split(" => ")
            replacements += Pair(before, after)
        }

        data class Molecule(val value: String, var steps: Int)

        // hardcoded epsilon pairs
        var startMolecules = mutableSetOf(
            Molecule("HF", 1),
            Molecule("NAl", 1),
            Molecule("OMg", 1),
        )

        val minSteps: Int
        outer@
        while (true) {
            val nextStartMolecules = mutableSetOf<Molecule>()
            for ((before, after) in replacements) {
                val nextMolecules = mutableSetOf<Molecule>()
                molecule@
                for (molecule in startMolecules) {
                    var startIndex = 0
                    while (startIndex < molecule.value.length) {
                        val nextIndex = molecule.value.indexOf(before, startIndex)
                        if (nextIndex == -1) continue@molecule
                        val endIndex = nextIndex + before.length
                        val nextMolecule = Molecule(
                            molecule.value.replaceRange(nextIndex, endIndex, after),
                            molecule.steps + 1
                        )
                        if (nextMolecule.value == targetMolecule) {
                            minSteps = nextMolecule.steps
                            break@outer
                        } else if (nextMolecule.value.length <= targetMolecule.length) {
                            nextMolecules += nextMolecule
                        }
                        startIndex = endIndex

                    }
                }

                for (nextMolecule in nextMolecules) {
                    val existing = nextStartMolecules.find { it.value == nextMolecule.value }
                    if (existing != null) {
                        existing.steps = min(existing.steps, nextMolecule.steps)
                    } else {
                        nextStartMolecules += nextMolecule
                    }
                }
            }

            startMolecules = nextStartMolecules
        }

        assertEquals(509, minSteps)
    }
}