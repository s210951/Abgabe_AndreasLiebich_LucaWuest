import java.util.Scanner;

public class Planungstool {

	// Main Methode
	public static void main(String[] args) {

		// Deklaration und Initalisierung der Variablen
		int task;
		String input_path;
		Scanner myScanner = new Scanner(System.in);

		// Textausgabe
		System.out.println("Planungstool\n");
		System.out.println("Bitte waehlen Sie, um welches Problem es sich handelt\n");
		System.out.println("Nummer\tTitel");
		System.out.println("1\tStrassenbau");
		System.out.println("2\tWasserversorgung");
		System.out.println("3\tStromversorgung");
		System.out.println("4\tFeuerwerksplan");
		System.out.println("5\tBrieftraeger Route");
		System.out.println("6\tVerkehrsplan");
		System.out.println("7\tAufgabenverteilung");

		// Eingabe Wiederholung bei ungueltigen Eingaben
		while (true) {
			System.out.print("\nProblemnummer: ");

			// Eingabe
			task = myScanner.nextInt();
			if ((task >= 1 && task <= 7)) {
				break;
			}
			System.out.println("Die gewaehlte Eingabe ist ungueltig");
		}

		// Textausgabe
		switch (task) {
		case 1: {
			System.out.println("\nStrassenbau Planung");
			System.out.println("Berechnung des minimalen Spannbaums aus");
			System.out.println("einem ungerichteten und gewichteten Graphen");
			System.out.println("durch Verwendung des Prim Algorithmus");
			break;
		}
		case 2: {
			System.out.println("\nWasserversorgung Planung");
			System.out.println("Berechnung des gesamtem Wasserflusses aus einem");
			System.out.println("gerichteten und gewichteten Graphen durch");
			System.out.println("Verwendung des Edmonds–Karp Algorithmus");
			break;
		}
		case 3: {
			System.out.println("\nStromversorgung Planung");
			System.out.println("Berechnung des minimalen Spannbaums aus");
			System.out.println("einem ungerichteten und gewichteten Graphen");
			System.out.println("durch Verwendung des Prim Algorithmus");
			break;
		}
		case 4: {
			System.out.println("\nFeuerwerksplan Planung");
			System.out.println("Berechnung des kuerzesten Weges aus");
			System.out.println("einem ungerichteten und gewichteten Graphen");
			System.out.println("durch Verwendung des Dijkstra Algorithmus");
			break;
		}
		case 5: {
			System.out.println("\nBrieftraeger Routen Planung");
			System.out.println("Berechnung eines Eulerpfades aus einem");
			System.out.println("ungerichteten und ungewichteten Graphen");
			System.out.println("durch Verwendung des Hierholzer Algorithmus");
			break;
		}
		case 6: {
			System.out.println("\nVerkehrsplan Planung");
			System.out.println("Berechnung des gesamtem Verkehrsflusses aus einem");
			System.out.println("gerichteten und gewichteten Graphen durch");
			System.out.println("Verwendung des Edmonds–Karp Algorithmus");
			break;
		}
		case 7: {
			System.out.println("\nAufgabenverteilung Planung");
			System.out.println("Berechnung des gesamtem Aufgabenflusses aus einem");
			System.out.println("gerichteten und ungewichteten Graphen durch");
			System.out.println("Verwendung des Edmonds–Karp Algorithmus");
			break;
		}
		}

		// Textausgabe
		System.out.print("\nBitte geben Sie den Dateinamen Ihrer Graphdatei ein: ");

		// Eingabe
		input_path = myScanner.next();

		System.out.println();

		// Deklaration und Initialisierung der Java Objektreferenzen
		FileManager fm = new FileManager(input_path + ".txt", input_path + "_output.txt");

		// Graph aus Datei laden
		int[][] graph = fm.readInput();

		// Aufgaben Individuelle Ausgabe
		switch (task) {
		case 1: {
			// Strassenbau
			System.out.println("Kuerzestes Strassennetz:");
			MinSpanningTree t = new MinSpanningTree();
			int[][] output = t.findMST(graph);
			writeMatrix(output);
			System.out.println("Dateiausgabe:");
			fm.writeOutput(output);
			break;
		}
		case 2: {
			// Wasserversorgung
			MaximumFlow m = new MaximumFlow();
			int x, y;
			System.out.print("Bitte waehlen Sie den Wasserwerkknoten: ");
			x = myScanner.nextInt();
			System.out.print("Bitte waehlen Sie den Supermarktknoten: ");
			y = myScanner.nextInt();
			int[][] output = m.findMaxFlow(graph, x, y);
			writeMatrix(output);
			System.out.println("Dateiausgabe:");
			fm.writeOutput(output);
			break;
		}
		case 3: {
			// Stromversorgung
			System.out.println("Kuerzestes Stromnetz:");
			MinSpanningTree t = new MinSpanningTree();
			int[][] output = t.findMST(graph);
			writeMatrix(output);
			System.out.println("Dateiausgabe:");
			fm.writeOutput(output);
			break;
		}
		case 4: {
			// Feuerwerksplan
			int x;
			char c = 'A';
			System.out.print("Bitte waehlen Sie den Zuendschnurknoten: ");
			x = myScanner.nextInt();
			ShortestPath sp = new ShortestPath();
			int[] output = sp.findShortestPath(graph, x);
			System.out.println("\nKnoten\tEntfernung");
			for (x = 0; x < output.length; x++) {
				System.out.println((char) (c + x) + "\t" + output[x]);
			}
			System.out.println();
			System.out.println("Dateiausgabe:");
			fm.writeOutput(output);
			break;
		}
		case 5: {
			// Brieftraeger Route
			EulerPath ep = new EulerPath();
			int[][] output = ep.findpath(graph);
			writeMatrix(output);
			System.out.println("Dateiausgabe:");
			fm.writeOutput(output);
			break;
		}
		case 6: {
			// Verkehrsplan
			int x, y;
			System.out.print("Bitte waehlen Sie den Autobahnknoten: ");
			x = myScanner.nextInt();
			System.out.print("Bitte waehlen Sie den Parkplatzknoten: ");
			y = myScanner.nextInt();
			MaximumFlow m = new MaximumFlow();
			int[][] output = m.findMaxFlow(graph, x, y);
			writeMatrix(output);
			System.out.println("Dateiausgabe:");
			fm.writeOutput(output);
			break;
		}
		case 7: {
			// Aufgabenverteilung
			MaximumFlow m = new MaximumFlow();
			int[][] output = m.findMaxFlow(graph, 0, graph.length - 1);
			writeMatrix(output);
			System.out.println("Dateiausgabe:");
			fm.writeOutput(output);
			break;
		}
		}
		//Scanner schließen
		myScanner.close();
	}

	// Methode zum Ausgeben einer 2d-Matrix
	private static void writeMatrix(int[][] data) {

		// Deklaration und Initalisierung der Variablen
		int vertexCount = data.length;
		char c = 'A';

		// Textausgabe
		System.out.print("Ausgegebene Matrix:\n ");
		
		//Formatierung der Ausgabematrix
		for (int x = 0; x < vertexCount; x++) {
			System.out.print(" ");
			System.out.print((char) (c + x));
		}
		System.out.println();
		for (int x = 0; x < vertexCount; x++) {
			System.out.print((char) (c + x));
			for (int y = 0; y < vertexCount; y++) {
				System.out.print(" " + data[x][y]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
