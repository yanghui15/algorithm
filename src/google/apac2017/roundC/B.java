package google.apac2017.roundC;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;

public class B {

	FastIO scan = new FastIO(new File("in"),new File("result2"));

	public boolean cal(int sum[][] , int x1 , int y1 , int k , int R , int C){
		int x2 = x1 + k - 1;
		int y2 = y1 + k - 1;
		if(x2 >= R || y2 >= C)
			return false;
		double result = sum[x2][y2];
		if(x1 - 1 >= 0)
			result -= sum[x1 - 1][y2];
		if(y1 - 1 >= 0)
			result -= sum[x2][y1 - 1];
		if(x1 - 1 >= 0 && y1 - 1 >= 0)
			result += sum[x1 - 1][y1 - 1];
		return result == 0;
	}

	public void solve(int times){

		int R = scan.nextInt();
		int C = scan.nextInt();
		int K = scan.nextInt();

		int[][] num = new int[R][C];
		for(int i = 0 ; i < K ; i ++){
			int x = scan.nextInt();
			int y = scan.nextInt();
			num[x][y] = 1;
		}

		int[][] sum = new int[R][C];
		for(int i = 0 ; i < R ; i ++){
			for(int j = 0 ; j < C ; j ++){
				//sum(i,j) = sum(i-1 ,j) + sum(i,j-1) - sum(i-1 , j-1) + num[i][j];
				sum[i][j] = num[i][j];
				if(i - 1 >= 0)
					sum[i][j] += sum[i-1][j];
				if(j - 1 >= 0)
					sum[i][j] += sum[i][j-1];
				if(i - 1 >= 0 && j - 1 >= 0){
					sum[i][j] -= sum[i-1][j-1];
				}
			}
		}

		long result = 0;

		for(int i = 0 ; i < R ; i ++){
			for(int j = 0 ; j < C ; j ++){
				int left = 1;
				int right = Math.max(R , C);
				int cnt = 0;
				while(left <= right){
					int mid = (left + right) / 2;
					boolean temp = cal(sum , i , j , mid , R , C);
					if(temp){
						cnt = mid;
						left = mid + 1;
					}else{
						right = mid - 1;
					}
				}
				result += cnt;
			}
		}

		scan.println("Case #" + times + ": " + result);
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

