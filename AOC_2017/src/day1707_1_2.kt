//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {
	var root: String = ""
	var checksum2: Int = 0

	var listA = mutableListOf<String>()
	var listB = mutableListOf<String>()


	File("day1707_puzzle_input.txt").forEachLine {
		var help = it.replace(",", "")
		var instruction = help.split(" ")
		listA.add(instruction[0])
		println("$instruction, ${instruction.size}")
		if (instruction.size > 2) {
			for (i in 3..instruction.size - 1) {
				listB.add(instruction[i])
			}
		}
	}

	listA.forEach {
		if (!listB.contains(it)) {
			root = it
		}
	}

	println()


	// part 2
	var mapC = mutableMapOf<String, Int>()
	var listD = mutableListOf<String>()
	File("day1707_puzzle_input.txt").forEachLine {
		var instruction2 = it.split(" ")
		if (instruction2.size < 3) {
			mapC.put(instruction2[0], instruction2[1].dropLast(1).drop(1).toInt())
		} else {
			listD.add(it)
		}
	}
	println("mapC : $mapC")
	println("listD : $listD")
	println()

	var listE = mutableListOf<Int>()
	var listF = mutableListOf<String>()

	var RunEnd: Boolean = false
	var k: Int = 0

	var hilf: Int = 0
//	for (k in 0..1) {
	while (!RunEnd) {
		println("  Step $k, listD $listD")

		listD.forEach() {
		//	println("*")
			var help3 = it.replace(",", "")
			var instruction3 = help3.split(" ")
			for (i in 3..instruction3.size - 1) {
				if (mapC.containsKey(instruction3[i])) {
					listE.add(mapC.getValue(instruction3[i]))
					hilf = mapC.getValue(instruction3[i])
				}
			}
		//	println("ListE $listE, instruction.size ${instruction3.size}, listE.distinct.size ${listE.distinct().size}")
			if (listE.distinct().size == 1 && (instruction3.size - 3) == listE.size) {  // achte darauf, wenn noch nicht alle ausgewertet sind, dann ergibt sich hier ein Fehler, und List F bleibt leer!
//				println("listE $listE")
				mapC.put(instruction3[0], instruction3[1].dropLast(1).drop(1).toInt() + listE.size * hilf)
				//println(mapC)
			} else if (listE.distinct().size > 1 && (instruction3.size - 3) == listE.size) {  // achte darauf, wenn noch nicht alle ausgewertet sind, dann ergibt sich hier ein Fehler, und List F bleibt leer!
				println("Treffer")
				println("listE $listE")
				println("it $it")
				//			mapC.put(instruction3[0], instruction3[1].dropLast(1).drop(1).toInt() + listE.size * hilf)
				//println(mapC)
				listF.add(it)
				RunEnd = true

			} else {

				listF.add(it)
				//println("Treffer!")
				//println(listE)
				//println(mapC)
				//println(it)
				//	println(instruction3[1].dropLast(1).drop(1).toInt())
				//	println()

			}

			listE.clear()
//			println(mapC)
//			println(it)
//			println("listF: $listF")
//				println("**")
		}

//		mapC.clear()
//		mapC = mapD
		listD.clear()
		listF.forEach {
			listD.add(it)
		}
//		println("ListF nach Durchgang $k: $listF")
		listF.clear()
//		println("ListD nach Durchgang $k: $listD")
		k = k + 1
	}


	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   The name of the bottom program is $root")
	println()


	// Ausgabe der Lösung für Part 2
	// 15 ist falsch. es ist aber auch nicht 8 gefragt, sondern 60
	//listE [2671, 2671, 2680]
	//it yruivis (2760) -> oxipms, ggpau, sphbbz, sphbbz = 1161 --> muss um 9 kleiner sein -->1152

	println()
	println("******************")
	println("Solution for part2")
	println("   ")
	println()

}
