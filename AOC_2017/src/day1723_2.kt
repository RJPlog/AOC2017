//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var checksum1: Int = 0

	var count: Int = 0

	for (i in 108100..(108100+17*1000) step 17) {
		//println("Schleife $count, b = $i")
		for (j in 2..i-1) {
			if (i % j == 0) {
				checksum1 = checksum1 + 1 
				println("   Division ist ganzzahlig für $i / $j, checksum1 = $checksum1")
				break
			}
		}
	}

	// Ausgabe der Lösung für Part 2
	// 6241, 1, -6241 , 948wrong
	println()
	println("******************")
	println("Solution for part2")
	println("    $checksum1 is left in register h")
	println()

}
