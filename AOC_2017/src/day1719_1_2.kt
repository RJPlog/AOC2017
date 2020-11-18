import java.io.File
import kotlin.math.*

// Aufsetzen des Grids für vorgegebene Größe und Favorite Number
fun SetupGrid1719(): MutableMap<String, String> {
	val Grid = mutableMapOf<String, String>()
	var Position: String
	var Texture: String
	var ypos: Int = 0


	File("day1719_puzzle_input.txt").forEachLine {
		for (xpos in 0..it.length - 1) {
			Position = xpos.toString() + "=" + ypos.toString()
			Texture = it[xpos].toString()
			Grid.put(Position, Texture)
		}
		ypos = ypos + 1
	}

	var filteredGrid = Grid.filterValues { it != "|" }
	filteredGrid = filteredGrid.filterValues { it != "-" }
	filteredGrid = filteredGrid.filterValues { it != "+" }
	filteredGrid = filteredGrid.filterValues { it != " " }

	println(filteredGrid)

	return Grid
}

fun WidthGrid1719(): Int {
	var width: Int = 0
	File("day1719_puzzle_input.txt").forEachLine { width = it.length - 1 }
	return width
}

fun DepthGrid1719(): Int {
	var depth: Int = 0
	File("day1719_puzzle_input.txt").forEachLine { depth = depth + 1 }
	return depth - 1
}

// Zeichnen des aktuellenGrids
fun PrintGrid1719(width: Int, depth: Int, Grid: MutableMap<String, String>) {
	for (y in 0..depth) {
		for (x in 0..width) {
			var Position = x.toString() + "=" + y.toString()
			print(Grid.get(Position))
		}
		println()
	}
	println()
}


