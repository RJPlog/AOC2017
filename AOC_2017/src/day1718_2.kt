//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var checksum1: String = ""
	var checksum2: String = ""


	var register_a = mutableMapOf<String, Long>()
	var melodie_a = mutableListOf<Long>()
	register_a.put("a", 0)
	register_a.put("b", 0)
	register_a.put("f", 0)
	register_a.put("i", 0)
	register_a.put("p", 0)
	register_a.put("t", 1)
	val ProgramCode_a = mutableListOf<String>()
	var ProgramCount_a: Int = 0
	var lastsound_a: Long = 0
	var wait_a: Boolean = false
	var rcvcount_a: Int = 0

	var register_b = mutableMapOf<String, Long>()
	var melodie_b = mutableListOf<Long>()
	register_b.put("a", 0)
	register_b.put("b", 0)
	register_b.put("f", 0)
	register_b.put("i", 0)
	register_b.put("p", 1)
	register_b.put("t", 1)
	val ProgramCode_b = mutableListOf<String>()
	var ProgramCount_b: Int = 0
	var lastsound_b: Long = 0
	var wait_b: Boolean = false
	var rcvcount_b: Int = 0


	// Part 1: Read ProgramCode_a
	File("day1718_puzzle_input.txt").forEachLine {
		ProgramCode_a.add(it)
		ProgramCode_b.add(it)
	}
	//for(k in 0..10) {
	while (!wait_a || !wait_b) { // schleife über beide Programme
		wait_a = false
		wait_b = false

		// *************************************************************
		// Run Program A
		// *************************************************************
		//for (l in 1..10) {
		while (!wait_a) {
			val instruction_a = ProgramCode_a[ProgramCount_a].split(" ")
			// print("ProgramCount_a: $ProgramCount_a, instruction_a: $instruction_a  ---  ")
			if (instruction_a[0] == "snd") {   //snd ist ok
				wait_b = false
				if (instruction_a[1].toLongOrNull() != null) {
					melodie_a.add(instruction_a[1].toLong())
				} else {
					melodie_a.add(register_a.getValue(instruction_a[1]))
				}
				ProgramCount_a = ProgramCount_a + 1
			} else if (instruction_a[0] == "set") {   //set ist ok
				if (instruction_a[2].toLongOrNull() != null) {
					register_a.put(instruction_a[1], instruction_a[2].toLong())
				} else {
					register_a.put(instruction_a[1], register_a.getValue(instruction_a[2]))
				}
				ProgramCount_a = ProgramCount_a + 1
			} else if (instruction_a[0] == "add") {   //add ist ok
				if (instruction_a[2].toLongOrNull() != null) {
					register_a.put(instruction_a[1], register_a.getValue(instruction_a[1]) + instruction_a[2].toLong())
				} else {
					register_a.put(
						instruction_a[1],
						register_a.getValue(instruction_a[1]) + register_a.getValue(instruction_a[2])
					)
				}
				ProgramCount_a = ProgramCount_a + 1
			} else if (instruction_a[0] == "mul") {   //mul ist ok
				if (instruction_a[2].toLongOrNull() != null) {
					register_a.put(instruction_a[1], register_a.getValue(instruction_a[1]) * instruction_a[2].toLong())
				} else {
					register_a.put(
						instruction_a[1],
						register_a.getValue(instruction_a[1]) * register_a.getValue(instruction_a[2])
					)
				}
				ProgramCount_a = ProgramCount_a + 1
			} else if (instruction_a[0] == "mod") {   //mod ist ok, achtung, rest ergibt keine neg.
				if (instruction_a[2].toLongOrNull() != null) {
					register_a.put(
						instruction_a[1],
						register_a.getValue(instruction_a[1]).rem(instruction_a[2].toLong())
					)
				} else {
					register_a.put(
						instruction_a[1],
						register_a.getValue(instruction_a[1]).rem(register_a.getValue(instruction_a[2]))
					)
				}
				ProgramCount_a = ProgramCount_a + 1
			} else if (instruction_a[0] == "rcv") {   //rcv ist ok
					//if (instruction_a[1].toLong() > 0 || instruction_a[1].toLong() < 0) {
					// falls melodie_b mehr werte enhält als bereits empfangen ;-)
					if (melodie_b.size > rcvcount_a) {
						lastsound_a = melodie_b[rcvcount_a]
						register_a.put(instruction_a[1], melodie_b[rcvcount_a])
						rcvcount_a++
						ProgramCount_a = ProgramCount_a + 1
					} else {
						wait_a = true  // dient um das Programm hier zu beenden
					}
					//}
				 // ProgramCount_a = ProgramCount_a + 1
			} else if (instruction_a[0] == "jgz") {   //jgz


				if (register_a.getValue(instruction_a[1]) > 0) {
					if (instruction_a[2].toLongOrNull() != null) {
						ProgramCount_a = ProgramCount_a + instruction_a[2].toInt()
					} else {
						ProgramCount_a = ProgramCount_a + register_a.getValue(instruction_a[2]).toInt()
					}
				} else {
					ProgramCount_a++
				}


			}
			//	print("   register_a: $register_a |")
			//	print("   melodie_a: $melodie_a | ")
			//	print("   lastsount: $lastsound_a ")
			//	println("wait_a: $wait_a, melodie_b.size ${melodie_b.size}, rcvcount_a $rcvcount_a")
		}

		//print("wait_a: $wait_a,   ")	

		// **********************************************************************************************	
		// Run Program B
		// **********************************************************************************************

		while (!wait_b  && ProgramCount_b < ProgramCode_b.size) {

			val instruction_b = ProgramCode_b[ProgramCount_b].split(" ")

			// print("ProgramCount_b: $ProgramCount_b, instruction_b: $instruction_b  ---  ")
			if (instruction_b[0] == "snd") {   //snd ist ok
				wait_a = false
				if (instruction_b[1].toLongOrNull() != null) {
					melodie_b.add(instruction_b[1].toLong())
				} else {
					melodie_b.add(register_b.getValue(instruction_b[1]))
				}
				ProgramCount_b = ProgramCount_b + 1
			} else if (instruction_b[0] == "set") {   //set ist ok
				if (instruction_b[2].toLongOrNull() != null) {
					register_b.put(instruction_b[1], instruction_b[2].toLong())
				} else {
					register_b.put(instruction_b[1], register_b.getValue(instruction_b[2]))
				}
				ProgramCount_b = ProgramCount_b + 1
			} else if (instruction_b[0] == "add") {   //add ist ok
				if (instruction_b[2].toLongOrNull() != null) {
					register_b.put(instruction_b[1], register_b.getValue(instruction_b[1]) + instruction_b[2].toLong())
				} else {
					register_b.put(
						instruction_b[1],
						register_b.getValue(instruction_b[1]) + register_b.getValue(instruction_b[2])
					)
				}
				ProgramCount_b = ProgramCount_b + 1
			} else if (instruction_b[0] == "mul") {   //mul ist ok
				if (instruction_b[2].toLongOrNull() != null) {
					register_b.put(instruction_b[1], register_b.getValue(instruction_b[1]) * instruction_b[2].toLong())
				} else {
					register_b.put(
						instruction_b[1],
						register_b.getValue(instruction_b[1]) * register_b.getValue(instruction_b[2])
					)
				}
				ProgramCount_b = ProgramCount_b + 1
			} else if (instruction_b[0] == "mod") {   //mod ist ok, achtung, rest ergibt keine neg.
				if (instruction_b[2].toLongOrNull() != null) {
					register_b.put(
						instruction_b[1],
						register_b.getValue(instruction_b[1]).rem(instruction_b[2].toLong())
					)
				} else {
					register_b.put(
						instruction_b[1],
						register_b.getValue(instruction_b[1]).rem(register_b.getValue(instruction_b[2]))
					)
				}
				ProgramCount_b = ProgramCount_b + 1
			} else if (instruction_b[0] == "rcv") {   //rcv ist ok
					//if (instruction_b[1].toLong() > 0 || instruction_b[1].toLong() < 0) {
					// falls melodie_b mehr werte enhält als bereits empfangen ;-)
					if (melodie_a.size > rcvcount_b) {
						lastsound_b = melodie_a[rcvcount_b]
						register_b.put(instruction_b[1], melodie_a[rcvcount_b])
						rcvcount_b++
						ProgramCount_b = ProgramCount_b + 1
					} else {
						wait_b = true  // dient um das Programm hier zu beenden
					}
					//}

				// ProgramCount_b = ProgramCount_b + 1
			} else if (instruction_b[0] == "jgz") {   //jgz

				if (register_b.getValue(instruction_b[1]) > 0) {
					if (instruction_b[2].toLongOrNull() != null) {
						ProgramCount_b = ProgramCount_b + instruction_b[2].toInt()
					} else {
						ProgramCount_b = ProgramCount_b + register_b.getValue(instruction_b[2]).toInt()
					}
				} else {
					ProgramCount_b++
				}
			}
			//	print("   register_b: $register_b |")
			//	print("   melodie_b: $melodie_b | ")
			//	println("   lastsount: $lastsound_b ")
			if (ProgramCount_b > ProgramCode_b.size -1)  {
				wait_b=true
			}
		}

	//	println("melodie_a: $melodie_a | wait_a: $wait_a")
	//	println("melodie_b: $melodie_b | wait_b: $wait_b")
	//	println()
	}  // End schleife über beide Programme


	//print("   register_a: $register_a |")
	//print("   melodie_a: $melodie_a | ")
	//println("   lastsount: $lastsound_a ")


	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   Program 1 send ${melodie_b.size} times a value")
	println()

}
