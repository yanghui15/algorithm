package leetcode.Contest_0904;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by yanghui on 16/9/4.
 */
public class Solution {

	public String trans(int cur){
		String result = Integer.toBinaryString(cur);
		if(result.length() > 8){
			return result.substring(result.length() - 8);
		}
		while(result.length() < 8){
			result = "0"+result;
		}
		return result;
	}

	public int validate(String str){
		int cnt = 0;
		for(int i = 0 ; i < str.length() ; i ++){
			if(str.charAt(i) == '1'){
				cnt ++;
			}else{
				return cnt;
			}
		}
		return cnt;
	}


	public boolean validUtf8(int[] data) {
		int len = data.length;
		if(len == 0) return true;
		int last = -1;
		int i = 0;
		for(; i < len ;){
			int cur = validate(trans(data[i]));
			if(cur == 0 || cur == 2 || cur == 3 || cur == 4){
				if(cur == 0){
					i ++;
					continue;
				}else{
					for(int j = 1 ; j < cur ; j ++){
						if(j >= len) return false;
						int temp = validate(trans(data[i + j]));
						if(temp != 1)
							return false;
					}
					i += cur;
				}
			}else{
				return false;
			}
		}
		return true;
	}

	public boolean isSubsequence(String s, String t) {
		if(s.length() == 0) return true;
		int idx = 0;
		for(int i = 0 ; i < t.length() ; i ++){
			if(t.charAt(i) == s.charAt(idx)){
				idx ++;
				if(idx >= s.length())
					return true;
			}
		}
		return false;
	}

	public int longestSubstring(String s, int K) {
		int n = s.length();
		if(n == 0){
			return 0;
		}
		if(K == 0)
			return 0;
		if(K == 1)
			return n;
		int result = 0;
		for(int k = 1 ; k <= 26 ; k ++){
			int cnt[] = new int[26];
			int cc = 0;
			int dd = 0;
			int idx = 0;
			for(int i = 0 ; i < n ; i ++){
				while(idx < n && cc + (cnt[s.charAt(idx) - 'a'] == 0 ? 1 : 0) <= k){
					cc += (cnt[s.charAt(idx) - 'a'] == 0 ? 1 : 0);
					cnt[s.charAt(idx) - 'a'] ++;
					if(cnt[s.charAt(idx) - 'a'] == K){
						dd ++;
					}
					idx ++;
				}
				if(dd == cc){
					result = Math.max(result , idx - i);
				}
				cnt[s.charAt(i) - 'a'] --;
				if(cnt[s.charAt(i) - 'a'] == 0){
					cc --;
				}
				if(cnt[s.charAt(i) - 'a'] == K - 1){
					dd --;
				}
			}
		}
		return result;
	}

	public int longestSubstring2(String s, int K) {
		System.out.println(s);
		int n = s.length();
		if(n == 0){
			return 0;
		}
		int cnt[] = new int[26];
		for(int i = 0 ; i < s.length();  i ++){
			cnt[s.charAt(i) - 'a'] ++;
		}
		HashSet<Character> set = new HashSet<Character>();
		for(int i = 0 ; i < 26 ; i ++){
			if(cnt[i] < K && cnt[i] > 0){
				set.add((char)(i + 'a'));
			}
		}
		if(set.size() == 0)
			return s.length();
		int result = 0;
		int idx = 0;
		for(int i = 0 ; i < s.length() ; i ++){
			if(set.contains(s.charAt(i))){
				continue;
			}else{
				idx = i;
				while(idx < s.length() && !set.contains(s.charAt(idx))){
					idx ++;
				}
				result = Math.max(result , longestSubstring2(s.substring(i,idx) , K));
				i = idx - 1;
			}
		}
		return result;
	}

	public String generate(String str , int cnt){
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < cnt ; i ++){
			sb.append(str);
		}
		return sb.toString();
	}

	public String decodeString(String s) {
//		System.out.println(s);
		int len = s.length();
		if(len == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(i < len){
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
				break;
			}else{
				sb.append(s.charAt(i));
			}
			i++;
		}
		if(i > 0)
			return sb.toString() + decodeString(s.substring(i));
		for(; i < len ; i ++){
			if(s.charAt(i) == '['){
				break;
			}else{
				sb.append(s.charAt(i));
			}
		}
		if(i == len) return s;
		int cur = Integer.valueOf(sb.toString());
		sb = new StringBuilder();
		int cnt = 1;
		i ++;
		for(;i < len ; i ++){
			if(s.charAt(i) == '['){
				cnt ++;
				sb.append(s.charAt(i));
			}else if(s.charAt(i) == ']'){
				cnt --;
				if(cnt == 0)
					break;
				else
					sb.append(s.charAt(i));
			}else{
				sb.append(s.charAt(i));
			}
		}
		i ++;
		return generate(decodeString(sb.toString()) , cur) + decodeString(s.substring(i));
	}

	public void run(){
		Scanner  scan = new Scanner(System.in);
		String str = scan.next();
		int k = scan.nextInt();
		System.out.println(this.longestSubstring2(str , k));
	}


	public static void main(String args[]){
		new Solution().run();
	}

}
