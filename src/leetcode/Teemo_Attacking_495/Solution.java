package leetcode.Teemo_Attacking_495;

import java.util.PriorityQueue;

/**
 * Created by yanghui on 2017/1/31.
 */
public class Solution {

	class Interval implements Comparable<Interval>{
		int left , right;
		public Interval(int left , int right){
			this.left = left;
			this.right = right;
		}

		@Override
		public int compareTo(Interval in) {
			if (in.left == this.left) {
				return Integer.compare(this.right , in.right);
			}else {
				return Integer.compare(this.left , in.left);
			}
		}
	}

	public int findPoisonedDuration(int[] timeSeries, int duration) {
		PriorityQueue<Interval> queue = new PriorityQueue<>();
		for(int i = 0 ; i < timeSeries.length ; i ++){
			queue.add(new Interval(timeSeries[i] , timeSeries[i] + duration - 1));
		}
		int ans = 0;
		while(!queue.isEmpty()){
			Interval a = queue.poll();
			Interval b = queue.poll();
			if(b == null){
				ans += a.right - a.left + 1;
				break;
			}
			if (b.left > a.right){
				ans += a.right - a.left + 1;
				queue.add(b);
			}else{
				a.right = b.right;
				queue.add(a);
			}
		}
		return ans;
	}

	public void run(){
		System.out.println(findPoisonedDuration(new int[]{1,2} , 2));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
