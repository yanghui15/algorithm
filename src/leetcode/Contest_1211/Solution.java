package leetcode.Contest_1211;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by yanghui on 16/12/11.
 */
public class Solution {

	public boolean validIpv4(String ip){
		String[] cur = ip.split("\\.");
		System.out.println(cur.length);
		if(cur.length != 4) return false;
		for(int i = 0 ; i < 4 ; i ++){
			if(cur[i].length() > 3 || cur[i].length() == 0) return false;
			for(int j = 0 ; j < cur[i].length() ; j ++){
				char tmp = cur[i].charAt(j);
				if(!(tmp >= '0' && tmp <= '9')) return false;
			}
			if(cur[i].startsWith("0")){
				if(!cur[i].equals("0")) return false;
			}
			int tmp = Integer.valueOf(cur[i]);
			if(!(tmp >= 0 && tmp <= 255)) return false;
		}
		return true;
	}

	public boolean validIpv6(String ip){
		String[] cur = ip.split(":");
		System.out.println(cur.length);
		if(cur.length != 8) return false;
		for(int i = 0 ; i < 8 ; i ++){
			if(cur[i].length() > 4 || cur[i].length() == 0) return false;
			for(int j = 0 ; j < cur[i].length() ; j ++){
				char tmp = cur[i].charAt(j);
				if(!((tmp >= '0' && tmp <= '9') || (tmp >= 'a' && tmp <= 'f') || (tmp >= 'A' && tmp <= 'F'))){
					return false;
				}
			}
		}
		return true;
	}


	public String validIPAddress(String IP) {
		if(validIpv4(IP)) return "IPv4";
		if(validIpv6(IP)) return "IPv6";
		return "Neither";
	}

	public int findMaxForm(String[] strs, int m, int n) {
		int length = strs.length;
		int[][] count = new int[length][2];
		for(int i = 0 ; i < length ; i ++){
			for(int j = 0 ; j < strs[i].length() ; j ++){
				count[i][strs[i].charAt(j) - '0'] ++;
			}
		}
		int[][] dp = new int[m+1][n+1];
		for(int i = 0 ; i < m ; i ++){
			Arrays.fill(dp[i] , -1);
		}
		int ans = 0;
		dp[0][0] = 0;
		for(int k = 0 ; k < length ; k ++){
			for(int i = m ; i >= count[k][0] ; i --){
				for(int j = n ; j >= count[k][1] ; j --){
					int x = i - count[k][0];
					int y = j - count[k][1];
					if(dp[x][y] != -1){
						dp[i][j] = Math.max(dp[i][j] , dp[x][y] + 1);
						ans = Math.max(dp[i][j] , ans);
					}
				}
			}
		}
		return ans;
	}

	public boolean judge(int[] houses , TreeSet<Integer> set , int radius){
		for(int i = 0 ; i < houses.length ; i ++){
			Integer a = set.ceiling(houses[i]);
			Integer b = set.floor(houses[i]);
			if(a == null && b == null) return false;
			if(a != null){
				if(Math.abs(a - houses[i]) - radius <= 0) continue;
			}
			if(b != null){
				if(Math.abs(b - houses[i]) - radius <= 0) continue;
			}
			return false;
		}
		return true;
	}

	public int findRadius(int[] houses, int[] heaters) {
		int left = 0;
		int right = 1000000000;
		TreeSet<Integer> set = new TreeSet<>();
		for(int i = 0 ; i < heaters.length ; i ++){
			set.add(heaters[i]);
		}
		int result = -1;
		while(left <= right){
			int mid = (left + right) / 2;
			boolean tmp = judge(houses , set , mid);
			if(tmp){
				result = mid;
				right = mid - 1;
			}else{
				left = mid + 1;
			}
		}
		return result;
	}

	public void solve(String s , int left , int right , String[][] dp){
		if(dp[left][right] != null) return;
		if(right - left  + 1 <= 4){
			dp[left][right] = s.substring(left,right + 1);
			return;
		}
		int length = right - left + 1;
		for(int i = length - 1 ; i >= 1 ; i --){
			if(length % i != 0) continue;
			String cur = s.substring(left , left + i);
			solve(s , left , left + i - 1 , dp);
			StringBuilder sb = new StringBuilder();
			for(int j = 0 ; j < length / i ; j ++){
				sb.append(cur);
			}
			if(sb.toString().equals(s.substring(left , right + 1))){
				String new_str = length/i + "[" + dp[left][left + i - 1] + "]";
				if(dp[left][right] == null || dp[left][right].length() > new_str.length()){
					dp[left][right] = new_str;
				}
			}
		}
		for(int i = left + 1 ; i <= right ; i ++){
			solve(s , left , i - 1 , dp);
			solve(s , i , right , dp);
			String newStr = dp[left][i-1] + dp[i][right];
			if(dp[left][right] == null || dp[left][right].length() > newStr.length()){
				dp[left][right] = newStr;
			}
		}
	}

	public String encode(String s) {
		String[][] dp = new String[s.length()][s.length()];
		solve(s , 0 , s.length() - 1 , dp);
		return dp[0][s.length() - 1];
	}

	public void run(){
		System.out.println(validIPAddress("1.1.1.1."));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
