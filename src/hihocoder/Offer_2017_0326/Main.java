package hihocoder.Offer_2017_0326;

import java.io.*;
import java.util.*;

/**
 * Created by yanghui on 2017/3/5.
 */
public class Main {

	FastIO scan = new FastIO();

	public void solveA() throws Exception{
		String str = scan.next();
		int n = str.length();
		int ans = n * 2;
		int cnth = 0;
		int cnti = 0;
		int cnto = 0;
		int idx = 0;
		for(int i = 0 ; i < n ; i ++){
			while(idx <= n){
				if(cnth == 2 && cnti == 1 && cnto == 1){
					ans = Math.min(ans , idx - i);
					break;
				}
				if(cnth > 2 || cnti > 1 || cnto > 1){
					break;
				}
				if(idx == n) break;
				char cur = str.charAt(idx ++);
				if(cur == 'h'){
					cnth ++;
				}else if(cur == 'i'){
					cnti ++;
				}else if(cur == 'o'){
					cnto ++;
				}
			}
			char cur = str.charAt(i);
			if(cur == 'h'){
				cnth --;
			}else if(cur == 'i'){
				cnti --;
			}else if(cur == 'o'){
				cnto --;
			}
		}
		if(ans > n){
			scan.println(-1);
		}else{
			scan.println(ans);
		}
	}

	public void solveB(){
		int T = scan.nextInt();
		for(int t = 0 ; t < T ; t ++){
			int n = scan.nextInt();
			int m = scan.nextInt();
			int[] values = new int[n];
			int[] stats = new int[n];
			for(int i = 0 ; i < n ; i ++){
				values[i] = scan.nextInt();
				int s = scan.nextInt();
				for(int j = 0 ; j < s ; j ++){
					int cur = scan.nextInt() - 1;
					stats[i] += (1 << cur);
				}
			}
			int[] dp = new int[1 << (m+1)];
			boolean[] flag = new boolean[1 << (m + 1)];
			flag[0] = true;
			int MAX = 1 << m;
			for(int i = 0 ; i < n ; i ++){
				int[] fx = new int[1 << (m+1)];
				boolean[] tmp = new boolean[1 << (m + 1)];
				for(int j = 0 ; j < (1 << (m+1)) ; j ++){
					fx[j] = dp[j];
					tmp[j] = flag[j];
				}
				for(int j = 0 ; j < MAX ; j ++){
					if(tmp[j]) {
						dp[j ^ stats[i]] = Math.max(dp[j ^ stats[i]], fx[j] + values[i]);
						flag[j ^ stats[i]] = true;
					}
				}
			}
			scan.println(dp[MAX - 1]);
		}
	}


	public int find(int p , int[] fa){
		int f = fa[p];
		if(f == p) return f;
		else{
			fa[p] = find(f , fa);
			return fa[p];
		}
	}


	public void merge(int x , int y , int[] fa){
		int fx = find(x , fa);
		int fy = find(y , fa);
		if(fx == fy) return;
		if(fx < fy){
			fa[fx] = fy;
		}else{
			merge(y , x , fa);
		}
	}

	public boolean judge(int x ,int y){
		return x >= 0 && x < 1000 && y >= 0 && y < 1000;
	}

	public void solveC(){
		int n = scan.nextInt();
		int[] fa = new int[1005 * 1005];
		for(int i = 0 ; i < fa.length ; i ++){
			fa[i] = i;
		}
		boolean[][] flag = new boolean[1000][1000];
		int[] a = new int[]{0 , 0 , 1 , -1};
		int[] b = new int[]{1 , -1 , 0 , 0};
		int cnt = 0 ; int sum = 0; int ans = 0;
		int tmp = 0;
		for(int i = 0 ; i < n ; i ++){
			int x = scan.nextInt();
			int y = scan.nextInt();
			flag[x][y] = true;
			int stat = x * 1000 + y;
			HashSet<Integer> set = new HashSet<>();
			for(int j = 0 ; j < 4 ; j ++){
				if(judge(x + a[j] , y + b[j])){
//					tmp ++;
					if(flag[x + a[j]][y + b[j]]){
						tmp ++;
						int stat_cur = (x + a[j]) * 1000 + (y + b[j]);
						set.add(find(stat_cur , fa));
					}
				}
			}
			ans = (i + 1);
			if(i == 0){
				cnt = 1;
				sum = 4;
			}else{
				cnt = cnt - set.size() + 1;
				sum = ans * 4 - tmp * 2;
				for(Integer rt : set){
					merge(rt , stat , fa);
				}
			}
			scan.println(cnt + " " + ans + " " + sum);
		}
	}

	public void run() throws Exception{
		solveC();
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
