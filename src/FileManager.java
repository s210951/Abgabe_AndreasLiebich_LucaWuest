import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

	// Initialisierung und Deklaration der Standartwerte
	public String default_input_path = "input.txt";
	public String default_output_path = "out.txt";

	// Deklaration der Eingabe-/Ausgabepfad Variablen
	private String input_path;
	private String output_path;
	private char ch = 'A';

	// Konstruktor mit Eingabe-/Ausgabepfad
	public FileManager(String input, String output) {
		input_path = input;
		output_path = output;
	}

	// Konstruktor ohne Argumente. Hier werden die Standartwerte verwendet
	public FileManager() {
		input_path = default_input_path;
		output_path = default_output_path;
	}

	// Methode zum Lesen einer Eingabedatei
	public int[][] readInput() {

		// Try Catch Block wird verwendet, um eine mögliche FileNotFoundException abzufangen,
		// die entsteht, falls die Datei nicht gefunden wird
		try {

			// Deklaration und Initialisierung der Java Objektreferenzen
			File myFile = new File(input_path);
			Scanner myScanner = new Scanner(myFile);
			ArrayList<String> input = new ArrayList<String>();

			// Datei wird zeilenweise bis zum Ende der Datei ausgelesen
			// Rückgabewert wird einer ArrayListe hinzugefügt
			while (myScanner.hasNextLine()) {
				input.add(myScanner.nextLine());
			}

			// Variablen Deklaration und Initialisierung
			// Berechnung der Knotenanzahl
			int vertexCount = input.get(0).substring(2, input.get(0).length()).split(" ").length;

			// Variablen Deklaration und Initalisierung
			int[][] output = new int[vertexCount][vertexCount];
			String[] temp = null;

			// String Matrix in Integer Matrix umwandeln
			for (int x = 1; x <= vertexCount; x++) {
				temp = input.get(x).split(" ");
				for (int i = 0; i < temp.length - 1; i++) {
					output[x - 1][i] = Integer.parseInt(temp[i + 1]);
				}
			}

			// Matrix spiegeln, wenn die Eingabe ungerichtet ist
			if (input.get(1).length() < vertexCount) {
				for (int x = 0; x < vertexCount; x++) {
					for (int y = 0; y < vertexCount; y++) {
						output[x][y] = output[y][x];
					}
				}
			}

			// Scanner schließen
			myScanner.close();

			// Rückgabe
			return output;
		}

		// Fehlerbehandlung
		catch (FileNotFoundException e) {
			System.out.println("Die Datei konnte nicht gefunden werden");
			e.printStackTrace();
			return null;
		}
	}

	// Methoden zum Schreiben einer Ausgabedatei
	public void writeOutput(int[][] input) {

		// Überprüfen, ob Daten existieren
		if (input != null) {

			// Try Catch Block wird verwendet, um eine mögliche IOException abzufangen,
			// die entsteht, falls die Datei nicht erstellt werden konnte
			try {

				// Deklaration und Initialisierung der Java Objektreferenzen
				File myFile = new File(output_path);

				// Überprüfen, ob die Datei erstellt wurde
				if (myFile.createNewFile()) {

					// Text-Rückgabe
					System.out.println("Datei wurde erzeugt");

					// Deklaration und Initialisierung der Java Objektreferenzen
					FileWriter myWriter = new FileWriter(output_path);

					// Schreibe die erste Zeile der Matrix mit aufeinanderfolgenden Buchstaben
					myWriter.write(" ");
					for (int x = 0; x < input.length; x++) {
						myWriter.write(" ");
						myWriter.write(ch + x);
					}

					// Schleife, die die einzelen Zeile durchläuft
					for (int y = 0; y < input.length; y++) {

						// Schreibe aufeinanderfolgende Buchstaben an den Anfang jeder Zeile
						myWriter.write(System.lineSeparator());
						myWriter.write(ch + y);

						// Schreibe die eingebenen Werte in die Zeile
						for (int x = 0; x < input.length; x++) {
							myWriter.write(" " + input[y][x]);
						}
					}

					// Writer schließen
					myWriter.close();

					// Text-Rückgabe, falls keine Datei erstellt wurde
				} else {
					System.out.println("Datei existiert bereits");
				}

				// Fehlerbehandlung
			} catch (IOException e) {
				System.out.println("Beim schreiben der Datei ist ein Fehler aufgetreten");
				e.printStackTrace();
			}

			// Text-Rückgabe, falls die Eingabe null ist
		} else {
			System.out.println("Es exisitieren keine Daten");
		}
	}

	// Methoden zum Schreiben einer Ausgabedatei
	public void writeOutput(int[] input) {

		// Überprüfen, ob Daten existieren
		if (input != null) {

			// Try Catch Block wird verwendet um eine mögliche IOException abzufangen,
			// die entsteht, falls die Datei nicht erstellt werden konnte
			try {

				// Deklaration und Initialisierung der Java Objektreferenzen
				File myFile = new File(output_path);

				// Überprüfen, ob die Datei erstellt wurde
				if (myFile.createNewFile()) {

					// Text-Rückgabe
					System.out.println("Datei wurde erzeugt");

					// Deklaration und Initialisierung der Java Objektreferenzen
					FileWriter myWriter = new FileWriter(output_path);

					// Schreibe die erste Zeile der Matrix
					myWriter.write("  " + ch);

					// Schreibe aufeinanderfolgende Buchstaben an den Anfang jeder Zeile,
					// gefolgt von den eingebenen Werten
					for (int y = 0; y < input.length; y++) {
						myWriter.write(System.lineSeparator());
						myWriter.write(ch + y);
						myWriter.write(" " + input[y]);
					}

					// Writer schließen
					myWriter.close();

					// Textrückgabe, falls keine Datei erstellt wurde
				} else {
					System.out.println("Datei existiert bereits");
				}

				// Fehlerbehandlung
			} catch (IOException e) {
				System.out.println("Beim schreiben der Datei ist ein Fehler aufgetreten");
				e.printStackTrace();
			}

			// Textrückgabe, falls die Eingabe null ist
		} else {
			System.out.println("Es exisitieren keine Daten");
		}
	}
}
