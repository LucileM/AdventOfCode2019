
/* --- Day 4: Secure Container --- */
// double never decrease

class Day04()  {
    val input = "124075-580769".split("-").map { it.toLong() }
    private fun criteria(str: String) : Boolean
    {
        var increase = str.zipWithNext().all { (left, right) -> right >= left }
        var double = str.zipWithNext().any { (left, right) -> right == left }
        return increase && double
    }

    private fun strictCriteria(str: String) : Boolean
    {
        var increase = str.zipWithNext().all { (left, right) -> right >= left }
        var strictDouble = (str.windowed(4, 1).any { x ->
                                val (a, b, c, d) = x.toCharArray()
                                a != b && c != d && b == c } || (str[0] == str[1] && str[1] != str[2])
                || (str[4] == str[5] && str[3] != str[4]))
        return increase && strictDouble
    }

    fun part1() : Any = (input[0]..input[1]).filter { criteria(it.toString()) }.count()
    fun part2() : Any = (input[0]..input[1]).filter { strictCriteria(it.toString()) }.count()
}

fun main()
{
    println("--- Day 4: Secure Container ---")
    println("Part1: " + Day04().part1())
    println("Part2: " + Day04().part2())
}