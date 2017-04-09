package leetcode.Contest_2017_0219;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by yanghui on 2017/2/19.
 */
public class Solution {

	public boolean detectCapitalUse(String word) {
		char[] words = word.toCharArray();
		int cnt = 0;
		for(int i = 0 ; i < words.length ; i ++){
			if(Character.isUpperCase(words[i]))
				cnt ++;
		}
		if(cnt == words.length){
			return true;
		}else if(cnt == 1 && Character.isUpperCase(words[0])){
			return true;
		}else if(cnt == 0){
			return true;
		}else{
			return false;
		}
	}

	public int findMaxLength(int[] nums) {
		int n = nums.length;
		if(n == 0) return 0;
		int ans = 0;
		int[] sum = new int[n];
		int[] cur = new int[n];
		for(int i = 0 ; i < n ; i ++){
			if(i == 0){
				sum[i] = nums[i];
			}else{
				sum[i] = sum[i-1] + nums[i];
			}
			cur[i] = (i + 1) - sum[i] - sum[i];//cnt1 - cnt0
		}
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i = n - 1 ; i >= 0 ; i --){
			if(map.containsKey(cur[i])) {
				ans = Math.max(ans, map.get(cur[i]) - i);
			}else{
				map.put(cur[i] , i);
			}
		}
		if(map.containsKey(0))
			ans = Math.max(ans , map.get(0) + 1);
		return ans;
	}

	int countArrange = 0;

	public void dfs(int cur , boolean[] index , int N){
		if(cur > N) {
			countArrange++;
			return;
		}
		for(int i = 1 ; i <= N ; i ++){
			if((cur % i == 0 || i % cur == 0) && !index[i]){
				index[i] = true;
				dfs(cur + 1 , index , N);
				index[i] = false;
			}
		}
	}

	public int countArrangement(int N) {
		dfs(1 , new boolean[N+1] , N);
		return countArrange;
	}

	public int findMinMoves(int[] machines) {
		long sum = 0;
		int n = machines.length;
		for(int i = 0 ; i < n ; i ++){
			sum = sum + (long) machines[i];
		}
		if(sum % n != 0) return -1;
		sum = sum / n;
		long ans = 0;
		for(int i = 0 ; i < n ; i ++){
			ans = ans + Math.abs(machines[i] - sum);
		}
		return (int)(ans / 2);
	}

	public void run(){
		System.out.println(findMinMoves(new int[]{0,3,0}));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
