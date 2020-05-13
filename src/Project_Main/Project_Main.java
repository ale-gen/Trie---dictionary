package Project_Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Trie.Prefix;
import Trie.Trie;

public class Project_Main {
	
	static String[] keys;
	
	public static void main(String[] args) throws IOException {
		
		service();
	}
	
	public static String[] getWordsFromFile() {
       
        final File file = new File("dictionary.txt");
        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
            	//System.out.println(line);
                keys[i] = line;
                i++;
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keys;
    }
	
	public static String[] readSizeFile() {
	       
        final File file = new File("dictionary.txt");
        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            int length = 0;;
            while ((line = bufferedReader.readLine()) != null) {
            	length++;
            }
            keys = new String[length];
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keys;
    }
	
	public static long getFileSize(String fileName) {
		File file = new File(fileName);
		if (!file.exists() || !file.isFile()) {
			System.out.println("The file is not found!");
			return -1;
		}
		return file.length();
	}
	
	public static void service() throws IOException {
		
		Trie trie = new Trie();
		
		readSizeFile();
		getWordsFromFile();
		
		for (int i = 0; i < keys.length; i++) {
			trie.insert(keys[i]);
		}
		int end = 1;
		
		while (end != 0) {
			System.out.println("What do you want to do? ");
			System.out.println("\n[1] - search the word \n[2] - search all words with prefix \n[3] - return the words number \n[4] - return the nodes number \n[5] - return the most frequently prefix with n length \n[6] - return the file (dictionary) size");
			int choice = getInt();
			
			switch (choice) {
			
			case 1: System.out.println("Which word do yuo want to find? ");
					String s1 = getString();
					if (trie.search(s1)) {
						System.out.println("The word is present in the tree");
					} else {
						System.out.println("The word isn't exist in the tree");
					}
					break;
					  
			case 2: System.out.println("What's the prefix do you want to search? ");
					String s2 = getString();
					List<String> list = trie.allWordsWithPrefix(s2);
					trie.displayList(list);
					System.out.println("Number of words starts with that prefix: " + list.size());
					break;
					  
			case 3: int number = trie.wordsNumber(trie.getRoot());
					System.out.println("How many words does the tree have? ");
					System.out.println("There are " + number + " words.");
					break;
					  
			case 4: int nodesNumber = trie.nodesNumber(trie.getRoot());
					System.out.println("How many nodes does the tree have? ");
					System.out.println("There are " + nodesNumber + " nodes.");
					break;
					
			case 5:	System.out.println("Enter the length of prefix: ");
					int n = getInt();
					List<Prefix> result = new ArrayList<Prefix>();
					int max = trie.findCommonPrefix(trie.getRoot(), n, 0, new char[n], 0, result);
					System.out.println("Max frequency: " + max);
					for (Prefix p : result) {
						System.out.println(p.value);
					}
					break;
					
			case 6: long size = getFileSize("dictionary.txt");
					System.out.println("Rozmiar pliku: " + size / 1024);
					break;
					
			case 0: end = 0;
					break;
					
			default: System.out.println("The data is incorrect!");
			}
		}
	}
	
	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
	
	public static String getString() throws IOException {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		return s;
	}
	
	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}
	

}
