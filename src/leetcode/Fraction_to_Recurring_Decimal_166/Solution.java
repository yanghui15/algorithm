package leetcode.Fraction_to_Recurring_Decimal_166;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yanghui on 16/10/19.
 */
public class Solution {

	public String solve(long numerator, long denominator) {
		if(numerator >= denominator && numerator % denominator == 0){
			return numerator / denominator + "";
		}
		long ans1 = numerator / denominator;
		numerator = numerator % denominator;
		ArrayList<Long> ans = new ArrayList<Long>();
		HashMap<Long,Integer> mod = new HashMap<Long,Integer>();
		long cur = numerator;
		int start = -1;
		int idx = 0;
		mod.put(cur , idx ++);
		while(true){
			cur = cur * 10;
			if(cur < denominator){
				mod.put(cur , idx ++);
				ans.add(0l);
				continue;
			}
			long temp = cur / denominator;
			cur = cur % denominator;
			ans.add(temp);
			if(cur == 0){
				break;
			}else{
				if(mod.containsKey(cur)){
					start = mod.get(cur);
					break;
				}else{
					mod.put(cur , idx ++);

				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(ans1);
		sb.append('.');
		if(start == -1){
			for(Long i : ans){
				sb.append(i);
			}
		}else{
			for(int i = 0 ; i < start ; i ++){
				sb.append(ans.get(i));
			}
			sb.append('(');
			for(int i = start ; i < ans.size() ; i ++){
				sb.append(ans.get(i));
			}
			sb.append(')');
		}
		return sb.toString();
	}

	public String fractionToDecimal(int numerator, int denominator) {
		if((numerator < 0 && denominator < 0) || (numerator > 0 && denominator > 0)){
			return solve(Math.abs((long)numerator) , Math.abs((long)denominator));
		}else if(numerator == 0){
			return "0";
		}else if(numerator < 0 || denominator < 0){
			return "-"+solve(Math.abs((long)numerator) , Math.abs((long)denominator));
		}else{
			return "false";
		}
	}

	public void run(){
		System.out.println(fractionToDecimal(1 , 6));
		System.out.println(Math.abs((long)-2147483648));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
