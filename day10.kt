import java.io.File

// https://adventofcode.com/2025/day/10
fun main() {
    val input = File("input.txt").readText()
    val machines: List<Machine> = input.lines().map { line ->
        Machine(
            indicator = line.substringBefore("]").drop(1)
                .map { char ->
                    char == '#'
                }
                .toBooleanArray(),
            buttons = line.substringAfter("] ").substringBefore(" {")
                .split(" ")
                .map { button ->
                    button
                        .substring(1, button.length - 1)
                        .split(",").map { it.toInt() }
                        .toIntArray()
                }
        )
    }
    var totalPresses = 0
    for (machine in machines) {
        var presses = 0
        val queue = ArrayDeque<Node>()
        queue.add(
            Node(
                indicator = machine.indicator,
                buttons = machine.buttons,
                presses = 0,
            )
        )
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (node.indicator.none { it }) {
                presses = node.presses
                break
            }
            for (button in node.buttons) {
                val newIndicator = BooleanArray(node.indicator.size) { index ->
                    node.indicator[index] xor button.contains(index)
                }
                if (newIndicator.contentEquals(node.indicator)) {
                    continue
                }
                queue.add(
                    Node(
                        indicator = newIndicator,
                        presses = node.presses + 1,
                        buttons = machine.buttons,
                    )
                )
            }
        }
        totalPresses += presses
    }
    println("output: $totalPresses")
}

class Node(
    val indicator: BooleanArray,
    val buttons: List<IntArray>,
    val presses: Int,
)

class Machine(val indicator: BooleanArray, val buttons: List<IntArray>)
