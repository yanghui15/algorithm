package leetcode.Target_Sum_494;

/**
 * Created by yanghui on 17/1/23.
 */
public class Solution {

	public int findTargetSumWays(int[] nums, int S) {
		int n = nums.length;
		int m = 2000 + 100;
		int[][] dp = new int[n+1][m];
		dp[0][0+1010] = 1;
		int idx = 0;
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < m ; j ++){
				if(j + nums[i] >= 0 && j + nums[i] < m)
					dp[i + 1][j + nums[i]] += dp[i][j];
				if(j - nums[i] >= 0 && j - nums[i] < m)
					dp[i + 1][j - nums[i]] += dp[i][j];
			}
		}
		if(S + 1010 < 0 || S + 1010 >= m) return 0;
		return dp[n][S + 1010];
	}

	public void run(){
		System.out.println(findTargetSumWays(new int[]{16,40,9,17,49,32,30,10,38,36,31,22,3,36,32,2,26,17,30,47} , 49));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
