//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {
	var queue = mutableListOf<Int>()
	var puzzle_input: Int =  265149 // 12 // 1024 // 23
	var count: Int = 0
	var help: Int = 1
	var checksum1: Int = 0

	//part2
	var mapA = mutableMapOf<String, Int>()
	var checksum2 = 0
	var mapAcontent: Int = 0
	var GameEnd: Boolean = false

	mapA.put("x0=y0", 1)

	while (count < puzzle_input) {
		queue.add(help)
		queue.add(help)
		count = count + 2 * help
		help = help + 1
	}

	println(queue)

	var direction: Int = 0
	var xpos: Int = 0
	var ypos: Int = 0
	count = 1
	queue.forEach {
		for (i in 1..it) {
			if (direction == 0) {
				xpos = xpos + 1
				count = count + 1
			} else if (direction == 1) {
				ypos = ypos - 1
				count = count + 1
			} else if (direction == 2) {
				xpos = xpos - 1
				count = count + 1
			} else if (direction == 3) {
				ypos = ypos + 1
				count = count + 1
			}
			// berechne MapA Inhalt für alle angrezenden Felder
			mapAcontent = mapAcontent + mapA.getOrDefault("x" + (xpos-1).toString() + "=y" + (ypos-1).toString(), 0)
			mapAcontent = mapAcontent + mapA.getOrDefault("x" + (xpos).toString() + "=y" + (ypos-1).toString(), 0)
			mapAcontent = mapAcontent + mapA.getOrDefault("x" + (xpos+1).toString() + "=y" + (ypos-1).toString(), 0)
			mapAcontent = mapAcontent + mapA.getOrDefault("x" + (xpos-1).toString() + "=y" + (ypos).toString(), 0)
			mapAcontent = mapAcontent + mapA.getOrDefault("x" + (xpos+1).toString() + "=y" + (ypos).toString(), 0)
			mapAcontent = mapAcontent + mapA.getOrDefault("x" + (xpos-1).toString() + "=y" + (ypos+1).toString(), 0)
			mapAcontent = mapAcontent + mapA.getOrDefault("x" + (xpos).toString() + "=y" + (ypos+1).toString(), 0)
			mapAcontent = mapAcontent + mapA.getOrDefault("x" + (xpos+1).toString() + "=y" + (ypos+1).toString(), 0)
			mapA.put("x" + (xpos).toString() + "=y" + (ypos).toString(), mapAcontent)
			if (mapAcontent > puzzle_input && !GameEnd) {
				checksum2 = mapAcontent
				GameEnd = true
			}
			mapAcontent = 0


			// println("count: $count, direction; $direction, x: $xpos, y: $ypos")
			if (count == puzzle_input) {
				println("Puzzle_Input steht bei:")
				println("x: ${xpos}, y: $ypos")
				checksum1 = abs(xpos) + abs(ypos)
			}

		}
		direction = (direction + 1) % 4

	}

	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   $checksum1 steps are required")
	println()

//	println(mapA)
	
	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   The checksum is: $checksum2")
	println()

}
