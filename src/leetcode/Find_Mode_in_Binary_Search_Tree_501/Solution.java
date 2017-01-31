package leetcode.Find_Mode_in_Binary_Search_Tree_501;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghui on 2017/1/31.
 */
public class Solution {

	class TreeNode{
		TreeNode left, right;
		int val;
	}

	public void visit(int[] mem , List<Integer> ans){
		if(mem[1] == mem[2]){
			ans.add(mem[0]);
		}else if(mem[1] > mem[2]){
			mem[2] = mem[1];
			ans.clear();
			ans.add(mem[0]);
		}
	}

	public void solve(TreeNode root , int[] mem , List<Integer> ans){
		if(root == null) return;
		solve(root.left , mem , ans);
		if(mem[2] == 0){
			mem[0] = root.val;
			mem[1] = 1;
			mem[2] = 1;
			ans.add(root.val);
			solve(root.right , mem , ans);
		}else{
			if(root.val == mem[0]){
				mem[1] ++;
			}else{
				mem[0] = root.val;
				mem[1] = 1;
			}
			visit(mem , ans);
			solve(root.right , mem , ans);
		}
	}

	public int[] findMode(TreeNode root) {
		List<Integer> ans = new ArrayList<Integer>();
		int[] mem = new int[3];// max , cnt , max_cnt
		solve(root , mem , ans);
		int[] result = new int[ans.size()];
		for(int i = 0 ; i < ans.size() ; i ++){
			result[i] = ans.get(i);
		}
		return result;
	}
}
