import java.util.LinkedList;

class MaximumFlow {

	// Methode zum Finden des größen Gesamtfluss
	int[][] findMaxFlow(int input[][], int sourceNode, int sinkNode) {

		// Deklaration und Initalisierung der Variablen
		int vertexCount = input.length;
		int x, y;
		int[][] output = new int[vertexCount][vertexCount];
		int[][] rGraph = new int[vertexCount][vertexCount];
		int parent[] = new int[vertexCount];
		int max_flow = 0;
		int path_flow;

		//Alle Werte von input nach rGraph kopieren
		for (x = 0; x < vertexCount; x++)
			for (y = 0; y < vertexCount; y++)
				rGraph[x][y] = input[x][y];
		
		// Schleife wird wiederholt bis Start-/Endknoten nicht mehr verbunden sind
		while (isConnected(rGraph, sourceNode, sinkNode, parent)) {

			// Setze Fluss des aktuellen Pfades auf unendlich
			path_flow = Integer.MAX_VALUE;

			// Ersetze das Gewicht des aktuellen Pfades, wenn das Gewicht der aktuellen
			// Kante kleiner als unendlich ist
			for (y = sinkNode; y != sourceNode; y = parent[y]) {
				x = parent[y];
				path_flow = Math.min(path_flow, rGraph[x][y]);

			}

			// Subtrahiere Gewicht des aktuellen Pfades von der Beziehung des Elternknoten
			// und addiere das Gewicht zur Beziehung des aktuellen Knotens
			for (y = sinkNode; y != sourceNode; y = parent[y]) {
				x = parent[y];
				rGraph[x][y] -= path_flow;
				rGraph[y][x] += path_flow;
			}

			// Addiere den Fluss des aktuellen Pfades zum Gesamtfluss
			max_flow += path_flow;

		}

		// Schreibe Rückgabematrix
		for (x = 0; x < rGraph.length; x++) {
			for (y = 0; y < rGraph.length; y++) {
				if (rGraph[y][x] != 0) {
					if (input[x][y] != 0) {
						output[x][y] = rGraph[y][x];
					}
				}
			}
		}

		// Textrückgabe
		System.out.println("\nMaximaler Fluss des Graphen: " + max_flow + "\n");

		// Rückgabe
		return output;
	}

	// Methode verwendet Breitensuche, um festzustellen, ob "sourceNode" und
	// "sinkNode" verbunden sind
	boolean isConnected(int rGraph[][], int sourceNode, int sinkNode, int parent[]) {

		// Deklaration und Initalisierung der Variablen
		int vertexCount = rGraph.length;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(sourceNode);
		boolean visited[] = new boolean[vertexCount];

		// Alle Knoten auf nicht besucht setzen
		for (int x = 0; x < vertexCount; ++x) {
			visited[x] = false;
		}

		// Setze den Anfangsknoten auf besucht und setze denn Elternknoten auf -1
		visited[sourceNode] = true;
		parent[sourceNode] = -1;

		// Schleife wird wiederholt bis Warteschlange leer ist
		while (queue.size() != 0) {

			// Aktueller Knoten auf den Anfang der Warteschlange setzen
			int current = queue.poll();

			// Schleife durchläuft alle Knoten
			for (int x = 0; x < vertexCount; x++) {

				// Überprüfen, ob der Iterationsknoten besucht ist und eine Verbindung zwischen
				// ihm und dem aktuellen Knoten besteht
				if (visited[x] == false && rGraph[current][x] > 0) {

					// Wenn der Iterationsknoten der Endknoten ist, setze den aktuellen Knoten
					// als Elternknoten und beende die Methode
					if (x == sinkNode) {
						parent[x] = current;

						// Rückgabe
						return true;
					}

					// Wenn der Iterationsknoten nicht der Endknoten ist, füge ihn zur Warteschlange
					queue.add(x);

					// Setze den Iterationsknoten auf besucht und setze den Elternknoten des
					// Iterationsknoten auf den aktuellen Knoten
					visited[x] = true;
					parent[x] = current;
				}
			}
		}

		// Rückgabe
		return false;
	}
}
