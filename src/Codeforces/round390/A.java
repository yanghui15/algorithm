package Codeforces.round390;


import java.io.*;
import java.util.*;

/**
 * Created by yanghui on 16/12/30.
 */
public class A {

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

	FastIO scan = new FastIO();

	public boolean valid(char[][] str){
		for(int i = 0 ; i < 4 ; i ++){
			for(int j = 0 ; j < 4 ; j ++){
				if(str[i][j] != 'x') continue;
				if(j + 1 < 4 && j + 2 < 4 && str[i][j + 1] == 'x' && str[i][j + 2] == 'x'){
					return true;
				}
				if(i + 1 < 4 && i + 2 < 4 && str[i + 1][j] == 'x' && str[i + 2][j] == 'x'){
					return true;
				}
				if(i + 1 < 4 && i + 2 < 4 && j + 1 < 4 && j + 2 < 4 && str[i + 1][j + 1] == 'x' && str[i + 2][j + 2] == 'x'){
					return true;
				}
				if(i + 1 < 4 && i + 2 < 4 && j - 1 >= 0 && j -2 >= 0 && str[i + 1][j - 1] == 'x' && str[i + 2][j - 2] == 'x'){
					return true;
				}
				if(i - 1 >=0 && i - 2 >= 0 && j + 1 < 4 && j + 2 < 4 && str[i - 1][j + 1] == 'x' && str[i - 2][j + 2] == 'x'){
					return true;
				}
				if(i - 1 >=0 && i - 2 >= 0 && j - 1 >= 0 && j - 2 >= 0 && str[i - 1][j - 1] == 'x' && str[i - 2][j - 2] == 'x'){
					return true;
				}
			}
		}
		return false;
	}

	public boolean gao(TreeMap<Integer,Integer> map  , HashMap<Integer,Integer> rehash, int cur , int idx){
		if(map.containsKey(cur)) return false;
		map.put(cur , idx);
		rehash.put(idx , cur);
		return true;
	}

	public void run(){
		int n = scan.nextInt();
		int k = scan.nextInt();
		int re[][] = new int[n][2];
		TreeMap<Integer,Integer> map = new TreeMap<>();
		HashMap<Integer,Integer> rehash = new HashMap<>();
		for(int i = 0 ; i < n ; i ++){
			re[i][0] = scan.nextInt();
			re[i][1] = scan.nextInt();
			map.put(re[i][0] , 0);
			map.put(re[i][1] , 0);
		}
		if(k == 1){
			int ans = 0;
			int ans_idx = -1;
			for(int i = 0 ; i < n ; i ++){
				if(re[i][1] - re[i][0] + 1 > ans){
					ans = re[i][1] - re[i][0] + 1;
					ans_idx = i + 1;
				}
			}
			scan.println(ans);
			scan.println(ans_idx);
		}else{
			int idx = 1 ;
			ArrayList<Integer> list = new ArrayList<>();
			for(Integer cur : map.keySet()){
				list.add(cur);
			}
			map.clear();

			for(Integer cur : list){
				if(gao(map , rehash , cur - 1 , idx))
					idx ++;
				if(gao(map , rehash , cur , idx))
					idx ++;
				if(gao(map , rehash , cur + 1 , idx))
					idx ++;
			}
			System.out.println(map);
//			for(int i = 0 ; i < list.size() ; i ++){
//				if(i == 0){
//					map.put(list.get(i) , idx);
//					rehash.put(idx , list.get(i));
//					idx ++;
//				}else{
//					if(list.get(i) - list.get(i - 1) != 1){
//						rehash.put(idx , list.get(i - 1) + 1);
//						idx ++;
//					}
//					map.put(list.get(i) , idx);
//					rehash.put(idx , list.get(i));
//					idx ++;
//				}
//			}


			int[] dp = new int[idx + 2];
			int[] nums = new int[idx + 1];
			for(int i = 0 ; i < n ; i ++){
				dp[map.get(re[i][0]) + 1] ++;
				dp[map.get(re[i][1]) + 1] --;
			}
			for(int i = 1 ; i <= idx ; i ++){
				nums[i] = nums[i - 1] + dp[i];
			}
			int ans = 0;
			int idx_left = 0;
			int i_left = 0;
			int i_right = 0;
			for(int i = 1 ; i <= idx ; i ++){
				if(nums[i] >= k){
					if(idx_left == 0){
						idx_left = i;
					}
					if(rehash.get(i) - rehash.get(idx_left) > ans){
						ans = rehash.get(i) - rehash.get(idx_left);
						i_left = rehash.get(idx_left);
						i_right = rehash.get(i);
					}
				}else{
					idx_left = 0;
				}
			}
			if(ans == 0){
				scan.println(0);
				for(int i = 1 ; i <= k ; i ++){
					if(i == k) scan.println(i);
					else
						scan.print(i + " ");
				}
			}else{
				scan.println(ans);
//				scan.println(i_left + " " + i_right);
				int cnt = 0;
				for(int i = 0 ; i < n ; i ++){
					if(re[i][0] <= i_left && re[i][1] >= i_right){
						cnt ++;
						if(cnt == k) {
							scan.println(i + 1);
							break;
						}
						else scan.print((i + 1) + " ");
					}
				}
			}
		}
	}

	public static void main(String args[]){
		new A().run();
	}

}
