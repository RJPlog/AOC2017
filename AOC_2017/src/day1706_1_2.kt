//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {
	var checksum1: Int = 1
	var checksum2: Int = 0

//	var listB = mutableListOf<Int>(0, 2, 7, 0)
	var listB = mutableListOf<Int>(4, 1, 15, 12, 0, 9, 9, 5, 5, 8, 7, 3, 14, 5, 12, 3)
	var listMax: Int = 0
	var listIndex: Int = 0
	var listB_string: String = ""
	var listA = mutableMapOf<String, Int>()
	var GameEnd = false


// wiederhole bis doppelt
//	for (j in 0..5) {
	while (!GameEnd) {
		listMax = listB.max()!!
		listIndex = listB.indexOfFirst { it == listMax }

//		println(" listMax: $listMax")
//		println(" listIndex: $listIndex")

		listB[listIndex] = 0

		while (listMax > 0) {
			listB_string = ""
			listIndex = (listIndex + 1) % listB.size
			listB[listIndex] = listB[listIndex] + 1
			listMax = listMax - 1
		}
		for (i in 0..listB.size - 1) {
			listB_string = listB_string + listB[i].toString() + "-"
		}
		if (listA.containsKey(listB_string)) {
			GameEnd = true
			checksum2 = listA.getValue(listB_string)
		} else {
			listA.put(listB_string, checksum1)
		}
		checksum1 = checksum1 + 1

	//	println("ListB nach einem Turn: $listA, GameEnd $GameEnd")
	}

	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   ${checksum1-1} redistribution cycles must be completed")
	println()


	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   ${checksum1 -1 - checksum2} steps does it take to reach the exit")
	println()

}
