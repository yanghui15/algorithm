package leetcode.Contest_0911;

import java.util.*;

/**
 * Created by yanghui on 16/9/11.
 */
public class Solution {

	public Solution(){

	}

	HashMap<Integer,ArrayList<Integer>> map;

	public Solution(int[] nums) {
		map = new HashMap<Integer,ArrayList<Integer>>();
		for(int i = 0 ; i < nums.length ; i ++){
			if(map.containsKey(nums[i])){
				map.get(nums[i]).add(i);
			}else{
				ArrayList<Integer> set = new ArrayList<Integer>();
				set.add(i);
				map.put(nums[i],set);
			}
		}
	}

	public int pick(int target) {
		ArrayList<Integer> temp = map.get(target);
		int len = temp.size();
		if(len == 1)
			return temp.get(0);
//
		Random r = new Random();
		return temp.get(temp.get(r.nextInt(len)));
	}

	public class Node{
		long val;
		int cnt;
		public Node(long val , int cnt){
			this.val = val;
			this.cnt = cnt;
		}
	}

	public int solve(long n) {

		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(n,0));
		int cnt = 0;
		HashSet<Long> set = new HashSet<Long>();
		set.add(n);
		while(!q.isEmpty()){
			Node top = q.poll();
			if(top.val == 1) {
				System.out.println(set.size());
				System.out.println(set);
				return top.cnt;
			}
			if(!set.contains(top.val / 2) && top.val % 2 == 0){
				q.add(new Node(top.val / 2 , top.cnt + 1));
				set.add(top.val / 2);
			}
			if(!set.contains(top.val - 1) && top.val % 2 == 1){
				q.add(new Node(top.val - 1 , top.cnt + 1));
				set.add(top.val - 1);
			}
			if(!set.contains(top.val + 1) && top.val % 2 == 1){
				q.add(new Node(top.val + 1 , top.cnt + 1));
				set.add(top.val + 1);
			}
		}
		return cnt;
	}

	public int integerReplacement(long n){
		return solve(n);
	}

	public int cal(Queue<Integer> q){
		int sum =0;
		int idx = 0;
		for(Integer i : q){
			sum += idx * i;
			idx ++;
		}
		return sum;
	}


	public int maxRotateFunction(int[] A) {
		if(A.length == 0) return 0;
		int s = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 0 ; i < A.length ; i ++){
			s += A[i];
			q.add(A[i]);
		}
		int init = cal(q);
		int sum = Integer.MIN_VALUE;
		for(int i = 0 ; i < A.length ; i ++){
			sum = Math.max(sum , init);
			int temp = q.poll();
			init = init - s + temp * A.length;
		}
		return sum;
	}

	public void run(){
		Scanner scan = new Scanner(System.in);
		long n = scan.nextLong();
		System.out.println(integerReplacement(n));
//		int a[] = new int[n];
//		for(int i = 0 ; i < n ; i ++){
//			a[i] = scan.nextInt();
//		}
//		int ans = maxRotateFunction(a);
//		System.out.println(ans);
//		Solution b = new Solution(a);
//		System.out.println(b.pick(2));
	}
	public static void main(String args[]){
		new Solution().run();
	}
}
