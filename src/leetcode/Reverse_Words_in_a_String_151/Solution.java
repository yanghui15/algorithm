package leetcode.Reverse_Words_in_a_String_151;

/**
 * Created by yanghui on 16/12/13.
 */
public class Solution {

	public void swap(char[] cur , int i , int j){
		char tmp = cur[i];
		cur[i] = cur[j];
		cur[j] = tmp;
	}

	public void reverse(char[] cur , int left , int right){
		while(left <= right){
			swap(cur , left , right);
			left ++;
			right --;
		}
	}

	public String reverseWords(String s) {
		StringBuilder sb = new StringBuilder();
		int n = s.length();
		for(int i = 0 ; i < s.length() ; i ++){
			if(s.charAt(i) == ' ') {
				if(sb.length() == 0) continue;
				else if(sb.charAt(sb.length() - 1) != ' '){
					sb.append(' ');
				}
			}else{
				sb.append(s.charAt(i));
			}
		}
		String str = sb.toString().trim();
		System.out.println(str);
		char[] cur = str.toCharArray();
		if(cur.length == 0) return "";
		reverse(cur , 0 , cur.length - 1);
		int left = 0;
		int right = 0;
		while(right < cur.length){
			while(right < cur.length && cur[right] != ' ') right ++;
			reverse(cur , left , right - 1);
			left = right + 1;
			right ++;
		}
		return new String(cur);

	}

	public void run(){
		System.out.println(reverseWords("   the s   ky     is    blu e    "));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
