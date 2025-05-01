/**
 *  @author José Manuel Sanchez Hernández - 24092
 *  @version 1.0
 *  Descripción: Clase que representa un nodo en el árbol de Huffman.
 *  Fecha de creación: 29/04/2025
 *  Fecha de última modificación: 30/04/2025
 */

public class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;
    
    /**
     * Constructor para crear un nodo de Huffman.
     * @param left Nodo izquierdo.
     * @param right Nodo derecho.
     */
    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.character = '\0';
        this.frequency = left.frequency + right.frequency;
        this.left = left;
        this.right = right;
    }
    
    /**
     * Constructor para crear un nodo de Huffman con un carácter y su frecuencia.
     * @param character Carácter del nodo.
     * @param frequency Frecuencia del carácter.
     */
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
    
    /**
     * Método que devuelve true si el nodo es una hoja, y false en caso contrario.
     * @return
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
    
    /**
     * Método que compara dos nodos de Huffman por su frecuencia.
     */
    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}