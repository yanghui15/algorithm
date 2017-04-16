package google.codejam2017.Round_1A;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;
import java.util.HashSet;

public class A {

	FastIO scan = new FastIO(new File("in"),new File("result"));

	public void solve(int times){
		int R = scan.nextInt();
		int C = scan.nextInt();
		char[][] data = new char[R][C];
		for(int i = 0 ; i < R ; i ++){
			String cur = scan.next();
			data[i] = cur.toCharArray();
		}
		boolean[] visit = new boolean[R];
		for(int i = 0 ; i < R ; i ++){
			int cnt = 0;
			for(int j = 0 ; j < C ; j ++){
				if(data[i][j] == '?'){
					cnt ++;
				}
			}
			if(cnt == C){
				visit[i] = true;
				continue;
			}
			for(int j = 0 ; j < C ; j ++){
				if(data[i][j] != '?'){
					if(j + 1 < C && data[i][j+1] == '?'){
						data[i][j+1] = data[i][j];
					}
				}
			}
			for(int j = C - 1 ; j >= 0 ; j --){
				if(data[i][j] != '?'){
					if(j - 1 >= 0 && data[i][j-1] == '?'){
						data[i][j-1] = data[i][j];
					}
				}
			}
		}

		for(int i = 0 ; i < R ; i ++){
			if(visit[i]){
				for(int j = i + 1 ; j < R ; j ++){
					if(!visit[j]){
						for(int k = 0 ; k < C ; k ++){
							data[i][k] = data[j][k];
						}
						visit[i] = false;
						break;
					}
				}
			}
		}

		for(int i = R-1 ; i >= 0 ; i --){
			if(visit[i]){
				for(int j = i - 1 ; j >= 0 ; j --){
					if(!visit[j]){
						for(int k = 0 ; k < C ; k ++){
							data[i][k] = data[j][k];
						}
						visit[i] = false;
						break;
					}
				}
			}
		}

		scan.println("Case #"+times+":");
		for(int i = 0 ; i < R ; i ++){
			scan.println(new String(data[i]));
		}

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

