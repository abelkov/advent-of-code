package year2015

import kotlin.math.max
import kotlin.test.*

private data class Ingredient(
    val capacity: Int,
    val durability: Int,
    val flavor: Int,
    val texture: Int,
    val calories: Int
)

private const val MAX_SPOONS = 100

class Day15 {
    @Test
    fun part1() {
        // Sugar: capacity 3, durability 0, flavor 0, texture -3, calories 2
        val regex = """(\w+): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)""".toRegex()
        val ingredients = mutableListOf<Ingredient>()
        for (line in readInput("year2015/Day15.txt").lines()) {
            val matchResult = regex.matchEntire(line)
            val (_, capacity, durability, flavor, texture, calories) = matchResult!!.groupValues.drop(1)
            ingredients += Ingredient(capacity.toInt(), durability.toInt(), flavor.toInt(), texture.toInt(), calories.toInt())
        }
        val bestScore = computeBestScore(ingredients)
        assertEquals(222870, bestScore)
    }

    private fun computeBestScore(ingredients: List<Ingredient>): Int {
        require(ingredients.size == 4)
        var bestScore = 0
        for (a in 0..MAX_SPOONS) {
            for (b in 0..MAX_SPOONS) {
                for (c in 0..MAX_SPOONS) {
                    for (d in 0..MAX_SPOONS) {
                        if (a + b + c + d > MAX_SPOONS) break

                        val capacity = ingredients[0].capacity * a +
                                ingredients[1].capacity * b +
                                ingredients[2].capacity * c +
                                ingredients[3].capacity * d
                        val durability = ingredients[0].durability * a +
                                ingredients[1].durability * b +
                                ingredients[2].durability * c +
                                ingredients[3].durability * d
                        val flavor = ingredients[0].flavor * a +
                                ingredients[1].flavor * b +
                                ingredients[2].flavor * c +
                                ingredients[3].flavor * d
                        val texture = ingredients[0].texture * a +
                                ingredients[1].texture * b +
                                ingredients[2].texture * c +
                                ingredients[3].texture * d

                        val score = capacity.coerceAtLeast(0) *
                                durability.coerceAtLeast(0) *
                                flavor.coerceAtLeast(0) *
                                texture.coerceAtLeast(0)
                        bestScore = max(bestScore, score)
                    }
                }
            }
        }

        return bestScore
    }
    
    @Test
    fun part2() {
        // Sugar: capacity 3, durability 0, flavor 0, texture -3, calories 2
        val regex = """(\w+): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)""".toRegex()
        val ingredients = mutableListOf<Ingredient>()
        for (line in readInput("year2015/Day15.txt").lines()) {
            val matchResult = regex.matchEntire(line)
            val (_, capacity, durability, flavor, texture, calories) = matchResult!!.groupValues.drop(1)
            ingredients += Ingredient(capacity.toInt(), durability.toInt(), flavor.toInt(), texture.toInt(), calories.toInt())
        }
        val bestScore = computeBestScore2(ingredients)
        assertEquals(117936, bestScore)
    }

    private fun computeBestScore2(ingredients: List<Ingredient>): Int {
        require(ingredients.size == 4)
        var bestScore = 0
        for (a in 0..MAX_SPOONS) {
            for (b in 0..MAX_SPOONS) {
                for (c in 0..MAX_SPOONS) {
                    for (d in 0..MAX_SPOONS) {
                        if (a + b + c + d > MAX_SPOONS) break

                        val calories = ingredients[0].calories * a +
                                ingredients[1].calories * b +
                                ingredients[2].calories * c +
                                ingredients[3].calories * d
                        if (calories != 500) continue

                        val capacity = ingredients[0].capacity * a +
                                ingredients[1].capacity * b +
                                ingredients[2].capacity * c +
                                ingredients[3].capacity * d
                        val durability = ingredients[0].durability * a +
                                ingredients[1].durability * b +
                                ingredients[2].durability * c +
                                ingredients[3].durability * d
                        val flavor = ingredients[0].flavor * a +
                                ingredients[1].flavor * b +
                                ingredients[2].flavor * c +
                                ingredients[3].flavor * d
                        val texture = ingredients[0].texture * a +
                                ingredients[1].texture * b +
                                ingredients[2].texture * c +
                                ingredients[3].texture * d

                        val score = capacity.coerceAtLeast(0) *
                                durability.coerceAtLeast(0) *
                                flavor.coerceAtLeast(0) *
                                texture.coerceAtLeast(0)
                        bestScore = max(bestScore, score)
                    }
                }
            }
        }

        return bestScore
    }
}

private operator fun <E> List<E>.component6(): E = this[5]
