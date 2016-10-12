package hihocoder.pro_1400;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by yanghui on 16/10/11.
 */
public class Main {

	FastIO scan = new FastIO();

	public void run(){

		int n = scan.nextInt();
		char str[] = scan.next().toCharArray();
		int m = scan.nextInt();
		int flag[][] = new int[26][26];
		for(int i = 0 ; i < m ; i ++){
			String cur = scan.next();
			flag[cur.charAt(1) - 'a'][cur.charAt(0) - 'a'] = 1;
			flag[cur.charAt(0) - 'a'][cur.charAt(1) - 'a'] = 1;
		}
		int dp[][] = new int[n][27];
		for(int i = 0 ; i < n ; i ++){
			Arrays.fill(dp[i] , Integer.MAX_VALUE);
		}
		for(int i = 0 ; i < n ; i ++){
			if(i == 0){
				dp[i][26] = 1;
				dp[i][str[i] - 'a'] = 0;
				continue;
			}
			dp[i][str[i] - 'a'] = Math.min(dp[i][str[i] - 'a'] , dp[i-1][26]);
			for(int j = 0 ; j < 27 ; j ++){
				if(j == 26){
					dp[i][j] = dp[i-1][j] + 1;
				}else{
					if(dp[i-1][j] != Integer.MAX_VALUE){
						if(flag[j][str[i] - 'a'] == 1){
							dp[i][j] = Math.min(dp[i][j] , dp[i-1][j] + 1);
						}else{
							dp[i][str[i] - 'a'] = Math.min(dp[i][str[i] - 'a'] , dp[i-1][j]);
						}
					}
				}
				
			}
//			for(int j = 0 ; j < 27 ; j ++){
//				scan.print(dp[i][j] + " ");
//			}
//			scan.println();
		}
		int ans = Integer.MAX_VALUE;
		for(int i = 0 ; i < 27 ; i ++){
			ans = Math.min(ans , dp[n-1][i]);
		}
		scan.println(ans);

	}

	public static void main(String args[]){
		new Main().run();
	}

	public class FastIO {
		BufferedReader br;
		StringTokenizer st;
		PrintWriter out;

		public FastIO(File in, File o) {
			try {
				br = new BufferedReader(new FileReader(in));
				out = new PrintWriter(new FileWriter(o));
			} catch (Exception e) {
				e.printStackTrace();
			}
			eat("");
		}

		public FastIO() {
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new OutputStreamWriter(System.out));
			eat("");
		}

		public void eat(String s) {
			st = new StringTokenizer(s);
		}

		public String nextLine() {
			try {
				return br.readLine();
			} catch (IOException e) {
				while(true);
				//throw new IOError(e);
			}
		}

		public boolean hasNext() {
			while (!st.hasMoreTokens()) {
				String s = nextLine();
				if (s == null)
					return false;
				eat(s);
			}
			return true;
		}

		public String next() {
			hasNext();
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public void print(String ans) {
			out.print(ans);
			out.flush();
		}

		public void print(int ans) {
			out.print(ans);
			out.flush();
		}

		public void print(long ans) {
			out.print(ans);
			out.flush();
		}

		public void print(double ans) {
			out.print(ans);
			out.flush();
		}

		public void println(String ans) {
			out.println(ans);
			out.flush();
		}

		public void println(int ans) {
			out.println(ans);
			out.flush();
		}

		public void println(long ans) {
			out.println(ans);
			out.flush();
		}

		public void println(double ans) {
			out.println(ans);
			out.flush();
		}

		public void println() {
			println("");
			out.flush();
		}

		public void printf(String arg0, Object... arg1) {
			out.printf(arg0, arg1);
			out.flush();
		}

		public void println(int[] ans) {
			for (int i = 0; i < ans.length; i++)
				print(ans[i] + " ");
			println();
		}

		public void println(long[] ans) {
			for (int i = 0; i < ans.length; i++)
				print(ans[i] + " ");
			println();
		}

		public void println(double[] ans) {
			for (int i = 0; i < ans.length; i++)
				print(ans[i] + " ");
			println();
		}

		public void println(Object... ans) {
			for (Object cur : ans)
				print(cur.toString() + " ");
			println();
		}

		public void println(Object ans[][]) {
			for (int i = 0; i < ans.length; i++) {
				println(ans[i]);
			}
		}

		public void println(int ans[][]) {
			for (int i = 0; i < ans.length; i++) {
				println(ans[i]);
			}
		}

		public void println(long ans[][]) {
			for (int i = 0; i < ans.length; i++) {
				println(ans[i]);
			}
		}

		public void println(double ans[][]) {
			for (int i = 0; i < ans.length; i++) {
				println(ans[i]);
			}
		}

		public void println(List<Object> ans) {
			for (Object i : ans) {
				print(i.toString() + " ");
			}
			println();
		}

		public void close() {
			out.flush();
			out.close();
		}
	}
}
