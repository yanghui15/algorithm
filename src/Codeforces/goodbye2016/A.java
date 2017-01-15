package Codeforces.goodbye2016;


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

	public void run(){
		int n = scan.nextInt();
		int c[] = new int[n + 1];
		int d[] = new int[n + 1];
		for(int i = 1 ; i <= n ; i ++){
			c[i] = scan.nextInt();
			d[i] = scan.nextInt();
		}

		long max[] = new long[n + 2];
		long min[] = new long[n + 2];
		max[0] = Integer.MAX_VALUE;
		min[0] = Integer.MIN_VALUE;
		for(int i = 1 ; i <= n ; i ++){
			if(min[i] > max[i]){
				scan.println("Impossible");
				return;
			}
			min[i] = min[i-1] + c[i];
			max[i] = max[i-1] + c[i];
			if(d[i] == 1){
				min[i] = Math.max(min[i] , 1900 + c[i]);
			}else{
				max[i] = Math.min(max[i] , 1899 + c[i]);
			}
//			System.out.println(min[i] + " " + max[i]);
		}
		if(min[n] > max[n]){
			scan.println("Impossible");
			return;
		}
		long cur = max[n] + 1;
		boolean flag = true;
		for(int i = n ; i >= 1 ; i --){
			cur -= c[i];
			if(d[i] == 1){
				if(cur < 1900){
					flag = false;
					break;
				}
			}else{
				if(cur >= 1900){
					flag = false;
					break;
				}
			}
		}
		if(flag){
			scan.println("Infinity");
		}else{
			scan.println(max[n]);
		}
	}

	public static void main(String args[]){
		new A().run();
	}

}
