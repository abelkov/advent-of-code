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