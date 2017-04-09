package hihocoder.Offer_2017_0409;

import java.io.*;
import java.util.*;

/**
 * Created by yanghui on 2017/3/5.
 */
public class Main {

	FastIO scan = new FastIO();

	public void solveA(){
		int n = scan.nextInt();
		for(int i = 0 ; i < n ;i ++){
			String str = scan.next();
			if(str.contains("_")){
				String[] strs = str.split("_");
				StringBuilder sb = new StringBuilder();
				sb.append(strs[0]);
				for(int j = 1 ; j < strs.length ; j ++){
					String cur = Character.toUpperCase(strs[j].charAt(0)) + strs[j].substring(1);
					sb.append(cur);
				}
				scan.println(sb.toString());
			}else{
				ArrayList<String> list = new ArrayList<>();
				int left = 0;
				for(int j = 1 ; j < str.length() ; j ++){
					if(str.charAt(j) >= 'A' && str.charAt(j) <= 'Z'){
						list.add(str.substring(left,j));
						left = j;
					}
				}
				list.add(str.substring(left,str.length()));
				StringBuilder sb = new StringBuilder();
				sb.append(list.get(0));
				sb.append('_');
				for(int j = 1 ; j < list.size() ; j ++){
					String cur = Character.toLowerCase(list.get(j).charAt(0)) + list.get(j).substring(1);
					sb.append(cur);
					if(j != list.size() - 1){
						sb.append('_');
					}
				}
				scan.println(sb.toString());
			}
		}
	}

	public long sum(long[][] sum , int i ,int j , int idx , int k){
		if(i == 0 && j == 0){
			return sum[idx][k];
		}else if(i == 0){
			return sum[idx][k] - sum[idx][j-1];
		}else if(j == 0){
			return sum[idx][k] - sum[i-1][k];
		}else {
			return sum[idx][k] + sum[i-1][j-1] - sum[idx][j-1] - sum[i-1][k];
		}
	}

