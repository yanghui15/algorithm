package leetcode.Target_Sum_494;

/**
 * Created by yanghui on 17/1/23.
 */
public class Solution {

	public int findTargetSumWays(int[] nums, int S) {
		int n = nums.length;
		int m = 2000 + 100;
		int[][] dp = new int[n][m];
		dp[0][0+1000] = 1;
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < m ; j ++){
				// cur = nums[i] + 1000 or -nums[i] + 1000;
			}
		}
	}

	public void run(){

	}

	public static void main(String args[]){
		new Solution().run();
	}
}
