import java.io.File
import kotlin.math.sqrt

// https://adventofcode.com/2025/day/8#part2
fun main() {
    val input = File("input.txt").readText()
    val boxes = input.lines().map { line ->
        val (x, y, z) = line.split(",").map { it.toInt() }
        BoxPosition(x, y, z)
    }
    val circuits = mutableListOf<Set<Int>>()
    var minimumDistance = 0.0
    var lastXMultiplication: Long
    while (true) {
        var startIndex = 0
        var endIndex = 0
        var smallestDistance = Double.MAX_VALUE
        for (i in boxes.indices) {
            for (j in i + 1..boxes.lastIndex) {
                val distance = boxes[i].distanceTo(boxes[j])
                if (distance < smallestDistance && distance > minimumDistance) {
                    smallestDistance = distance
                    startIndex = i
                    endIndex = j
                }
            }
        }
        minimumDistance = smallestDistance
        val mergingCircuits = circuits.filter { circle ->
            circle.contains(startIndex) || circle.contains(endIndex)
        }
        circuits.removeAll(mergingCircuits)
        val newCircuit: Set<Int> = buildSet {
            addAll(mergingCircuits.flatten())
            add(startIndex)
            add(endIndex)
        }
        circuits.add(newCircuit)
        if (circuits[0].size == boxes.size) {
            lastXMultiplication = boxes[startIndex].x.toLong() * boxes[endIndex].x.toLong()
            break
        }
    }
    println("output: $lastXMultiplication")
}

class BoxPosition(
    val x: Int,
    val y: Int,
    val z: Int,
) {
    fun distanceTo(other: BoxPosition): Double {
        val dx = (this.x - other.x).toDouble()
        val dy = (this.y - other.y).toDouble()
        val dz = (this.z - other.z).toDouble()
        return sqrt(dx * dx + dy * dy + dz * dz)
    }
}
