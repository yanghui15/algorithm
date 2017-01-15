package leetcode.Magical_String_481;

/**
 * Created by yanghui on 17/1/15.
 */
public class Solution {

	public int magicalString(int n) {
		int[] nums = new int[100000+10];
		nums[0] = 1;
		nums[1] = 2;
		nums[2] = 2;
		int idx = 3;
		int cur = 0;
		out :
		for(int i = 2 ; i <= n ; i ++){
			for(int j = 0 ; j < nums[i] ; j ++){
				if(idx > n) break out;
				nums[idx ++] = cur + 1;
			}
			cur = (cur + 1) % 2;
		}
		int ans = 0;
		for(int i = 0 ; i < n ; i ++){
			if(nums[i] == 1) ans ++;
		}
		return ans;
	}

	public void run(){
		System.out.println(magicalString(6));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
