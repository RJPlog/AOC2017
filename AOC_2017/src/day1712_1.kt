//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*


fun main(args: Array<String>) {
	var checksum1: Int = 0
	var checksum2: Int = 0
	var group_c = mutableMapOf<String, Int>()
	var group_com = mutableMapOf<Int, Int>()
	group_c.put("0", 0)
	var groupcount: Int = 0

//	println("   group_c: $group_c")

	var EvalEnd: Boolean = false


// part 1
	for (k in 0..30) { // Große Schleife über alle Gruppen
		while (!EvalEnd) {
			File("day1712_puzzle_input.txt").forEachLine {
				var line = it.replace(" <-> ", " ")
				line = line.replace(",", "")
				var group_instruction = line.split(" ")
//		println("group_instruction: $group_instruction")
				group_instruction.forEach {
					if (group_c.containsKey(it)) {
						group_instruction.forEach {
							if (!group_c.containsKey(it)) {
								group_c.put(it, groupcount)
//						println("   group_c updated: $group_c")
							}
						}
					}
				}
			}
//	println(group_c)
			if (group_c.size == checksum1) {
				EvalEnd = true
				group_com.put(0, group_c.size)
				groupcount = groupcount + 1
			} else {
				checksum1 = group_c.size
			}
		}

	} // End große Schleife über alle Gruppen.	

	checksum1 = group_c.size

	// part 2


	checksum1 = group_c.size

// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   $checksum1 programs are in the group that contains program ID 0")

// 5879 ist falsch
	println()
	println("******************")
	println("Solution for part2")
//	println("   The furthest steps he was away is $checksum2")
	println()

}