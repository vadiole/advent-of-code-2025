import java.io.File

// https://adventofcode.com/2025/day/12
fun main() {
    val input = File("input.txt").readText()
    val shapes = input.split("\n\n").dropLast(1).map { block ->
        block.lines().drop(1).map { line ->
            BooleanArray(line.length) { i ->
                line[i] == '#'
            }
        }
    }
    val trees = input.split("\n\n").last().lines().map { line ->
        val (areaLine, shapesLine) = line.split(": ")
        val numberOfPresents = shapesLine.split(" ").map { it.toInt() }.toIntArray()
        val (width, height) = areaLine.split("x").map { it.toInt() }
        Tree(width, height, numberOfPresents)
    }
    var canFitCounter = 0
    for (tree in trees) {
        val totalArea = tree.width * tree.height
        var shapesArea = 0
        for (i in tree.numberOfPresents.indices) {
            val shape = shapes[i]
            val count = tree.numberOfPresents[i]
            shapesArea += count * shape.sumOf { line ->
                line.count { it }
            }
        }
        if (shapesArea <= totalArea) {
            canFitCounter++
        }
    }
    println(canFitCounter)
}

class Tree(
    val width: Int,
    val height: Int,
    val numberOfPresents: IntArray,
)
