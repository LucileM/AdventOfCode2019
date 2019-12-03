import Resources.resourceAsStringList

/* --- Day 1: The Tyranny of the Rocket Equation --- */
// https://adventofcode.com/2019/day/1

class Day01(myInputStrings: List<String>)
{
    private val myIntputInt: List<Int> = myInputStrings.map { it.toInt() }

    /* --- Part One --- */
    private fun fuel(weight: Int): Int = weight / 3 - 2
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sum-by.html
    fun part1(): Int = myIntputInt.sumBy { fuel(it.toInt()) }

    /* --- Part Two --- */
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/generate-sequence.html
    private fun solvePart2(weight: Int): Int = generateSequence(fuel(weight), ::fuel).takeWhile { it > 0 }.sum()
    fun part2(): Int = myIntputInt.sumBy { solvePart2(it.toInt()) }
}

fun main() {
    val day01 = Day01(resourceAsStringList("Day01_MyInput.txt"))
    println("--- Day 1: The Tyranny of the Rocket Equation ---")
    println("Part1: " + day01.part1())
    println("Part2: " + day01.part2())
}