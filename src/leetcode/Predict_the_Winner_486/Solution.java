package leetcode.Predict_the_Winner_486;

/**
 * Created by yanghui on 2017/2/7.
 */
public class Solution {

	public int dfs(int[] nums , int left , int right , Integer[][] dp){
		if(dp[left][right] == null)
			dp[left][right] = ((left == right) ? nums[left] :
					Math.max(
							nums[left] - dfs(nums , left + 1 , right , dp) ,
							nums[right] - dfs(nums , left , right - 1 , dp)
					));
		return dp[left][right];
	}

	public boolean PredictTheWinner(int[] nums) {
		Integer[][] dp = new Integer[nums.length][nums.length];
		return dfs(nums , 0 , nums.length - 1 , dp) >= 0;
	}

	public void run(){
		System.out.println(PredictTheWinner(new int[]{1, 5, 233, 7}));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
