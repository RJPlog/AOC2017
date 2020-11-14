//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var checksum1: String = ""
	var checksum2: String = ""

	var programs: String = "abcdefghijklmnop"

	println("puzzle input:   $programs")
	
	// das muster wiederholt sich alle 42 durchläufe. der Rest von  1 billion geteilt durch 42 ergibt 34, d.h. man muss nur 34 durchläufe machen, alles andere dauert zu lange.
	println(1000000000.rem(42))

	for (i in 1..34) {
		File("day1716_puzzle_input.txt").forEachLine {
			var instruction = it.split(",")
			instruction.forEach {
				//	print(it)
				if (it[0] == 's') {
					var hilf1 = programs.dropLast(it.drop(1).toInt())
					var hilf2 = programs.takeLast(it.drop(1).toInt())
					programs = hilf2 + hilf1
				} else if (it[0] == 'x') {
					var helpx = it.drop(1).split("/")
					var hilfx1 = programs[helpx[0].toInt()]
					var hilfx2 = programs[helpx[1].toInt()]
					//println("  hilf3 $hilf3  hilf4 $hilf4 ")
					programs = programs.replace(hilfx1, 'x')
					programs = programs.replace(hilfx2, hilfx1)
					programs = programs.replace('x', hilfx2)

				} else if (it[0] == 'p') {
					var helpp = it.drop(1).split("/")
					var hilfp1 = helpp[0]
					var hilfp2 = helpp[1]
					programs = programs.replace(hilfp1, "x")
					programs = programs.replace(hilfp2, hilfp1)
					programs = programs.replace("x", hilfp2)

				}

			}
		}
		if (programs == "abcdefghijklmnop") {
			println("wiederholung bei $i")
		}
		if (i % 1000 == 0) {
			println("   ${i / 1000} x 1.000")
		}
		if (i == 1) {
			checksum1 = programs
		}
	}
	checksum2 = programs

	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("  The programs standing after their dance is $checksum1")
	println()

	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   The programs standing after their billion dance is : $checksum2")
	println()

}
