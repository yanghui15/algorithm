package leetcode.H_Index_II_275;

/**
 * Created by yanghui on 16/9/3.
 */
public class Solution {
	public int hIndex(int[] citations) {

		int len = citations.length;
		if(len == 0) return 0;

		int left = 0;
		int right = len - 1;
		while(left < right){
			int mid = (left + right) / 2;
			if(citations[mid] == (len - mid)){
				return len - mid;
			}else if(citations[mid] > (len - mid)){
				right = mid - 1;
			}else{
				left = mid + 1;
			}
		}
//		System.out.println(citations[left] + " "+left +" "+(len - left));
		if(citations[left] == (len - left)){
			return len - left;
		}else if(citations[left] > len - left){
			return len - left;
		}else{
			return len - left - 1;
		}
	}
}
