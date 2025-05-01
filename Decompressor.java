/**
 *  @author José Manuel Sanchez Hernández - 24092
 *  @version 1.0
 *  Descripción: Clase que contiene un método para descomprimir un archivo comprimido utilizando el algoritmo de Huffman.
 *  Fecha de creación: 29/04/2025
 *  Fecha de última modificación: 30/04/2025
 */

import java.io.*;
import java.util.*;

public class Decompressor {
    
    /**
     * Descomprime un archivo comprimido utilizando el algoritmo de Huffman.
     * @param inputFilePath Ruta del archivo comprimido.
     * @param outputFilePath Ruta del archivo de salida descomprimido.
     */
    public static void decompress(String inputFilePath, String outputFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            
            String numCharsLine = reader.readLine();
            if (numCharsLine == null || numCharsLine.isEmpty()) {
                throw new IOException("Archivo comprimido inválido: falta el número de caracteres");
            }
            int numOfChars = Integer.parseInt(numCharsLine);
            
            Map<String, Character> decodingMap = new HashMap<>();
            for (int i = 0; i < numOfChars; i++) {
                String line = reader.readLine();
                
                if (line == null || line.isEmpty()) {
                    throw new IOException("Archivo de compresión dañado en la tabla de códigos");
                }
                
                int colonIndex = line.indexOf(':');
                if (colonIndex <= 0) {
                    throw new IOException("Formato inválido en la tabla de códigos: " + line);
                }
                
                String charPart = line.substring(0, colonIndex);
                String code = line.substring(colonIndex + 1);
                
                char character;
                if (charPart.equals("\\n")) {
                    character = '\n';
                } else if (charPart.equals("\\r")) {
                    character = '\r';
                } else if (charPart.equals("\\s")) {
                    character = ' ';
                } else if (charPart.equals("\\c")) {
                    character = ':';
                } else if (charPart.equals("\\b")) {
                    character = '\\';
                } else {
                    character = charPart.charAt(0);
                }
                
                decodingMap.put(code, character);
            }
            
            StringBuilder compressedTextBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                compressedTextBuilder.append(line);
            }
            reader.close();
            
            String compressedText = compressedTextBuilder.toString();
            
            StringBuilder decompressedText = new StringBuilder();
            StringBuilder currentCode = new StringBuilder();
            
            for (char bit : compressedText.toCharArray()) {
                currentCode.append(bit);
                if (decodingMap.containsKey(currentCode.toString())) {
                    decompressedText.append(decodingMap.get(currentCode.toString()));
                    currentCode = new StringBuilder();
                }
            }
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                writer.write(decompressedText.toString());
            }
            
            System.out.println("Texto descomprimido exitosamente.");
            
            
        } catch (IOException e) {
            System.err.println("Error al descomprimir: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error al leer el formato del archivo comprimido: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}