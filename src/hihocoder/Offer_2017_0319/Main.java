package hihocoder.Offer_2017_0319;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by yanghui on 2017/3/5.
 */
public class Main {

	FastIO scan = new FastIO();

	public void solveA() throws Exception{
		int T = scan.nextInt();
		for(int i = 0 ; i < T ; i ++){
			String str = scan.next();
			int cnt = 0;
			for(int j = 0 ; j < str.length() ; j ++){
				if(str.charAt(j) == 'A') cnt ++;
			}
			if(cnt >= 2){
				scan.println("NO");
			}else{
				cnt = 0;
				boolean flag = false;
				for(int j = 0 ; j < str.length() ; j ++){
					if(str.charAt(j) == 'L'){
						cnt ++;
						if(cnt == 3){
							flag = true;
							break;
						}
					}else{
						cnt = 0;
					}
				}
				if(flag){
					scan.println("NO");
				}else{
					scan.println("YES");
				}
			}
		}
	}

	public void solveB(){
		int N = scan.nextInt();
		long[][][] dp = new long[N+1][2][3];
		long mod = 1000000000 + 7;
		dp[1][0][0] = 1;
		dp[1][1][0] = 1;
		dp[1][0][1] = 1;
		dp[1][1][1] = 0;
		for(int i = 2 ; i <= N ; i ++){
			for(int j = 0 ; j < 3 ; j ++){
				dp[i][1][0] = (dp[i][1][0] + dp[i-1][0][j] + dp[i-1][1][j]) % mod;
				dp[i][0][0] = (dp[i][0][0] + dp[i-1][0][j]) % mod;
			}

			for(int j = 1 ; j < 3 ; j ++){
				dp[i][1][j] = (dp[i][1][j] + dp[i-1][1][(j + 3 - 1) % 3]) % mod;
				dp[i][0][j] = (dp[i][0][j] + dp[i-1][0][(j + 3 - 1) % 3]) % mod;
			}
		}
		long ans = 0;
		for(int i = 0 ; i < 3 ; i ++){
			ans = (ans + dp[N][0][i] + dp[N][1][i]) % mod;
		}
		scan.println(ans);
	}

