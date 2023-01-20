import java.util.ArrayList;
import java.util.PriorityQueue;

public class ShortestPath {

	// Methode zum Finden des kürzesten Weges
	public int[] findShortestPath(int[][] input, int start) {

		// Deklaration und Initalisierung der Variablen
		ArrayList<ArrayList<Vertex>> relation = new ArrayList<ArrayList<Vertex>>();
		PriorityQueue<Vertex> priority = new PriorityQueue<>((v1, v2) -> v1.getDistance() - v2.getDistance());
		int vertexCount = input[0].length;
		int[] range = new int[vertexCount];

		// Schleife, die der Verbindungsliste eine leere Liste anfügt
		for (int x = 0; x < vertexCount; x++) {
			relation.add(new ArrayList<>());
			
			// Setze alle Entfernungen auf unendlich
			range[x] = Integer.MAX_VALUE;
		}
		
		// Setze Entfernung des Startknoten auf 0
		range[start] = 0;

		// Fügt alle bestehenden Verbindugen des Graphen der Verbindungsliste hinzu
		for (int x = 0; x < vertexCount; x++) {
			for (int y = 0; y < vertexCount; y++) {
				if (input[x][y] != 0) {
					relation.get(x).add(new Vertex(y, input[x][y]));
				}
			}
		}

		// Fügt den Startknoten der Prioritätenliste hinzu
		priority.add(new Vertex(start, 0));

		// Schleife wird wiederholt bis Prioritätenliste leer ist
		while (priority.size() > 0) {

			// Knoten mit höchster Priorität auf aktuellen Knoten setzen
			Vertex current = priority.poll();

			// Alle Nachbarknoten des aktuellen Knoten durchlaufen
			for (Vertex x : relation.get(current.getVertexNumber())) {

				// Überprüfen, ob Distanz des aktuellen Knoten plus die Distanz zum Nachbarknoten
				// kleiner ist als die Distanz des Nachbarknoten
				if (range[current.getVertexNumber()] + x.getDistance() < range[x.getVertexNumber()]) {

					// Distanz des Nachbarknoten überschreiben
					range[x.getVertexNumber()] = x.getDistance() + range[current.getVertexNumber()];

					// Nachbarknoten in Prioritätenliste hinzufügen
					priority.add(new Vertex(x.getVertexNumber(), range[x.getVertexNumber()]));

				}
			}
		}

		//Rückgabe
		return range;

	}

	// Knoten Klasse
	class Vertex {

		// Deklaration der Instanzvariablen
		int vertexNumber;
		int distance;

		// Konstruktor
		public Vertex(int v, int d) {
			this.vertexNumber = v;
			this.distance = d;
		}

		// Getter
		public int getVertexNumber() {
			return vertexNumber;
		}

		public int getDistance() {
			return distance;
		}
	}
}
