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
	var detectnext: Boolean = true
	var count: Int = 2
	var value_one: Int = 0

	buffer.put(0, 0)

	println(1 % 3)

	for (i in 1..50000000) {
		newindex = (step + newindex) % i + 1
		//println("Newindex: $newindex, ")
		// filtere Map jetzt nach Values >= Index, erhöhe dann alle Values um eins
/*		if (newindex == i) {
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
		}  */
		if (newindex == 1) {
			value_one = i
		}
		if (i % 1000000 == 0) {
			print("i: $i ")
/*		for (k in 0..i) {
			val keys = buffer.filterValues { it == k }.keys
			if (detectnext) {
			print("$keys ")
			count = count -1
				if (count == 0) {
					detectnext = false
				}
			}

		}
		count = 2
		detectnext= true  */
			print("   value_one $value_one")
			println()
		}
	}



	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   The value after 0 the moment 50000000 is inserted : $value_one")
	println()

}
