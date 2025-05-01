/**
 *  @author José Manuel Sanchez Hernández - 24092
 *  @version 1.0
 *  Descripción: Clase que representa un árbol de Huffman y proporciona métodos para construir el árbol y generar códigos de Huffman.
 *  Fecha de creación: 29/04/2025
 *  Fecha de última modificación: 30/04/2025
 */

import java.util.*;

public class HuffmanTree {
    private HuffmanNode root;
    private Map<Character, String> encodingMap;
    
    /**
     * Constructor que construye el árbol de Huffman a partir del texto dado.
     * @param text Texto con el que se construirá el árbol de Huffman.
     */
    public HuffmanTree(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode(left, right);
            priorityQueue.add(parent);
        }
        
        root = priorityQueue.poll();
        
        encodingMap = new HashMap<>();
        generateCodes(root, "");
    }
    
    /**
     * Método recursivo que genera los códigos de Huffman para cada carácter en el árbol.
     * @param node Nodo actual del árbol.
     * @param code Código de Huffman acumulado hasta el nodo actual.
     */
    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) return;
        
        if (node.isLeaf()) {
            encodingMap.put(node.character, code.length() > 0 ? code : "0");
        }
        
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }
    
    /**
     * Método que devuelve el mapa de codificación de Huffman.
     * @return Mapa de codificación de Huffman
     */
    public Map<Character, String> getEncodingMap() {
        return encodingMap;
    }
    
    /**
     * Método que devuelve la raíz del árbol de Huffman.
     * @return Raíz del árbol de Huffman
     */
    public HuffmanNode getRoot() {
        return root;
    }
    
}