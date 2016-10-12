package leetcode.Combination_Sum_IV_377;

import java.util.Arrays;

/**
 * Created by yanghui on 16/9/6.
 */
public class Solution {
	public int combinationSum4(int[] nums, int target) {
		if(target <= 0) return 0;
		int dp[] = new int[target+1];
		Arrays.sort(nums);
		for(int i = 0 ; i < nums.length ; i ++){
			if(nums[i] <= target)
				dp[nums[i]] = 1;
		}
		for(int i = 1 ; i <= target ; i ++){
			for(int j = 0 ; j < nums.length ; j ++){
				if(nums[j] > i)
					break;
				dp[i] += dp[i - nums[j]];
			}
		}
		return dp[target];
	}
}
