package leetcode.Ugly_Number_II_264;

/**
 * Created by yanghui on 16/9/1.
 */
public class Solution {
	public int min(int x , int y , int z){
		return Math.min(Math.min(x , y) , z);
	}
	public int nthUglyNumber(int n) {
		if(n <= 0) return 0;
		int dp[] = new int[n+1];
		dp[1] = 1;
		int idx2 = 1;
		int idx3 = 1;
		int idx5 = 1;
		if(n == 1)
			return dp[n];
		for(int i = 2 ; i <= n ; i ++){
			int x = dp[idx2] * 2 , y = dp[idx3] * 3 , z = dp[idx5] * 5;
			while(x <= dp[i-1]){
				idx2 ++;
				x = dp[idx2] * 2;
			}
			while(y <= dp[i-1]){
				idx3 ++;
				y = dp[idx3] * 3;
			}
			while(z <= dp[i-1]){
				idx5 ++;
				z = dp[idx5] * 5;
			}
			int min = min(x , y , z);
			dp[i] = min;
			if(min == x) idx2 ++;
			else if(min == y) idx3 ++;
			else idx5 ++;
		}
		return dp[n];
	}
}
