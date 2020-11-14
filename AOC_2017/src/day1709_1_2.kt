//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*



fun main(args: Array<String>) {
	var checksum1: Int = 0
	var checksum2: Int = 0

	var puzzle_input: String = ""

	// einlesen puzzle_input  --> puzzle_input 16175
	File("day1709_puzzle_input.txt").forEachLine {
		puzzle_input = it
	}
	println("   puzzle_input ${puzzle_input.length}")

	// count garbage
	checksum2 = count_garbage(puzzle_input)
	
	
	// removal of garbage   --> after cleaning: 3339
	puzzle_input = remove_garbage(puzzle_input)
	
	println("   after cleaning: ${puzzle_input.length}")
	// count scores of groups

	var level: Int = 0
	
	puzzle_input.forEach {
		if (it.equals('{')) {
			level = level + 1
		} else if(it.equals('}')) {
			checksum1 = checksum1 + level	
			level = level - 1		
		}
	}
	

// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   The total score for all groups is $checksum1")

	// 5879 ist falsch
	println()
	println("******************")
	println("Solution for part2")
	println("   $checksum2 non-canceled characters are within the garbage")
	println()

}

fun count_garbage (fc_input: String): Int{   // removal of garbage
		
	var GarbageFound: Boolean = false
	var index_start: Int = 0
	var index_start_found: Boolean = false
	var index_end: Int = 0
	var i: Int = 0
	var GarbageClean: Boolean = false
	var input: String
	var checksum: Int = 0
	var count_exclamation: Int = 0
	
	input = fc_input

	// wiederhole solange bis kein Garbage mehr gefunden wird		
	while (!GarbageClean) {
		while (!GarbageFound) {
			if (!index_start_found && input[i].equals('<')) {
				index_start = i
				index_start_found = true
			}
			if (index_start_found && input[i].equals('>')) {
				index_end = i
				GarbageFound = true
			} else if (index_start_found && input[i].equals('!')) {
				i = i + 1
				count_exclamation = count_exclamation +1
			}
			i = i + 1
			if (i > input.length - 1) {
				GarbageClean = true
				break
			}
		}

		if (GarbageFound == true) {
			input = input.removeRange(index_start, index_end + 1)
			checksum = checksum + (index_end-(index_start+1)) - 2*  count_exclamation
		}
//		println("index_start: $index_start, index_end: $index_end")
//		println("puzzle_input: $input")
		i = 0
		index_start = 0
		index_start_found = false
		GarbageFound = false
		index_end = 0
		count_exclamation = 0
	}
	
	return checksum
} 

fun remove_garbage (fc_input: String): String{   // removal of garbage
		
	var GarbageFound: Boolean = false
	var index_start: Int = 0
	var index_start_found: Boolean = false
	var index_end: Int = 0
	var i: Int = 0
	var GarbageClean: Boolean = false
	var input: String
	
	input = fc_input

	// wiederhole solange bis kein Garbage mehr gefunden wird		
	while (!GarbageClean) {
		while (!GarbageFound) {
			if (!index_start_found && input[i].equals('<')) {
				index_start = i
				index_start_found = true
			}
			if (index_start_found && input[i].equals('>')) {
				index_end = i
				GarbageFound = true
			} else if (index_start_found && input[i].equals('!')) {
				i = i + 1
			}
			i = i + 1
			if (i > input.length - 1) {
				GarbageClean = true
				break
			}
		}

		if (GarbageFound == true) {
			input = input.removeRange(index_start, index_end + 1)
		}
//		println("index_start: $index_start, index_end: $index_end")
//		println("puzzle_input: $input")
		i = 0
		index_start = 0
		index_start_found = false
		GarbageFound = false
		index_end = 0
	}
	
	return input
} 