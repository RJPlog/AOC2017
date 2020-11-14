//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var checksum1: Int = 0
	var checksum2: Int = 0
	var row: String
	var counter: Int = 0
	var denominator: Int = 0

	File("day1702_puzzle_input.txt").forEachLine {
		row = it.replace("	", " ")  // take care, here are not spaces but tab's to replace, which you cannot see here
		var number = row.split(" ")
		var min: Int = 10000
		var max: Int = 0
		number.forEach {
		// part 1
			if (it.toString().toInt() < min) {
				min = it.toString().toInt()
			}
			if (it.toString().toInt() > max) {
				max = it.toString().toInt()
			}
			// part 2
			counter = it.toString().toInt()
			number.forEach {
				denominator = it.toString().toInt()
				if (counter % denominator == 0 && counter != denominator) {
					checksum2 = checksum2 + counter / denominator
//					println("checksum : $checksum2")
				}
			}
		}
		checksum1 = checksum1 + (max - min)
	}

	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   The checksum is: $checksum1")
	println()

		// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   The checksum is: $checksum2")
	println()

}
