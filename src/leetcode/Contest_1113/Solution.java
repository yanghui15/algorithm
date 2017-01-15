package leetcode.Contest_1113;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by yanghui on 16/11/13.
 */
public class Solution {

	public boolean find132pattern(int[] nums) {
		int N = nums.length;
		if(N < 3) return false;
		TreeSet<Integer> set = new TreeSet<Integer>();
		int dp[] = new int[N];
		dp[0] = nums[0];
		for(int i = 1 ; i < N ; i ++){
			dp[i] = Math.min(dp[i-1] , nums[i]);
		}
		for(int i = nums.length - 1 ; i > 0 ; i --){
			Integer result = set.lower(nums[i]);
			if(result != null && result > dp[i-1]) return true;
			set.add(nums[i]);
		}
		return false;
	}

	public int findContentChildren(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);
		int N = g.length;
		int idx = 0;
		int ans = 0;
		for(int i = 0 ; i < N ; i ++){
			while(idx < s.length && s[idx] < g[i]){
				idx ++;
			}
			if(idx == s.length){
				break;
			}
			ans ++;
			idx ++;
		}
		return ans;
	}

	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		int N = A.length;
		if(N == 0) return 0;
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < N ; j ++){
				if(map.containsKey(A[i] + B[j])){
					map.put(A[i] + B[j] , map.get(A[i] + B[j]) + 1);
				}else{
					map.put(A[i] + B[j] , 1);
				}
			}
		}
		int ans = 0;
		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < N ; j ++){
				int cur = C[i] + D[j];
				if(map.containsKey(-cur)){
					ans += map.get(-cur);
				}
			}
		}
		return ans;
	}

	public boolean repeatedSubstringPattern(String str) {
		if(str == null || str.length() < 2) return false;
		int n = str.length();
		for(int i = 1 ; i < n ; i ++){
			if(str.charAt(i) == str.charAt(0)){
				if(n % (i - 0) == 0){
					int j = i;
					int idx = 0;
					boolean flag = true;
					while(j < n){
						if(idx == i){
							idx = 0;
						}
						if(str.charAt(idx) != str.charAt(j)){
							flag = false;
							break;
						}else{
							j ++;
							idx ++;
						}
					}
					if(flag){
						return true;
					}
				}
			}
		}
		return false;
	}

	public void run(){
		System.out.println(find132pattern(new int[]{-2 , 1,1}));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
