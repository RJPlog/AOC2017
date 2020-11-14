//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*


fun main(args: Array<String>) {
	var checksum1: Int = 0
	var checksum2: Int = 0

	var xpos: Double = 0.0
	var ypos: Double = 0.0
		var positions = mutableMapOf<String, Int>()
	
	File("day1711_puzzle_input.txt").forEachLine {
		var puzzle_input = it.split(",")

		puzzle_input.forEach {
			if (it.equals("n")) {
				ypos = ypos + 1.0
			} else if (it.equals("ne")) {
				ypos = ypos + 0.5
				xpos = xpos + 1.0
			} else if (it.equals("se")) {
				ypos = ypos - 0.5
				xpos = xpos + 1.0
			} else if (it.equals("nw")) {
				ypos = ypos + 0.5
				xpos = xpos - 1.0
			} else if (it.equals("sw")) {
				ypos = ypos - 0.5
				xpos = xpos - 1.0
			} else if (it.equals("s")) {
				ypos = ypos - 1.0
			}
			positions.put(abs(xpos).toString()+"-"+abs(ypos).toString(),0)
		}
		println(positions.size)
	}

	println("x: $xpos, y: $ypos")
	println()
	
	xpos = abs(xpos)
	ypos = abs(ypos)
	
	checksum1 = calc_dist(xpos, ypos)
	
	positions.keys.forEach {
		var pos = it.split('-')
		xpos = pos[0].toString().toDouble()
		ypos = pos[1].toString().toDouble()
		if (calc_dist(xpos, ypos) > checksum2) {
			checksum2 = calc_dist(xpos,ypos)
		}
	}

// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   The fewest number of steps required to reach is $checksum1")

// 5879 ist falsch
	println()
	println("******************")
	println("Solution for part2")
	println("   The furthest steps he was away is $checksum2")
	println()

}


fun calc_dist(x: Double, y: Double): Int {
	var dist: Int = 0
	var rootreached: Boolean = false 
	var x_in = x
	var y_in = y


	if (x_in == 0.0 && y_in == 0.0) {
		rootreached = true
	}

//	println("round: $dist, xpos: $x_in, ypos: $y_in")
	while (!rootreached) {
		if (x_in > 0.0 && y_in > 0.0) {
			x_in = x_in - 1.0
			y_in = y_in - 0.5
		} else if (x_in == 0.0 && y_in > 0) {
			y_in = y_in - 1.0
		} else if (x_in > 0.0 && y_in == 0.0) {
			x_in = x_in - 1.0
		}

		dist = dist + 1
//		println("round: $dist, xpos: $x_in, ypos: $y_in")
		if (x_in == 0.0 && y_in == 0.0) {
			rootreached = true
		}
	}
	
	return dist
}
