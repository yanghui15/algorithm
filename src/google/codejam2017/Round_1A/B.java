package google.codejam2017.Round_1A;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;

public class B {

	FastIO scan = new FastIO(new File("in"),new File("result"));

	public int[] search(int val , int target){
		int left = 0;
		double ll = (double) target * 0.9;
		double rr = (double) target * 1.1;
		while(val * left < ll){
			left ++;
		}
		if(val * left <= rr){
			int[] ans = new int[]{left , left};
			while(val * ans[1] <= rr){
				ans[1] ++;
			}
			ans[1] --;
			return ans;
		}else{
			return new int[]{-1 , -1};
		}
	}

	public void solve(int times){
		int N = scan.nextInt();
		int P = scan.nextInt();
		int[] values = new int[N];
		for(int i = 0 ; i < N ; i ++){
			values[i] = scan.nextInt();
		}

		int[][] data = new int[P][N];
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < P ; j ++){
				data[j][i] = scan.nextInt();
			}
		}

		int ans = 0;
		for(int i = 0 ; i < P ; i ++){
			for(int j = 0 ; j < N ; j ++){

			}
		}

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

