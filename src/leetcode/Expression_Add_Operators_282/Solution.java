package leetcode.Expression_Add_Operators_282;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghui on 16/9/3.
 */
public class Solution {

	public void dfs(String num , String str , int idx , long sum , long target , long multi , List<String> result){
		if(sum == target && idx == num.length()){
			result.add(str);
			return;
		}
		for(int i = idx; i < num.length() ; i ++){
			if(i != idx && num.charAt(idx) == '0') return;
			long cur = Long.valueOf(num.substring(idx , i+1));
			if(idx == 0){
				dfs(num ,  str + cur , i + 1 , sum + cur , target , cur , result);
			}else{
				dfs(num , str + "+" + cur , i + 1 , sum + cur , target , cur , result);
				dfs(num , str + "-" + cur , i + 1 , sum - cur , target , -cur , result);
				dfs(num , str + "*" + cur , i + 1 , sum - multi + multi * cur , target , multi * cur , result);
			}

		}
	}

	public List<String> addOperators(String num, int target) {
		List<String> result = new ArrayList<String>();
		if(num == null) return result;
		dfs(num , "" , 0 , 0 , target , 0  , result);
		return result;
	}

	public void run(){
		List<String> result = addOperators("" , 0);
		for(String cur : result){
			System.out.println(cur);
		}
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
