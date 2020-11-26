import java.io.File
import kotlin.math.*


fun main(args: Array<String>) {

	var solution1: Int = 0
	var width = WidthGrid1721()
	var depth = WidthGrid1721()
	var width_old = width
	var r1: String = ""
	var r2: String = ""
	var r3: String = ""
	var f1: String = ""
	var f2: String = ""
	var f3: String = ""
	var f4: String = ""

	// Inital Grid wird aus Puzzle_Input eingelesen, Anfangswert ist ein 3x3 Feld
	var Grid_Init = SetupGrid1721()

	//	println(Grid_Init)
	PrintGrid1721(width, depth, Grid_Init)

	for (k in 1..5) {    //Scheife von 1..5
		println("****  Durchgang: $k ****")
		println()
	//	println("+ Input +")
	//	PrintGrid1721(width, depth, Grid_Init)
		// Erzeuge neue Länge, je nachdem ob width % 2 == 0 oder width % 3 == 0
		if (width % 2 == 0) {
			width = width / 2 * 3
			depth = width
		} else {
			width = width / 3 * 4
			depth = width
		}

		var Grid_New = SetupCleanGrid1721(width, depth)
		//PrintGrid1721(width, depth, Grid_New)

		// Transfer des alten Grids in das neue
		Grid_New = TransferGrid1721(width_old, Grid_Init, Grid_New)
	//	PrintGrid1721(width, depth, Grid_New)
		width_old = width

		// Ersetze alte Blöcke anhand der Regeln
		var jump: Int = 0
		var rule: String = ""
		var response: String = ""
		var stopturn: Boolean = false
		if (Grid_New.getValue("0=2") == "-") {
			jump = 3
		} else if (Grid_New.getValue("0=3") == "-") {
			jump = 4
		}

		for (i in 0..width - 1 step jump) {
			for (j in 0..width - 1 step jump) {
				rule = ""
				for (y in i..i + jump - 2) {
					for (x in j..j + jump - 2) {
						rule = rule + Grid_New.getValue(x.toString() + "=" + y.toString())
					}
					rule = rule + "/"
				}
				rule = rule.dropLast(1)

				r1 = ""
				r2 = ""
				r3 = ""
				f1 = ""
				f2 = ""
				f3 = ""
				f4 = ""

				// create rot's and flip's
				if (rule.length == 5) {
					r1 = rule[1].toString() + rule[4] + "/" + rule[0] + rule[3]
					r2 = rule[4].toString() + rule[3] + "/" + rule[1] + rule[0]
					r3 = rule[3].toString() + rule[0] + "/" + rule[4] + rule[1]
					f1 = rule[3].toString() + rule[4] + "/" + rule[0] + rule[1]
					f2 = rule[4].toString() + rule[1] + "/" + rule[3] + rule[0]
					f3 = rule[1].toString() + rule[0] + "/" + rule[4] + rule[3]
					f4 = rule[0].toString() + rule[3] + "/" + rule[1] + rule[4]	
				} else if (rule.length == 11) {
					r1 =
						rule[8].toString() + rule[4] + rule[0] + "/" + rule[9] + rule[5] + rule[1] + "/" + rule[10] + rule[6] + rule[2]
					r2 =
						rule[10].toString() + rule[9] + rule[8] + "/" + rule[6] + rule[5] + rule[4] + "/" + rule[2] + rule[1] + rule[0]
					r3 =
						rule[2].toString() + rule[6] + rule[10] + "/" + rule[1] + rule[5] + rule[9] + "/" + rule[0] + rule[4] + rule[8]
					f1 =
						rule[8].toString() + rule[9] + rule[10] + "/" + rule[4] + rule[5] + rule[6] + "/" + rule[0] + rule[1] + rule[2]
					f2 =
						rule[10].toString() + rule[6] + rule[2] + "/" + rule[9] + rule[5] + rule[1] + "/" + rule[8] + rule[4] + rule[0]
					f3 =
						rule[2].toString() + rule[1] + rule[0] + "/" + rule[6] + rule[5] + rule[4] + "/" + rule[10] + rule[9] + rule[8]
					f4 =
						rule[0].toString() + rule[4] + rule[8] + "/" + rule[1] + rule[5] + rule[9] + "/" + rule[2] + rule[6] + rule[10]
				}
			/*	println("rots and flips")
				println(rule)
				println(r1)
				println(r2)
				println(r3)
				println(f1)
				println(f2)
				println(f3)
				println(f4) */

				// get response
				response = "invalidinvalidinvalid"



				while (!stopturn) {
					File("day1721_puzzle_input_2.txt").forEachLine {
						var line = it.split(" => ")
						if (line[0] == rule || line[0] == r1 || line[0] == r2 || line[0] == r3 || line[0] == f1 || line[0] == f2 || line[0] == f3 || line[0] == f4) {
							response = line[1]
							stopturn = true
						}
					}
					//turn rule
					if (response == "invalidinvalidinvalid") {
						// turn
						stopturn = true // --> später entfernen
					}
				}
				stopturn = false
				//println("rule: $rule, response: $response, jump $jump, i $i, j $j")

				// now since we have the response, we could start replacing elements
				var responseind = 0
				for (y in i..i + jump - 1) {
					for (x in j..j + jump - 1) {
						if (response[responseind].equals('/')) {
							responseind++
						}
						Grid_New.put(x.toString() + "=" + y.toString(), response[responseind].toString())
						responseind++
					}
				}
			}
		}
		println()
	//	println("+ Output +")
	//	PrintGrid1721(width, depth, Grid_New)
		// Kopiere New Grid in Start Grid
		Grid_Init = Grid_New
	}    // Ende Schleife von 0..5

	// Für alle Elemente von Start Grid, zähle #
	Grid_Init.forEach {
		if (it.value == "#") {
			solution1++
		}
	}

	
	// Ausgabe der Lösung für Part 1
	println()
	println("******************")
	println("Solution for part1")
	println("    $solution1 pixels stay on after 5 iterations")
	println()


}


