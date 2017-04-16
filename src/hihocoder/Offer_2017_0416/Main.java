package hihocoder.Offer_2017_0416;

import java.io.*;
import java.util.*;

/**
 * Created by yanghui on 2017/3/5.
 */
public class Main {

	FastIO scan = new FastIO();

	public int lowbit(int x){
		return x & (x - 1);
	}

	public void add(int x , int[] a){
		while(x < a.length){
			a[x] ++;
			x += lowbit(x);
		}
	}

	public long sum(int x , int[] a){
		long ans = 0;
		while(x > 0){
			ans += a[x];
			x -= lowbit(x);
		}
		return ans;
	}

	public long sum(int L , int R , int[] a){
		return sum(R , a) - sum(L - 1 , a);
	}

	public void solveA(){
//		int n = scan.nextInt();
		int n = 1000;
		int[] data = new int[n+1];
		for(int i = 1 ; i < n+1 ; i ++){
//			data[i] = scan.nextInt();
			data[i] = 1000000;
		}

		Arrays.sort(data);

		HashMap<Integer,HashMap<Integer,Integer>> map = new HashMap<>();

		for(int i = 0 ; i < n ; i ++){
			for(int j = i + 1 ; j < n ; j ++){
				int cur = data[j] - data[i];
				HashMap<Integer,Integer> tmp = null;
				if(map.containsKey(cur)){
					tmp = map.get(cur);
				}else{
					tmp = new HashMap<>();
				}
				if(tmp.containsKey(i)){
					tmp.put(i , tmp.get(i) + 1);
				}else{
					tmp.put(i , 1);
				}
				map.put(cur , tmp);
			}
		}

		long ans = 0;
		for(int i = 0 ; i < n ; i ++){
			for(int j = i + 1 ; j < n ; j ++){
				int cur = data[j] - data[i];
				HashMap<Integer,Integer> tmp = map.get(cur);
//				System.out.println(i + " " + j + " " + cur);
//				System.out.println(tmp);
				long tt = 0;
				for(Integer cc : tmp.keySet()){
					if(cc == i || cc == j || cc < j) continue;
					tt += tmp.get(cc) * 2;
				}
//				System.out.println(tt);
				ans += tt;
			}
		}
		ans = ans * 2;
		scan.println(ans);

	}

	public void solveB(){
		int N = scan.nextInt();
		int M = scan.nextInt();
		double[] a = new double[N+1];
		for(int i = 1 ; i <= N ; i ++){
			a[i] = scan.nextDouble();
		}
		double[][] dp = new double[N+1][M+1];
		dp[0][0] = 1.0;
		for(int i = 1 ; i <= N ; i ++){
			for(int j = 0 ; j <= i && j <= M; j ++){
				if(j == 0){
					dp[i][j] = dp[i-1][j] * (1 - a[i]);
				}else
					dp[i][j] = dp[i-1][j - 1] * a[i] + dp[i-1][j] * (1 - a[i]);
			}
		}
		scan.println(dp[N][M]);

	}

	class TreeNode{
		int idx;
		ArrayList<TreeNode> nxt;
		public TreeNode(int idx){
			this.idx = idx;
			nxt = new ArrayList<>();
		}
		public void add(TreeNode next){
			nxt.add(next);
		}

	}

	public boolean dfs(TreeNode root , int target , boolean[] visit){
		if(visit[root.idx] && root.idx == target) return true;
		boolean ans = false;
		visit[root.idx] = true;
		for(TreeNode cur : root.nxt){
			ans = ans || dfs(cur , target , visit);
		}
		return ans;
	}

	public void solveC(){
		int N = scan.nextInt();
		TreeNode[] tree = new TreeNode[N+1];
		for(int i = 1 ; i <= N ; i ++){
			tree[i] = new TreeNode(i);
		}
		int[][] edges = new int[N+1][2];
		int[] in = new int[N+1];
		boolean[] visit = new boolean[N+1];
		boolean flag = false;
		for(int i = 1 ; i <= N ; i ++){
			edges[i][0] = scan.nextInt();
			edges[i][1] = scan.nextInt();
			tree[edges[i][0]].add(tree[edges[i][1]]);
			in[edges[i][1]] ++;
			if(edges[i][1] == 1){
				flag = true;
			}
		}
		if(flag){
			for(int i = 1 ; i <= N ; i ++){
				if(edges[i][1] == 1){
					scan.println(i);
					return;
				}
			}
		}
		int cur = 0;
		for(int i = 1 ; i <= N ; i ++){
			if(in[i] == 2){
				cur = i;
				break;
			}
		}
		if(dfs(tree[cur] , cur , visit)){
			for(int i = 1 ; i <= N ; i ++){
				if(edges[i][1] == cur && visit[edges[i][0]]){
					scan.println(i);
					return;
				}
			}
		}else{
			ArrayList<Integer> ans = new ArrayList<>();
			for(int i = 1 ; i <= N ; i ++){
				if(edges[i][1] == cur)
					ans.add(i);
			}
			Collections.sort(ans);
			scan.println(ans.get(0) + " " + ans.get(1));
		}

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
