package leetcode.Super_Ugly_Number_313;

/**
 * Created by yanghui on 16/9/3.
 */
public class Solution {
	public int nthSuperUglyNumber(int n, int[] primes) {
		if(n <= 0) return 0;
		int dp[] = new int[n+1];
		int len = primes.length;
		int idx[] = new int[len];
		dp[1] = 1;
		for(int i = 2 ; i <= n ; i ++){
			int cur[] = new int[len];
			int mn = Integer.MAX_VALUE;
			for(int j = 0 ; j < len ; j ++){
				cur[j] = dp[idx[j]] * primes[j];
				while(cur[j] <= dp[i-1]){
					idx[j] ++;
					cur[j] = dp[idx[j]] * primes[j];
				}
				mn = Math.min(mn , cur[j]);
			}
			dp[i] = mn;
		}
		return dp[n];
	}
}