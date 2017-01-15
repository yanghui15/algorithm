package leetcode.Sliding_Window_Median_480;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by yanghui on 17/1/15.
 */
public class Solution {

	int min_count = 0;
	int max_count = 0;

	public void add(TreeMap<Integer,Integer> map , int number){
		if(map.containsKey(number)) map.put(number , map.get(number) + 1);
		else map.put(number , 1);
	}

	public void delete(TreeMap<Integer,Integer> map , int number){
		map.put(number , map.get(number) - 1);
		if(map.get(number) == 0) map.remove(number);
	}

	public void delete(TreeMap<Integer,Integer> min , TreeMap<Integer,Integer> max , int number){
		if(min_count == max_count){
			if(max.containsKey(number)){
				delete(max , number);
			}else{
				delete(min , number);
				int mn = max.firstKey();
				delete(max , mn);
				add(min , mn);
			}
			max_count --;
		}else{
			if(min.containsKey(number)){
				delete(min , number);
			}else{
				delete(max , number);
				int mx = min.lastKey();
				delete(min , mx);
				add(max , mx);
			}
			min_count --;
		}
	}

	public void add(TreeMap<Integer,Integer> min , TreeMap<Integer,Integer> max , int number){
		if(min_count == 0){
			add(min , number);
			min_count ++;
			return;
		}
		if(min_count == max_count){
			// insert to min;
			if(number <= max.firstKey()){
				add(min , number);
			}else{
				add(max , number);
				int mn = max.firstKey();
				delete(max , mn);
				add(min , mn);
			}
			min_count ++;
		}else{
			// insert to max;
			if(number >= min.lastKey()){
				add(max , number);
			}else{
				add(min , number);
				int mx = min.lastKey();
				delete(min , mx);
				add(max , mx);
			}
			max_count ++;
		}
	}

	public double get_median(TreeMap<Integer,Integer> min , TreeMap<Integer,Integer> max){
		if(min_count == max_count){
			return ((double) min.lastKey() + (double) max.firstKey()) / 2.0d;
		}else{
			return (double) min.lastKey();
		}
	}

	public double[] medianSlidingWindow(int[] nums, int k) {
		min_count = 0;
		max_count = 0;
		int n = nums.length;
		double[] ans = new double[n - k + 1];
		TreeMap<Integer,Integer> min = new TreeMap<>();
		TreeMap<Integer,Integer> max = new TreeMap<>();
		for(int i = 0 ; i < k ; i ++){
			add(min , max , nums[i]);
		}
		ans[0] = get_median(min , max);
		for(int i = k ; i < n ; i ++){
			delete(min , max , nums[i - k]);
			add(min , max , nums[i]);
			ans[i - k + 1] = get_median(min , max);
		}
		return ans;
	}

	public void run(){
		System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7} , 3)));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
