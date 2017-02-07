package leetcode.Contest_2017_0205;

import java.util.*;

/**
 * Created by yanghui on 2017/2/5.
 */
public class Solution {

	public int[] nextGreaterElements(int[] nums) {
		int n = nums.length;
		int ans[] = new int[n];
		TreeSet<Integer> set = new TreeSet<>();
		int[][] re = new int[n][2];
		for(int i = 0 ; i < n ; i ++){
			re[i] = new int[]{nums[i] , i};
		}
		Arrays.sort(re, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o2[0] , o1[0]);
			}
		});
		int start = 0;
		int end = 0;
		for(int i = 0 ; i < n ; i ++){
			Integer cur = set.higher(re[i][1]);
			ans[re[i][1]] = cur == null ? -1 : nums[cur];
			Integer nxt = set.isEmpty() ? null : set.first();
			if(cur == null){
				ans[re[i][1]] = nxt == null ? -1 : nums[nxt];
			}
			if(i + 1 < n && re[i][0] == re[i+1][0]){
				end = i + 1;
			}
			if(i + 1 < n && re[i][0] != re[i + 1][0]){
				for(int j = start ; j <= end ; j ++){
					set.add(re[j][1]);
				}
				start = i + 1;
				end = i + 1;
			}
		}
		return ans;
	}

	public String[] findRelativeRanks(int[] nums) {
		int n = nums.length;
		if(n == 1) return new String[]{"Gold Medal"};
		int[][] re = new int[n][2];
		for(int i = 0 ; i < n ; i ++){
			re[i][0] = nums[i];
			re[i][1] = i;
		}
		Arrays.sort(re, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o2[0] , o1[0]);
			}
		});
		String[] ans = new String[n];
		for(int i = 0 ; i < n ; i ++){
			if(i == 0){
				ans[re[i][1]] = "Gold Medal";
			}else if(i == 1){
				ans[re[i][1]] = "Silver Medal";
			}else if(i == 2){
				ans[re[i][1]] = "Bronze Medal";
			}else{
				ans[re[i][1]] = (i + 1)+"";
			}
		}
		return ans;
	}

	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		HashMap<Integer , Integer> map = new HashMap<>();
		for(int i = 0 ; i < nums.length ; i ++){
			map.put(nums[i] , i);
		}
		int[] ans = new int[findNums.length];
		for(int i = 0 ; i < findNums.length ; i ++){
			int idx = map.get(findNums[i]);
			ans[i] = -1;
			for(int j = idx + 1 ; j < nums.length ; j ++){
				if(nums[j] > findNums[i]){
					ans[i] = nums[j];
					break;
				}
			}
		}
		return  ans;
	}


	public boolean judge(int i , int j , int n , int m){
		return i >= 0 && i < n && j >= 0 && j < m;
	}

	public int[] findDiagonalOrder(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int[] ans = new int[n*m];
		int idx = 0;
		int i = 0;
		int j = 0;
		int cur = 1;
		while(idx < n * m){
			ans[idx ++] = matrix[i][j];
			if(cur == 1){
				if(judge(i - 1 , j + 1 , n , m)){
					i = i - 1;
					j = j + 1;
				}else if(judge(i , j + 1 , n , m)){
					j = j + 1;
					cur = -1;
				}else if(judge(i + 1 , j , n , m)){
					i = i + 1;
					cur = -1;
				}
			}else{
				if(judge(i + 1 , j - 1 , n , m)){
					i = i + 1;
					j = j - 1;
				}else if(judge(i + 1 , j , n , m)){
					i = i + 1;
					cur = 1;
				}else if(judge(i , j + 1 , n , m)){
					j = j + 1;
					cur = 1;
				}
			}
		}
		return ans;
	}

	public void run(){
		System.out.println(Arrays.toString(findDiagonalOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}})));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
