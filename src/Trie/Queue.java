package Trie;

public class Queue {
	
	int maxSize;
	Node[] queue;
	int numberOfChar;
	int first;
	int last;
	
	public Queue(int size) {
		first = 0;
		last = -1;
		maxSize = size;
		queue = new Node[maxSize];
	}
	
	public void push(Node node) {
		if (last == maxSize - 1) {
			last = -1;
		}
		queue[++last] = node;
		numberOfChar++;
	}
	
	public Node pop() {
		
		Node temp = queue[first++];
		if (first == maxSize) 
			first = 0;
		numberOfChar--;
		return temp;
	}
	
	public Node getChar() {
		return queue[first];
	}
	
	public int size() {
		return numberOfChar;
	}
	
	public boolean isEmpty() {
		return numberOfChar == 0;
	}
	
	public boolean isFull() {
		return numberOfChar == maxSize;
	}

}
