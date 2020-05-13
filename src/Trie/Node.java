package Trie;

public class Node {
	
	private static final int ALPHABET_SIZE = 26;
	
	private Node[] children;
	private boolean isEnd;
	private char key;
	private boolean isLeaf;
	private int counter;
	
	public Node() {
		children = new Node[ALPHABET_SIZE];
	}
	
	public Node(int key) {
		children = new Node[ALPHABET_SIZE];
	}
	
	public Node(int key, char ch) {
		children = new Node[ALPHABET_SIZE];
	}

	public static int getAlphabetSize() {
		return ALPHABET_SIZE;
	}

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}

	public Node[] getChildren() {
		return children;
	}

	public void setChildren(Node[] children) {
		this.children = children;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
	
	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int howManyChildren(Node node) {
		int number = 0;
		
		for (int i = 0; i < node.getAlphabetSize(); i++) {
			if (node.getChildren()[i] != null) {
				number++;
			}
		}
		return number;
	}
}

