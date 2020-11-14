//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*


fun main(args: Array<String>) {
	var checksum1: Int = 0
	var checksum2: Int = 0
	var numbers = mutableMapOf<String, Int>()
	var group_c = mutableMapOf<String, Int>()
	var group_com = mutableMapOf<Int, Int>()
//	group_c.put("0", 0)
	var groupcount: Int = 0

//	println("   group_c: $group_c")

	var EvalEnd: Boolean = false


// part 1
	for (k in 0..1999) { // Große Schleife über alle Gruppen
		if (!numbers.containsKey(k.toString())) {
			group_c.clear()
			group_c.put(k.toString(), groupcount)
			numbers.put(k.toString(), groupcount)
//			println("* $k $group_c")
			EvalEnd = false
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
								numbers.put(it, groupcount)
//						println("   group_c updated: $group_c")
							}
						}
					}
				}
			}
//	println(group_c)
			if (group_c.size == checksum1) {
				EvalEnd = true
				group_com.put(groupcount, group_c.size)
				groupcount = groupcount + 1
			} else {
				checksum1 = group_c.size
			}
		}
		} //End if group.c contains key
	} // End große Schleife über alle Gruppen.	

	
println(numbers)
	println(numbers.size)	
println(group_com)
	checksum2 = group_com.size

	// part 2



// 179, 180 ist falsch
	println()
	println("******************")
	println("Solution for part2")
	println("   $checksum2 groups are there in total")
	println()

}