package google.apac2017.roundC;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.*;

public class A {

	FastIO scan = new FastIO(new File("in"),new File("result"));

	public double max(double a , double b , double c , double d){
		return Math.max(Math.max(a , b) , Math.max(c , d));
	}

	public double dfs(int x , int y , int[][] flag , int R , int C , boolean value[][]
			, int cnt , int S , double val , double P , double Q){
		if(x < 0 || x >= R || y < 0 || y >= C)
			return val;
		val +=  (value[x][y] ? P : Q) * Math.pow(1 - (value[x][y] ? P : Q)  , flag[x][y]);
		if(cnt == S){
			return val;
		}
		flag[x][y] ++;
		double result =  max(dfs(x + 1 , y , flag , R , C , value , cnt + 1 , S , val , P , Q),
				dfs(x - 1 , y , flag , R , C , value , cnt + 1 , S , val , P , Q),
				dfs(x , y + 1 , flag , R , C , value , cnt + 1 , S , val , P , Q),
				dfs(x , y - 1 , flag , R , C , value , cnt + 1 , S , val , P , Q));
		flag[x][y] --;
		return result;
	}


	public void solve(int times){
		int R = scan.nextInt();
		int C = scan.nextInt();
		int Rs = scan.nextInt();
		int Cs = scan.nextInt();
		int S = scan.nextInt();

		double P = scan.nextDouble();
		double Q = scan.nextDouble();

		boolean[][] value = new boolean[R][C];
		int[][] flag = new int[R][C];

		for(int i = 0 ; i < R ; i ++){
			for(int j = 0 ; j < C ; j ++){
				value[i][j] = scan.next().equals("A");
			}
		}

		int x = Rs;
		int y = Cs;
		int cnt = 0;
		double val = 0.0;

		System.out.println(S);

		if(S == 0){
			scan.println("Case #" + times + ": " + 0.0);
			return;
		}

		double result  =  max(dfs(x + 1 , y , flag , R , C , value , cnt + 1 , S , val , P , Q),
				dfs(x - 1 , y , flag , R , C , value , cnt + 1 , S , val , P , Q),
				dfs(x , y + 1 , flag , R , C , value , cnt + 1 , S , val , P , Q),
				dfs(x , y - 1 , flag , R , C , value , cnt + 1 , S , val , P , Q));

		scan.println("Case #" + times + ": " + result);
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

