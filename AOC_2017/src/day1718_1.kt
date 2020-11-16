//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var checksum1: String = ""
	var checksum2: String = ""


	var register = mutableMapOf<String, Long>()
	var melodie = mutableListOf<Long>()
	register.put("a", 0)
	register.put("b", 0)
	register.put("f", 0)
	register.put("i", 0)
	register.put("p", 0)

	val ProgramCode = mutableListOf<String>()

	var ProgramCount: Int = 0

	var lastsound: Long = 0

	// Part 1: Read ProgramCode
	File("day1718_puzzle_input.txt").forEachLine {

		ProgramCode.add(it)
	}


	// Part 1: Run Program
	while (ProgramCount < ProgramCode.size) {
//	while (ProgramCount < 15) {
		val instruction = ProgramCode[ProgramCount].split(" ")
		print("programcount: $ProgramCount, instruction: $instruction  ---  ")
		if (instruction[0] == "snd") {   //snd ist ok
			if (instruction[1].toLongOrNull() != null) {
				melodie.add(instruction[1].toLong())
			} else {
				melodie.add(register.getValue(instruction[1]))
			}
			ProgramCount = ProgramCount + 1
		} else if (instruction[0] == "set") {   //set ist ok
			if (instruction[2].toLongOrNull() != null) {
				register.put(instruction[1], instruction[2].toLong())
			} else {
				register.put(instruction[1], register.getValue(instruction[2]))
			}
			ProgramCount = ProgramCount + 1
		} else if (instruction[0] == "add") {   //add ist ok
			if (instruction[2].toLongOrNull() != null) {
				register.put(instruction[1], register.getValue(instruction[1]) + instruction[2].toLong())
			} else {
				register.put(instruction[1], register.getValue(instruction[1]) + register.getValue(instruction[2]))
			}
			ProgramCount = ProgramCount + 1
		} else if (instruction[0] == "mul") {   //mul ist ok
			if (instruction[2].toLongOrNull() != null) {
				register.put(instruction[1], register.getValue(instruction[1]) * instruction[2].toLong())
			} else {
				register.put(instruction[1], register.getValue(instruction[1]) * register.getValue(instruction[2]))
			}
			ProgramCount = ProgramCount + 1
		} else if (instruction[0] == "mod") {   //mod ist ok, achtung, rest ergibt keine neg.
			if (instruction[2].toLongOrNull() != null) {
				register.put(instruction[1], register.getValue(instruction[1]).rem(instruction[2].toLong()))
			} else {
				register.put(instruction[1], register.getValue(instruction[1]).rem(register.getValue(instruction[2])))
			}
			ProgramCount = ProgramCount + 1
		} else if (instruction[0] == "rcv") {   //rcv ist ok
			if (instruction[1].toLongOrNull() != null) {
				if (instruction[1].toLong() > 0 || instruction[1].toLong() < 0) {
					lastsound = melodie[melodie.size - 1]
					ProgramCount = ProgramCount + 1000  // dient um das Programm hier zu beenden
				}
			} else {
				if (register.getValue(instruction[1]) > 0 || register.getValue(instruction[1]) < 0) {
					lastsound = melodie[melodie.size - 1]
					ProgramCount = ProgramCount + 1000  // dient um das Programm hier zu beenden
				} 
			}
			ProgramCount = ProgramCount + 1
		} else if (instruction[0] == "jgz") {   //jgz
			if (register.getValue(instruction[1]) > 0) {
				if (instruction[2].toLongOrNull() != null) {
					ProgramCount = ProgramCount + instruction[2].toInt()
				} else {
					ProgramCount = ProgramCount + register.getValue(instruction[2]).toInt()
				}
			} else {
				ProgramCount++
			}

		}
		print("   register: $register |")
		print("   melodie: $melodie | ")
		println("   lastsount: $lastsound ")
	}

//	println("register: $register")
//	println("melodie: $melodie")
//	println("lastsount: $lastsound ")

	// Ausgabe der Lösung für Part 1
	// 7481 is to low
	println()
	println("******************")
	println("Solution for part1")
	println("  The value of the recovered frequency is $lastsound")
	println()

	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("    $checksum2")
	println()

}
