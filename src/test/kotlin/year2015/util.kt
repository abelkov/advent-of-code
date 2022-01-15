package year2015

fun readInput(path: String): String =
    ClassLoader.getSystemClassLoader().getResource(path)!!.readText()