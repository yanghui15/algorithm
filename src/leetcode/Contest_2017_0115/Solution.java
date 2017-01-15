package leetcode.Contest_2017_0115;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by yanghui on 17/1/15.
 */
public class Solution {

	public int cal(int[] sum , int left , int right){
		if(left == 0) return sum[right];
		return sum[right] - sum[left - 1];
	}

	public int findMaxConsecutiveOnes(int[] nums) {
		int n = nums.length;
		if(n == 0) return 0;
		int sum[] = new int[n];
		sum[0] = nums[0];
		for(int i = 1 ; i < n ; i ++){
			sum[i] = sum[i-1] + nums[i];
		}
		int ans = 0;
		for(int i = 0 ; i < n ; i ++){
			int left = i;
			int right = n-1;
			int result = 0;
			while(left <= right){
				int mid = (left + right) / 2;
				if(cal(sum , i , mid) == (mid - i + 1)){
					result = mid;
					left = mid + 1;
				}else{
					right = mid - 1;
				}
			}
			ans = Math.max(ans , result - i + 1);
		}
		return ans;
	}

	public String trans(String cur){
		StringBuilder sb = new StringBuilder();
		if(cur.length() < 3) return cur;
		for(int i = 0 ; i < cur.length(); ){
			if(i == 0 || sb.length() == 0) {
				sb.append(cur.charAt(i));
				i ++;
			}
			else{
				int cnt = 1;
				int j = sb.length() - 2;
				while(j >= 0 && sb.charAt(j) == sb.charAt(sb.length() - 1)){
					j --;
					cnt ++;
				}
				j = i;
				for(; j < cur.length() ; j ++){
					if(sb.charAt(sb.length() - 1) == cur.charAt(j)){
						cnt ++;
					}else
						break;
				}
				if(cnt >= 3){
					i = j;
					char tmp = sb.charAt(sb.length() - 1);
					while(sb.length() > 0 && sb.charAt(sb.length() - 1) == tmp){
						sb.deleteCharAt(sb.length() - 1);
					}
				}else{
					sb.append(cur.charAt(i));
					i ++;
				}
			}
		}
//		System.out.println(cur + " " + sb.toString());
		return sb.toString();
	}

	public int dfs(String board , int[] cnt , HashMap<String,HashMap<int[],Integer>> map){
		if(board.length() == 0) return 0;
		if(map.containsKey(board) && map.get(board).containsKey(cnt)){
			return map.get(board).get(cnt);
		}
//		System.out.println(board);
//		System.out.println(Arrays.toString(cnt));
		int ans = -1;
		for(int i = 0 ; i < 26 ; i ++){
			if(cnt[i] > 0){
				for(int j = 0 ; j < board.length() ;){
					if(board.charAt(j) != (char)('A' + i)) {
						j ++;
						continue;
					}
					else{
						int tmp = 0;
						while(j < board.length() && board.charAt(j) == (char)('A' + i)){
							tmp ++;
							j ++;
						}
						if(tmp + cnt[i] >= 3){
							StringBuilder sb = new StringBuilder(board);
							int result = -1;
							if(tmp == 2){
								sb.insert(j , (char)('A' + i));
								cnt[i] --;
								String nxt = trans(sb.toString());
								result = dfs(nxt , cnt , map);
								cnt[i] ++;
								if(result != -1) result += 1;
							}else if(tmp == 1){
								sb.insert(j , (char)('A' + i));
								sb.insert(j , (char)('A' + i));
								cnt[i] -= 2;
								String nxt = trans(sb.toString());
								result = dfs(nxt , cnt , map);
								cnt[i] += 2;
								if(result != -1) result += 2;
							}
							if(result == -1) continue;
							else if(ans == -1){
								ans = result;
							}else{
								ans = Math.min(ans , result);
							}
						}
					}
				}
			}
		}
		HashMap<int[] , Integer> tmp = map.get(board);
		if(tmp == null) tmp = new HashMap<>();
		tmp.put(cnt , ans);
		map.put(board , tmp);
		return ans;
	}

	public int findMinStep(String board, String hand) {
		if(board == null || hand == null || hand.length() == 0) return -1;
		int[] cnt = new int[26];
		for(int i = 0 ; i < hand.length() ; i ++){
			cnt[hand.charAt(i) - 'A'] ++;
		}
		HashMap<String , HashMap<int[] , Integer>> map = new HashMap<>();
		int ans = dfs(board , cnt , map);
		return ans;
	}

	public void run(){
		long start = System.currentTimeMillis();
		System.out.println(findMinStep("BWWGWBYRRGWYYRB","WYYY"));
		System.out.println(System.currentTimeMillis() - start);
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
