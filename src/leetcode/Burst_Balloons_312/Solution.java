package leetcode.Burst_Balloons_312;

/**
 * Created by yanghui on 16/9/1.
 */
public class Solution {
	public int maxCoins(int[] nums) {
		int length  = nums.length;

		int re[] = new int[length+2];
		for(int i = 1 ; i <= length ; i ++){
			re[i] = nums[i-1];
		}
		re[0] = 1;
		re[length + 1] = 1;
		int dp[][] = new int[length + 2][length + 2];

		for(int len = 0 ; len <= length ; len ++){
			for(int left = 1 ; left + len <= length ; left ++){
				int right = left + len;
				for(int k = left ; k <= right ; k ++){
					dp[left][right] = Math.max(dp[left][right] , re[left-1] * re[k]*re[right+1]
							+ dp[left][k - 1] + dp[k+1][right]);
				}
			}
		}

		return dp[1][length];
	}
}