// Aufsetzen des Grids für vorgegebene Größe und Favorite Number
fun SetupGrid1721(): MutableMap<String, String> {
	val Grid = mutableMapOf<String, String>()
	var Position: String
	var Texture: String
	var ypos: Int = 0
	File("day1721_puzzle_input_1.txt").forEachLine {
		for (xpos in 0..it.length - 1) {
			Position = xpos.toString() + "=" + ypos.toString()
			Texture = it[xpos].toString()
			Grid.put(Position, Texture)
		}
		ypos = ypos + 1
	}
	return Grid
}

fun WidthGrid1721(): Int {
	var width: Int = 0
	File("day1721_puzzle_input_1.txt").forEachLine { width = it.length }
	return width
}

fun SetupCleanGrid1721(width: Int, depth: Int): MutableMap<String, String> {
	val Grid = mutableMapOf<String, String>()
	for (ypos in 0..depth - 1) {
		for (xpos in 0..width - 1) {
			var Position = xpos.toString() + "=" + ypos.toString()
			Grid.put(Position, "-")
		}
	}
	return Grid
}


// Zeichnen des aktuellenGrids
fun PrintGrid1721(width: Int, depth: Int, Grid: MutableMap<String, String>) {
	for (y in 0..depth - 1) {
		for (x in 0..width - 1) {
			var Position = x.toString() + "=" + y.toString()
			print(Grid.get(Position))
		}
		println()
	}
	println()
}

// Übertrag der Werte in das neue Grid
fun TransferGrid1721(
	width: Int,
	Grid_old: MutableMap<String, String>,
	Grid_new: MutableMap<String, String>
): MutableMap<String, String> {
	Grid_old.forEach {
		var Position = it.key
		var instruction = Position.split("=")
		var xpos = instruction[0].toInt()
		var ypos = instruction[1].toInt()
		//println("position: $Position, width: $width, x/ ${(xpos / 2)}, x rem ${xpos.rem(2)}  ,  y/ ${(ypos / 2)}, y rem ${ypos.rem(2)}  ,")
		if (width % 2 == 0) {
			Grid_new.put(
				((xpos / 2) * 3 + xpos.rem(2)).toString() + "=" + ((ypos / 2) * 3 + ypos.rem(2)).toString(),
				Grid_old.getValue(Position)
			)
		} else {
			Grid_new.put(
				((xpos / 3) * 4 + xpos.rem(3)).toString() + "=" + ((ypos / 3) * 4 + ypos.rem(3)).toString(),
				Grid_old.getValue(Position)
			)

		}
	}
	return Grid_new
}
