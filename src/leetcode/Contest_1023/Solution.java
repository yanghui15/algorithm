package leetcode.Contest_1023;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yanghui on 16/10/23.
 */
public class Solution {

	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> list = new ArrayList<Integer>();
		if(s == null || p == null || s.length() < p.length()) return list;
			int a[] = new int[26];
		int b[] = new int[26];
		for(int i = 0 ; i < p.length() ; i ++){
			a[p.charAt(i) - 'a'] ++;
		}
		for(int i = 0 ; i < p.length() ; i ++){
			b[s.charAt(i) - 'a'] ++;
		}
		int idx = p.length();
		for(int i = 0 ; i < s.length() ; i ++){
			boolean flag = true;
			for(int j = 0 ; j < 26 ; j ++){
				flag = flag && a[j] == b[j];
			}
			if(flag){
				list.add(i);
			}
			if(idx >= s.length()) break;
			b[s.charAt(i) - 'a'] --;
			b[s.charAt(idx ++) - 'a'] ++;
		}
		return list;
	}

	public String parseTernary(String expression) {
		if(expression == null || expression.length() == 0) return "";
		Stack<Character> stack = new Stack<Character>();
		for(int i = expression.length() - 1 ; i >= 0 ; i --){
			if(expression.charAt(i) == '?'){
				char a = stack.pop();
				stack.pop();
				char b = stack.pop();
				if(expression.charAt(i-1) == 'T'){
					stack.push(a);
				}else{
					stack.push(b);
				}
				i--;
			}else{
				stack.push(expression.charAt(i));
			}
		}
		return stack.pop()+"";
	}

	public int findKthNumber2(int n, int k) {
		int cnt = 0;
		for (int i = 1, count = 0, limit = n / 10; count < n; count++) {
			cnt ++;
			if(cnt == k) return i;
			if (i <= limit) {
				i *= 10;
			} else {
				if (i == n) {
					i /= 10;
				}
				for (++i; i % 10 == 0; i /= 10) {}
			}
		}
		return -1;
	}

	public int cal(long cur , int n , long[] pow){
		int cnt = 0;
		String prefix = String.valueOf(cur);
		String number = String.valueOf(n);

		String start = number.substring(0 , prefix.length());

		for(int i = prefix.length() ; i <= number.length() ; i ++){
			if(i == number.length()){
				if(cur == Integer.valueOf(start)){
					cnt += Math.max(0 , n - Integer.parseInt(start) * pow[i - prefix.length()] + 1);
				}else if(cur < Integer.valueOf(start)){
					cnt += pow[i - prefix.length()];
				}
			}else{
				cnt += pow[i - prefix.length()];
			}
		}
		return cnt;
	}


	public int findKthNumber(int n, int k) {
		long ans = 0;
		long[] pow = new long[10];
		pow[0] = 1;
		for(int i = 1 ; i < 10 ; i ++)
			pow[i] = pow[i-1] * 10l;
		while(k > 0){
			for(int i = 0 ; i <= 9 ; i ++){
				if(i == 0 && ans == 0) {
					continue;
				}
				int cnt = cal(ans * 10 + i , n , pow);
				if(cnt >= k){
					ans = ans * 10 + i;
					k --;
					break;
				}else{
					k -= cnt;
				}
			}
		}
		return (int) ans;
	}

	public void run(){
		System.out.println(findKthNumber(1000,1000) == findKthNumber2(1000,1000));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
