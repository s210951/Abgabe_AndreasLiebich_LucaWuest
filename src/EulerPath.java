import java.util.*;

class EulerPath {

	// Methode zum Finden eines vollständigen Euler-Pfades
	int[][] findpath(int[][] input) {

		// Deklaration und Initalisierung der Variablen
		int vertexCount = input.length;
		int current = 0;
		LinkedList<Integer> neighbourCount = new LinkedList<>();
		Stack<Integer> stack = new Stack<>();
		LinkedList<Integer> path = new LinkedList<>();
		int[][] output = new int[input.length][input.length];
		
		// Textrückgabe
		System.out.println("Eulerpfad: ");

		// Zeilen der Eingabematrix summieren und Werte zu LinkedList hinzufügen
		for (int x = 0; x < vertexCount; x++) {
			neighbourCount.add(summarize(input[x], 0));
		}

		// Schleife läuft solange noch ein Eintrag auf dem Stack ist oder
		// der aktuelle Knoten noch einen Nachbarn hat
		while (!stack.isEmpty() || summarize(input[current], 0) != 0) {

			// Wenn der aktuelle Knoten keinen Nachbarn hat
			if (summarize(input[current], 0) == 0) {

				// Textrückgabe
				char c = 'A';
				System.out.print((char) (c + current) + "-");
				path.add(current);
				current = stack.pop();

			} else {
				// Schleife durchläuft einzelne Knoten
				for (int x = 0; x < vertexCount; x++) {

					// Überprüfe, ob der aktuelle Knoten einen Nachbarn hat
					if (input[current][x] == 1) {

						// Füge aktuellen Knoten zum Stack hinzu
						stack.add(current);

						// Entfernt Verbindung aus Eingabematrix
						input[current][x] = 0;
						input[x][current] = 0;
						current = x;
						break;
					}
				}
			}
		}

		// Textrückgabe
		System.out.print("A\n\n");
		
		// Schreibe path in eine Integer Matrix
		for (int x = 0; x < path.size() - 1; x++) {
			output[path.get(x)][path.get(x + 1)] = x + 1;
		}
		output[path.getLast()][path.get(0)] = path.size();

		// Rückgabe
		return output;

	}

	// Methode zum Summieren von Integer Arrays mit Startwert
	int summarize(int[] arr, int sum) {
		for (int x = 0; x < arr.length; x++)
			sum += arr[x];
		return sum;
	}
}