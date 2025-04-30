public class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;
    
    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.character = '\0';
        this.frequency = left.frequency + right.frequency;
        this.left = left;
        this.right = right;
    }
    
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
    
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
    
    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}