//  08.11.2020 start with 2017

import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {

	var checksum1: String = ""
	var checksum2: String = ""

	var particle: Int = 0
	var particle_min: Int = 0
	var acc_act: Int = 0
	var acc_min: Int = 1000

	File("day1720_puzzle_input.txt").forEachLine {
		var instruction = it.split(", ")
		var acc = instruction[2].drop(3).dropLast(1).split(",")
		particle++
		acc_act = abs(acc[0].toInt()) + abs(acc[1].toInt()) + abs(acc[2].toInt())
		//println(acc_act)
		if (acc_act < acc_min) {
			acc_min = acc_act
			particle_min = particle - 1
		}

	}


	// part2

	var p_a: Int
	var x_a: Int
	var y_a: Int
	var z_a: Int
	var vx_a: Int
	var vy_a: Int
	var vz_a: Int
	var ax_a: Int
	var ay_a: Int
	var az_a: Int

	var p_b: Int
	var x_b: Int
	var y_b: Int
	var z_b: Int
	var vx_b: Int
	var vy_b: Int
	var vz_b: Int
	var ax_b: Int
	var ay_b: Int
	var az_b: Int


	var particleList = mutableMapOf<Int, String>()
	var collision = mutableListOf<Int>()

	particle = 0

	File("day1720_puzzle_input.txt").forEachLine {
		var instruction = it.split(", ")
		particleList.put(particle, it)
		particle++
	}

	for (i in 0..50) { // Schleife über die Zeit --> finally I have no abbort criteria, this was just try and error
		particleList.forEach() {
			// 1. Schleife über alle Partikel
			p_a = it.key
			var instruction = it.value.split(", ")
			var pos = instruction[0].drop(3).dropLast(1).split(",")
			var vel = instruction[1].drop(3).dropLast(1).split(",")
			var acc = instruction[2].drop(3).dropLast(1).split(",")
			x_a = pos[0].toInt(); y_a = pos[1].toInt(); z_a = pos[2].toInt()
			vx_a = vel[0].toInt(); vy_a = vel[1].toInt(); vz_a = vel[2].toInt()
			ax_a = acc[0].toInt(); ay_a = acc[1].toInt(); az_a = acc[2].toInt()
			//println("p_a $p_a, pos: $pos, vel: $vel, acc: $acc")
			//println("p_a $p_a, x_a $x_a, y_a $y_a, z_a $z_a, vx_a $vx_a, vy_a $vy_a, vz_a $vz_a, ax_a $ax_a, ay_a $ay_a, az_a $az_a")
			particleList.forEach() {
				// 2. Schleife über alle Partikel
				p_b = it.key
				if (p_b != p_a) { // nur wenn beide Partikel nicht gleich
					var instruction = it.value.split(", ")
					var pos = instruction[0].drop(3).dropLast(1).split(",")
					var vel = instruction[1].drop(3).dropLast(1).split(",")
					var acc = instruction[2].drop(3).dropLast(1).split(",")
					x_b = pos[0].toInt(); y_b = pos[1].toInt(); z_b = pos[2].toInt()
					vx_b = vel[0].toInt(); vy_b = vel[1].toInt(); vz_b = vel[2].toInt()
					ax_b = acc[0].toInt(); ay_b = acc[1].toInt(); az_b = acc[2].toInt()
					//println("   p_b $p_b, pos: $pos, vel: $vel, acc: $acc")
					//println("   p_b $p_b, x_b $x_b, y_b $y_b, z_b $z_b, vx_b $vx_b, vy_b $vy_b, vz_b $vz_b, ax_b $ax_b, ay_b $ay_b, az_b $az_b")

					// jetzt prüfe ob postion gleich, wenn ja speichere p_a, p_b
					var xpos_a = x_a + vx_a * i + ax_a * (i + 1) * i / 2
					var ypos_a = y_a + vy_a * i + ay_a * (i + 1) * i / 2
					var zpos_a = z_a + vz_a * i + az_a * (i + 1) * i / 2
					var xpos_b = x_b + vx_b * i + ax_b * (i + 1) * i / 2
					var ypos_b = y_b + vy_b * i + ay_b * (i + 1) * i / 2
					var zpos_b = z_b + vz_b * i + az_b * (i + 1) * i / 2
					if (xpos_a == xpos_b && ypos_a == ypos_b && zpos_a == zpos_b) {
						collision.add(p_a)
						collision.add(p_b)
					}

				} // nur wenn beide Partikel nicht gleich
			} // 2. Schleife über alle Partikel
		} // 1. Schleife über alle Partikel

		println("step: $i collision with ${collision.distinct()}")
		println()
		// lösche alle collision keys aus map
		collision.distinct().forEach {
		particleList.remove(it)
		}
		println("Remaining particles: ${particleList.size}")
		// leere collision
		collision.clear()
		//starte wieder

	} // Schleife über die Zeit


	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("  $particle_min particle will stay closest to position <0,0,0> in the long term?")
	println()

	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   The programs standing after their billion dance is : ${particleList.size}")
	println()

}