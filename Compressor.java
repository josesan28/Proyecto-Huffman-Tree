/**
 *  @author José Manuel Sanchez Hernández - 24092
 *  @version 1.0
 *  Descripción: Clase que contiene un método para comprimir un texto utilizando el algoritmo de Huffman.
 *  Fecha de creación: 29/04/2025
 *  Fecha de última modificación: 30/04/2025
 */

import java.io.*;
import java.util.*;

public class Compressor {
    
    /**
     * Comprime un texto utilizando el algoritmo de Huffman y lo guarda
     * @param text Texto a comprimir
     * @param outputFilePath Ruta del archivo de salida comprimido
     */
    public static void compress(String text, String outputFilePath) {
        try {
            HuffmanTree tree = new HuffmanTree(text);
            Map<Character, String> encodingMap = tree.getEncodingMap();
            
            StringBuilder compressedText = new StringBuilder();
            for (char c : text.toCharArray()) {
                compressedText.append(encodingMap.get(c));
            }
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                writer.write(encodingMap.size() + "\n");
                
                for (Map.Entry<Character, String> entry : encodingMap.entrySet()) {
                    char c = entry.getKey();
                    String code = entry.getValue();
                    
                    if (c == '\n') {
                        writer.write("\\n:" + code + "\n");
                    } else if (c == '\r') {
                        writer.write("\\r:" + code + "\n");
                    } else if (c == ' ') {
                        writer.write("\\s:" + code + "\n");
                    } else if (c == ':') {
                        writer.write("\\c:" + code + "\n");
                    } else if (c == '\\') {
                        writer.write("\\b:" + code + "\n");
                    } else {
                        writer.write(c + ":" + code + "\n");
                    }
                }
                
                writer.write(compressedText.toString());
            }
            
        } catch (IOException e) {
            System.err.println("Error al comprimir: " + e.getMessage());
        }
    }
}