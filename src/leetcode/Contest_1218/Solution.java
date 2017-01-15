package leetcode.Contest_1218;

import java.io.File;
import java.util.*;

/**
 * Created by yanghui on 16/12/18.
 */
public class Solution {

	public class TrieNode{
		TrieNode[] nxt;
		boolean flag;
		public TrieNode(){
			nxt = new TrieNode[26];
			flag = false;
		}
	}

	public void insert(TrieNode rt , String s , int idx){
		if(idx >= s.length()) return;
		int cur = s.charAt(idx) - 'a';
		if(rt.nxt[cur] == null){
			rt.nxt[cur] = new TrieNode();
		}
		if(idx == s.length() - 1) rt.nxt[cur].flag = true;
		insert(rt.nxt[cur] , s , idx + 1);
	}

	public int search(TrieNode rt , String s , int idx , int[] dp){
		if(idx >= s.length()) return -1;
		int cur = s.charAt(idx) - 'a';
		if(rt.nxt[cur] != null){
			if(rt.nxt[cur].flag){
				if(idx == s.length() - 1) return 1;
				if(dp[idx + 1] > 0) return 2;
			}
			return search(rt.nxt[cur] , s , idx + 1 , dp);
		}
		return -1;
	}

	public boolean judge(String s , TrieNode rt){
		int len = s.length();
		if(len == 0) return false;
		int dp[] = new int[s.length()];
		for(int i = s.length() - 1 ; i >= 0 ; i --){
			dp[i] = search(rt , s , i , dp);
		}
		return dp[0] == 2;
	}

	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return Integer.compare(s1.length() , s2.length());
			}
		});
		List<String> ans = new ArrayList<>();
		TrieNode root = new TrieNode();
		for(int i = 0 ; i < words.length ; i ++){
			if(judge(words[i] , root)){
				ans.add(words[i]);
			}
			insert(root , words[i] , 0);
		}
		return ans;
	}

	public boolean dfs(int[] nums , boolean[] visit , int cur , int cnt , int sq_len){
		if(cnt == 3) return true;
		System.out.println(cur + " " + sq_len);
		for(int i = 0 ; i < nums.length ; i ++){
			if(visit[i]) continue;
			if(cur + nums[i] > sq_len) return false;
			else{
				visit[i] = true;
				boolean re = false;
				if(cur + nums[i] == sq_len){
					re = dfs(nums , visit , 0 , cnt + 1 , sq_len);
				}else{
					re = dfs(nums , visit , cur + nums[i] , cnt , sq_len);
				}
				if(re) return true;
				else{
					visit[i] = false;
				}
			}
		}
		return false;
	}

	public boolean makesquare(int[] nums) {
		int n = nums.length;
		if(n < 4) return false;
		int sum = 0;
		for(int i = 0 ; i < n ; i ++){
			sum += nums[i];
		}
		if(sum % 4 != 0) return false;
		int sq_len = sum / 4;
		boolean[] visit = new boolean[nums.length];
		return dfs(nums , visit , 0 , 0 , sq_len);
	}

	public void help(int[] cnt , int num){
		for(int i = 0 ; i < 30 ; i ++){
			if(((num >> i) & 1) == 1) cnt[i] ++;
		}
	}

	public int totalHammingDistance(int[] nums) {
		int n = nums.length;
		if(n == 1) return 0;
		int cnt[] = new int[30];
		help(cnt , nums[0]);
		int ans = 0;
		for(int i = 1 ; i < n ; i ++){
			for(int j = 0 ; j < 30 ; j ++){
				if(((nums[i] >> j) & 1) == 1){
					ans += i - cnt[j];
				}else{
					ans += cnt[j];
				}
			}
			help(cnt , nums[i]);
		}
		return ans;
	}

	public int hammingDistance(int x, int y) {
		int ans = x ^ y;
		int cnt = 0;
		for(int i = 0 ; i <= 31 ; i ++){
			if(((ans >> i) & 1) == 1)
				cnt ++;
		}
		return cnt;
	}

	public void run(){
		System.out.println(makesquare(new int[]{5,5,5,5,16,4,4,4,4,4,3,3,3,3,4}));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
