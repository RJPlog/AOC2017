//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*



fun main(args: Array<String>) {
	var checksum1: Int = 0
	var checksum2: Int = 0
	var skip_size: Int = 0
//	var input_length = listOf<Int>(3, 4, 1, 5)
	var input_length = listOf<Int>(102,255,99,252,200,24,219,57,103,2,226,254,1,0,69,216)
	var j: Int = 0
	var cur_pos = 0

	var hash_input= mutableListOf<Int>()
	var hash_output= mutableListOf<Int>()
	// einlesen puzzle_input  --> puzzle_input 16175
//	for(i in 0..4) {
	for(i in 0..255) {
		hash_input.add(i)
		hash_output.add(i)
	}
	println("   hash_input ${hash_input}")
//		println("   hash_input ${hash_input.size}")
//			println("   hash_input ${0 % hash_input.size}")
//			println("   hash_input ${1 % hash_input.size}")
//			println("   hash_input ${2 % hash_input.size}")
//			println("   hash_input ${3 % hash_input.size}")
//			println("   hash_input ${4 % hash_input.size}")
//			println("   hash_input ${5 % hash_input.size}")
	
	

for (m in 0..input_length.size-1) {	
	// reverse elements
	println("Step $m")
	for (k in 1..input_length[j] ) {
		println (" index: ${(cur_pos + k - 1) % hash_input.size}, index2: ${(input_length[j] - k + cur_pos) % hash_input.size}")		
		hash_output[(cur_pos + k - 1) % hash_input.size] = hash_input[(input_length[j] - k + cur_pos) % hash_input.size]
	}
	for (k in input_length[j] .. hash_input.size-1 ) {
		println (" index: ${(cur_pos + k ) % hash_input.size}, index2: ${(k + cur_pos) % hash_input.size}")
		hash_output[(cur_pos + k ) % hash_input.size] =  hash_input[(k + cur_pos) % hash_input.size ]
	}
	// erhöhe Skipwert
	cur_pos = (cur_pos + input_length[j] + skip_size) % hash_input.size
	skip_size = skip_size + 1		
	println("   hash_output ${hash_output}, cur_pos: $cur_pos, skip_size: $skip_size")
	hash_input.clear()
	hash_output.forEach  {
		hash_input.add(it)
	}
	j = (j +1) % input_length.size
}
	
	checksum1 = hash_output[0]*hash_output[1]
	
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