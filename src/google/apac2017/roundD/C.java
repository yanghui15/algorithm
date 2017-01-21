package google.apac2017.roundD;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;
import java.util.ArrayList;

public class C {

	FastIO scan = new FastIO(new File("in"),new File("result"));


	public long cal(String sentence , ArrayList<int[]> voc[] , long mod){
		int n = sentence.length();
		long dp[] = new long[n+1];
		dp[0] = 1l;
		for(int i = 1 ; i <= n ; i ++){
			for(int j = 0 ; j < 21 ; j ++){
				if(voc[j].size() == 0) continue;
				if(i - j < 0) continue;
				int cur[] = new int[26];
				for(int k = i - j ; k < i ; k ++){
					cur[sentence.charAt(k) - 'a'] ++;
				}
				long cnt = 0;
				for(int[] a : voc[j]){
					boolean flag = true;
					for(int k = 0 ; k < 26 ; k ++){
						if(cur[k] != a[k]){
							flag = false;
							break;
						}
					}
					if(flag){
						cnt ++;
					}
				}
				dp[i] = (dp[i] + cnt * dp[i-j] % mod) % mod;
			}
		}
		return dp[n] % mod;
	}

	public void solve(int times){
		int V = scan.nextInt();
		int S = scan.nextInt();
		ArrayList<int[]>[] voc= new ArrayList[21];
		for(int i = 0 ; i < 21 ; i ++){
			voc[i] = new ArrayList<int[]>();
		}
		long mod = 1000000007l;
		for(int i = 0 ; i < V ; i ++){
			String str = scan.next();
			int cnt[] = new int[26];
			for(int j = 0 ; j < str.length() ; j ++){
				cnt[str.charAt(j) - 'a'] ++;
			}
			voc[str.length()].add(cnt);
		}
		scan.print("Case #" + times + ": ");
		for(int i = 0 ; i < S ; i ++){
			String sentence = scan.next();
			if(i != S - 1)
				scan.print(cal(sentence , voc , mod) + " ");
			else
				scan.print(cal(sentence , voc , mod));
		}
		scan.println();
	}

	public void run(){
		int T = scan.nextInt();
		for(int i = 1 ; i <= T ; i ++){
			solve(i);
		}
	}


	public static void main(String args[]){
		new C().run();
	}

}

