/**
 *  @author José Manuel Sanchez Hernández - 24092
 *  @version 1.0
 *  Descripción: Clase principal que ejecuta el programa de compresión y descompresión de archivos de texto.
 *  Fecha de creación: 01/04/2025
 *  Fecha de última modificación: 01/05/2025
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String option = "";
        Scanner scanner = new Scanner(System.in);

        while (!option.equals("3")) {
            System.out.println("\nCompresor y descompresor de archivos de texto\n");
            System.out.println("1. Comprimir archivo");
            System.out.println("2. Descomprimir archivo");
            System.out.println("3. Salir");

            System.out.print("\nIngresa una opción: ");

            option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("\nIngresa el nombre del archivo a comprimir: ");
                    String fileName = scanner.nextLine();
                    String text = readFromFile(fileName);
                    System.out.print("\nIngresa el nombre del nuevo archivo comprimido: ");
                    String outputCompressed = scanner.nextLine();
                    Compressor.compress(text, outputCompressed);
                    System.out.println("\nArchivo comprimido guardado como " + outputCompressed);
                    break;

                case "2":
                    System.out.print("\nIngresa el nombre del archivo a descomprimir: ");
                    String compressedFileName = scanner.nextLine();
                    String outputDecompressed = "output.txt";
                    Decompressor.decompress(compressedFileName, outputDecompressed);
                    System.out.println("\nArchivo descomprimido guardado como " + outputDecompressed);
                    break;

                case "3":
                    System.out.println("\nSaliendo...");
                    break;
                default:
                    System.out.println("\nOpción inválida");
                    break;
            }    
        }
        scanner.close();
        
    }

    /**
     * Método para leer el contenido de un archivo y devolverlo como una cadena.
     * @param filePath Ruta del archivo a leer.
     * @return Contenido del archivo como una cadena.
     */
     private static String readFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
        return content.toString();
    }
}