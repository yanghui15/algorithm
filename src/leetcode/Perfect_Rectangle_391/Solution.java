package leetcode.Perfect_Rectangle_391;

import java.util.Arrays;

/**
 * Created by yanghui on 16/9/1.
 */
public class Solution {

	public class point implements Comparable<point>{
		int x ,y;
		public point(int x , int y){
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(point p) {
			if(this.x == p.x)
				return this.y - p.y;
			return this.x - p.x;
		}
	}

	public boolean isRectangleCover(int[][] rectangles) {
		int len = rectangles.length;
		point p[] = new point[len * 4];
		long sum = 0;
		int idx = 0;
		for(int i = 0 ; i < len ; i ++){
			sum += (long)(rectangles[i][2] - rectangles[i][0]) * (long)(rectangles[i][3] - rectangles[i][1]);
			p[idx ++] = new point(rectangles[i][0] , rectangles[i][1]);
			p[idx ++] = new point(rectangles[i][2] , rectangles[i][3]);
			p[idx ++] = new point(rectangles[i][0] , rectangles[i][3]);
			p[idx ++] = new point(rectangles[i][2] , rectangles[i][1]);
		}
		Arrays.sort(p);
		int left = p[0].x;
		int left_min = p[0].y;
		int left_max = -1;
		for(int i = 0 ; i < idx ; i ++){
			if(p[i].x != left){
				left_max = p[i-1].y;
				break;
			}
		}
		int right = p[idx - 1].x;
		int right_min = -1;
		int right_max = p[idx - 1].y;
		for(int i = idx - 1 ; i >= 0 ; i --){
			if(p[i].x != right){
				right_min =p[i+1].y;
				break;
			}
		}
		if(left_max - left_min != right_max - right_min)
			return false;
		long temp = (long)(right - left) * (long)(left_max - left_min);
		return temp == sum;
	}
}
