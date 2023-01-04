package year2015

import kotlin.test.Test
import kotlin.test.assertEquals

private const val GRID_SIZE = 100
private const val STEPS = 100

class Day18 {
    private fun getOnNeighbors(field: Array<Array<Int>>, i: Int, j: Int): Int = listOf(
        field[i - 1][j - 1],
        field[i - 1][j],
        field[i - 1][j + 1],
        field[i][j + 1],
        field[i + 1][j + 1],
        field[i + 1][j],
        field[i + 1][j - 1],
        field[i][j - 1]
    ).sum()

    @Test
    fun part1() {
        // 1 == On, 0 == Off
        val field: Array<Array<Int>> = Array(GRID_SIZE + 2) { Array(GRID_SIZE + 2) { 0 } }
        readInput("year2015/Day18.txt").lines().forEachIndexed { i, row ->
            row.forEachIndexed { j, light ->
                field[i + 1][j + 1] = if (light == '#') 1 else 0
            }
        }

        repeat(STEPS) {
            val newField = Array(GRID_SIZE + 2) { Array(GRID_SIZE + 2) { 0 } }
            for (i in 1..GRID_SIZE) {
                for (j in 1..GRID_SIZE) {
                    val onNeighbors = getOnNeighbors(field, i, j)
                    if (field[i][j] == 1) {
                        newField[i][j] = if (onNeighbors in 2..3) 1 else 0
                    } else {
                        newField[i][j] = if (onNeighbors == 3) 1 else 0
                    }
                }
            }

            for (i in 1..GRID_SIZE) {
                for (j in 1..GRID_SIZE) {
                    field[i][j] = newField[i][j]
                }
            }
        }

        val count = field.sumOf { row -> row.sumOf { it } }
        assertEquals(1061, count)
    }

    @Test
    fun part2() {
        // 1 == On, 0 == Off
        val field: Array<Array<Int>> = Array(GRID_SIZE + 2) { Array(GRID_SIZE + 2) { 0 } }
        readInput("year2015/Day18.txt").lines().forEachIndexed { i, row ->
            row.forEachIndexed { j, light ->
                field[i + 1][j + 1] = if (light == '#') 1 else 0
            }
        }

        val corners = listOf(
            1 to 1,
            1 to GRID_SIZE,
            GRID_SIZE to GRID_SIZE,
            GRID_SIZE to 1,
        )

        for ((i, j) in corners) {
            field[i][j] = 1
        }

        repeat(STEPS) {
            val newField = Array(GRID_SIZE + 2) { Array(GRID_SIZE + 2) { 0 } }
            for (i in 1..GRID_SIZE) {
                for (j in 1..GRID_SIZE) {
                    val onNeighbors = getOnNeighbors(field, i, j)
                    if (field[i][j] == 1) {
                        newField[i][j] = if (onNeighbors in 2..3) 1 else 0
                    } else {
                        newField[i][j] = if (onNeighbors == 3) 1 else 0
                    }
                }
            }

            for (i in 1..GRID_SIZE) {
                for (j in 1..GRID_SIZE) {
                    field[i][j] = newField[i][j]
                }
            }
            for ((i, j) in corners) {
                field[i][j] = 1
            }
        }

        val count = field.sumOf { row -> row.sumOf { it } }
        assertEquals(1006, count)
    }
}