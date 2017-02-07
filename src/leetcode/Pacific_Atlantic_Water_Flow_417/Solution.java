package leetcode.Pacific_Atlantic_Water_Flow_417;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yanghui on 2017/2/7.
 */
public class Solution {

	public boolean judge(int x , int y , int n , int m){
		return x >= 0 && x < n && y >= 0 && y < m;
	}

	public void bfs(int[][] matrix , int n , int m , Queue<int[]> queue , boolean[][] visit){
		int[] up = new int[]{0 , 0 , 1,  -1};
		int[] lr = new int[]{1 , -1 , 0 , 0};
		while(!queue.isEmpty()){
			int[] top = queue.poll();
			if(visit[top[0]][top[1]]) continue;
			visit[top[0]][top[1]] = true;
			for(int i = 0 ; i < 4 ; i ++){
				int x = top[0] + up[i];
				int y = top[1] + lr[i];
				if(judge(x , y , n , m) && matrix[x][y] >= matrix[top[0]][top[1]]){
					queue.add(new int[]{x , y});
				}
			}
		}
	}

	public List<int[]> pacificAtlantic(int[][] matrix) {
		List<int[]> ans = new ArrayList<>();
		int n = matrix.length;
		if(n == 0) return ans;
		int m = matrix[0].length;
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visit1 = new boolean[n][m];
		for(int i = 0 ; i < n ; i ++){
			queue.add(new int[]{i , 0});
		}
		for(int i = 0 ; i < m ; i ++){
			queue.add(new int[]{0 , i});
		}
		bfs(matrix , n , m , queue , visit1);
		boolean[][] visit2 = new boolean[n][m];
		for(int i = 0 ; i < n ; i ++){
			queue.add(new int[]{i , m-1});
		}
		for(int i = 0 ; i < m ; i ++){
			queue.add(new int[]{n-1 , i});
		}
		bfs(matrix , n , m , queue , visit2);
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < m ; j ++){
				if(visit1[i][j] && visit2[i][j])
					ans.add(new int[]{i , j});
			}
		}
		return ans;
	}

	public void run(){
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
