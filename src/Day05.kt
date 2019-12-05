import Resources.resourceAsStringList
/* --- Day 5: Sunny with a Chance of Asteroids ---
 */
// https://adventofcode.com/2019/day/5

class Day05(myIntList: List<Int>)
{
    val mutableList = myIntList.toMutableList()

    fun getValue(mode: Int, pos: Int) : Int {
        return when (mode) {
            0 -> {
                mutableList[pos]
            }
            1 -> {
                pos
            }
            else -> throw Exception("error")
        }
    }

    fun part1(): List<Int>
    {
        // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html#kotlin.collections.MutableList
        //mutableList[1] = n
        //mutableList[2] = v
        var i = 0
        var end = false
        while (!end) {

            val opcode = mutableList[i] % 10


            when (opcode)
            {
                99 -> end = true
                1 -> {
                    val pos1 = mutableList[i + 1]
                    val pos2 = mutableList[i + 2]
                    val pos3 = mutableList[i + 3]

                    var mode1 = (mutableList[i]/100) % 10
                    var mode2 = (mutableList[i]/1000) % 10
                    var value1 = getValue(mode1, pos1)
                    var value2 = getValue(mode2, pos2)

                    mutableList[pos3] = value1 + value2
                    i += 4
                }
                2 -> {
                    val pos1 = mutableList[i + 1]
                    val pos2 = mutableList[i + 2]
                    val pos3 = mutableList[i + 3]

                    var mode1 = (mutableList[i]/100) % 10
                    var mode2 = (mutableList[i]/1000) % 10
                    var value1 = getValue(mode1, pos1)
                    var value2 = getValue(mode2, pos2)
                    mutableList[pos3] = value1 * value2
                    i += 4
                }
                3 -> {
                    println("3: read something")
                    mutableList[mutableList[i + 1]] = readLine()!!.toInt()
                    i += 2
                }
                4 -> {
                    println("4: " + mutableList[mutableList[i + 1]])
                    i += 2
                }
                5 -> {
                    val pos1 = mutableList[i + 1]
                    val pos2 = mutableList[i + 2]

                    var mode1 = (mutableList[i]/100) % 10
                    var mode2 = (mutableList[i]/1000) % 10
                    var value1 = getValue(mode1, pos1)
                    var value2 = getValue(mode2, pos2)
                    if (value1 != 0) {
                        i = value2
                    } else {
                        i += 3
                    }
                }
                6-> {
                    val pos1 = mutableList[i + 1]
                    val pos2 = mutableList[i + 2]

                    var mode1 = (mutableList[i]/100) % 10
                    var mode2 = (mutableList[i]/1000) % 10
                    var value1 = getValue(mode1, pos1)
                    var value2 = getValue(mode2, pos2)
                    if (value1 == 0) {
                        i = value2
                    } else {
                        i += 3
                    }
                }
                7-> {
                    val pos1 = mutableList[i + 1]
                    val pos2 = mutableList[i + 2]
                    val pos3 = mutableList[i + 3]

                    var mode1 = (mutableList[i]/100) % 10
                    var mode2 = (mutableList[i]/1000) % 10
                    var value1 = getValue(mode1, pos1)
                    var value2 = getValue(mode2, pos2)
                    if (value1 < value2) {
                        mutableList[pos3] = 1
                    } else {
                        mutableList[pos3] = 0
                    }
                    i += 4
                }
                8-> {
                    val pos1 = mutableList[i + 1]
                    val pos2 = mutableList[i + 2]
                    val pos3 = mutableList[i + 3]

                    var mode1 = (mutableList[i]/100) % 10
                    var mode2 = (mutableList[i]/1000) % 10
                    var value1 = getValue(mode1, pos1)
                    var value2 = getValue(mode2, pos2)
                    if (value1 == value2) {
                        mutableList[pos3] = 1
                    } else {
                        mutableList[pos3] = 0
                    }
                    i += 4
                }
                else -> end = true
            }
        }
        return mutableList
    }
    /* --- Part Two --- */
    fun part2() {}
}

fun main() {
    val input = Resources.resourceAsString("Day05_MyInput.txt")
    val opCodes = input.split(",").map { n -> n.toInt() }
    val day05 = Day05(opCodes)

    println("--- Day 5: ---")
    println("Part1: " + day05.part1())
    println("Part2: " + day05.part2())
}