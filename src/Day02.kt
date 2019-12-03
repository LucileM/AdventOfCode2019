import Resources.resourceAsStringList
import Resources.resourceAsString

/* --- Day 2: 1202 Program Alarm --- */
// https://adventofcode.com/2019/day/2

class Day02 {
    fun part1(myIntList: List<Int>, n:Int, v:Int): Int
    {
        // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html#kotlin.collections.MutableList
        val mutableList = myIntList.toMutableList()
        mutableList[1] = n
        mutableList[2] = v
        var i = 0
        var end = false
        while (!end) {
            val opcode = mutableList[i]

            val pos1 = mutableList[i + 1]
            val pos2 = mutableList[i + 2]
            val pos3 = mutableList[i + 3]

            val value1 = mutableList[pos1]
            val value2 = mutableList[pos2]

            when (opcode)
            {
                99 -> end = true
                1 -> mutableList[pos3] = value1 + value2
                2 -> mutableList[pos3] = value1 * value2
                else -> end = true
            }
            i += 4
        }
        return mutableList[0]
    }

    fun part2(opCodes: List<Int>): Int
    {
        for (n in 0..99)
        {
            for (v in 0..99)
            {
                if (part1(opCodes, n, v) == 19690720)
                {
                    return 100 * n + v
                }
            }
        }
        return -1
    }
}

fun main() {
    val input = resourceAsString("Day02_MyInput.txt")
    val opCodes = input.split(",").map { n -> n.toInt() }
    println("--- Day 2: 1202 Program Alarm ---")
    println("Part1: " + Day02().part1(opCodes,12, 1))
    println("Part2: " + Day02().part2(opCodes))
}