package leetcode.Contest_2017_0212;

import java.util.*;

/**
 * Created by yanghui on 2017/2/12.
 */
public class Solution {

	public int lowbit(int x){
		return x & -x;
	}

	public void add(int[] f , int x , int N){
		while(x <= N){
			f[x] ++;
			x += lowbit(x);
		}
	}

	public int sum(int[] f , int x){
		int ans = 0;
		while(x > 0){
			ans += f[x];
			x -= lowbit(x);
		}
		return ans;
	}

	public int reversePairs(int[] nums) {
		int f[] = new int[nums.length * 2 + 10];
		TreeMap<Long, Integer> map = new TreeMap<>();
		for(int num : nums){
			map.put((long)num , 0);
			map.put((long)num * 2 , 0);
		}
		int idx = 1;
		for(Long cur : map.keySet()){
			map.put(cur, idx ++);
		}
		int ans = 0;
		for(int i = nums.length - 1 ; i >= 0 ; i --){
			ans += sum(f , map.get((long)nums[i]) - 1);
			add(f , map.get(2 * (long)nums[i]) , idx);
		}
		return ans;
	}

	class TreeNode{
		TreeNode left , right;
		int val;
		public TreeNode(int val){
			this.val = val;
		}
	}

	public int[] findValueMostElement(TreeNode root) {
		ArrayList<Integer> ans = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int count = 0;
		int max = Integer.MIN_VALUE;
		int new_list = 1;
		while(!queue.isEmpty()){
			TreeNode top = queue.poll();
			count ++;
			max = Math.max(max , top.val);
			if(count == new_list){
				ans.add(max);
				max = Integer.MIN_VALUE;
			}
			if(top.left != null) queue.add(top.left);
			if(top.right != null) queue.add(top.right);
			if(count == new_list){
				count = 0;
				new_list = queue.size();
			}
		}
		int result[] = new int[ans.size()];
		for(int i = 0 ; i < result.length ; i ++){
			result[i] = ans.get(i);
		}
		return result;
	}

	public String convertTo7(int num) {
		return Integer.toString(num , 7);
	}

	public void run(){
		System.out.println(convertTo7(-7));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
