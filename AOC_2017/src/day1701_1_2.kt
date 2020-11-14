//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var chapta: Int = 0


	File("day1701_puzzle_input.txt").forEachLine {
		var input_string = it
		chapta = 0
		for (i in 0..input_string.length - 1) {
			//println("i, $i,  i+1 ${(i+1) % input_string.length}}")
			if (input_string[i] == input_string[(i + 1) % input_string.length]) {
				chapta = chapta + input_string[i].toString().toInt()
			}
		}
		println(input_string)
		println("Chapta: $chapta")
	}

	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   The solution to your captcha: $chapta")
	println()


	File("day1701_puzzle_input.txt").forEachLine {
		var input_string = it
		chapta = 0
		for (i in 0..input_string.length - 1) {
			//println("i, $i,  i+1 ${(i+1) % input_string.length}}, i+ input_string.length / 2: ${(i + (input_string.length) /2) % input_string.length}")
			if (input_string[i] == input_string[(i + (input_string.length) / 2) % input_string.length]) {
				chapta = chapta + input_string[i].toString().toInt()
			}
		}
		println(input_string)
		println("Chapta: $chapta")
	} 

	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   The solution to your captcha: $chapta")
	println()

}