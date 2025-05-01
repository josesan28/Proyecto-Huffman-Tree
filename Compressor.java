import java.io.*;
import java.util.*;

public class Compressor {
    
    public static void compress(String text, String outputFilePath) {
        try {
            HuffmanTree tree = new HuffmanTree(text);
            Map<Character, String> encodingMap = tree.getEncodingMap();
            
            StringBuilder compressedText = new StringBuilder();
            for (char c : text.toCharArray()) {
                compressedText.append(encodingMap.get(c));
            }
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                // Escribir el número de caracteres diferentes
                writer.write(encodingMap.size() + "\n");
                
                for (Map.Entry<Character, String> entry : encodingMap.entrySet()) {
                    writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
                }
                
                writer.write(compressedText.toString());
            }
                    
        } catch (IOException e) {
            System.err.println("Error al comprimir: " + e.getMessage());
        }
    }
}