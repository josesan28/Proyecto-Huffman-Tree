import java.util.*;

public class HuffmanTree {
    private HuffmanNode root;
    private Map<Character, String> encodingMap;
    
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
    
    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) return;
        
        if (node.isLeaf()) {
            encodingMap.put(node.character, code.length() > 0 ? code : "0");
        }
        
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }
    
    public Map<Character, String> getEncodingMap() {
        return encodingMap;
    }
    
    public HuffmanNode getRoot() {
        return root;
    }
    
    public void printTree() {
        System.out.println("Tabla de códigos Huffman:");
        for (Map.Entry<Character, String> entry : encodingMap.entrySet()) {
            System.out.println("'" + entry.getKey() + "' -> " + entry.getValue());
        }
    }
}