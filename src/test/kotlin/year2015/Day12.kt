package year2015

import year2015.JsonEntity.*
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12 {
    @Test
    fun part1() {
        val input = readInput("year2015/Day12.txt")
        val sum = Regex("-?\\d+").findAll(input).map { it.value.toInt() }.sum()
        assertEquals(111754, sum)
    }

    @Test
    fun part2() {
        val input = readInput("year2015/Day12.txt")
        val json = JsonParser(input).parse()
        val sum = json.eval()
        assertEquals(65402, sum)
    }
}

class JsonParser(private val input: String) {
    private var i = 0
    private val numberRegex = "-|\\d".toRegex()

    fun parse(): JsonEntity =
        parseEntity()

    private fun parseEntity(): JsonEntity = when {
        input[i] == '{' -> parseObject()
        input[i] == '[' -> parseArray()
        input[i] == '"' -> parseString()
        else -> parseNumber()
    }

    // {"d":"red","e":[1,2,3,4],"f":5}
    private fun parseObject(): JsonObject {
        i++ // advance '{'
        val map = mutableMapOf<String, JsonEntity>()
        while (input[i] != '}') {
            val key = parseString().value
            i++ // advance ':'
            val entity = parseEntity()
            map[key] = entity
            if (input[i] == ',') {
                i++ // advance ','
            }
        }
        i++ // advance '}'
        return JsonObject(map)
    }

    // [1,{"c":"red","b":2},3]
    private fun parseArray(): JsonArray {
        i++ // advance '['
        val elements = mutableListOf<JsonEntity>()
        while (input[i] != ']') {
            elements += parseEntity()
            if (input[i] == ',') {
                i++ // advance ','
            }
        }
        i++ // advance ']'
        return JsonArray(elements)
    }

    // "red"
    private fun parseString(): JsonString {
        val string = buildString {
            i++ // advance opening '"'
            while (input[i] != '"') {
                append(input[i])
                i++
            }
            i++ // advance closing '"'
        }
        return JsonString(string)
    }

    // 1234
    private fun parseNumber(): JsonNumber {
        val number = buildString {
            while (input[i].toString().matches(numberRegex)) {
                append(input[i])
                i++
            }
        }
        return JsonNumber(number.toInt())
    }
}

sealed class JsonEntity {
    abstract fun eval(): Int

    data class JsonString(val value: String) : JsonEntity() {
        override fun eval(): Int = 0
    }

    data class JsonNumber(private val value: Int) : JsonEntity() {
        override fun eval(): Int = value
    }

    class JsonArray(private val elements: List<JsonEntity>) : JsonEntity() {
        override fun eval(): Int =
            elements.sumOf(JsonEntity::eval)
    }

    class JsonObject(private val map: Map<String, JsonEntity>) : JsonEntity() {
        override fun eval(): Int {
            val red = JsonString("red")
            return if (map.containsValue(red)) 0 else map.values.sumOf(JsonEntity::eval)
        }
    }
}