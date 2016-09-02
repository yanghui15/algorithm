package leetcode.Integer_to_English_Words_273;

/**
 * Created by yanghui on 16/9/2.
 */
public class Solution {

	public String trans(int number){
		if(number == 0) return "";
		String map[] = new String[100];
		map[1] = "One";map[2] = "Two";
		map[3] = "Three";map[4] = "Four";
		map[5] = "Five";map[6] = "Six";
		map[7] = "Seven";map[8] = "Eight";
		map[9] = "Nine";
		map[10] = "Ten";map[20] = "Twenty";
		map[30] = "Thirty";map[40] = "Forty";
		map[50] = "Fifty";map[60] = "Sixty";
		map[70] = "Seventy";map[80] = "Eighty";
		map[90] = "Ninety";
		map[11] = "Eleven";map[12] = "Twelve";
		map[13] = "Thirteen";map[14] = "Fourteen";
		map[15] = "Fifteen";map[16] = "Sixteen";
		map[17] = "Seventeen";map[18] = "Eighteen";
		map[19] = "Nineteen";
		if(number < 100){
			if(number <= 20)
				return map[number];
			else{
				if(number % 10 != 0)
					return map[number - number % 10] + " " + trans(number % 10);
				else
					return map[number - number % 10];
			}
		}else{
			if(number % 100 != 0)
				return map[number / 100] +" Hundred "+ trans(number % 100);
			else
				return map[number / 100] +" Hundred";
		}
	}


	public String dfs(int number  , int mod , int cnt){
		System.out.println(number + " " + mod + " "+cnt);
		if(number == 0) {
			return trans(mod);
		}
		String result = "";
		if(cnt == 1){
			result += "Thousand";
		}else if(cnt == 2){
			result += "Million";
		}else if(cnt == 3){
			result += "Billion";
		}
		if(mod != 0){
			if(number % 1000 != 0)
				return dfs(number / 1000 , number % 1000 , cnt + 1) + " " + result + " "+ trans(mod);
			else
				return dfs(number / 1000 , number % 1000 , cnt + 1) + " " + trans(mod);
		}
		else{
			if(number < 1000)
				return dfs(number / 1000 , number % 1000 , cnt + 1) + " " + result ;
			else if(number % 1000 != 0)
				return dfs(number / 1000 , number % 1000 , cnt + 1) + " " + result ;
			return dfs(number / 1000 , number % 1000 , cnt + 1) ;
		}

	}

	public String numberToWords(int num) {
		if(num == 0) return "Zero";
		return dfs(num/1000 , num % 1000 , 1);
	}
}
