package leetcode.Smallest_Good_Base_483;

/**
 * Created by yanghui on 2017/1/26.
 */
public class Solution {

	public String smallestGoodBase(String n) {
		long number = Long.valueOf(n);
		int max = (int)(Math.log(number) / Math.log(2)) + 1;
		for(int i = max ; i >= 3 ; i --){

		}
	}

	public void run(){

	}

	public static void main(String args[]){
		new Solution().run();
	}
}
