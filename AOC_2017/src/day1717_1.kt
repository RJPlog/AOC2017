//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var checksum1: String = ""
	var checksum2: String = ""

	var buffer = mutableMapOf<Int, Int>()
	var step: Int = 380
	var position: Int = 0
	var newindex: Int = 0
	var detectnext: Boolean= false
	var count: Int = 2

	buffer.put(0, 0)

	println(1 % 3)

	for (i in 1..2017) {
		newindex = (step + newindex) % buffer.size + 1

		//println("Newindex: $newindex, ")
		// filtere Map jetzt nach Values >= Index, erhöhe dann alle Values um eins
		if (newindex == buffer.size) {
			//println("   hinten anhängen")
			buffer.put(i, newindex)
		} else {
			//println("  mitten anhängen")
			var preparebuffer = buffer.filterValues { it >= newindex }
			//println("    preparebuffer: $preparebuffer")
			preparebuffer.forEach {
				//println("x")
				buffer.put(it.key, it.value + 1)
			}

			buffer.put(i, newindex)
		}

		//print("i: $i   ")
		for (k in 0..buffer.size - 1) {
			val keys = buffer.filterValues { it == k }.keys
			if(keys.contains(2017)) {
				detectnext = true
			}
			if (detectnext) {
			print("$keys ")
			count = count -1
				if (count == 0) {
					detectnext = false
				}
			}

		}
		//println()
	}


	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("  The value after 2017 is $checksum1")
	println()

	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   The programs standing after their billion dance is : $checksum2")
	println()

}
