package google.kickstart2017.RoundA;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;

public class B {

	FastIO scan = new FastIO(new File("in"),new File("result"));


	public int get_K(String cur , int idx){
		int cnt = 0;
		while(idx >= 0){
			if(cur.charAt(idx) != '*'){
				cnt ++;
			}
			if(cnt == 5) break;
			idx --;
		}
		return idx + 1;
	}

	public boolean gao(String x , String y){
		boolean[][] dp = new boolean[x.length() + 1][y.length() + 1];
		dp[0][0] = true;
		for(int i = 0 ; i < y.length() ; i ++){
			if(y.charAt(i) != '*') break;
			else{
				dp[0][i + 1] = true;
			}
		}
		for(int i = 0 ; i < x.length() ; i ++){
			for(int j = 0 ; j < y.length() ; j ++){
				char curx = x.charAt(i);
				char cury = y.charAt(j);
				if(curx == cury){
					if(curx == '*'){
						for(int k = get_K(x , i) ; k <= i ; k ++){
							if(dp[k][j]){
								dp[i+1][j+1] = true;
								break;
							}
						}
						for(int k = get_K(y , j) ; k <= j ; k ++){
							if(dp[i][k]){
								dp[i+1][j+1] = true;
								break;
							}
						}
					}else{
						dp[i+1][j+1] = dp[i][j];
					}
				}else{
					if(curx == '*'){
						for(int k = get_K(y , j) ; k <= j + 1 ; k ++){
							if(dp[i][k]){
								dp[i+1][j+1] = true;
								break;
							}
						}
					}else if(cury == '*'){
						for(int k = get_K(x , i) ; k <= i + 1 ; k ++){
							if(dp[k][j]){
								dp[i+1][j+1] = true;
								break;
							}
						}
					}else{
						dp[i+1][j + 1] = false;
					}
				}
//				System.out.println(x.substring(0,i+1) + " " + y.substring(0,j+1) + " " + dp[i + 1][j+ 1]);
			}
		}

//		for(int i = 0 ; i <= x.length() ; i ++){
//			for(int j = 0 ; j <= y.length() ; j ++){
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
//
//		System.out.println();

		return dp[x.length()][y.length()];
	}

	public void solve(int times){
		String x = scan.next();
		String y = scan.next();

		if(gao(x , y) != gao(y , x)){
			System.out.println("AA");
		}

		boolean result = gao(x , y) || gao(y , x);
		if(result){
			scan.println("Case #" + times +": TRUE");
		}else{
			scan.println("Case #" + times +": FALSE");
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

