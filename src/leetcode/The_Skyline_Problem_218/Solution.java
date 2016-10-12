package leetcode.The_Skyline_Problem_218;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yanghui on 16/9/8.
 */
public class Solution {

	public class Node{
		int left , right;
		int max;
		int flag = -1;
		public Node(int left , int right){
			this.left = left;
			this.right = right;
		}
		public int mid(){
			return (left + right) / 2;
		}
	}

	Node tree[];

	public void build(int left , int right , int idx){
		tree[idx] = new Node(left , right);
		if(left == right){
			tree[idx].max = 0;
			return;
		}
		int mid = tree[idx].mid();
		build(left , mid , idx << 1);
		build(mid + 1 , right , idx << 1 | 1);
	}

	public void pushup(int idx){
		tree[idx].max = Math.max(tree[idx << 1].max , tree[idx << 1 | 1].max);
	}

	public void pushdown(int idx){
		if(tree[idx].flag > 0){
			tree[idx << 1].max = Math.max(tree[idx << 1].max , tree[idx].flag);
			tree[idx << 1].flag = Math.max(tree[idx << 1].flag , tree[idx].flag);

			tree[idx << 1 | 1].max = Math.max(tree[idx << 1 | 1].max , tree[idx].flag);
			tree[idx << 1 | 1].flag = Math.max(tree[idx << 1 | 1].flag , tree[idx].flag);

			tree[idx].flag = -1;
		}
	}

	public void update(int left , int right , int idx , int val){
		if(left == tree[idx].left && right == tree[idx].right){
			tree[idx].max = Math.max(tree[idx].max , val);
			tree[idx].flag = Math.max(tree[idx].flag , val);
			return;
		}
		pushdown(idx);
		int mid = tree[idx].mid();
		if(right <= mid){
			update(left , right , idx << 1 , val);
		}else if(left > mid){
			update(left , right , idx << 1 | 1 , val);
		}else{
			update(left , mid , idx << 1 , val);
			update(mid + 1 , right , idx << 1 | 1 , val);
		}
		pushup(idx);
	}

	public int query(int cur , int idx){
		if(tree[idx].left == tree[idx].right){
			return tree[idx].max;
		}
		pushdown(idx);
		int mid = tree[idx].mid();
		if(cur <= mid){
			return query(cur , idx << 1);
		}else{
			return query(cur , idx << 1 | 1);
		}
	}

	public class line implements Comparable<line>{
		int x , y;
		boolean isleft;
		public line(int x , int y , boolean isleft){
			this.x = x;
			this.y = y;
			this.isleft = isleft;
		}

		@Override
		public int compareTo(line l) {
			if(this.x == l.x){
				return l.y - this.y;
			}else{
				return this.x - l.x;
			}
		}
	}


	public List<int[]> getSkyline(int[][] buildings) {
		int len = buildings.length;
		List<int[]> list = new ArrayList<int[]>();
		if(len == 0)
			return list;
		line lines[] = new line[len * 2];
		for(int i = 0 ; i < len ; i ++){
			lines[i] = new line(buildings[i][0] , buildings[i][2] , true);
			lines[i + len] = new line(buildings[i][1] , buildings[i][2] , false);
		}
		Arrays.sort(lines);
		return null;
	}

}