	public void swap(int[] nums , int i , int j){
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public void reverse(int[] nums , int left , int right){
		while(left <= right){
			swap(nums , left , right);
			left ++;
			right --;
		}
	}

	public boolean nextPermutation(int[] nums) {
		if(nums == null || nums.length < 2) return false;
		int n = nums.length;
		int idx = n - 2;
		while(idx >= 0){
			if(nums[idx] < nums[idx + 1]){
				break;
			}else{
				idx --;
			}
		}
		if(idx == -1) {
			reverse(nums , 0 , n - 1);
			return false;
		}
		int j = -1;
		for(int i = idx + 1 ; i < n ; i ++){
			if(nums[i] > nums[idx]){
				if(j == -1){
					j = i;
				}else{
					if(nums[j] >= nums[i]) j = i;
				}
			}
		}
		swap(nums , idx , j);
		reverse(nums , idx + 1 , n - 1);
		return true;
	}

	class point{
		int x , y;
		public point(int x , int y){
			this.x = x;
			this.y = y;
		}
	}

	class pattern{
		ArrayList<point> list;
		int val;

		public pattern(int val){
			this.val = val;
			list = new ArrayList<>();
		}

		public int size(){
			return this.list.size();
		}

		public point get(int idx){
			return this.list.get(idx);
		}
	}

	public boolean judge(point a , point b){
		return a.x == b.x && a.y == b.y;
	}

	public boolean judge(point a , point b , point c){
		if(a.x == b.x) {
			if(a.x == c.x)
				return true;
			else
				return false;
		}
		if(a.y == b.y) {
			if(a.y == c.y)
				return true;
			else
				return false;
		}
		return (b.y - a.y) * (c.x - b.x) == (c.y - b.y) * (b.x - a.x);
	}

	public ArrayList<point> gao(ArrayList<point> list){
		ArrayList<point> ans = new ArrayList<>();
		if(list.size() < 3){
			return list;
		}else{
			ans.add(list.get(0));
			ans.add(list.get(1));
			for(int i = 2 ; i < list.size() ; i ++){
				if(judge(list.get(i) , ans.get(ans.size() - 1))) continue;
				if(judge(ans.get(ans.size() - 2) , ans.get(ans.size() - 1) , list.get(i))){
					ans.remove(ans.size()-1);
					ans.add(list.get(i));
				}else{
					ans.add(list.get(i));
				}
			}
		}
		return ans;
	}

	public int valid(int[] a , pattern p[] , ArrayList<point> valid) {
		pattern pat = new pattern(0);
		for(int i = 0 ; i < a.length ; i ++){
			if(i > 0){
				point last = p[i-1].list.get(p[i].size() - 1);
				point first = p[i].list.get(0);
				if(last.x != first.x || last.y != first.y){
					return -1;
				}
			}
			for(point cur : p[i].list){
				pat.list.add(cur);
			}
			pat.val += p[i].val;
		}
		if(!judge(valid.get(0) , pat.get(0))){
			return -1;
		}
		int idx = 1;
		for(int i = 1 ; i < valid.size() ; i ++){
			if(idx >= pat.size()) return -1;
			if(judge(valid.get(i) , pat.get(i))){
				continue;
			}else{
				return -1;
			}
		}
		return pat.val;
	}

	public void solveD(){
		int n = scan.nextInt();
		int[] a = new int[n];
		for(int i = 0 ; i < n ; i ++){
			a[i] = i;
		}
		pattern[] p = new pattern[n];
		for(int i = 0 ; i < n ; i ++){
			int t = scan.nextInt();
			int c = scan.nextInt();
			p[i] = new pattern(c);
			for(int j = 0 ; j < t ; j ++){
				p[i].list.add(new point(scan.nextInt() , scan.nextInt()));
			}
		}

		ArrayList<point> valid = new ArrayList<>();
		boolean flag = false;
		int m = scan.nextInt();
		for(int i = 0 ; i < m ; i ++){
			point[] tmp = new point[2];
			for(int j = 0 ; j < 2 ; j ++){
				tmp[i] = new point(scan.nextInt() , scan.nextInt());
			}
			if(i == 0){
				valid.add(tmp[0]);
				valid.add(tmp[1]);
			}else{
				if(judge(tmp[0] , valid.get(valid.size() - 1))){
					valid.add(tmp[1]);
				}else{
					flag = true;
				}
			}
		}
		if(flag) {
			scan.println(-1);
			return;
		}

		int ans = Integer.MAX_VALUE;
		while(true){
			int tmp = valid(a , p , valid);
			if(tmp > 0){
				ans = Math.min(ans , tmp);
			}
			if(nextPermutation(a)){
				continue;
			}else{
				break;
			}
		}
		scan.println(ans);
	}

	public boolean binary_search(int[] data , long val , long k){
		long ans = 0;
		HashMap<Integer,Integer> map = new HashMap<>();
		int idx = -1;
		long sum = 0;
		for(int i = 0 ;i < data.length ; i ++){
			while(idx + 1 < data.length){
				int cnt = map.get(data[idx + 1]) == null ? 0 : map.get(data[idx + 1]);
				if(sum + cnt <= val) {
					sum += cnt;
					map.put(data[idx + 1], cnt + 1);
					idx++;
				}else{
					break;
				}
			}
			ans += idx - i + 1;
			sum -= map.get(data[i]) - 1;
			map.put(data[i] , map.get(data[i]) - 1);
		}
		return ans >= k;
	}

	public void solveC(){
		int T = scan.nextInt();
		for(int t = 0 ; t < T ; t ++){
			int n = scan.nextInt();
			long k = scan.nextLong();
			int[] data = new int[n];
			for(int i = 0 ; i < n ; i ++){
				data[i] = scan.nextInt();
			}
			long left = 0;
			long right = (long) n * (long)(n + 1) / 2L;
			long result = 0;
			while(left <= right){
				long mid = (left + right) / 2;
				if(binary_search(data , mid , k)){
					result = mid;
					right = mid - 1;
				}else{
					left = mid + 1;
				}
			}
			scan.println(result);
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
