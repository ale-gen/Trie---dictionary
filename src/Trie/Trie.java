package Trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
	
	private Node root;

	public Trie() {
		root = new Node();
		root.setKey(' ');
		root.setLeaf(false);
	}
	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void insert(String s) {
		
		Node node = root;
		
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			int index = ch - 'a';
			if (node.getChildren()[index] == null) {
				Node temp = new Node();
				temp.setKey(ch);
				node.getChildren()[index] = temp;
				node = temp;
				node.setCounter(node.getCounter() + 1);
			} else {
				node = node.getChildren()[index];
				node.setCounter(node.getCounter() + 1);
			}
		}
		node.setEnd(true);
		for (int j = 0; j < node.getAlphabetSize(); j++) {
			if (node.getChildren()[j] != null) {
				node.setLeaf(false);
				break;
			} else {
				node.setLeaf(true);
			}
		}
		
	}
	
	public boolean search(String s) {
		Node node = searchNode(s);
		if (node == null) {
			return false;
		} else {
			if (node.isEnd()) {
				return true;
			}
		}
		return false;
	}
	
	public Node searchNode(String s) {
		Node node = root;
		
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			int index = ch - 'a';
			
			if (node.getChildren()[index] != null) {
				node = node.getChildren()[index];
			} else {
				return null;
			}
		}
		if (node == root) {
			return null;
		}
		return node;
	}
	
	int count = 0;
	
	public int wordsNumber(Node node) {
		
		for (int i = 0; i < node.getAlphabetSize(); i++) {
			
			if (node.getChildren()[i] != null) {
				if (node.getChildren()[i].isEnd()) {
					count++;
				} 
				wordsNumber(node.getChildren()[i]);
				
			}
			
		}
		return count;
	}
	
	int nodeCount = 0;
	
	public int nodesNumber(Node node) {
		
		for (int i = 0; i < node.getAlphabetSize(); i++) {
			if (node.getChildren()[i] != null) {
				nodeCount++;
				nodesNumber(node.getChildren()[i]);
			}
		}
		return nodeCount;
	}
	
	public boolean startsWith(String prefix) {
		if (searchNode(prefix) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	Queue que2 = new Queue(100);
	
	public void frequentPrefix(int n, Node node, int currentChild, int howManyTimes) {
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < node.getAlphabetSize(); j++)  {
				if (node.getChildren()[j] != null) {
					que2.push(node.getChildren()[j]);
					node = node.getChildren()[j];
					int number = node.howManyChildren(node);
					System.out.println(number);
					break;
				}
			}
		}	
	}
	
	public List<String> allWordsWithPrefix(String prefix) {
		
		Node node = root;
		
		List<String> allWords = new ArrayList<String>();
		
		for (int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			int index = ch - 'a';
			if (node.getChildren()[index] != null) {
				node = node.getChildren()[index];
			} else {
				System.out.println("There isn't any words with that prefix.");
				return allWords;
			}
		}
		
		collect(node, prefix, allWords);
		return allWords;
	}
	
	public void collect(Node node, String prefix, List<String> allWords) {
		
		if (node == null) {
			return;
		}
		
		if (node.isEnd()) {
			allWords.add(prefix);
		}
		
		for (Node childNode : node.getChildren()) {
			if (childNode == null) {
				continue;
			}
			char childCharacter = childNode.getKey();
			collect(childNode, prefix + childCharacter, allWords);
		}
	}
	
	public void displayList(List<String> list) {
		for (String s : list) {
			System.out.println(s);
		}
		System.out.println();
	}
	
	public int findCommonPrefix(Node node, int n, int max, char[] c, int depth, List<Prefix> result) {
		
		if (n == 0) {
			if (node.getCounter() >= max) {
				if (node.getCounter() > max) {
					result.clear();
					max = node.getCounter();
				}
				result.add(new Prefix(max, String.valueOf(c)));
			}
			return max;
		}
		
		for (Node child : node.getChildren()) {
			if (child != null) {
				c[depth] = child.getKey();
				max = Math.max(max, findCommonPrefix(child, n - 1, max, c, depth + 1, result));
			}
		}
		return max;
	}
	
	public int findCommonPrefixWithMoreLength(Node node, int n, int max, char[] c, int depth, List<Prefix> result) {
		
		max = findCommonPrefix(node, n, max, c, depth, result);
		
		return max;
	}
	
	public int findCommonPrefixWithLessLength(Node node, int n, int max, char[] c, int depth, List<Prefix> result) {
		
		max = findCommonPrefix(node, 1, max, c, depth, result);
		
		return max;
	}

}
