package leetcode.Binary_Tree_Level_Order_Traversal_II_107;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yanghui on 16/9/28.
 */
public class Solution {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
		if(root == null){
			return result;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		List<Integer> cur = new ArrayList<Integer>();
		int idx = 0;
		int size = 1;
		while(!q.isEmpty()){
			TreeNode top = q.poll();
			cur.add(top.val);
			idx ++;
			if(idx == size){
				List<Integer> temp = new ArrayList<Integer>();
				temp.addAll(cur);
				result.addFirst(temp);
				cur.clear();
				idx = 0;
				size = q.size();
			}
			if(top.left != null)
				q.add(top.left);
			if(top.right != null)
				q.add(top.right);
		}
		return result;
	}

}
