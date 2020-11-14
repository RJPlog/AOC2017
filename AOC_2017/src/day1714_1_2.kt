//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var checksum1: Int = 0
	var checksum2: Int = 0

	var puzzle_input: String = "hfdlxzhv"
	var hash: String = ""
	var part: String = ""
	var xp: Int = 0
	var yp: Int = 0
	var Grid1714 = mutableMapOf<String, String>()

	for (i in 0..127) {
		hash = puzzle_input + "-" + i.toString()
		hash = knothash(hash)

		hash.forEach {
			part = it.toString()
			part = part.toInt(16).toString(2).padStart(4, '0')
			part.forEach {
				if (it.equals('1')) {
					checksum1 = checksum1 + 1
					Grid1714.put(xp.toString() + "=" + yp.toString(), "#")
				} else {
					Grid1714.put(xp.toString() + "=" + yp.toString(), ".")
				}
				xp = xp + 1
			}

		}
		xp = 0
		yp = yp + 1
	}

//	println(Grid1714)
//	PrintGrid1714(128, 128, Grid1714)

// Grid auswerten.
// Wiederhole das, bis du kein # mehr findest.
// Durchsuche Grid, wenn # setze auf 1, break.
// Von 1 aus starte und suche alle angrenzenden Einsen, zähle diese uns setze alle auf .


	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   $checksum1 steps are required")
	println()

	

//		PrintGrid1714(128, 10, Grid1714)
	println()
	checksum2 = FindRegion(Grid1714)


	// Ausgabe der Lösung für Part 2
	// 8252 is to high, 1056 is to low
	println()
	println("******************")
	println("Solution for part2")
	println("   The checksum is: $checksum2")
	println()

}


fun FindRegion(Grid_input: MutableMap<String, String>): Int {  // aus 1624

	var region: Int = 0
	var endnotreached = true
	var Grid = Grid_input
	var Position: String = ""

	
// ab hier nochmal!!!	
//for (k in 0..4) {
	while(true){
		var step: String = "1"
		var PrepEnd = false
		var endnotreached = true
	// filter Map nach fragmentierten Inhalten
	var prepareGrid = Grid.filterValues { it == "#" }

	//  falls keine fragmentierent Inhalte mehr da sind, beende funktion und gibt Anzal der regionen zurück
	if (prepareGrid.size == 0) {
		return region
	}

	// jetzt setze den ersten Wert in der Map auf 2, um mit dem defragmentieren zu beginnnen.
	prepareGrid.forEach {
		if (prepareGrid.getValue(it.key).equals("#") && !PrepEnd) {
			Grid.put(it.key, "1")
			PrepEnd = true
		}
	}
	// test: lass uns die Map jetzt nochmal anschauen:
//	PrintGrid1714(128, 10, Grid)  //--> ok tut
//	println(region)

	// ab jetzt wird von "1" an gezählt, bis nichts mehr gefunden wird erreicht wird, und dann der Zählerwert zurück gegeben.
	while (endnotreached) {

		val filteredGrid = Grid.filterValues { it == step }
		var Hilf1: String
		var Texture: String
		endnotreached = false
		filteredGrid.forEach {
			//println("it $it")
			val instruction = it.toString().split("=")
			Position = instruction[0] + "-" + instruction[1]
			Texture = (step.toInt() + 1).toString()
			Hilf1 = (instruction[0].toInt() - 1).toString() + "=" + instruction[1]
			if (Grid.containsKey(Hilf1)) {
				//println("Instruction: $instruction, Position: $Position, Texture: $Texture, Hilf1: $Hilf1")
				if (Grid.get(Hilf1) == "#") {
					Grid.put(Hilf1, Texture)
					endnotreached = true
				}
			}
			Hilf1 = (instruction[0].toInt() + 1).toString() + "=" + instruction[1]
			//println("*, ${Grid.containsKey(Hilf1)}")  // zwischen hier und den nächsten Print tut es nciht
			if (Grid.containsKey(Hilf1)) {
			//	println("Instruction: $instruction, Position: $Position, Texture: $Texture, Hilf1: $Hilf1")
				if (Grid.get(Hilf1) == "#") {
					Grid.put(Hilf1, Texture)
					endnotreached = true
				}
			}
			//println(" instruction 0: ${instruction[0]}   instruction 1: ${instruction[1]}")
			Hilf1 = instruction[0] + "=" + (instruction[1].toInt() - 1).toString()
			//println("*, ${Grid.containsKey(Hilf1)}")// zwischen hier und den letzten Print tut es nciht
			if (Grid.containsKey(Hilf1)) {
			//	println("Instruction: $instruction, Position: $Position, Texture: $Texture, Hilf1: $Hilf1")
				if (Grid.get(Hilf1) == "#") {
					Grid.put(Hilf1, Texture)
					endnotreached = true
				}
			}
			Hilf1 = instruction[0] + "=" + (instruction[1].toInt() + 1).toString()
			if (Grid.containsKey(Hilf1)) {
			//	println("Instruction: $instruction, Position: $Position, Texture: $Texture, Hilf1: $Hilf1")
				if (Grid.get(Hilf1) == "#") {
					Grid.put(Hilf1, Texture)
					endnotreached = true
				}
			}
		}
		step = (step.toInt() + 1).toString()
//		PrintGrid1714(128, 10, Grid)
	}
	region = region +1
	// hier filtern nach allem was nicht . oder # ist, dann neue runde
	prepareGrid = Grid.filterValues { it != "#" }
	prepareGrid = prepareGrid.filterValues { it != "." }
	prepareGrid.forEach {
		Grid.put(it.key, ".")
	}
//		PrintGrid1714(10, 10, Grid) 
	
} // die große schleife über alle Regionen
		
//	PrintGrid24(10, 4, Grid)
//	println(" EndnotReached: $endnotreached")   
	return region
}


fun PrintGrid1714(width: Int, depth: Int, Grid: MutableMap<String, String>) {
	for (y in 0..depth - 1) {
		for (x in 0..width - 1) {
			var Position = x.toString() + "=" + y.toString()
			print(Grid.get(Position))
		}
		println()
	}
	println()
}

fun knothash(input: String): String {
	var skip_size: Int = 0
	var input_length = mutableListOf<Int>()

	var j: Int = 0
	var cur_pos = 0

	var hash_input = mutableListOf<Int>()
	var hash_output = mutableListOf<Int>()
	// einlesen puzzle_input  --> puzzle_input 16175

	input.forEach {
		input_length.add(it.toChar().toInt())
	}
	input_length.add(17)
	input_length.add(31)
	input_length.add(73)
	input_length.add(47)
	input_length.add(23)

//	for (i in 0..4) {
	for (i in 0..255) {
		hash_input.add(i)
		hash_output.add(i)
	}

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
	for (k in 0..15) {
		for (l in 0..15) {
			value = value xor hash_output[l + k * 16]
		}
//	println(value.toString(16))
		dens_hash = dens_hash + value.toString(16).padStart(2, '0')
		value = 0
	}
	return dens_hash
}
