package leetcode.Frog_Jump_403;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Created by yanghui on 16/9/19.
 */
public class Solution {

	private class Node{
		int last;
		int cur;
		public Node(int last , int cur){
			this.last = last;
			this.cur = cur;
		}
	}


	public boolean canCross(int[] stones) {
		int n = stones.length;
		if(n == 0) return false;
		TreeSet<Integer> set = new TreeSet<Integer>();
		HashSet<Integer> flag = new HashSet<Integer>();
		for(int i = 0 ; i < n ; i ++){
			set.add(stones[i]);
		}
		Queue<Node> q = new LinkedList<Node>();
		if(!set.contains(1)){
			return false;
		}
		Node node = new Node(1 , 1);
		flag.add(0);
		q.add(node);
		while(!q.isEmpty()){
			Node cur = q.poll();
			flag.add(cur.cur);
			if(cur.cur == set.last()){
				return true;
			}
			if(set.contains(cur.cur + cur.last + 1) && !flag.contains(cur.cur + cur.last + 1)){
				q.add(new Node(cur.last + 1 , cur.cur + cur.last + 1));
			}
			if(set.contains(cur.cur + cur.last) && !flag.contains(cur.cur + cur.last)){
				q.add(new Node(cur.last , cur.cur + cur.last));
			}
			if(set.contains(cur.cur + cur.last - 1) && !flag.contains(cur.cur + cur.last - 1)){
				q.add(new Node(cur.last - 1 , cur.cur + cur.last - 1));
			}
		}
		return false;
	}

}
