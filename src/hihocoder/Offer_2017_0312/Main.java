package hihocoder.Offer_2017_0312;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yanghui on 2017/3/5.
 */
public class Main {

	FastIO scan = new FastIO();

	public void solveA() throws Exception{

		String s = scan.nextLine().trim();
		String e = scan.nextLine().trim();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long start = df.parse(s).getTime();
		long end = df.parse(e).getTime();
		long ans = (end - start) / 1000;
		String[] times = new String[]{
				"1972-06-30 23:59:60", "1972-12-31 23:59:60",
				"1973-12-31 23:59:60",
				"1974-12-31 23:59:60",
				"1975-12-31 23:59:60",
				"1976-12-31 23:59:60",
				"1977-12-31 23:59:60",
				"1978-12-31 23:59:60",
				"1979-12-31 23:59:60",
				"1981-06-30 23:59:60",
				"1982-06-30 23:59:60",
				"1983-06-30 23:59:60",
				"1985-06-30 23:59:60",
				"1987-12-31 23:59:60",
				"1989-12-31 23:59:60",
				"1990-12-31 23:59:60",
				"1992-06-30 23:59:60",
				"1993-06-30 23:59:60",
				"1994-06-30 23:59:60",
				"1995-12-31 23:59:60",
				"1997-06-30 23:59:60",
				"1998-12-31 23:59:60",
				"2005-12-31 23:59:60",
				"2008-12-31 23:59:60",
				"2012-06-30 23:59:60",
				"2015-06-30 23:59:60",
				"2016-12-31 23:59:60",
		};
		for(int i = 0 ; i < times.length ; i ++){
			if(times[i].compareTo(s) >= 0) ans ++;
			if(times[i].compareTo(e) >= 0) ans --;
		}
		System.out.println(ans);
	}

	public boolean judge(int i , int j , int n , int m){
		return i >= 0 && i < n && j >= 0 && j < m;
	}

	public void solveB(){
		int n , m;
		n = scan.nextInt();
		m = scan.nextInt();
		char[][] str = new char[n][m];
		for(int i = 0 ; i < n ; i ++){
			str[i] = scan.next().toCharArray();
		}
		int[][] dp = new int[n][m];
		int[][] dp2 = new int[n][m];
		int[][] dp3 = new int[n][m];
		int[][] dp4 = new int[n][m];
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < m ; j ++){
				if(str[i][j] == '0'){
					dp[i][j] = 0;
				}else{
					dp[i][j] = n * m + 1;
					if(judge(i-1 , j , n , m)){
						dp[i][j] = Math.min(dp[i][j] , dp[i-1][j] + 1);
					}
					if(judge(i , j - 1 , n , m)){
						dp[i][j] = Math.min(dp[i][j] , dp[i][j - 1] + 1);
					}
				}
			}
		}

		for(int i = n-1 ; i >= 0 ; i --){
			for(int j = m - 1 ; j >= 0 ; j --){
				if(str[i][j] == '0'){
					dp2[i][j] = 0;
				}else{
					dp2[i][j] = n * m + 1;
					if(judge(i+1 , j , n , m)){
						dp2[i][j] = Math.min(dp2[i][j] , dp2[i+1][j] + 1);
					}
					if(judge(i , j + 1 , n , m)){
						dp2[i][j] = Math.min(dp2[i][j] , dp2[i][j + 1] + 1);
					}
				}
			}
		}

		for(int i = 0 ; i < n ; i ++){
			for(int j = m - 1 ; j >= 0 ; j --){
				if(str[i][j] == '0'){
					dp3[i][j] = 0;
				}else{
					dp3[i][j] = n * m + 1;
					if(judge(i-1 , j , n , m)){
						dp3[i][j] = Math.min(dp3[i][j] , dp3[i-1][j] + 1);
					}
					if(judge(i , j + 1 , n , m)){
						dp3[i][j] = Math.min(dp3[i][j] , dp3[i][j + 1] + 1);
					}
				}
			}
		}

		for(int i = n-1 ; i >= 0 ; i --){
			for(int j = 0 ; j < m ; j ++){
				if(str[i][j] == '0'){
					dp4[i][j] = 0;
				}else{
					dp4[i][j] = n * m + 1;
					if(judge(i+1 , j , n , m)){
						dp4[i][j] = Math.min(dp4[i][j] , dp4[i+1][j] + 1);
					}
					if(judge(i , j - 1 , n , m)){
						dp4[i][j] = Math.min(dp4[i][j] , dp4[i][j - 1] + 1);
					}
				}
			}
		}

		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < m ; j ++){
				dp[i][j] = Math.min(Math.min(dp[i][j] , dp2[i][j]) , Math.min(dp3[i][j] , dp4[i][j]));
			}
		}

		for(int i = 0 ; i < n ; i ++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < m; j++) {
				sb.append(dp[i][j]);
				sb.append(" ");
			}
			scan.println(sb.toString().trim());
		}
		scan.close();
	}



	public void run() throws Exception{
		solveA();
	}

	public static void main(String args[]) throws Exception{
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