	public void solveB(){
		int N = scan.nextInt();
		int M = scan.nextInt();
		long K = scan.nextLong();
		int[][] data = new int[N][M];
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < M ; j ++){
				data[i][j] = scan.nextInt();
			}
		}
		long[][] sum = new long[N][M];
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < M ; j ++){
				if(i == 0 && j == 0) {
					sum[i][j] = data[i][j];
				}else if(i == 0){
					sum[i][j] = sum[i][j-1] + data[i][j];
				}else if(j == 0){
					sum[i][j] = sum[i-1][j] + data[i][j];
				}else{
					sum[i][j] = sum[i-1][j] + sum[i][j-1] + data[i][j] - sum[i-1][j-1];
				}
			}
		}
		int ans = 0;
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < M ; j ++){
				int idx = N-1;
				for(int k = j ; k < M ; k ++){
					while(idx >= i){
						long cur = sum(sum , i , j , idx , k);
						if(cur > K){
							idx --;
						}else{
							break;
						}
					}
					ans = Math.max(ans , (idx - i + 1) * (k - j + 1));
				}
			}
		}
		scan.println(ans);
	}

	public void cal(char cur , ArrayList<HashMap<String , Integer>> list){
		for(int j = 1 ; j <= 7 ; j ++){
			HashMap<String,Integer> set = new HashMap<>();
			set.put(""+cur + j , 1);
			set.put(""+cur + (j+1) , 1);
			set.put(""+cur + (j+1) , 1);
			list.add(set);
		}
		for(int j = 1 ; j <= 9 ; j ++){
			HashMap<String,Integer> set = new HashMap<>();
			set.put(""+cur + j , 3);
			list.add(set);
		}
	}

	public boolean add(HashMap<String,Integer> a , HashMap<String,Integer> map){
		for(String cur : a.keySet()){
			if(map.containsKey(cur) && map.get(cur) + a.get(cur) > 4){
				return false;
			}
		}
		for(String cur : a.keySet()){
			if(map.containsKey(cur)){
				map.put(cur , map.get(cur) + a.get(cur));
			}else{
				map.put(cur , a.get(cur));
			}
		}
		return true;
	}

	public void dfs(ArrayList<HashMap<String,Integer>> list , ArrayList<HashMap<String,Integer>> list2 , ArrayList<HashMap<String,Integer>> result){
		for(HashMap<String,Integer> first : list){
			for(HashMap<String,Integer> second : list2){
				HashMap<String,Integer> map = new HashMap<>();
				add(first , map);
				boolean sec = add(second , map);
				if(!sec) continue;
				result.add(map);
			}
		}
	}

	public void solveC(){
		ArrayList<HashMap<String , Integer>> ab = new ArrayList<>();
		ArrayList<HashMap<String , Integer>> ac = new ArrayList<>();
		ArrayList<HashMap<String , Integer>> bc = new ArrayList<>();
		cal('a' , ab);
		cal('b' , ab);
		cal('a' , ac);
		cal('c' , ac);
		cal('b' , bc);
		cal('c' , bc);
		ArrayList<HashMap<String , Integer>> abs = new ArrayList<>();
		ArrayList<HashMap<String , Integer>> abss = new ArrayList<>();
		dfs(ab , ab , abs);
		dfs(ab , abs , abss);

		ArrayList<HashMap<String , Integer>> acs = new ArrayList<>();
		ArrayList<HashMap<String , Integer>> acss = new ArrayList<>();
		dfs(ac , ac , acs);
		dfs(ac , acs , acss);

		ArrayList<HashMap<String , Integer>> bcs = new ArrayList<>();
		ArrayList<HashMap<String , Integer>> bcss = new ArrayList<>();
		dfs(bc , bc , bcs);
		dfs(bc , bcs , bcss);

		ArrayList<HashMap<String,Integer>> list = new ArrayList<>();
		list.addAll(abss);
		list.addAll(acss);
		list.addAll(bcss);
//		System.out.println(list.size());

		int n = scan.nextInt();
		HashMap<String,Integer> map = new HashMap<>();
		for(int i = 0 ; i < 14 ; i ++){
			String cur = scan.next();
			if(map.containsKey(cur)){
				map.put(cur , map.get(cur) + 1);
			}else{
				map.put(cur , 1);
			}
		}
		for(int i = 0 ; i < n ; i ++){
			scan.next();
		}
		scan.println(-1);
	}

	public long[][] multiply(long[][] a , long[][] b , long mod){
		long[][] ans = new long[64][64];
		for(int i = 0 ; i < 64 ; i ++){
			for(int j = 0 ;  j < 64 ; j ++){
				long cur = 0;
				for(int k = 0 ; k < 64 ; k ++){
					cur = (cur + a[i][k] * b[k][j]) % mod;
				}
				ans[i][j] = cur;
			}
		}
		return ans;
	}

	public long[][] pow(long[][] a , long N , long mod){
		long[][] result = new long[64][64];
		for(int i = 0 ; i < 64 ; i ++) result[i][i] = 1;
		while(N > 0){
			if(N % 2 == 1){
				result = multiply(result , a , mod);
			}
			a = multiply(a , a , mod);
			N = N / 2;
		}
		return result;
	}

	public boolean judge(int i , int j){
		return i >= 0 && i < 8 && j >= 0 && j < 8;
	}

	public void solveD(){
		int N = scan.nextInt();
		int R = scan.nextInt() - 1;
		int C = scan.nextInt() - 1;
		long mod = 1000000007L;
		int[] dx = new int[]{1 , 2 , -1 , -2 , 1 , 2 , -1 , -2};
		int[] dy = new int[]{2 , 1 , -2 , -1 , -2 , -1 , 2 , 1};
		long ans = 0;
		long[][] data = new long[64][64];
		for(int i = 0 ; i < 8 ; i ++){
			for(int j = 0 ; j < 8 ; j ++){
				for(int k = 0 ; k < 8 ; k ++){
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(judge(nx , ny)){
						data[i * 8 + j][nx * 8 + ny] = 1;
					}
				}
			}
		}
		data = pow(data , N , mod);
		for(int i = 0 ; i < 64 ; i ++){
			ans = (ans + data[R * 8 + C][i]) % mod;
		}
		scan.println(ans);
	}

	public void run() throws Exception{
		solveD();
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
