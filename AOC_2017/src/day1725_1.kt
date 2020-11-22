import java.io.File
import kotlin.math.*


fun main(args: Array<String>) {

	var checksum1: Int = 0
	var tape = mutableMapOf<Int, Int>()
	var position: Int = 0
	var state: String = "A"


	for (i in 1..12964419) {

		if (!tape.containsKey(position)) {
			tape.put(position, 0)
		}

		if (state == "A") {
			if (tape.getValue(position) == 0) {
				tape.put(position, 1)
				position++
				state = "B"
			} else if (tape.getValue(position) == 1) {
				tape.put(position, 0)
				position++
				state = "F"
			}
		} else if (state == "B") {
			if (tape.getValue(position) == 0) {
				tape.put(position, 0)
				position--
				state = "B"
			} else if (tape.getValue(position) == 1) {
				tape.put(position, 1)
				position--
				state = "C"
			}
		} else if (state == "C") {
			if (tape.getValue(position) == 0) {
				tape.put(position, 1)
				position--
				state = "D"
			} else if (tape.getValue(position) == 1) {
				tape.put(position, 0)
				position++
				state = "C"
			}
		} else if (state == "D") {
			if (tape.getValue(position) == 0) {
				tape.put(position, 1)
				position--
				state = "E"
			} else if (tape.getValue(position) == 1) {
				tape.put(position, 1)
				position++
				state = "A"
			}
		} else if (state == "E") {
			if (tape.getValue(position) == 0) {
				tape.put(position, 1)
				position--
				state = "F"
			} else if (tape.getValue(position) == 1) {
				tape.put(position, 0)
				position--
				state = "D"
			}
		} else if (state == "F") {
			if (tape.getValue(position) == 0) {
				tape.put(position, 1)
				position++
				state = "A"
			} else if (tape.getValue(position) == 1) {
				tape.put(position, 0)
				position--
				state = "E"
			}
		}

	}

	tape.forEach{
		checksum1 = checksum1 + it.value
	}
	
	//println(tape)

	// Ausgabe der Lösung für Part 1
	//1994527 to high
	println()
	println("******************")
	println("Solution for part1")
	println("    $checksum1 is the diagnostic checksum")
	println()


}