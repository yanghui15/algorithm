package leetcode.First_Bad_Version_278;

/**
 * Created by yanghui on 16/9/3.
 */
public class Solution extends VersionControl {
	public int firstBadVersion(int n) {
		long left = 1 ;
		long right = n;
		while(left < right){
			long mid = (left + right) / 2L;
			if(isBadVersion((int)mid)){
				right = mid;
			}else{
				left = mid + 1;
			}
		}
		return (int)left;
	}
}
