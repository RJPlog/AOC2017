//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var checksum1: Int = 0
	var checksum2: String = ""
	
	var j:Int = 0

	var register = mutableMapOf<String, Long>()

	register.put("a", 0)
	register.put("b", 0)
	register.put("c", 0)
	register.put("d", 0)
	register.put("e", 0)
	register.put("f", 0)
	register.put("g", 0)
	register.put("h", 0)

	val ProgramCode = mutableListOf<String>()

	var ProgramCount: Int = 0


	// Part 1: Read ProgramCode
	File("day1723_puzzle_input.txt").forEachLine {

		ProgramCode.add(it)
	}


	// Part 1: Run Program
	while (ProgramCount < ProgramCode.size) {
//	while (j < 100) {
		val instruction = ProgramCode[ProgramCount].split(" ")
		if (ProgramCount == 25) {
			println("h = ${register.getValue("h")}")
		}
		//print("programcount: $ProgramCount, instruction: $instruction  ---  ")

		if (instruction[0] == "set") {   //set ist ok
			if (instruction[2].toLongOrNull() != null) {
				register.put(instruction[1], instruction[2].toLong())
			} else {
				register.put(instruction[1], register.getValue(instruction[2]))
			}
			ProgramCount = ProgramCount + 1
		} else if (instruction[0] == "sub") {   //sub ist ok
			if (instruction[2].toLongOrNull() != null) {
				register.put(instruction[1], register.getValue(instruction[1]) - instruction[2].toLong())
			} else {
				register.put(instruction[1], register.getValue(instruction[1]) - register.getValue(instruction[2]))
			}
			ProgramCount = ProgramCount + 1
		} else if (instruction[0] == "mul") {   //mul ist ok
			checksum1 = checksum1 + 1
			if (instruction[2].toLongOrNull() != null) {
				register.put(instruction[1], register.getValue(instruction[1]) * instruction[2].toLong())
			} else {
				register.put(instruction[1], register.getValue(instruction[1]) * register.getValue(instruction[2]))
			}
			ProgramCount = ProgramCount + 1
		} else if (instruction[0] == "jnz") {   //jgz
			if (instruction[1].toLongOrNull() != null) {
				if (instruction[1] != "0") {
					if (instruction[2].toLongOrNull() != null) {
						ProgramCount = ProgramCount + instruction[2].toInt()
					} else {
						ProgramCount = ProgramCount + register.getValue(instruction[2]).toInt()
					}
				} else {
									ProgramCount++
				}
			} else if (register.getValue(instruction[1]) > 0 || register.getValue(instruction[1]) < 0) {
				if (instruction[2].toLongOrNull() != null) {
					ProgramCount = ProgramCount + instruction[2].toInt()
				} else {
					ProgramCount = ProgramCount + register.getValue(instruction[2]).toInt()
				}
			} else {
				ProgramCount++
			}

		}
	//	println("register: $register,   ProgramCount $ProgramCount, $instruction")
j= j+1
	}

//	println("register: $register")
//	println("melodie: $melodie")
//	println("lastsount: $lastsound ")

	// Ausgabe der Lösung für Part 1
	// 6241
	println()
	println("******************")
	println("Solution for part1")
	println("  $checksum1 the mul was invoked")
	println()

	
	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("    $checksum2")
	println()

}
