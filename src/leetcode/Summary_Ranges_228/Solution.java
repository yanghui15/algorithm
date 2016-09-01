package leetcode.Summary_Ranges_228;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghui on 16/9/1.
 */
public class Solution {
	public List<String> summaryRanges(int[] nums) {
		List<String> list = new ArrayList<String>();
		int len = nums.length;
		if(len == 0)
			return list;
		int start = nums[0];
		int cnt = 1;
		for(int i = 1; i < len ; i ++){
			if(nums[i] - start == cnt){
				cnt ++;
				continue;
			}else{
				if(cnt > 1)
					list.add(new String(start+"->"+nums[i-1]));
				else
					list.add(new String(start+""));
				cnt = 1;
				start = nums[i];
			}
		}
		if(cnt > 1)
			list.add(new String(start+"->"+nums[len-1]));
		else
			list.add(new String(start+""));
		return list;
	}
}
