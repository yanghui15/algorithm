package leetcode.Kth_Smallest_Element_in_a_Sorted_Matrix_378;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yanghui on 16/10/21.
 */
public class Solution {

	public int search3(int[][] matrix , int x1 , int y1 , int x2 , int y2 , long val){
		if(x1 > x2 || y1 > y2) return 0;
		int xstart = x1 , xend = x2 , ystart = y1 , yend = y2;
		int xresult = -1 , yresult = -1;
		while(xstart <= xend || ystart <= yend){
			int xmid = (xstart + xend) / 2;
			int ymid = (ystart + yend) / 2;
			if(matrix[xmid][ymid] <= val){
				xresult = xmid;
				yresult = ymid;
				xstart = xmid + 1;
				ystart = ymid + 1;
			}else{
				xend = xmid - 1;
				yend = ymid - 1;
			}
		}
		if(xresult < 0 || yresult < 0) return 0;
		return (xresult - x1 + 1) * (yresult - y1 + 1) +
				search3(matrix , x1 , yresult + 1 , xresult , y2 , val) +
				search3(matrix , xresult + 1 , y1 , x2 , yresult , val);
	}

	public int search(int[][] matrix , int x1 , int y1 , int x2 , int y2 , long val){
		if(x1 > x2 || y1 > y2) return 0;
		int idx = (x1 + x2) / 2;
		int idy = (y1 + y2) / 2;
		int num = (idx - x1 + 1) * (idy - y1 + 1);
		int result = 0;
		if(matrix[idx][idy] <= val){
			result += num;
			if(idx + 1 <= x2 && y1 <= idy) result += search(matrix , idx + 1 , y1 , x2 , idy , val);
			if(idy + 1 <= y2) result += search(matrix , x1 , idy + 1 , x2 , y2 , val);
		}else{
			if(x1 <= idx - 1) result += search(matrix , x1 , y1 , idx - 1 , y2 , val);
			if(y1 <= idy - 1) result += search(matrix , idx , y1 , x2 , idy - 1 , val);
		}
		return result;
	}

	public int search2(int[][] matrix , int x1 , int y1 , int x2 , int y2 , long val){
		int j = y2;
		int cnt = 0;
		for(int i = x1 ; i <= x2 ; i ++){
			while(j >= y1 && matrix[i][j] > val){
				j --;
			}
			cnt += j + 1;
		}
		return cnt;
	}

	public int kthSmallest(int[][] matrix, int k) {
		int n = matrix.length;
		if(n == 0) return 0;
		long left = matrix[0][0];
		long right = matrix[n-1][n-1];
		long result = 0;
		while(left <= right){
			long mid = (left + right) / 2;
//			int cura = search(matrix , 0 , 0 , n - 1 , n - 1 , mid);
			int cur = search3(matrix , 0 , 0 , n - 1 , n - 1 , mid);
//			System.out.println(mid + " "+cur);
			if(cur < k){
				left = mid + 1;
			}else{
				result = mid;
				right = mid - 1;
			}
		}
		return (int)result;
	}

	public void run(){
		int n = 20000;
		int num[][] = new int[n][n];
		int idx = 1;
		for(int i = 0 ; i < n ; i ++){
			for(int j = 0 ; j < n ; j ++){
				num[i][j] = idx ++;
			}
		}

long start = System.currentTimeMillis();
		int ans = kthSmallest(num , 123);
long end = System.currentTimeMillis();
		System.out.println(ans + " " +(end - start));

	}

	public static void main(String args[]){
		new Solution().run();
	}

}
