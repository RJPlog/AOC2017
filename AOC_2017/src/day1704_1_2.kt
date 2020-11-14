//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {
	var MapA = mutableMapOf<String, Int>()
	var MapB = mutableMapOf<String, Int>()
	var checksum1: Int = 0
	var checksum2: Int = 0
	var DoNotCount: Boolean = false

	File("day1704_puzzle_input.txt").forEachLine {
		var line = it.split(" ")
		MapA.clear()
		DoNotCount = false
		line.forEach{
			if(MapA.containsKey(it)) {
				DoNotCount = true
			} else {
				MapA.put(it, 1)
			}
		}
		if (!DoNotCount) {
			checksum1= checksum1 +1
		}
	}
	
		File("day1704_puzzle_input.txt").forEachLine {
		var line = it.split(" ")
		MapB.clear()
		DoNotCount = false
		line.forEach{

//			println(it.toCharArray().sorted().joinToString("")) // .sort().toString())
			if(MapB.containsKey(it.toCharArray().sorted().joinToString(""))) {
				DoNotCount = true
			} else {
				MapB.put(it.toCharArray().sorted().joinToString(""), 1)
			}
		}
		if (!DoNotCount) {
			checksum2= checksum2 +1
		}
	}

	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   $checksum1 passphrases are valid")
	println()


	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   $checksum2 passphrases are valid")
	println()

}
