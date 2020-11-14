//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var checksum1: Int = 0
	var checksum2: Int = 0

	var number_a: Long = 116  //65
	var number_b: Long = 299  //8921
	val mul_a: Long = 16807
	val mul_b: Long = 48271
	val divisor: Long = 2147483647
	var num_a_string: String = ""
	var num_b_string: String = ""

	for (i in 0..5000000-1) {

		while (true) {
			number_a = (number_a * mul_a).rem(divisor)
			if ((number_a % 4) < 1) {
				break
			}
		}

		while (true) {
		number_b = (number_b * mul_b).rem(divisor)
			if ((number_b % 8) < 1) {
				break
			}				
		}	

//		print("$number_a ${number_a % 4}  ")
//		println(number_b)

		num_a_string = number_a.toString(2).takeLast(16)
		num_b_string = number_b.toString(2).takeLast(16)

		if (num_a_string == num_b_string) {
			checksum2++
		}

		if (i % 10000 == 0) {
		println(i)
		}

	}


	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("  The judge's final count is $checksum1")
	println()

	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   The checksum is: $checksum2")
	println()

}
