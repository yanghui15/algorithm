package leetcode.Contest_1120;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yanghui on 16/11/20.
 */
public class Solution {

	public class point{
		int x , y;
		public point(int x , int y){
			this.x = x ;
			this.y = y ;
		}
	}

	public boolean judge(int x , int y , int n , int m){
		return x >= 0 && x < n && y >= 0 && y < m;
	}

	public int islandPerimeter2(int[][] grid) {
		if(grid == null) return 0;
		int n = grid.length;
		if(n == 0) return 0;
		int m = grid[0].length;
		point start = null;
		out:
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < m ; j ++){
				if(grid[i][j] == 1){
					start = new point(i , j);
					break out;
				}
			}
		}
		if(start == null) return 0;
		int ans = 4;
		Queue<point> q = new LinkedList<point>();
		boolean visit[][] = new boolean[n][m];
		int a[] = new int[]{1 , -1 , 0 , 0};
		int b[] = new int[]{0 , 0 , 1 , -1};
		q.add(start);
		while(!q.isEmpty()){
			point top = q.poll();
			if(visit[top.x][top.y]) {
				ans -= 2;
				continue;
			}
			for(int i = 0 ; i < 4 ; i ++){
				if(judge(top.x + a[i] , top.y + b[i] , n , m) && !visit[top.x + a[i]][top.y + b[i]]
						&& grid[top.x + a[i]][top.y + b[i]] == 1){
					q.add(new point(top.x + a[i] , top.y + b[i]));
					ans += 2;
				}
			}
		}
		return ans;
	}

	public long cal(int[] nums , int val){
		long ans = 0;
		for(int i = 0 ; i < nums.length ; i ++){
			ans += (long) Math.abs(nums[i] - val);
		}
		return ans;
	}

	public int minMoves3(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		int n = nums.length;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i = 0 ; i < n ; i ++){
			max = Math.max(max , nums[i]);
			min = Math.min(min , nums[i]);
		}
		int left = min;
		int right = max;
		int result = 0;
		while(left <= right){
			int mid = (left + right) / 2;
			int cnt = 0;
			for(int i = 0 ; i < n ; i ++){
				if(nums[i] > mid) cnt ++;
			}
			if(cnt > n - cnt){
				right = mid - 1;
			}else{
				result = mid;
				left = mid + 1;
			}
		}
		return result;
	}

	public int minMoves2(int[] nums) {
		if(nums == null || nums.length < 2) return 0;
		int n = nums.length;
		Arrays.sort(nums);
		return (int)Math.min(Math.min(cal(nums , nums[n / 2]) , cal(nums , nums[n / 2 + 1])) , cal(nums , nums[n/2 - 1]));
	}

	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		if((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
		if(desiredTotal == 0) return true;
		int n = (int) Math.pow(2 , maxChoosableInteger);
		int pow[] = new int[maxChoosableInteger + 1];
		for(int i = 0 ; i <= maxChoosableInteger ; i ++){
			pow[i] = (int) Math.pow(2 , i);
		}
		int[] dp = new int[n + 1];
		Arrays.fill(dp , 2);
		int[] sum = new int[n + 1];
		for(int i = 0 ; i < n ; i ++){
			int stat = i;
			for(int j = 1 ; j <= maxChoosableInteger ; j ++){
				if((stat & 1) == 1){
					sum[i] += j;
				}
 				stat = stat / 2;
			}
			if(sum[i] >= desiredTotal) dp[i] = -1;
		}

		for(int i = n - 1 ; i >= 0 ; i --){
			if(dp[i] != 2) continue;
			dp[i] = -1;
			int stat = i;
			for(int j = 1 ; j <= maxChoosableInteger ; j ++){
				if((stat & 1) == 0){
					dp[i] = Math.max(dp[i] ,  dp[i + pow[j-1]] * -1);
				}
				stat = stat / 2;
			}
		}
		return dp[0] == 1;
	}

	public int islandPerimeter(int[][] grid) {
		if(grid == null) return 0;
		int n = grid.length;
		if(n == 0) return 0;
		int m = grid[0].length;
		int ans = 0;
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < m ; j ++){
				if(grid[i][j] == 1){
					ans += 2;
				}
			}
		}
		ans += 2;
		return ans;
	}

	public void run(){
		long start = System.currentTimeMillis();
		System.out.println(canIWin(20 , 210));
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
