import com.google.ortools.Loader
import com.google.ortools.linearsolver.MPSolver
import com.google.ortools.linearsolver.MPVariable
import java.io.File

// https://adventofcode.com/2025/day/10#part2
// https://developers.google.com/optimization/install/java
fun main() {
    Loader.loadNativeLibraries()
    val input = File("input.txt").readText()
    val machines: List<Machine> = input.lines().map { line ->
        Machine(
            joltage = line.substringAfter("{").dropLast(1)
                .split(",")
                .map { char ->
                    char.toInt()
                }
                .toIntArray(),
            buttons = line.substringAfter("] ").substringBefore(" {")
                .split(" ")
                .map { button ->
                    button
                        .substring(1, button.length - 1)
                        .split(",").map { it -> it.toInt() }
                        .toIntArray()
                }
        )
    }
    var totalPresses = 0
    for (machine in machines) {
        val solver = MPSolver.createSolver("CP-SAT")
        // Create variables that the solver can change
        val buttonVariables: List<MPVariable> = machine.buttons.map { button ->
            solver.makeIntVar(0.0, Int.MAX_VALUE.toDouble(), button.joinToString(","))
        }
        // Set constraints for the solver to satisfy
        for (j in machine.joltage.indices) {
            val targetJoltage = machine.joltage[j].toDouble()
            val constraint = solver.makeConstraint(targetJoltage, targetJoltage)
            for (buttonIndex in machine.buttons.indices) {
                val button = machine.buttons[buttonIndex]
                if (button.contains(j)) {
                    val buttonVariable = buttonVariables[buttonIndex]
                    constraint.setCoefficient(buttonVariable, 1.0)
                }
            }
        }
        // Minimize the total number of button presses
        val objective = solver.objective()
        for (buttonVariable in buttonVariables) {
            objective.setCoefficient(buttonVariable, 1.0)
        }
        objective.setMinimization()
        val resultStatus = solver.solve()
        if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
            totalPresses += buttonVariables.sumOf { it.solutionValue().toInt() }
        }
    }
    println("output: $totalPresses")
}

class Machine(val joltage: IntArray, val buttons: List<IntArray>)
