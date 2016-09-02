package leetcode.Missing_Number_268;

/**
 * Created by yanghui on 16/9/2.
 */
public class Solution {
	public int missingNumber(int[] nums) {
		int sum = 0;
		int len = nums.length;
		if(len == 0)
			return 0;
		for(int i = 0 ; i < len ; i ++){
			sum += nums[i];
		}
		long temp = (long)(len+1) * (long)(len) / 2L;
		return (int)(temp - sum);
	}

	public int missingNumber_Xor(int[] nums) { //xor
		int res = nums.length;
		for(int i=0; i<nums.length; i++){
			res ^= i;
			res ^= nums[i];
		}
		return res;
	}
}
