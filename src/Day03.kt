
import Resources.resourceAsStringList
import kotlin.math.abs
//https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.math/absolute-value.html

/* --- Day 3: Crossed Wires --- */
// https://adventofcode.com/2019/day/3

// https://kotlinlang.org/docs/reference/data-classes.html

class Day03() {
    data class Vector(val dir: Direction, val steps: Int)
    data class Point (val x: Int, val y: Int)
    enum class Direction { UP, DOWN, LEFT, RIGHT }

    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/map.html
    private val myInput = resourceAsStringList("Day03_MyInput.txt").map {
        val indications = it.split(",")
        indications.map {
                command ->
                val direction = when (command[0]) {
                    'U' -> Direction.UP
                    'D' -> Direction.DOWN
                    'L' -> Direction.LEFT
                    'R' -> Direction.RIGHT
                    else -> throw Exception("error")
                }
            var step = command.substring(1).toInt()
            Vector(direction, step)
        }
    }

    fun part1() : Any
    {
        val (lineOne, lineTwo) = myInput.map{
                vectorList ->
                    val set = mutableSetOf<Point>()
                    var position = Point(0, 0)
                    for (vector in vectorList)
                    {
                        repeat(vector.steps)
                        {
                            position = when (vector.dir)
                            {
                                Direction.UP -> Point(position.x, position.y+1)
                                Direction.DOWN -> Point(position.x, position.y-1)
                                Direction.LEFT -> Point(position.x-1, position.y)
                                Direction.RIGHT -> Point(position.x+1, position.y)
                            }
                            set.add(position)
                        }
                    }
            set
        }
        // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/intersect.html
        val point = lineOne.intersect(lineTwo).minBy { abs(it.x) + abs(it.x) }!!
        return abs(point.x) + abs(point.y)
    }

     fun part2() : Any
     {
        val (lineOne, lineTwo) = myInput.map { vectorList ->
            val set = mutableMapOf<Point, Int>()
            var steps = 0
            var position = Point(0, 0)
            for (vector in vectorList)
            {
                repeat(vector.steps)
                {
                    steps += 1
                    position = when (vector.dir)
                    {
                        Direction.UP -> Point(position.x, position.y+1)
                        Direction.DOWN -> Point(position.x, position.y-1)
                        Direction.LEFT -> Point(position.x-1, position.y)
                        Direction.RIGHT -> Point(position.x+1, position.y)
                    }
                    set[position] = steps
                }
            }
            set
        }
         val output = (lineOne.keys).intersect(lineTwo.keys).sortedBy { abs(it.x) + abs(it.x) }.map { point ->
                val first = lineOne.keys.single { it == point }
                val second = lineTwo.keys.single { it == point }
                val one = lineOne.getOrDefault(first, 0)
                val two = lineTwo.getOrDefault(second, 0)
                one + two
            }.sorted()
        return output.first()
    }
}

fun main() {
    println("--- Day 3: Crossed Wires ---")
    println("Part1: " + Day03().part1())
    println("Part2: " + Day03().part2())
}
