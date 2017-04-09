package leetcode.Contest_2017_0312;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yanghui on 2017/3/12.
 */
public class Solution {

	public void swap(char[] str , int i , int j){
		char tmp = str[i];
		str[i] = str[j];
		str[j] = tmp;
	}

	public void reverse(char[] str , int left , int right){
		while(left <= right){
			swap(str , left , right);
			left ++;
			right --;
		}
	}

	public String reverseStr(String s, int k) {
		char[] str = s.toCharArray();
		int idx = 0;
		int cur = 0;
		while(idx < str.length){
			if(idx + k <= str.length){
				if(cur == 0){
					int left = idx;
					int right = idx + k - 1;
					reverse(str , left , right);
					idx = idx + k;
				}else{
					idx = idx + k;
				}
			}else{
				if(cur == 0){
					int left = idx;
					int right = str.length - 1;
					reverse(str , left , right);
					break;
				}else{
					break;
				}
			}
			cur = (cur + 1) % 2;
		}
		return new String(str);
	}

	public int get_difference(String a , String b){
		if(a.compareTo(b) < 0) return get_difference(b , a);
		int hour_a = Integer.valueOf(a.split(":")[0]);
		int min_a = Integer.valueOf(a.split(":")[1]);
		int hour_b = Integer.valueOf(b.split(":")[0]);
		int min_b = Integer.valueOf(b.split(":")[1]);
		int ans_a = hour_a * 60 + min_a;
		int ans_b = hour_b * 60 + min_b;
		return Math.min(ans_a - ans_b , (ans_b + 24 * 60 - ans_a));
	}

	public int findMinDifference(List<String> timePoints) {
		HashSet<String> set = new HashSet<>(timePoints);
		ArrayList<String> list = new ArrayList<>(set);
		if(list.size() < timePoints.size()) return 0;
		int ans = 24 * 60;
		for(int i = 0 ; i < list.size() ; i ++){
			for(int j = i + 1 ; j < list.size() ; j ++){
				ans = Math.min(ans , get_difference(list.get(i) , list.get(j)));
			}
		}
		return ans;
	}

	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int x){
			this.val = x;
		}
	}

	public String get_next(char[] str , int idx){
		if(idx >= str.length) return "";
		StringBuilder sb = new StringBuilder();
		int left = 1;
		int right = 0;
		sb.append(str[idx]);
		idx ++;
		while(idx < str.length){
			if(str[idx] == '('){
				left ++;
			}
			if(str[idx] == ')'){
				right ++;
			}
			sb.append(str[idx]);
			idx ++;
			if(left == right)
				break;
		}
		return sb.toString();
	}

	public TreeNode str2tree(String s) {
		if(s.length() == 0) return null;
		char[] str = s.toCharArray();
		int idx = 0;
		StringBuilder sb = new StringBuilder();
		while(idx < s.length()){
			if(str[idx] != '('){
				sb.append(str[idx ++]);
			}else{
				break;
			}
		}
		TreeNode root = new TreeNode(Integer.valueOf(sb.toString()));
		String ll = get_next(str , idx);
		String rr = get_next(str , idx + ll.length());
		if(ll.length() > 0)
			root.left = str2tree(ll.substring(1 , ll.length() - 1));
		if(rr.length() > 0)
			root.right = str2tree(rr.substring(1 , rr.length() - 1));
		return root;
	}

	public String long_prefix(String a , String b){
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < a.length() ; i ++){
			if(a.charAt(i) == b.charAt(i)){
				sb.append(a.charAt(i));
			}else{
				break;
			}
		}
		return sb.toString();
	}

	public List<String> wordsAbbreviation(List<String> dict) {
		List<String> ans = new ArrayList<>();
		for(int i = 0 ; i < dict.size() ; i ++){
			String pre = "";
			boolean flag = false;
			String a = dict.get(i);
			for(int j = 0 ; j < dict.size() ; j ++){
				if(j == i) continue;
				String b = dict.get(j);
				if(a.length() == b.length()
						&& a.charAt(0) == b.charAt(0)
						&& a.charAt(a.length() - 1) == b.charAt(b.length() - 1)){
					flag = true;
					String prefix = long_prefix(a , b);
					if(prefix.length() > pre.length()){
						pre = prefix;
					}
				}
			}
			if(!flag){
				char head = a.charAt(0);
				char tail = a.charAt(a.length() - 1);
				String tmp = "" + head + (a.length() - 2) + tail;
				if(tmp.length() < a.length()) ans.add(tmp);
				else
					ans.add(a);
			}else{
				String head = pre+a.charAt(pre.length());
				if(head.length() == a.length()){
					ans.add(head);
				}else{
					char tail = a.charAt(a.length() - 1);
					int cnt = a.length() - 1 - head.length();
					String tmp = head+cnt+tail;
					if(tmp.length() < a.length()) ans.add(tmp);
					else
						ans.add(a);
				}
			}
		}
		return ans;
	}

	public void run(){
		System.out.println(str2tree("4(2(3)(1))(6(5))"));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
