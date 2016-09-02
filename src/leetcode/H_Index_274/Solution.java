package leetcode.H_Index_274;

import java.util.Arrays;

/**
 * Created by yanghui on 16/9/3.
 */
public class Solution {
	public int hIndex(int[] citations) {
		int len = citations.length;
		if(len == 0) return 0;

		Arrays.sort(citations);
		int last = 0;
		int idx = -1;
		for(int i = 0 ; i < citations.length ; i ++){
			int last_size = citations.length - i;
			if(last_size < citations[i]){
				last = last_size;
				idx = i;
				break;
			}else if(last_size == citations[i]){
				return last_size;
			}
		}
		return last;
	}
}
