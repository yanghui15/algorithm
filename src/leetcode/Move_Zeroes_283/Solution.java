package leetcode.Move_Zeroes_283;

/**
 * Created by yanghui on 16/9/3.
 */
public class Solution {

	public void moveZeroes(int[] nums) {
		int len = nums.length;
		if(len == 0)
			return;
		int idx = 0;
		for(int i = 0 ; i < len ; i ++){
			if(nums[i] != 0){
				nums[idx ++] = nums[i];
			}
		}
		for(; idx < len ; idx ++){
			nums[idx] = 0;
		}
	}
}