package leetcode.Word_Search_II_212;

import data_structure.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yanghui on 16/10/13.
 */
public class Solution {

	class TrieNode{
		char cur;
		TrieNode[] next;
		boolean flag = false;
		public TrieNode(char cur) {
			this.cur = cur;
			this.next = new TrieNode[26];
			this.flag = false;
		}

		@Override
		public String toString() {
			return "TrieNode{" +
					"cur=" + cur +
					", next=" + Arrays.toString(next) +
					", flag=" + flag +
					'}';
		}
	}

	public void add(TrieNode root , String str , int idx){
		if(idx >= str.length()) return;
		if(root.next[str.charAt(idx) - 'a'] == null){
			root.next[str.charAt(idx) - 'a'] = new TrieNode(str.charAt(idx));
		}
		if(idx == str.length() - 1){
			root.next[str.charAt(idx) - 'a'].flag = true;
		}else
			add(root.next[str.charAt(idx) - 'a'] , str , idx + 1);
	}

	public void dfs(char[][] board , TrieNode root , int idx , int idy ,
	                StringBuilder sb , List<String> result , boolean[][] visit ,
	                int n , int m , int up_down[] , int[] left_right){
		if(root == null) return;
		if(visit[idx][idy]) return;
		if(root.cur != board[idx][idy]) return;
		sb.append(root.cur);
		visit[idx][idy] = true;
		if(root.flag){
			result.add(sb.toString());
			root.flag = false;
		}
		for(int i = 0 ; i < 4 ; i ++){
			int cur_i = idx + up_down[i];
			int cur_j = idy + left_right[i];
			if(cur_i >= 0 && cur_i < n && cur_j >= 0 && cur_j < m) {
				dfs(board, root.next[board[idx + up_down[i]][idy + left_right[i]] - 'a'], idx + up_down[i],
						idy + left_right[i], sb, result, visit, n, m, up_down, left_right);
			}
		}
		visit[idx][idy] = false;
		sb.deleteCharAt(sb.length() - 1);
	}

	public List<String> findWords(char[][] board, String[] words) {
		List<String> result = new ArrayList<String>();
		int n = board.length;
		if(n == 0) return result;
		int m = board[0].length;
		boolean visit[][] = new boolean[n][m];
		TrieNode root = new TrieNode('.');
		for(int i = 0 ; i < words.length ; i ++){
			add(root , words[i] , 0);
		}
		StringBuilder sb = new StringBuilder();
		int up_down[] = new int[]{0 , 0 , 1 , -1};
		int left_right[] = new int[]{1 , -1 , 0 , 0};
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < m ; j ++){
				for(int k = 0 ; k < 26 ; k ++){
					if(root.next[k] != null){
						dfs(board , root.next[k] , i , j  , sb , result , visit ,  n , m , up_down , left_right);
					}
				}
			}
		}

		return result;
	}

	public void run(){

		char[][] board = {{'o' , 'a' , 'a' , 'n'},
				{'e' , 't' , 'a' , 'e' },
				{'i' , 'h' , 'k' , 'r'},
				{'i' , 'f' , 'l' , 'v'}};
		String[] words = {"oath","pea","eat","rain"};
		List<String> result = findWords(board , words);
		for(String i : result){
			System.out.println(i);
		}
	}


	public static void main(String args[]){
		new Solution().run();
	}
}
