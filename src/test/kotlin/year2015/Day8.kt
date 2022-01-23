package year2015

import year2015.HexState.*
import kotlin.test.Test
import kotlin.test.assertEquals

private enum class HexState { OFF, FIRST, SECOND }

class Day8 {
    @Test
    fun part1() {
        var charCount = 0
        var memoryCount = 0
        var escapeSequenceStarted = false
        var hexState = OFF
        readInput("year2015/Day8.txt").lines().forEach { line: String ->
            for (char in line) {
                charCount++
                if (char == '\\') {
                    if (escapeSequenceStarted) {
                        // second backslash
                        memoryCount++
                        escapeSequenceStarted = false
                    } else {
                        // first backslash
                        escapeSequenceStarted = true
                    }
                } else if (escapeSequenceStarted && char == '"') {
                    memoryCount++
                    escapeSequenceStarted = false
                } else if (escapeSequenceStarted && hexState == OFF && char == 'x') {
                    hexState = FIRST
                } else if (escapeSequenceStarted && hexState == FIRST) {
                    // consume first hex char
                    hexState = SECOND
                } else if (escapeSequenceStarted && hexState == SECOND) {
                    // consume second hex char
                    hexState = OFF
                    escapeSequenceStarted = false
                    memoryCount++
                } else if (char == '"') {
                    // skip prefix/suffix quotes
                    continue
                } else {
                    // regular char
                    memoryCount++
                }
            }
        }

        // println(charCount - memoryCount)
        assertEquals(1371, charCount - memoryCount)
    }

    @Test
    fun part2() {
        var charCount = 0
        var escapedCharCount = 0
        readInput("year2015/Day8.txt").lines().forEach { line: String ->
            escapedCharCount += 2 // prefix/suffix double quotes
            for (char in line) {
                charCount++
                escapedCharCount++
                if (char in listOf('"', '\\')) {
                    escapedCharCount++ // one more for the escaping backslash
                }
            }
        }

        // println(escapedCharCount - charCount)
        assertEquals(2117, escapedCharCount - charCount)
    }
}
