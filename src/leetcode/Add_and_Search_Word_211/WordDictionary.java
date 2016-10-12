package leetcode.Add_and_Search_Word_211;

import java.util.Scanner;

/**
 * Created by yanghui on 16/10/6.
 */
public class WordDictionary {

	class Node{
		char cur;
		Node[] next;
		boolean flag;
		public Node(char cur){
			this.cur = cur;
			flag = false;
			next = new Node[26];
		}
	}

	Node root;

	public WordDictionary(){
		root = new Node('.');
	}

	public void add(String word , int idx , Node rt){
//		System.out.println(word + " "+rt.cur + " "+word.charAt(idx) + " "+idx);
		if(idx == word.length()){
			rt.flag = true;
			return;
		}
		if(rt.next[word.charAt(idx) - 'a'] == null){
			rt.next[word.charAt(idx) - 'a'] = new Node(word.charAt(idx));
		}
		add(word , idx + 1 , rt.next[word.charAt(idx) - 'a']);
	}

	// Adds a word into the data structure.
	public void addWord(String word) {
		if(word.length() == 0) return;
		add(word , 0 , root);
	}

	public boolean search(String word , int idx , Node rt){
		if(rt == null) return false;
		if(idx == word.length()){
			return rt.flag;
		}
		if(word.charAt(idx) != '.'){
			return search(word , idx + 1 , rt.next[word.charAt(idx) - 'a']);
		}else{
			for(int i = 0 ; i < 26 ; i ++){
				boolean result = search(word , idx + 1 , rt.next[i]);
				if(result) return true;
			}
			return false;
		}
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		return search(word , 0 , root);
	}

	public void run(){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		for(int i = 0 ; i < n ; i ++){
			String str = scan.next();
			addWord(str);
		}
		int m = scan.nextInt();
		for(int i = 0 ; i < m ; i ++){
			String str = scan.next();
			System.out.println(search(str));
		}
	}

	public static void main(String args[]){
		new WordDictionary().run();
	}

}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
