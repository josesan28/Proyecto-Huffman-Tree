import java.io.*;
import java.util.*;

public class Decompressor {
    
    public static void decompress(String inputFilePath, String outputFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            
            int numOfChars = Integer.parseInt(reader.readLine());
            
            Map<String, Character> decodingMap = new HashMap<>();
            for (int i = 0; i < numOfChars; i++) {
                String line = reader.readLine();
                char character = line.charAt(0);
                String code = line.substring(2);
                decodingMap.put(code, character);
            }
            
            String compressedText = reader.readLine();
            reader.close();
            
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
        }
    }
}