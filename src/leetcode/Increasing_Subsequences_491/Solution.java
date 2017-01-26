package leetcode.Increasing_Subsequences_491;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yanghui on 2017/1/25.
 */
public class Solution {

	public void dfs(Set<List<Integer>> set , List<Integer> cur , int idx , int[] nums){
		if(cur.size() >= 2){
			set.add(new ArrayList<Integer>(cur));
		}
		for(int i = idx ; i < nums.length ; i ++){
			if(cur.size() == 0 || cur.get(cur.size() - 1) <= nums[i]){
				cur.add(nums[i]);
				dfs(set , cur , i + 1 , nums);
				cur.remove(cur.size() - 1);
			}
		}
	}

	public List<List<Integer>> findSubsequences(int[] nums) {
		Set<List<Integer>> set = new HashSet<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		dfs(set , cur , 0 , nums);
		return new ArrayList<List<Integer>>(set);
	}

	public void run(){
		System.out.println(findSubsequences(new int[]{4, 6, 7, 7}));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
