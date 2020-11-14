//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {
	var checksum1: Int = 0
	var checksum2: Int = 0

	var listA = mutableListOf<Int>()
	var progcount: Int = 0
	var hilf = 0


	File("day1705_puzzle_input.txt").forEachLine {
		listA.add(it.toInt())
	}

	while (progcount < listA.size || progcount < 0) {
		hilf = listA[progcount]
		listA[progcount] = listA[progcount] + 1
		progcount = progcount + hilf
		checksum1 = checksum1 + 1
	}

	listA.clear()
	hilf = 0
	var hilf2: Int = 0
	progcount = 0

	File("day1705_puzzle_input.txt").forEachLine {
		listA.add(it.toInt())
	}

	while (progcount < listA.size || progcount < 0) {
		hilf = listA[progcount]
		if (hilf > 2) {
			hilf2 = -1
		} else {
			hilf2 = 1
		}

		listA[progcount] = listA[progcount] + hilf2
		progcount = progcount + hilf
		checksum2 = checksum2 + 1
	}


	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   $checksum1 steps does it take to reach the exit")
	println()


	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   $checksum2 steps does it take to reach the exit")
	println()

}
