package leetcode.Maximum_XOR_of_Two_Numbers_in_an_Array_421;

/**
 * Created by yanghui on 16/10/24.
 */
public class Solution {

	class TrieNode{
		int number;
		TrieNode next[];
		public TrieNode(int number){
			next = new TrieNode[2];
			this.number = number;
		}
	}

	public void add(TrieNode rt , String cur , int idx){
		if(idx >= cur.length()) return;
		if(rt.next[cur.charAt(idx) - '0'] == null){
			rt.next[cur.charAt(idx) - '0'] = new TrieNode(cur.charAt(idx) - '0');
		}
		add(rt.next[cur.charAt(idx) - '0'] , cur , idx + 1);
	}

	public int search(TrieNode rt , String cur , int idx){
		if(idx >= cur.length()) return 0;
		if(cur.charAt(idx) == '0'){
			if(rt.next[1] != null)
				return search(rt.next[1] , cur , idx + 1) + (int)Math.pow(2 , 32 - idx - 1);
			else
				return search(rt.next[0] , cur , idx + 1);
		}else{
			if(rt.next[0] != null)
				return search(rt.next[0] , cur , idx + 1) + (int)Math.pow(2 , 32 - idx - 1);
			else
				return search(rt.next[1] , cur , idx + 1);
		}
	}

	public int findMaximumXOR(int[] nums) {
		TrieNode root = new TrieNode(-1);
		int n = nums.length;
		if(n < 2) return 0;
		int max_len = 30;
		String b[] = new String[n];
		for(int i = 0 ; i < n ; i ++){
			char cur[] =  new char[max_len];
			for(int j = 0 ; j < max_len ; j ++){
				cur[max_len - j - 1] = (char)((((nums[i] >> j) & 1) == 1 ? 1 : 0)+'0');
			}
			b[i] = new String(cur);
			add(root , b[i] , 0);
		}

		int ans = 0;
		for(int i = 0 ; i < n ; i ++){
			int temp = search(root , b[i] , 0 );
			System.out.println(nums[i] + " "+ temp + " "+(nums[i]^temp));
			ans = Math.max(ans , search(root , b[i] , 0));
		}
		return ans;
	}

	public void run(){
		int a[] = new int[]{3, 10, 5, 25, 2, 8};
		System.out.println(findMaximumXOR(a));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}