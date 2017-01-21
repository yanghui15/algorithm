package google.apac2017.roundD;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;

public class A {

	FastIO scan = new FastIO(new File("in"),new File("result"));


	public int gcd(int a , int b){
		return (a == 0) ? b : gcd(b% a , a);
	}

	public void solve(int times){
		int n = scan.nextInt();
		int m = scan.nextInt();
		if(n == 1 || m == 0){
			scan.println("Case #" + times + ": " + 1);
			return;
		}

		double dp[][] = new double[n+m+2][n+2];

		dp[0][0] = 1;
		for (int i = 0; i < n + m; i++) {
			for (int j = 0; j <= Math.min(n, i); j++) {
				if (dp[i][j] == 0) continue;
				double val = dp[i][j];
				if (2 * j + 2 > i + 1)
					dp[i + 1][j + 1] += val * (n - j) / (n + m - i);
				if (2 * j > i + 1)
					dp[i + 1][j] += val * (m - i + j) / (n + m - i);
			}
		}

		scan.println("Case #" + times + ": " + dp[n + m][n]);
	}

	public void run(){
		int T = scan.nextInt();
		for(int i = 1 ; i <= T ; i ++){
			solve(i);
		}
	}


	public static void main(String args[]){
		new A().run();
	}

}

