package leetcode.Contest_2017_0121;

import java.util.*;

/**
 * Created by yanghui on 17/1/21.
 */
public class Solution {

	public int[] findPermutation(String s) {
		int n = s.length();
		int[] ans = new int[n + 1];
		int idx = 1;
		ans[0] = 1;
		for(int i = 0 ; i < n ; i ++){
			ans[idx] = ans[idx - 1];
			idx ++;

		}
		return ans;
	}

	public HashMap<Integer,Integer> cal2(int[] nums , int start , int end){
		HashMap<Integer , Integer> map = new HashMap<>();
		int len = end - start;
		int max = (int) Math.pow(2 , len);
		for(int i = 0 ; i < max ; i ++){
			int stat = i;
			int cur = 0;
			for(int j = 0 ; j < len ; j ++){
				if((stat & 1) == 1){
					cur += nums[j + start];
				}else{
					cur += -nums[j + start];
				}
				stat = stat >> 1;
			}
			if(map.containsKey(cur)){
				map.put(cur , map.get(cur) + 1);
			}else{
				map.put(cur , 1);
			}
		}
		return map;
	}

	public int findTargetSumWays(int[] nums, int S) {
		int n = nums.length;
		int len = n / 2;
		HashMap<Integer,Integer> map1 = cal2(nums , 0 , len);
		HashMap<Integer,Integer> map2 = cal2(nums , len , n);
		int ans = 0;
		for(Integer cur : map1.keySet()){
			if(map2.containsKey(S - cur)){
				ans += map1.get(cur) * map2.get(S - cur);
			}
		}
		return ans;
	}

	public List<List<Integer>> findSubsequences(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		if(nums.length < 2) return ans;
		int n = nums.length;
		int max = (int) Math.pow(2 , n);

		HashSet<Integer> set = new HashSet<>();
		TreeMap<Integer,Integer> map = new TreeMap<>();
		for(int i = 0 ; i < n ; i ++){
			map.put(nums[i] , 0);
		}
		int idx = 0;
		for(Integer cur : map.keySet()){
			map.put(cur , idx ++);
		}

		for(int i = 0 ; i < max ; i ++){
			int stat = i;
			int ss = 0;
			List<Integer> tmp = new ArrayList<>();
			for(int j = 0 ; j < n ; j ++){
				if((stat & 1) == 1){
					tmp.add(nums[j]);
					ss += (int)Math.pow(2 , map.get(nums[j]));
				}
				stat = stat >> 1;
			}
			if(tmp.size() < 2 || set.contains(ss)) continue;
			boolean flag = true;
			for(int j = 1 ; j < tmp.size() ; j ++){
				if(tmp.get(j) < tmp.get(j-1)){
					flag = false;
					break;
				}
			}
			if(flag){
				ans.add(tmp);
				set.add(ss);
			}
		}
		return ans;
	}

	public void run(){
		System.out.println(Arrays.toString(findPermutation("DDIIDI")));
//		System.out.println(findTargetSumWays(new int[]{16,40,9,17,49,32,30,10,38,36,31,22,3,36,32,2,26,17,30,47} , 49));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