fun WalkGrid1719(Grid_input: MutableMap<String, String>): String {
	var letters: String = ""
	var xpos = 0
	var ypos = 0
	var Grid = Grid_input
	var step = "0"
	var direction: String = "D"
	var Hilf1: String = ""
	var Hilf2: String = ""
	var Hilf3: String = ""
	var Hilf4: String = ""
	var count: Int = 0

	// setze Startpunkt
	while (true) {
		if (Grid.getValue(xpos.toString() + "=0") == "|") {
			break
		}
		xpos++
	}

	var endnotreached = true

	while (endnotreached) {
		//for (i in 0..50) {
		if (direction == "D") {    // gehe nach unten
			Hilf1 = (xpos).toString() + "=" + (ypos + 1).toString() // Feld direkt in Gehrichtung
			Hilf2 = (xpos - 1).toString() + "=" + (ypos + 1).toString()   // Feld links von Gehrichtung
			Hilf3 = (xpos + 1).toString() + "=" + (ypos + 1).toString()   // Feld rechts von Gehrichtung
			if (Grid.getValue(Hilf1) == " ") { // falls nichts mehr kommt ist hier das Ende
				break
				//return letters
			} else if (((Grid.getValue(Hilf1) == "+") && Grid.getValue(Hilf3) == " ")) { //  Abbiegen, drehe dich in Richtung Westen
				direction = "L"
			} else if (((Grid.getValue(Hilf1) == "+") && Grid.getValue(Hilf2) == " ")) { //  Abbiegen, drehe dich in Richung Osten
				direction = "R"
			} else {
				if ((Grid.getValue(Hilf1) != "+") && (Grid.getValue(Hilf1) != "|") && (Grid.getValue(Hilf1) != "-")) { //  Gerade weiter aber nehme Buchstabe auf
					letters = letters + Grid.getValue(Hilf1)
				}
			}
			ypos++
			count++
		} else if (direction == "R") {  //gehe nach Westen
			Hilf1 = (xpos + 1).toString() + "=" + (ypos).toString() // Feld direkt in Gehrichtung
			Hilf2 = (xpos + 1).toString() + "=" + (ypos - 1).toString()   // Feld oben von Gehrichtung
			Hilf3 = (xpos + 1).toString() + "=" + (ypos + 1).toString()   // Feld unten von Gehrichtung
			if (Grid.getValue(Hilf1) == " ") { // falls nichts mehr kommt ist hier das Ende
				break
				//return letters
			} else if (((Grid.getValue(Hilf1) == "+") && Grid.getValue(Hilf3) == " ")) { //  Abbiegen, drehe dich in Richtung Norden
				direction = "U"
			} else if (((Grid.getValue(Hilf1) == "+") && Grid.getValue(Hilf2) == " ")) { //  Abbiegen, drehe dich in Richung Süden
				direction = "D"
			} else {
				if ((Grid.getValue(Hilf1) != "+") && (Grid.getValue(Hilf1) != "|") && (Grid.getValue(Hilf1) != "-")) { //  Gerade weiter aber nehme Buchstabe auf
					letters = letters + Grid.getValue(Hilf1)
				}
			}
			xpos++
			count++
		} else if (direction == "L") { //gehe nach Osten
			Hilf1 = (xpos - 1).toString() + "=" + (ypos).toString() // Feld direkt in Gehrichtung
			Hilf2 = (xpos - 1).toString() + "=" + (ypos - 1).toString()   // Feld oben von Gehrichtung
			Hilf3 = (xpos - 1).toString() + "=" + (ypos + 1).toString()   // Feld unten von Gehrichtung
			if (Grid.getValue(Hilf1) == " ") { // falls nichts mehr kommt ist hier das Ende
				break
				//return letters
			} else if (((Grid.getValue(Hilf1) == "+") && Grid.getValue(Hilf3) == " ")) { //  Abbiegen, drehe dich in Richtung Norden
				direction = "U"
			} else if (((Grid.getValue(Hilf1) == "+") && Grid.getValue(Hilf2) == " ")) { //  Abbiegen, drehe dich in Richung Süden
				direction = "D"
			} else {
				if ((Grid.getValue(Hilf1) != "+") && (Grid.getValue(Hilf1) != "|") && (Grid.getValue(Hilf1) != "-")) { //  Gerade weiter aber nehme Buchstabe auf
					letters = letters + Grid.getValue(Hilf1)
				}
			}
			xpos--
			count++
		} else if (direction == "U") { //gehe nach oben/Norden
			Hilf1 = (xpos).toString() + "=" + (ypos - 1).toString() // Feld direkt in Gehrichtung
			Hilf2 = (xpos - 1).toString() + "=" + (ypos - 1).toString()   // Feld links von Gehrichtung
			Hilf3 = (xpos + 1).toString() + "=" + (ypos - 1).toString()   // Feld rechts von Gehrichtung
			if (Grid.getValue(Hilf1) == " ") { // falls nichts mehr kommt ist hier das Ende
				break
				//return letters
			} else if (((Grid.getValue(Hilf1) == "+") && Grid.getValue(Hilf3) == " ")) { //  Abbiegen, drehe dich in Richtung Westen
				direction = "L"
			} else if (((Grid.getValue(Hilf1) == "+") && Grid.getValue(Hilf2) == " ")) { //  Abbiegen, drehe dich in Richung Osten
				direction = "R"
			} else {
				if ((Grid.getValue(Hilf1) != "+") && (Grid.getValue(Hilf1) != "|") && (Grid.getValue(Hilf1) != "-")) { //  Gerade weiter aber nehme Buchstabe auf
					letters = letters + Grid.getValue(Hilf1)
				}
			}
			ypos--
			count++
		}

	}


	letters = letters + "-" + (count+1).toString()
	return letters
}


fun main(args: Array<String>) {

	var solution1: String = ""

	// Grid wird aus Puzzle_Input eingelesen
	var Grid_Init = SetupGrid1719()

	// Ermittle width and depth
	var width = WidthGrid1719()
	var depth = DepthGrid1719()

//	println(Grid_Init)

	PrintGrid1719(width, depth, Grid_Init)

	solution1 = WalkGrid1719(Grid_Init)

	// gesucht wird die Reihenfolge von YEUTDINXAP
	println()
	println("******************")
	println("Solution for part1")
	println("    What letters will it see:  $solution1")
	println()


}