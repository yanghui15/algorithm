package leetcode.The_Skyline_Problem_218;

import java.util.*;

/**
 * Created by yanghui on 16/9/8.
 */
public class Solution {

	class SegNode{
		int left , right;
		int max;
		int flag;
		public SegNode(int left , int right){
			this.left = left;
			this.right = right;
			this.max = 0;
			this.flag = -1;
		}
		public int mid(){
			return (left + right) / 2;
		}
	}

	SegNode tree[];

	public void build(int left , int right , int idx){
		tree[idx] = new SegNode(left , right);
		if(left == right){
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

	public void update(int left , int right, int idx , int value){
		if(tree[idx].left == left && tree[idx].right == right){
			tree[idx].max = Math.max(tree[idx].max , value);
			tree[idx].flag = Math.max(tree[idx].flag , value);
			return;
		}
		pushdown(idx);
		int mid = tree[idx].mid();
		if(right <= mid)
			update(left , right , idx << 1 , value);
		else if(left > mid)
			update(left , right , idx << 1 | 1 , value);
		else{
			update(left , mid , idx << 1 , value);
			update(mid + 1 , right , idx << 1 | 1 , value);
		}
		pushup(idx);
	}

	public int query(int left , int idx){
		if(tree[idx].left == tree[idx].right){
			return tree[idx].max;
		}
		pushdown(idx);
		int mid = tree[idx].mid();
		if(left <= mid)
			return query(left , idx << 1);
		else
			return query(left , idx << 1 | 1);
	}

	public List<int[]> getSkyline(int[][] buildings) {
		int len = buildings.length;
		List<int[]> list = new ArrayList<int[]>();
		if(len == 0)
			return list;
		TreeSet<Integer> set = new TreeSet<Integer>();
		for(int i = 0 ; i < len ; i ++){
			set.add(buildings[i][0]);
			set.add(buildings[i][1]);
		}
		int idx = 0;
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(Integer cur : set){
			idx ++;
			map.put(cur , idx ++);
		}
		int re[] = new int[idx];
		for(Integer cur : map.keySet()){
			re[map.get(cur)] = cur;
		}
		tree = new SegNode[idx * 4];
		build(1 , idx , 1);
		for(int i = 0 ; i < len ; i ++){
			update(map.get(buildings[i][0]) , map.get(buildings[i][1]) , 1 , buildings[i][2]);
		}
		int[] height = new int[idx + 1];
		for(int i = 1 ; i < idx ; i ++){
			height[i] = query(i , 1);
		}
		int last = 0;
		for(int i = 0 ; i <= idx ; i ++){
			if(height[i] > last){
				list.add(new int[]{re[i] , height[i]});
				last = height[i];
			}else if(height[i] < last){
				list.add(new int[]{re[i - 1] , height[i]});
				last = height[i];
			}
		}
		return list;
	}

	public void run(){
		int[][] buildings = new int[][]{ {2 ,9 ,10}, {3 ,7 ,15}, {5 ,12, 12}, {15, 20 ,10}, {19, 24 ,8} };
		List<int[]> list = getSkyline(buildings);
		for(int[] cur : list){
			System.out.println(cur[0] + " " + cur[1]);
		}
	}

	public static void main(String arg[]){
		new Solution().run();
	}

}
