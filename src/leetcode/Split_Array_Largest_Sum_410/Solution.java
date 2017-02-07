package leetcode.Split_Array_Largest_Sum_410;

/**
 * Created by yanghui on 2017/2/7.
 */
public class Solution {

	public int splitArray(int[] nums, int m) {
		int n = nums.length;
		int max = nums[0];
		long sum = 0;
		for(int i = 0 ; i < n ; i ++){
			max = Math.max(max , nums[i]);
			sum += nums[i];
		}
		long left = max;
		long right = sum;
		long result = 0;
		while(left <= right){
			long mid = (left + right) / 2;
			int cnt = 0;
			long tmp = 0;
			for(int i = 0 ; i < n ; i ++){
				if(tmp + nums[i] <= mid){
					tmp += nums[i];
				}else{
					cnt ++;
					tmp = nums[i];
				}
			}
			if(tmp > 0) cnt ++;
			if(cnt > m){
				left = mid + 1;
			}else{
				result = mid;
				right = mid - 1;
			}
		}
		return (int)result;
	}

	public void run(){
		System.out.println(splitArray(new int[]{7,2,5,10,8} , 2));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
