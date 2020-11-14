//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var checksum1: Int = 0
	var checksum2: Int = 0
	var map_fire = mutableMapOf<Int, Int>()
	var map_firepos = mutableMapOf<Int, Int>()

	File("day1713_puzzle_input.txt").forEachLine {
		var instruction = it.split(": ")
		map_fire.put(instruction[0].toString().toInt(), instruction[1].toString().toInt())

	}
	println(map_fire)


	var time_offset: Int = 0
	var time: Int = 0
	var scanner: Int = 1
	var detect: Boolean = true

//  for (n in 0..10) {	
	while(detect) {
			detect = false
	println("Step: $time_offset")
	for (position in 0..94) {
		// detect = false  -->> soll im gleichen lauf nicht mehr true werden können
		time = position + time_offset

		map_fire.forEach {

		}
		if (map_fire.containsKey(position)) {
			if ((time / (map_fire.get(position)!! - 1)) % 2 == 0) {
				scanner = time % (map_fire.get(position)!! - 1)  // how to implement a scanner moving back and forth?
			} else {
				scanner = (map_fire.get(position)!! - 1) - time % (map_fire.get(position)!! - 1)
			}
			if (scanner == 0) {
				detect = true
				checksum1 = checksum1 + position*map_fire.get(position)!!
			}
		}
		//println("position: $position, time $time, scanner $scanner, detect $detect")
		map_fire.forEach {

		}

	} // end loop over positions
	println()
	time_offset = time_offset+1
} // end timeoffset loop

	// Ausgabe der Lösung für Part 1
//	println()
//	println("******************")
//	println("Solution for part1")
//	println("   The checksum is: $checksum1")
//	println()

	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   The fewest number of picoseconds is: ${time_offset-1}")
	println()

}