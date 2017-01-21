package google.apac2017.roundD;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;

public class B {

	FastIO scan = new FastIO(new File("in"),new File("result"));

	public int cal(int i){
		int cnt = 0;
		while(i > 0){
			if((i & 1) == 1) cnt ++;
			i = (i >> 1);
		}
		return cnt;
	}

	public void solve(int times){
		int n = scan.nextInt();
		int m = scan.nextInt();
		int ans = 0;
		int max = (int)Math.pow(2 , n*m);
		int flag[][] = new int[n][m];
		for(int i = 0 ; i < max ; i ++){
			int cur = cal(i);
			if(cur <= ans) continue;
			int idx = 0;
			for(int j = 0 ; j < n ; j ++){
				for(int k = 0 ; k < m ; k ++){
					flag[j][k] = ((i >> idx) & 1) == 1 ? 1 : 0;
					idx ++;
				}
			}
			boolean temp = true;
			out:
			for(int j = 0 ; j < n ; j ++){
				for(int k = 0 ; k < m ; k ++){
					if(flag[j][k] == 0) continue;
					if((j-1 >= 0 && flag[j-1][k] == 1 && j+1 < n && flag[j+1][k] == 1)
						|| (k - 1 >= 0 && flag[j][k-1] == 1 && k+1 < m && flag[j][k+1] == 1)){
						temp = false;
						break out;
					}
				}
			}
			if(temp){
				ans = Math.max(ans , cal(i));
			}
		}
		scan.println("Case #" + times + ": " + ans);
	}

	public void solve2(int times){
		int n = scan.nextInt();
		int m = scan.nextInt();
	}

	public void run(){
		int T = scan.nextInt();
		for(int i = 1 ; i <= T ; i ++){
			solve(i);
		}
	}


	public static void main(String args[]){
		new B().run();
	}

}

