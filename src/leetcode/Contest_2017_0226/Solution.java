package leetcode.Contest_2017_0226;

import java.util.*;

/**
 * Created by yanghui on 2017/2/26.
 */
public class Solution {

	public boolean valid(int i , int j , int n , int m){
		return i >= 0 && i < n && j >= 0 && j < m;
	}

	public int judge(char[][] board , int x , int y , int n , int m , int[] a , int[] b){
		if(valid(x , y , n , m) && board[x][y] != 'E') return 0;
		int cnt = 0;
		for(int i = 0 ; i < 4 ; i ++){
			if((valid(x + a[i], y + b[i] , n , m) && board[x + a[i]][y + b[i]] == 'M')){
				cnt ++;
			}
		}
		return cnt;
	}

	public char[][] updateBoard(char[][] board, int[] click) {
		int n = board.length;
		if(n == 0){
			return board;
		}
		int m = board[0].length;
		if(board[click[0]][click[1]] == 'M'){
			board[click[0]][click[1]] = 'X';
			return board;
		}else if(board[click[0]][click[1]] == 'E'){
			Queue<int[]> queue = new LinkedList<>();
			queue.add(new int[]{click[0] , click[1]});
			int a[] = new int[]{0 , 0 ,  1 , -1};
			int b[] = new int[]{1 , -1 , 0 , 0};
			boolean[][] visit = new boolean[n][m];
			while(!queue.isEmpty()){
				int[] top = queue.poll();
				if(visit[top[0]][top[1]]) continue;
				char cur = board[top[0]][top[1]];
				visit[top[0]][top[1]] = true;
				if(cur == 'B' || (cur >= '1' && cur <= '8')){
					continue;
				}else if(cur == 'E'){
					int count = judge(board , top[0] , top[1] , n , m , a , b);
					if(count == 0){
						for(int i = 0 ; i < 4 ; i ++){
							if((valid(top[0] + a[i], top[1] + b[i] , n , m))){
								queue.add(new int[]{top[0] + a[i] , top[1] + b[i]});
							}
						}
						board[top[0]][top[1]] = 'B';
					}else{
						board[top[0]][top[1]] = (char)('0' + count);
					}
				}
			}
			return board;
		}else{
			return board;
		}
	}

	class Node{
		Set<Integer> nxt ;
		int idx;
		public Node(int x){
			this.idx = x;
			nxt = new HashSet<>();
		}
		public void add(int x){
			this.nxt.add(x);
		}
	}

	public String findLongestWord(String s, List<String> d) {
		Node[] node = new Node[26];
		for(int i = 0 ; i < 26 ; i ++){
			node[i] = new Node(i);
		}
		int n = s.length();
		for(int i = 0 ; i < n ; i ++){
			for(int j = i + 1 ; j < n ; j ++){
				node[s.charAt(i) - 'a'].add(s.charAt(j) - 'a');
			}
		}
		String ans = "";
		return ans;
	}

	public void run(){

	}

	public static void main(String args[]){
		new Solution().run();
	}
}
