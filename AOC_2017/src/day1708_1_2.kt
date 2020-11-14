//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {
	var checksum1: Int = 0
	var checksum2: Int = 0

	var register = mutableMapOf<String, Int>()

	// initalisieren der Register
	File("day1708_puzzle_input.txt").forEachLine {
		var instruction = it.split(" ")
		register.put(instruction[0], 0)
	}

	var sign: Int = 0
	var value: Int = 0

	// berechnen der Register
	File("day1708_puzzle_input.txt").forEachLine {
		var instruction = it.split(" ")
		if (instruction[1] == "inc") {
			sign = 1
		} else if (instruction[1] == "dec") {
			sign = -1
		}
		if (instruction[5].equals(">")) {
			if (register.getValue(instruction[4]) > instruction[6].toString().toInt()) {  //b inc 5 if a > 1
				value = register.getValue(instruction[0]) + sign * instruction[2].toString().toInt()
				register.put(instruction[0], value)
				if (value > checksum2) {
					checksum2 = value
				}
			}
		} else if (instruction[5] == ">=") {
			if (register.getValue(instruction[4]) >= instruction[6].toString().toInt()) {  //b inc 5 if a > 1
				value = register.getValue(instruction[0]) + sign * instruction[2].toString().toInt()
				register.put(instruction[0], value)
				if (value > checksum2) {
					checksum2 = value
				}
			}
		} else if (instruction[5] == "<") {
			if (register.getValue(instruction[4]) < instruction[6].toString().toInt()) {  //b inc 5 if a > 1
				value = register.getValue(instruction[0]) + sign * instruction[2].toString().toInt()
				register.put(instruction[0], value)
				if (value > checksum2) {
					checksum2 = value
				}
			}
		} else if (instruction[5] == "<=") {
			if (register.getValue(instruction[4]) <= instruction[6].toString().toInt()) {  //b inc 5 if a > 1
					value = register.getValue(instruction[0]) + sign * instruction[2].toString().toInt()
				register.put(instruction[0], value)
				if (value > checksum2) {
					checksum2 = value
				}
			}
		} else if (instruction[5] == "==") {
			if (register.getValue(instruction[4]) == instruction[6].toString().toInt()) {  //b inc 5 if a > 1
				value = register.getValue(instruction[0]) + sign * instruction[2].toString().toInt()
				register.put(instruction[0], value)
				if (value > checksum2) {
					checksum2 = value
				}
			}
		} else if (instruction[5] == "!=") {
			if (register.getValue(instruction[4]) != instruction[6].toString().toInt()) {  //b inc 5 if a > 1
				value = register.getValue(instruction[0]) + sign * instruction[2].toString().toInt()
				register.put(instruction[0], value)
				if (value > checksum2) {
					checksum2 = value
				}
			}
		} else {
			println("missing instructio: ${instruction[5]}")
		}
	}

	register.values.forEach {
		if (it > checksum1) {
			checksum1 = it
		}
	}


// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   The largest value in any register is $checksum1")


	println()
	println("******************")
	println("Solution for part2")
	println("   The largest value inbetween in any register is $checksum2")
	println()

}