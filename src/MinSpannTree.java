class MinSpanningTree {

	// Methode zum Finden eines minimalen Spannbaum
	int[][] findMST(int input[][]) {

		// Deklaration und Initalisierung der Variablen
		int vertexCount = input.length;
		int[][] output = new int[vertexCount][vertexCount];
		int parent[] = new int[vertexCount];
		int cost[] = new int[vertexCount];
		int current;
		Boolean includedVertices[] = new Boolean[vertexCount];

		// Initialisiere Kosten jedes Knoten mit unendlich und setze alle Knoten auf
		// nicht besucht
		for (int x = 0; x < vertexCount; x++) {
			cost[x] = Integer.MAX_VALUE;
			includedVertices[x] = false;
		}

		// Setze den Punkt 0 als Startpunkt und setze denn Elternknoten auf -1
		cost[0] = 0;
		parent[0] = -1;

		// Schleife, die alle Knoten durchläuft
		for (int x = 0; x < vertexCount - 1; x++) {

			// Den bestmöglichen Knoten mit minmalCost Methode suchen
			current = minimalCost(cost, includedVertices, vertexCount);

			// Gefundenen Knoten auf besucht setzen
			includedVertices[current] = true;

			// Schleife, die alle Verbindungen vom aktuellen Knoten durchläuft
			for (int y = 0; y < vertexCount; y++)

				// Aktueller Knoten in Elternarray schreiben und Kantenlänge in Kostenarray schreiben
				if (input[current][y] != 0 && includedVertices[y] == false && input[current][y] < cost[y]) {
					parent[y] = current;
					cost[y] = input[current][y];
				}
		}

		// Schreibe Rückgabematrix
		for (int x = 1; x < vertexCount; x++) {
			output[parent[x]][x] = input[x][parent[x]];

			// Spiegeln der Ausgabe, da der Graph ungerichtet ist
			output[x][parent[x]] = input[x][parent[x]];

		}

		// Textrückgabe
		System.out.println("Kante \tGewicht");
		for (int x = 1; x < vertexCount; x++) {
			System.out.println(parent[x] + " - " + x + "\t" + input[x][parent[x]]);
		}
		System.out.println();
		
		// Rückgabe
		return output;
	}

	// Methode zum Finden des Knoten mit dem kleinsten Preis, der noch nicht Teil des
	// Spannbaums ist
	int minimalCost(int cost[], Boolean includedVertices[], int vertexCount) {

		// Deklaration und Initalisierung der Variablen
		int min = Integer.MAX_VALUE;
		int min_index = -1;

		// Schleife, die alle Knoten durchläuft
		for (int x = 0; x < vertexCount; x++)

			// Suche nach dem Knoten mit dem kleinsten Preis, der noch nicht Teil des
			// Spannbaums ist
			if (includedVertices[x] == false && cost[x] < min) {
				min = cost[x];
				min_index = x;
			}

		// Rückgabe
		return min_index;
	}
}
