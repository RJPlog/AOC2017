import java.io.File
import kotlin.math.*

fun main(args: Array<String>) {
// ****************************************************************************	
// *     Declaration Variablen
// ****************************************************************************


	var solution1: Int = 0
	var solution1_max: Int = 0
	var solution2: Int = 0
	var solution2_max_length: Int = 0
	var solution2_max_strength: Int = 0
	var bridge = mutableMapOf<String, String>()
	var segments = mutableMapOf<String, String>()
	var gameend: Boolean = false
	var dockfound: Boolean = false


	File("day1724_puzzle_input.txt").forEachLine {
		var part = it.split("/")
		if (part[0] == "0") {
			bridge.put(it, part[1])
		} else if (part[1] == "0") {
			bridge.put(it, part[0])
		}
	}

	var filteredbridge = bridge.filterValues { it != "X" }

	do { // mache solange Pfade nicht vollständig sind (dock != X)
		filteredbridge.forEach {
			// für alle Elemente der Filtered Bridge
			// zerlege PFad
			var current_segment = it.key
			var current_segments = it.key.split("--")
			var dock = it.value
			// erneuere segments Map
			File("day1724_puzzle_input.txt").forEachLine {
				segments.put(it, "Z")
			}
			// lösche alle Teilstücke aus
			current_segments.forEach {
				segments.remove(it)
			}

			// für alle Segmente
			segments.forEach {
				var segment_part = it.key.split("/")
				// wenn teilsegment = dock
				if (segment_part[0] == dock) {
					// stelle neuees Segment/dock in bridge
					bridge.put(current_segment + "--" + it.key, segment_part[1])
					// lösche altes Element
					bridge.remove(current_segment)
					// setze Anschluss gefunden auf true
					dockfound = true
				} else if (segment_part[1] == dock) {
					// stelle neuees Segment/dock in bridge
					bridge.put(current_segment + "--" + it.key, segment_part[0])
					// lösche altes Element
					bridge.remove(current_segment)
					// setze Anschluss gefunden auf true
					dockfound = true
				}
				// if Anschluss gefunden false
				if (!dockfound) {
					// setze altes Segment Dock auf X
					bridge.put(current_segment, "X")
				}
				dockfound = false        // Anschluss gefunden auf True
			}

		}
		filteredbridge =
			bridge.filterValues { it != "X" } // filtere alle abgeschlossen teilstücke für nächsten Druchgang aus
		if (filteredbridge.size == 0) { // falls alle Teilstücke abgeschlossen sind, breche ab
			gameend = true
		}
		println(filteredbridge.size)
	} while (!gameend) // mache solange Pfade nicht vollständig sind (dock != X) 



	bridge.forEach {
		//println("Brücke $it")
		var hilf1 = it.key.split("--")

		hilf1.forEach {
			var hilf2 = it.split("/")
			solution1 = solution1 + hilf2[0].toInt() + hilf2[1].toInt()
		}
		bridge.put(it.key,hilf1.size.toString()+"-"+solution1.toString())		
		//println(solution1)
		if (solution1 > solution1_max) {
			solution1_max = solution1 // hier wenn es tut die max auswertung setzen
		}
		
		if (hilf1.size > solution2_max_length) {
			solution2_max_length = hilf1.size
			solution2_max_strength = solution1
		} else if (hilf1.size == solution2_max_length) {
			if (solution1 >solution2_max_strength) {
			solution2_max_strength = solution1
			}
		}
		
		solution1 = 0
	}
	
//	bridge.forEach() {
//		println("${it.value}")
//	}

	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("   $solution1_max is the strength of the strongest bridge you can make")
	println()


	// Ausgabe der Lösung für Part 2
	println()
	println("******************")
	println("Solution for part2")
	println("   $solution2_max_strength is the strength of the longest bridge you can make ")
	println()

}