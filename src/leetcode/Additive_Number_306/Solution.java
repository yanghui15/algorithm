package leetcode.Additive_Number_306;

import java.math.BigInteger;

/**
 * Created by yanghui on 16/9/4.
 */
public class Solution {

	public boolean dfs(String num , int idx , String cur , String last , int cnt){
//		System.out.println(num.substring(idx)+" "+cur +" "+last);
		if(idx == num.length()){
			if(cnt == 0) return false;
			else return true;
		}
		if(num.length() - idx < cur.length())
			return false;
		int i = idx;
		for( ; i < num.length() ; i ++){
			if(i - idx >= cur.length())
				break;
			if(num.charAt(i) != cur.charAt(i - idx)){
				return false;
			}
		}
		BigInteger temp = new BigInteger(cur).add(new BigInteger(last));
		return dfs(num , i , temp.toString() , cur , cnt + 1);
	}

	public boolean isAdditiveNumber(String num) {
		int len = num.length();
		if(len < 3)
			return false;
		for(int i = 1 ; i < len ; i ++){
			for(int j = i + 1 ; j < len ; j ++){
				String a = num.substring(0 , i);
				String b = num.substring(i , j);
				if(a.startsWith("0") && a.length() > 1)
					continue;
				if(b.startsWith("0") && b.length() > 1)
					continue;
//				System.out.println(a+" "+b);
				BigInteger temp = (new BigInteger(a)).add(new BigInteger(b));
				boolean flag = dfs(num , j , temp.toString() , b , 0);
				if(flag)
					return true;
			}
		}
		return false;
	}
}
