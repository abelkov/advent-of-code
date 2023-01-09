package year2015

import kotlin.math.max
import kotlin.math.min
import kotlin.test.Test
import kotlin.test.assertEquals

class Day21 {
    data class Item(val cost: Int, val damage: Int = 0, val armor: Int = 0)

    private val weapons = listOf(
        Item(cost = 8, damage = 4),
        Item(cost = 10, damage = 5),
        Item(cost = 25, damage = 6),
        Item(cost = 40, damage = 7),
        Item(cost = 74, damage = 8),
    )
    private val armors = listOf(
        Item(cost = 0, armor = 0), // no armor
        Item(cost = 13, armor = 1),
        Item(cost = 31, armor = 2),
        Item(cost = 53, armor = 3),
        Item(cost = 75, armor = 4),
        Item(cost = 102, armor = 5),
    )
    private val rings = listOf(
        Item(cost = 0, damage = 0, armor = 0), // no ring left
        Item(cost = 0, damage = 0, armor = 0), // no ring right
        Item(cost = 25, damage = 1, armor = 0),
        Item(cost = 50, damage = 2, armor = 0),
        Item(cost = 100, damage = 3, armor = 0),
        Item(cost = 20, damage = 0, armor = 1),
        Item(cost = 40, damage = 0, armor = 2),
        Item(cost = 80, damage = 0, armor = 3),
    )

    @Test
    fun part1() {
        var minCost = Int.MAX_VALUE

        for (weapon in weapons) {
            for (armor in armors) {
                for ((leftRing, rightRing) in rings.combinations().filter { it.size == 2 }) {
                    val cost = weapon.cost + armor.cost + leftRing.cost + rightRing.cost

                    var playerHP = 100
                    val playerBaseDamage = weapon.damage + leftRing.damage + rightRing.damage
                    val playerArmor = armor.armor + leftRing.armor + rightRing.armor

                    var bossHP = 109
                    val bossBaseDamage = 8
                    val bossArmor = 2

                    val playerActualDamage = (playerBaseDamage - bossArmor).coerceAtLeast(1)
                    val bossActualDamage = (bossBaseDamage - playerArmor).coerceAtLeast(1)

                    while (playerHP > 0) {
                        bossHP -= playerActualDamage
                        if (bossHP <= 0) {
                            minCost = min(minCost, cost)
                            break
                        } else {
                            playerHP -= bossActualDamage
                        }
                    }
                }
            }
        }

        assertEquals(111, minCost)
    }

    @Test
    fun part2() {
        var maxCost = 0

        for (weapon in weapons) {
            for (armor in armors) {
                for ((leftRing, rightRing) in rings.combinations().filter { it.size == 2 }) {
                    val cost = weapon.cost + armor.cost + leftRing.cost + rightRing.cost

                    var playerHP = 100
                    val playerBaseDamage = weapon.damage + leftRing.damage + rightRing.damage
                    val playerArmor = armor.armor + leftRing.armor + rightRing.armor

                    var bossHP = 109
                    val bossBaseDamage = 8
                    val bossArmor = 2

                    val playerActualDamage = (playerBaseDamage - bossArmor).coerceAtLeast(1)
                    val bossActualDamage = (bossBaseDamage - playerArmor).coerceAtLeast(1)

                    while (bossHP > 0) {
                        bossHP -= playerActualDamage
                        if (bossHP > 0) {
                            playerHP -= bossActualDamage
                            if (playerHP <= 0) {
                                maxCost = max(maxCost, cost)
                                break
                            }
                        }
                    }
                }
            }
        }

        assertEquals(188, maxCost)
    }
}