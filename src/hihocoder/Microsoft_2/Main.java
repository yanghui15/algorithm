package hihocoder.Microsoft_2;

import java.io.*;
import java.util.*;

/**
 * Created by yanghui on 2017/3/5.
 */
public class Main {

	FastIO scan = new FastIO();

	public void solveB(){
		long n = scan.nextLong();
		int q = scan.nextInt();
		long ans = n;
		long cnt = 1;
		long time = 0;
		while(true){
			long cur = time + n / cnt;
			if(n % cnt != 0) cur ++;
			if(cur > ans) break;
			else{
				ans = cur;
				time += q;
				cnt = cnt * 2;
			}
		}
		scan.println(ans);
	}

	public void add(HashMap<Integer,Integer> map , int cur){
		if(map.containsKey(cur)){
			map.put(cur , map.get(cur) + 1);
		}else{
			map.put(cur , 1);
		}
	}

	public void solveA(){
		HashMap<Integer,Integer> a = new HashMap<>();
		HashMap<Integer,Integer> b = new HashMap<>();
		HashMap<Integer,Integer> c = new HashMap<>();
		HashMap<Integer,Integer> d = new HashMap<>();

		int n = scan.nextInt();
		int[][] data = new int[n][2];
		for(int i = 0 ; i < n ; i ++){
			data[i][0] = scan.nextInt();
			data[i][1] = scan.nextInt();
			add(a , data[i][0]);
			add(b , data[i][1]);
			add(c , data[i][0] + data[i][1]);
			add(d , data[i][0] - data[i][1]);
		}
		long ans = 0;
		for(int i = 0 ; i < n ; i ++){
			ans += a.get(data[i][0]) - 1;
			ans += b.get(data[i][1]) - 1;
			ans += c.get(data[i][0] + data[i][1]) - 1;
			ans += d.get(data[i][0] - data[i][1]) - 1;
		}
		ans = ans / 2;
		scan.println(ans);
	}

	public void solveC(){
		int N = scan.nextInt();
		int[][] coins = new int[N][2];
		int sum = 0;
		for(int i = 0 ; i < N ; i ++){
			coins[i][0] = scan.nextInt();
			coins[i][1] = scan.nextInt();
			sum += coins[i][0] + coins[i][1];
		}
		sum = sum / (2 * N);
		long ans = 0;
		int[] dif = new int[2];
		for(int i = 0 ; i < N; i ++){
			coins[i][0] += dif[0];
			coins[i][1] += dif[1];

			int max_idx = (coins[i][0] > coins[i][1] ? 0 : 1);
			int min_idx = (max_idx == 0 ? 1 : 0);
			if(coins[i][max_idx] > sum && coins[i][min_idx] < sum){
				int abs = Math.min(Math.abs(coins[i][max_idx] - sum) , Math.abs(coins[i][min_idx] - sum));

				ans += abs;
				coins[i][max_idx] -= abs;
				coins[i][min_idx] += abs;
			}

			dif[0] = coins[i][0] - sum;
			dif[1] = coins[i][1] - sum;

			ans += Math.abs(dif[0]) + Math.abs(dif[1]);
		}
		scan.println(ans);
	}

	class TreeNode{
		int idx;
		ArrayList<TreeNode> nxt;
		int Ini = 0;
		int IPi = 0;
		int cost = 0;
		public TreeNode(int idx){
			this.idx = idx;
			this.nxt = new ArrayList<>();
		}
		public void add(TreeNode node){
			this.nxt.add(node);
		}
	}

	public int dfs(TreeNode root , boolean[] visit){
		ArrayList<Integer> info = new ArrayList<>();
		ArrayList<Integer> cost = new ArrayList<>();
		visit[root.idx] = true;
		for(TreeNode next : root.nxt){
			if(visit[next.idx]) continue;
			int tmp = dfs(next , visit);
			if(tmp > 0) {
				info.add(next.IPi);
				cost.add(tmp);
			}
		}
		int sum = 0;
		for(Integer cur : info) sum += cur;
		int[] dp = new int[sum + 1];
		Arrays.fill(dp , 50000000);
		dp[0] = 0;
		for(int i = 0 ; i < info.size() ; i ++){
			for(int j = sum ; j >= info.get(i) ; j --){
				dp[j] = Math.min(dp[j] , dp[j - info.get(i)] + cost.get(i));
			}
		}
		int ans = 50000000;
		for(int i = root.Ini ; i <= sum ; i ++){
			ans = Math.min(ans , dp[i]);
		}
		if(ans == 50000000) return -1;
		return ans + root.cost;
	}

	public void solveD(){
		int N = scan.nextInt();
		TreeNode[] trees = new TreeNode[N+1];
		int rt = 0;
		for(int i = 1 ; i <= N ; i ++){
			trees[i] = new TreeNode(i);
		}
		for(int i = 1 ; i <= N ;i ++){
			int Fi = scan.nextInt();
			int Ini = scan.nextInt();
			int IPi = scan.nextInt();
			int Ci = scan.nextInt();
			trees[i].Ini = Ini;
			trees[i].IPi = IPi;
			trees[i].cost = Ci;
			if(Fi == 0){
				rt = i;
			}else{
				trees[i].add(trees[Fi]);
				trees[Fi].add(trees[i]);
			}
		}
		int ans = dfs(trees[rt] , new boolean[N+1]);
		scan.println(ans);
	}

	public void run(){
		solveC();
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
