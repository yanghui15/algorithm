package leetcode.Best_Time_to_Buy_and_Sell_Stock_IV_188;

/**
 * Created by yanghui on 16/9/3.
 */
public class Solution {
	public int maxProfit(int k, int[] prices) {
		int n = prices.length;
		if(n <= 1)
			return 0;
		if(k == 0)
			return 0;

		if(k > n){
			int ans = 0;
			for(int i = 1 ; i < n ; i ++){
				ans += Math.max(prices[i] - prices[i-1] , 0);
			}
			return ans;
		}

		int dp[] = new int[n];
		int dp2[] = new int[n];

		for(int i = 0 ; i < k ; i ++){
			if(i == 0){
				dp2[0] = -prices[0];
				for(int j = 1 ; j < n ; j ++){
					dp2[j] = Math.max(-prices[j] , dp2[j-1]);
				}
			}else{
				dp2[0] = -prices[0];
				for(int j = 1 ; j < n ; j ++){
					dp2[j] = Math.max(dp2[j-1] , dp[j] - prices[j]);
				}
			}
			for(int j = 1 ; j < n ; j ++){
				dp[j] = Math.max(dp[j-1] , dp2[j] + prices[j]);
			}
		}
		return dp[n-1];
	}
}
