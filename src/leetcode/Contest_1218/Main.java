package leetcode.Contest_1218;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by yanghui on 17/1/8.
 */
public class Main {

	public boolean dfs(int[] nums , boolean[] visit , int cur , int cnt , int tot){
		if(cnt == 3) return true;
		if(cur == tot) return dfs(nums , visit , 0 , cnt + 1 , tot);
		for(int i = 0 ; i < nums.length ; i ++){
			if(visit[i]) continue;
			if(cur + nums[i] > tot) return false;
			visit[i] = true;
			boolean tmp = dfs(nums, visit, cur + nums[i] , cnt , tot);
			if (tmp) return true;
			else visit[i] = false;
		}
		return false;
	}

	public void swap(int[] nums , int i , int j){
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public void reverse(int[] nums , int left , int right){
		while(left <= right){
			swap(nums , left , right);
			left ++;
			right --;
		}
	}

	public boolean makesquare(int[] nums) {
		Arrays.sort(nums);
		reverse(nums , 0 , nums.length - 1);
		int sum = 0;
		for(int i = 0 ; i < nums.length ; i ++){
			sum += nums[i];
		}
		if(sum % 4 != 0 || sum == 0) return false;
		boolean[] visit = new boolean[nums.length];
		return dfs(nums , visit , 0 , 0 , sum / 4);
	}

	public void run(){
		System.out.println(makesquare(new int[]{5,5,5,5,16,4,4,4,4,4,3,3,3,3,4}));
	}

	public static void main(String args[]){
		new Main().run();
	}
}
