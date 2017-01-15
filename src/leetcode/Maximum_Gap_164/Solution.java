package leetcode.Maximum_Gap_164;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by yanghui on 16/12/17.
 */
public class Solution {

	public int maximumGap(int[] nums) {
		int len = nums.length;
		if(len < 2) return 0;
		long max = Integer.MIN_VALUE;
		long min = Integer.MAX_VALUE;
		for(int i = 0 ; i < len ; i ++){
			max = Math.max(max , nums[i]);
			min = Math.min(min , nums[i]);
		}
		long slot = (max - min) / (len - 1);
		if(slot == 0) return (int)min;
		int[] mn = new int[(int)((max - min) / slot + 1)];
		int[] mx = new int[(int)((max - min) / slot + 1)];
		Arrays.fill(mn , Integer.MAX_VALUE);
		Arrays.fill(mx , Integer.MIN_VALUE);
		for(int i = 0 ; i < len ; i ++){
			int tmp = (int)((nums[i] - min) / slot);
			mn[tmp] = Math.min(mn[tmp] , nums[i]);
			mx[tmp] = Math.max(mx[tmp] , nums[i]);
		}
		int ans = -1;
		int idx = 0;
		for(int i = 0 ; i < mx.length ; i ++){
			if(mx[i] == Integer.MIN_VALUE) continue;
			idx = Math.max(idx , i + 1);
			while(idx < mn.length && mn[idx] == Integer.MAX_VALUE){
				idx ++;
			}
			if(idx < mn.length)
				ans = Math.max(ans , mn[idx] - mx[i]);
			if(mn[i] != Integer.MAX_VALUE){
				ans = Math.max(ans , mx[i] - mn[i]);
			}
		}
		return ans;
	}

	public int test(int[] nums){
		if(nums.length < 2) return 0;
		Arrays.sort(nums);
		int ans = -1;
		for(int i = 1 ; i < nums.length ; i ++){
			ans = Math.max(ans , nums[i] - nums[i-1]);
		}
		return ans;
	}

	public void run() {
		int len = 1000000;
		int[] nums = new int[len];
		Random r = new Random();
		for(int i = 0 ; i < len ; i ++){
			nums[i] = r.nextInt();
		}
		int a = maximumGap(nums);
		int b = test(nums);
		System.out.println(a == b);
	}

	public static void main(String args[]){
		new Solution().run();
	}


}
