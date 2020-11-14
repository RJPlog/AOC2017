//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*


fun main(args: Array<String>) {
	var checksum1: Int = 0
	var checksum2: Int = 0
	var skip_size: Int = 0
	var puzzle_input: String = ""
	var input_length = mutableListOf<Int>()
//	var input_length = listOf<Int>(102,255,99,252,200,24,219,57,103,2,226,254,1,0,69,216)
	var j: Int = 0
	var cur_pos = 0

	var hash_input = mutableListOf<Int>()
	var hash_output = mutableListOf<Int>()
	// einlesen puzzle_input  --> puzzle_input 16175

	File("day1710_puzzle_input.txt").forEachLine {
		puzzle_input = it
	}
	puzzle_input.forEach {
		input_length.add(it.toChar().toInt())
	}
	input_length.add(17)
	input_length.add(31)
	input_length.add(73)
	input_length.add(47)
	input_length.add(23)

	println("Translated Input: $input_length")
	// 49,44,50,44,51,17,31,73,47,23

	var test = 65 xor 27 xor 9 xor 1 xor 4 xor 3 xor 40 xor 50 xor 91 xor 7 xor 6 xor 0 xor 2 xor 5 xor 68 xor 22
	println("xor $test")
	
//	for (i in 0..4) {
	for(i in 0..255) {
		hash_input.add(i)
		hash_output.add(i)
	}
//	println("   hash_input ${hash_input}")
//		println("   hash_input ${hash_input.size}")
//			println("   hash_input ${0 % hash_input.size}")
//			println("   hash_input ${1 % hash_input.size}")
//			println("   hash_input ${2 % hash_input.size}")
//			println("   hash_input ${3 % hash_input.size}")
//			println("   hash_input ${4 % hash_input.size}")
//			println("   hash_input ${5 % hash_input.size}")


	for (n in 0..63) {
		for (m in 0..input_length.size - 1) {
			// reverse elements
	//		println("Step $m")
			for (k in 1..input_length[j]) {
				//	println(" index: ${(cur_pos + k - 1) % hash_input.size}, index2: ${(input_length[j] - k + cur_pos) % hash_input.size}")
				hash_output[(cur_pos + k - 1) % hash_input.size] =
					hash_input[(input_length[j] - k + cur_pos) % hash_input.size]
			}
			for (k in input_length[j]..hash_input.size - 1) {
				//	println(" index: ${(cur_pos + k) % hash_input.size}, index2: ${(k + cur_pos) % hash_input.size}")
				hash_output[(cur_pos + k) % hash_input.size] = hash_input[(k + cur_pos) % hash_input.size]
			}
			// erhöhe Skipwert
			cur_pos = (cur_pos + input_length[j] + skip_size) % hash_input.size
			skip_size = skip_size + 1
//			println("   hash_output ${hash_output}, cur_pos: $cur_pos, skip_size: $skip_size")
			hash_input.clear()
			hash_output.forEach {
				hash_input.add(it)
			}
			j = (j + 1) % input_length.size
		}
		j = 0
	}

var value: Int = 0
	var dens_hash: String = ""
for (k in 0..15){	
 for(l in 0..15) {
		value = value xor hash_output[l+k*16]
	}
//	println(value.toString(16))
	dens_hash= dens_hash + value.toString(16).padStart(2,'0')
value = 0
}
	
	// 44f4befbf303cbafd085f97741d51d ist falsch

	println()
	println("******************")
	println("Solution for part2")
	println("   -$dens_hash-")
		println(dens_hash.length)
	println()

}