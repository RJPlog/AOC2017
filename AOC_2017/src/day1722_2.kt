import java.io.File
import kotlin.math.*

// Aufsetzen des Grids für vorgegebene Größe und Favorite Number
fun SetupGrid1722_b(): MutableMap<String, String> {
	val Grid = mutableMapOf<String, String>()
	var Position: String
	var Texture: String
	var ypos: Int = 0


	File("day1722_puzzle_input.txt").forEachLine {
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

fun WidthGrid1722_b(): Int {
	var width: Int = 0
	File("day1722_puzzle_input.txt").forEachLine { width = it.length - 1 }
	return width
}

fun DepthGrid1722_b(): Int {
	var depth: Int = 0
	File("day1722_puzzle_input.txt").forEachLine { depth = depth + 1 }
	return depth - 1
}

// Zeichnen des aktuellenGrids
fun PrintGrid1722_b(width: Int, depth: Int, Grid: MutableMap<String, String>) {
	for (y in 0..depth) {
		for (x in 0..width) {
			var Position = x.toString() + "=" + y.toString()
			print(Grid.get(Position))
		}
		println()
	}
	println()
}


fun WalkGrid1722_b(Grid_input: MutableMap<String, String>): Int {
	var infections: Int = 0
	var xpos = 0
	var ypos = 0
	var position: String = ""
	var Grid = Grid_input
	var step = "0"
	var direction: String = "U"
	var Hilf1: String = ""

	// setze Startpunkt
	xpos = (WidthGrid1722_b() + 1) / 2
	ypos = (DepthGrid1722_b() + 1) / 2

	println("xpos: $xpos, ypos: $ypos")
	var endnotreached = true

//	while (endnotreached) {
	for (i in 0..9999999) {
		// wo bin ich
		position = xpos.toString() + "=" + ypos.toString()
		// println("xpos: $xpos, ypos: $ypos")
		// falls unbekanntes Gebiet, füge neuen Knoten hinzu
		if (!Grid.containsKey(position)) {
			Grid.put(position, ".")
		}
		if (direction == "D") {    // gehe nach unten
			Hilf1 = Grid.getValue(position)
			if (Hilf1 == ".") { // node is clean --> will be infected
				direction = "R"
				Grid.put(position, "W")
				xpos++   // gehe einen Schritt nach links
			} else if (Hilf1 == "#") { //  node is infected -> will be clean
				direction = "L"
				Grid.put(position, "F")
				xpos--   // gehe einen Schritt nach rechts
			} else if (Hilf1 == "W") { //  node is infected -> will be clean
				Grid.put(position, "#")
				infections = infections + 1
				ypos++   // gehe einen Schritt nach rechts
			} else if (Hilf1 == "F") { //  node is infected -> will be clean
				direction = "U"
				Grid.put(position, ".")
				ypos--   // gehe einen Schritt nach rechts
			}

		} else if (direction == "R") {  //gehe nach Westen
			Hilf1 = Grid.getValue(position)
			if (Hilf1 == ".") { // node is clean --> will be infected
				direction = "U"
				Grid.put(position, "W")
				ypos--   // gehe einen Schritt nach links
			} else if (Hilf1 == "F") { //  node is infected -> will be clean
				direction = "L"
				Grid.put(position, ".")
				xpos--   // gehe einen Schritt nach rechts
			} else if (Hilf1 == "W") { //  node is infected -> will be clean
				Grid.put(position, "#")
				infections = infections + 1
				xpos++   // gehe einen Schritt nach rechts
			} else if (Hilf1 == "#") { //  node is infected -> will be clean
				direction = "D"
				Grid.put(position, "F")
				ypos++   // gehe einen Schritt nach rechts
			}

		} else if (direction == "L") { //gehe nach Osten
			Hilf1 = Grid.getValue(position)
			if (Hilf1 == ".") { // node is clean --> will be infected
				direction = "D"
				Grid.put(position, "W")
				ypos++   // gehe einen Schritt nach links
			} else if (Hilf1 == "#") { //  node is infected -> will be clean
				direction = "U"
				Grid.put(position, "F")
				ypos--   // gehe einen Schritt nach rechts
			} else if (Hilf1 == "W") { //  node is infected -> will be clean
				Grid.put(position, "#")
				infections = infections + 1
				xpos--  // gehe einen Schritt nach rechts
			} else if (Hilf1 == "F") { //  node is infected -> will be clean
				direction = "R"
				Grid.put(position, ".")
				xpos++   // gehe einen Schritt nach rechts
			}


		} else if (direction == "U") { //facing north
			Hilf1 = Grid.getValue(position)
			if (Hilf1 == ".") { // node is clean --> will be infected
				direction = "L"
				Grid.put(position, "W")
				xpos--   // gehe einen Schritt nach links
			} else if (Hilf1 == "#") { //  node is infected -> will be clean
				direction = "R"
				Grid.put(position, "F")
				xpos++   // gehe einen Schritt nach rechts
			} else if (Hilf1 == "W") { //  node is infected -> will be clean
				Grid.put(position, "#")
				infections = infections + 1
				ypos--  // gehe einen Schritt nach rechts
			} else if (Hilf1 == "F") { //  node is infected -> will be clean
				direction = "D"
				Grid.put(position, ".")
				ypos++   // gehe einen Schritt nach rechts
			}
		}

		//PrintGrid1722_b(8, 7, Grid)
		//println("xpos_neu: $xpos, ypos_neu: $ypos, infections $infections")

	}


	return infections
}


fun main(args: Array<String>) {

	var solution1: Int

	// Grid wird aus Puzzle_Input eingelesen
	var Grid_Init = SetupGrid1722_b()

	// Ermittle width and depth
	var width = WidthGrid1722_b()
	var depth = DepthGrid1722_b()

//	println(Grid_Init)

	PrintGrid1722_b(width, depth, Grid_Init)

	solution1 = WalkGrid1722_b(Grid_Init)

	// gesucht wird die Reihenfolge von YEUTDINXAP
	println()
	println("******************")
	println("Solution for part1")
	println("    $solution1 bursts caused an infection")
	println()


}