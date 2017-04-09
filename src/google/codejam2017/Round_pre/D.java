package google.codejam2017.Round_pre;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class D {

	FastIO scan = new FastIO(new File("in"),new File("result"));

	public void gao(char[][] result , int n){
		int idx = 1;
		for(int j = 0 ; idx < n && j < n ; j ++){
			if(result[0][j] == 'o') continue;
			else{
				result[idx][j] = 'x';
				idx ++;
			}
		}
		if(result[0][n-2] == 'o'){
			result[n-1][n-1] = 'x';
		}else{
			result[n-1][n-2] = 'o';
			result[n-2][n-1] = 'x';
		}
	}

	public boolean valid(char[][] result , int n){
		boolean flag = true;
		for(int i = 0 ; i < n ; i ++){
			int cnt = 0;
			for(int j = 0 ; j < n ; j ++){
				if(result[i][j] != '.' && result[i][j] != '+') cnt ++;
			}
			if(cnt > 1){
				flag = false;
				break;
			}
			cnt = 0;
			for(int j = 0 ; j < n ; j ++){
				if(result[j][i] != '.' && result[j][i] != '+') cnt ++;
			}
			if(cnt > 1){
				flag = false;
				break;
			}
		}
		for(int i = 0 ; i < n ; i ++){
			int cnt = 0;
			int idx = i;
			int idy = 0;
		}
		return flag;
	}

	public void solve(int times){
		int n = scan.nextInt();
		int m = scan.nextInt();
		char[][] data = new char[n][n];
		char[][] result = new char[n][n];
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < n ; j ++){
				data[i][j] = '.';
				result[i][j] = '.';
			}
		}
		for(int i = 0 ; i < m ; i ++){
			char cur = scan.next().charAt(0);
			int idx = scan.nextInt() - 1;
			int idy = scan.nextInt() - 1;
			data[idx][idy] = cur;
			if(cur == 'x'){
				result[idx][idy] = 'o';
			}else{
				result[idx][idy] = '+';
			}
		}
		boolean flag = false;
		for(int i = 0 ; i < n ; i ++){
			if(result[0][i] == 'o'){
				flag = true;
			}
			if(result[0][i] == '.'){
				result[0][i] = '+';
			}
		}
		if(!flag){
			result[0][0] = 'o';
		}
		if(n > 1){
			if(result[0][0] != 'o'){
				gao(result , n);
			}else{
				char[][] tmp = new char[n][n];
				for(int i = 0 ; i < n ; i ++){
					for(int j = 0 ; j < n ; j ++){
						tmp[i][j] = result[i][n-1-j];
					}
				}
				gao(tmp , n);
				for(int i = 0 ; i < n ;i ++){
					for(int j = 0 ; j < n ; j ++){
						result[i][j] = tmp[i][j];
					}
				}
			}
		}

		boolean hah = valid(result , n);
		int ans = 0;
		int cnt = 0;
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < n ; j ++){
				if(result[i][j] == '+' || result[i][j] == 'x'){
					ans ++;
				}else if(result[i][j] == 'o'){
					ans += 2;
				}
				if(result[i][j] != data[i][j])
					cnt ++;
			}
		}
		scan.println("Case #" + times + ": " + ans + " " + cnt);
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < n ; j ++){
				if(result[i][j] != data[i][j]){
					scan.println(""+result[i][j] + " " + (i + 1) + " " + (j + 1));
				}
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
		new D().run();
	}

}

