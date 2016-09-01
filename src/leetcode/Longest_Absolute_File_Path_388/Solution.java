package leetcode.Longest_Absolute_File_Path_388;

import java.util.HashMap;

/**
 * Created by yanghui on 16/9/1.
 */
public class Solution {

	private int dfs(String input , int start , HashMap<Integer,Integer> map){
		//System.out.println(input + " " + start + " " +map);
		if(start >= input.length()) return 0;
		//System.out.println(input.substring(start));
		int result = -1;
		int idx = start;
		int cnt = 0;
		for(; idx < input.length() ; idx ++){
			if(input.charAt(idx) == '\t') {
				cnt++;
			}
			else
				break;
		}
		int len = 0;
		boolean flag = false;
		for( ; idx < input.length() ; idx ++){
			if(input.charAt(idx) == '\n'){
				break;
			}
			else if(input.charAt(idx) == '.'){
				len ++;
				flag = true;
			}else{
				len ++;
			}
		}

		//System.out.println(cnt);

		if(flag){
			result = Math.max(result , map.get(cnt - 1) +  len + 1);
			result = Math.max(dfs(input , idx + 1, map) , result);
		}else{
			map.put(cnt , map.get(cnt-1) + len + 1);
			result = Math.max(dfs(input , idx + 1, map) , result);
		}
		return result;

	}

	public int lengthLongestPath(String input) {
		int len = input.length();
		if(len == 0)
			return 0;
		HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
		map.put(-1 , -1);
		int result = dfs(input , 0 ,map);
		//System.out.println(result);
		return result;
	}

}
