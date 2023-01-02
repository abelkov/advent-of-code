package year2015

import kotlin.math.max
import kotlin.test.*

class Day14 {
    @Test
    fun part1() {
        // Vixen can fly 19 km/s for 7 seconds, but then must rest for 124 seconds.
        val regex = """(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".toRegex()
        var maxDistance = 0
        for (line in readInput("year2015/Day14.txt").lines()) {
            val matchResult = regex.matchEntire(line)
            val (_, speedString, maxMoveTimeString, maxRestTimeString) = matchResult!!.groupValues.drop(1)
            val speed = speedString.toInt()
            val maxMoveTime = maxMoveTimeString.toInt()
            val maxRestTime = maxRestTimeString.toInt()

            var distance = 0
            var time = 0
            var moveTime = maxMoveTime
            var restTime = maxRestTime
            val raceTime = 2503
            while (time < raceTime) {
                time++
                if (moveTime > 0) {
                    distance += speed
                    moveTime--
                } else {
                    restTime--
                    if (restTime == 0) {
                        moveTime = maxMoveTime
                        restTime = maxRestTime
                    }
                }
            }

            maxDistance = max(maxDistance, distance)
        }

        assertEquals(2660, maxDistance)
    }

    @Test
    fun part2() {
        data class Reindeer(
            val speed: Int,
            val maxMoveTime: Int,
            val maxRestTime: Int,
            var distance: Int = 0,
            var score: Int = 0,
            var moveTime: Int = maxMoveTime,
            var restTime: Int = maxRestTime
        ) {
            fun update() {
                if (moveTime > 0) {
                    distance += speed
                    moveTime--
                } else {
                    restTime--
                    if (restTime == 0) {
                        moveTime = maxMoveTime
                        restTime = maxRestTime
                    }
                }
            }
        }

        val reindeers = mutableSetOf<Reindeer>()
        // Vixen can fly 19 km/s for 7 seconds, but then must rest for 124 seconds.
        val regex = """(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".toRegex()
        for (line in readInput("year2015/Day14.txt").lines()) {
            val matchResult = regex.matchEntire(line)
            val (_, speedString, maxMoveTimeString, maxRestTimeString) = matchResult!!.groupValues.drop(1)
            val speed = speedString.toInt()
            val maxMoveTime = maxMoveTimeString.toInt()
            val maxRestTime = maxRestTimeString.toInt()
            reindeers += Reindeer(speed, maxMoveTime, maxRestTime)
        }

        var time = 0
        val raceTime = 2503
        while (time < raceTime) {
            time++
            reindeers.forEach { it.update() }
            val bestDistance = reindeers.maxOf { it.distance }
            for (reindeer in reindeers) {
                if (reindeer.distance == bestDistance) {
                    reindeer.score++
                }
            }
        }

        val bestScore = reindeers.maxOf { it.score }
        assertEquals(1256, bestScore)
    }
}