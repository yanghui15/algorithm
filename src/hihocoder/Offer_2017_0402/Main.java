package hihocoder.Offer_2017_0402;

import java.io.*;
import java.util.*;

/**
 * Created by yanghui on 2017/3/5.
 */
public class Main {

	FastIO scan = new FastIO();

	public void solveD() throws Exception{
		int T = scan.nextInt();
		for(int t = 0 ; t < T ; t ++){
			int n = scan.nextInt();
			long data[] = new long[n];
			for(int i = 0 ; i < n ; i ++){
				data[i] = scan.nextInt();
			}
			Arrays.sort(data);
			long ans = 0;
			for(int i = 0 ; i < n ; i ++){
				long max = 0;
				for(int j = i - 1 ; j >= 0 ; j --){
					long and = data[i] & data[j];
					max = Math.max(max , and);
					if(max > data[j]) break;
					ans = Math.max(ans , data[i] * data[j] * and);
				}
			}
			System.out.println(ans);
		}
	}

	public void solveA(){
		int N = scan.nextInt();
		HashSet<Integer> set = new LinkedHashSet<>();
		boolean[] visit = new boolean[N+1];
		for(int i = 2 ; i <= N ; i ++){
			if(!visit[i]){
				set.add(i);
				for(int j = i ; j <= N ; j += i){
					visit[j] = true;
				}
			}
		}
		for(Integer cur : set){
			if(set.contains(N - cur)){
				System.out.println(cur + " " + (N - cur));
				return;
			}
		}
	}

	public void solveB(){
		int N = scan.nextInt();
		HashSet<Integer> set = new HashSet<Integer>();
		HashSet<Integer> data[] = new HashSet[N];
		for(int i = 0 ; i < N ; i ++){
			int C = scan.nextInt();
			data[i] = new HashSet<>();
			int sum = 0;
			for(int j = 0 ; j < C ; j ++){
				int cur = scan.nextInt();
				if(j == C-1) break;
				sum += cur;
				data[i].add(sum);
				set.add(sum);
			}
		}
		int max = N;
		for(Integer cur : set) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (!data[i].contains(cur)) cnt++;
			}
			max = Math.min(max , cnt);
		}
		System.out.println(max);
	}

	public void set(int[][] data , int idx , int jdx , char tmp){
		if(tmp == '/'){
			data[idx][jdx + 2] = 1;
			data[idx + 1][jdx + 1] = 1;
			data[idx + 2][jdx] = 1;
		}else if(tmp == '\\'){
			data[idx][jdx] = 1;
			data[idx + 1][jdx + 1] = 1;
			data[idx + 2][jdx + 2] = 1;
		}
	}

	public boolean judge(int i , int j , int N , int M){
		return i >= 0 && i < N && j >= 0 && j < M;
	}

	public void bfs(int startx , int starty , int[][] data , boolean[][] visit , int N , int M){
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{startx , starty});
		int a[] = new int[]{0 , 0 , 1 , -1};
		int b[] = new int[]{1 , -1 , 0 , 0};
		while(!q.isEmpty()){
			int[] top = q.poll();
			if(visit[top[0]][top[1]]) continue;
			visit[top[0]][top[1]] = true;
			for(int i = 0 ; i < 4 ; i ++){
				if(judge(top[0] + a[i] , top[1] + b[i] , N , M)
						&& !visit[top[0] + a[i]][top[1] + b[i]] && data[top[0] + a[i]][top[1] + b[i]] == 0){
					q.add(new int[]{top[0] + a[i] , top[1] + b[i]});
				}
			}
 		}
	}

	public void solveC(){
		int n = scan.nextInt();
		int m = scan.nextInt();
		int[][] data = new int[n*3][m*3];
		for(int i = 0 ; i < n ; i ++){
			char[] tmp = scan.nextLine().toCharArray();
			for(int j = 0 ; j < m ; j ++){
				set(data , i * 3 , j * 3 , tmp[j]);
			}
		}
		boolean[][] visit = new boolean[n*3][m*3];
		int ans = 0;
		for(int i = 0 ; i < data.length ; i ++){
			for(int j = 0 ; j < data[i].length ; j ++){
				if(data[i][j] == 0 && !visit[i][j]){
					ans ++;
					bfs(i , j , data , visit , data.length , data[i].length);
				}
			}
		}
		scan.println(ans);
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
