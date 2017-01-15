package leetcode.Count_The_Repetitions_466;

/**
 * Created by yanghui on 17/1/9.
 */
public class Solution {

	public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
		int n = s1.length();
		int m = s2.length();
		int[][] dp = new int[30][n];
		int mx = n1 * s1.length();
		for(int i = 0 ; i < n ; i ++){
			int cnt = 0;
			for(int j = 0 ; j < m ; j ++){
				int idx = (i + cnt) % n;
				while(i + cnt <= mx && s1.charAt(idx) != s2.charAt(j)){
					cnt ++;
					idx = (i + cnt) % n;
				}
				if(i + cnt > mx) {
					cnt = -1;
					break;
				}
				else
					cnt ++;
			}
			dp[0][i] = cnt;
		}

		for(int i = 1 ; i < 30 ; i ++){
			for(int j = 0 ; j < n ; j ++){
				//dp[i][j] = dp[i-1][j] + dp[i-1][(j + dp[i-1][j]%n]
				if(dp[i-1][j] != -1 && dp[i-1][((j + dp[i-1][j])%n)] != -1) {
					dp[i][j] = dp[i - 1][j] + dp[i - 1][((j + dp[i - 1][j]) % n)];
					if(dp[i][j] > mx){
						dp[i][j] = -1;
					}
				}
				else
					dp[i][j] = -1;
			}
		}

		int ans = 0;
		int idx = 0;

		for(int i = 29 ; i >= 0 ; i --){
			if(dp[i][(idx % n)] != -1 && idx + dp[i][(idx % n)] <= mx){
				ans += (int) Math.pow(2 , i);
				idx += dp[i][(idx % n)];
			}
		}
		return ans / n2;
	}

	public void run(){
		System.out.println(getMaxRepetitions("niconiconi" , 99981 , "nico" , 81));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}

