package year2015

fun readInput(path: String): String =
    ClassLoader.getSystemClassLoader().getResource(path)!!.readText()

fun <T> Set<T>.permutations(): Set<List<T>> {
    if (isEmpty()) return emptySet()

    fun <T> allPermutations(list: List<T>): Set<List<T>> {
        if (list.isEmpty()) return setOf(emptyList())

        val result: MutableSet<List<T>> = mutableSetOf()
        for (i in list.indices) {
            allPermutations(list - list[i]).forEach { item ->
                result.add(item + list[i])
            }
        }
        return result
    }

    return allPermutations(toList())
}

fun <T> List<T>.combinations(): List<List<T>> {
    val combinations = mutableListOf<List<T>>(listOf())
    for (element in this) {
        val existingCombinations = combinations.toList()
        for (comb in existingCombinations) {
            val mutComb = comb.toMutableList()
            mutComb += element
            combinations += mutComb
        }
    }
    return combinations
}