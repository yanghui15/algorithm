package leetcode.Contest_1204;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yanghui on 16/12/4.
 */
public class Solution {

	public int cal(String s1 , int n1 , String s2 , int n2 , int len  , int start){
		int n = s1.length();
		int m = s2.length();
		int j = 0;
		for(int i = 0 ; i < len ; i ++){
			int idx = (start + i) % n;
			if(s1.charAt(idx) == s2.charAt(j)){
				j ++;
			}
			if(j == m){
				return i + 1;
			}
		}
		return -1;
	}

	public boolean All(String s1 , String s2){
		if(s2.length() != 1) return false;
		for(int i = 0 ; i < s1.length() ; i ++){
			if(s1.charAt(i) != s2.charAt(0)) return false;
		}
		return true;
	}

	public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
		int n = s1.length();
		System.out.println(n);
		int m = s2.length();
		if(All(s1 , s2)){
			return n * n1 / n2;
		}
		int len = n * m;
		int nxt[] = new int[n];
		for(int i = 0 ; i < n ; i ++){
			nxt[i] = cal(s1 , n1 , s2 , n2 , len , i);
		}
		int tot = n * n1;
		int ans = 0;
		for(int i = 0 ; i < tot ; ){
			int idx = i % n;
			if(nxt[idx] == -1) break;
			i += nxt[idx];
			if(i <= tot)
				ans ++;
		}
		return ans / n2;
	}

	public int findSubstringInWraproundString(String p) {
		if(p == null || p.length() == 0) return 0;
		int n = p.length();
		int[] dp = new int[n];
		dp[0] = 1;
		for(int i = 1 ; i < n ; i ++){
			int cur = p.charAt(i) - 'a';
			int last = p.charAt(i-1)  - 'a';
			if((last + 1) % 26 == cur){
				dp[i] = dp[i - 1] + 1;
			}else{
				dp[i] = 1;
			}
		}
		int ans[] = new int[26];
		for(int i = 0 ; i < n ; i ++){
			int idx = i - dp[i] + 1;
			for(int j = idx ; j <= i ; j ++){
				ans[p.charAt(j) - 'a'] = Math.max(ans[p.charAt(j) - 'a'] , i - j + 1);
			}
		}
		int result = 0;
		for(int i = 0 ; i < 26 ; i ++){
			result += ans[i];
		}
		return result;
	}


	public int cross(int[]a , int[] b){
		return (a[0] * b[1] - a[1] * b[0]) < 0 ? -1 : 1;
	}

	public int[] get(List<Integer> a , List<Integer> b){
		int[] ans = new int[2];
		ans[0] = a.get(0) - b.get(0);
		ans[1] = a.get(1) - b.get(1);
		return ans;
	}


	public boolean isConvex(List<List<Integer>> points) {
		if (points == null || points.size() < 3) return false;
		Collections.sort(points, new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> a, List<Integer> b) {
				if(a.get(0) == b.get(0)) return Integer.compare(a.get(1) , b.get(1));
				return Integer.compare(a.get(0) , b.get(0));
			}
		});
		int n = points.size();
		points.add(new ArrayList<Integer>(points.get(0)));
		points.add(new ArrayList<Integer>(points.get(1)));
		int now = cross(get(points.get(1) , points.get(0)) , get(points.get(2) , points.get(1)));
		for(int i = 1 ; i < n ; i ++){
			int next = cross(get(points.get(i+1) , points.get(i)) , get(points.get(i+2) , points.get(i)));
			if(now * next < 0) return false;
			now = next;
		}
		return true;
	}

	public int countSegments(String s) {
		if(s == null || s.length() == 0) return 0;
		int n = s.length();
		int ans = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < n ; i ++){
			if(s.charAt(i) == ' '){
				if(sb.length() != 0) ans ++;
				sb = new StringBuilder();
			}else{
				sb.append(s.charAt(i));
			}
		}
		if(sb.length() != 0) ans ++;
		return ans;
	}

	public void run(){
		long start = System.currentTimeMillis();
		System.out.println(getMaxRepetitions("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab" , 1000000 , "a" , 1));
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
