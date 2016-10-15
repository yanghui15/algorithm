package leetcode.Trapping_Rain_Water_II_407;

import java.util.PriorityQueue;

/**
 * Created by yanghui on 16/10/15.
 */
public class Solution {

	class Node implements Comparable<Node>{
		int x , y;
		int height;
		public Node(int x , int y , int height){
			this.x = x;
			this.y = y;
			this.height = height;
		}

		@Override
		public int compareTo(Node n) {
			return Integer.compare(this.height , n.height);
		}
	}

	public boolean judge(int i , int j , int  n , int m){
		return i >= 0 && i < n && j >= 0 && j < m;
	}

	public int trapRainWater(int[][] heightMap) {
		int n = heightMap.length;
		if(n == 0) return 0;
		int m = heightMap[0].length;
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		boolean[][] visit = new boolean[n][m];

		for(int i = 0 ; i < n ; i ++){
			q.add(new Node(i , 0 , heightMap[i][0]));
			q.add(new Node(i , m - 1 , heightMap[i][m-1]));
			visit[i][0] = true;
			visit[i][m-1] = true;
		}
		for(int i = 0 ; i < m ; i ++){
			q.add(new Node(0 , i , heightMap[0][i]));
			q.add(new Node(n - 1 , i , heightMap[n-1][i]));
			visit[0][i] = true;
			visit[n-1][i] = true;
		}

		int a[] = new int[]{0 , 0 , 1 , -1};
		int b[] = new int[]{1 , -1 , 0 , 0};
		int ans = 0;

		while(!q.isEmpty()){
			Node top = q.poll();
			for(int i = 0 ; i < 4 ; i ++){
				if(judge(top.x + a[i] , top.y + b[i] , n , m) && !visit[top.x + a[i]][top.y + b[i]]){
					visit[top.x + a[i]][top.y + b[i]] = true;
					q.add(new Node(top.x + a[i] , top.y + b[i] , Math.max(heightMap[top.x + a[i]][top.y + b[i]] , top.height)));
					if(heightMap[top.x + a[i]][top.y + b[i]] < top.height){
						ans += top.height - heightMap[top.x + a[i]][top.y + b[i]];
					}
				}
			}
		}

		return ans;
	}

	public void run(){
		int[][] heightMap = new int[][]{{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};

		int result = trapRainWater(heightMap);
		System.out.println(result);
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
