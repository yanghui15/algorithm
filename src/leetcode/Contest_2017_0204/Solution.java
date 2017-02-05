package leetcode.Contest_2017_0204;

import java.util.*;

/**
 * Created by yanghui on 2017/2/4.
 */
public class Solution {

	class IPOHelp implements Comparable<IPOHelp> {
		int profit;
		int cap;

		public IPOHelp(int profit , int cap){
			this.profit = profit;
			this.cap = cap;
		}
		@Override
		public int compareTo(IPOHelp ipo) {
			if(this.profit == ipo.profit){
				return Integer.compare(this.cap , ipo.cap);
			}
			return Integer.compare(ipo.profit , this.profit);
		}
	}

	public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
		int n = Profits.length;
		IPOHelp node[] = new IPOHelp[n];
		for(int i = 0 ; i < n ; i ++){
			node[i] = new IPOHelp(Profits[i] , Capital[i]);
		}
		Arrays.sort(node, new Comparator<IPOHelp>() {
			@Override
			public int compare(IPOHelp o1, IPOHelp o2) {
				return Integer.compare(o1.cap , o2.cap);
			}
		});
		PriorityQueue<IPOHelp> queue = new PriorityQueue<>();
		int init = W;
		int ans = W;
		int idx = 0 ;
		for(int i =  0 ; i < k ; i ++){
			while(idx < n && node[idx].cap <= init){
				queue.add(node[idx]);
				idx ++;
			}
			if(!queue.isEmpty()){
				IPOHelp top = queue.poll();
				ans += top.profit;
				init += top.profit;
			}else{
				break;
			}
		}
		return ans;
	}

	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public void dfs(TreeNode root , HashMap<TreeNode , Integer> map){
		if(root == null) return;
		dfs(root.left , map);
		dfs(root.right , map);
		int cur = root.val;
		if(root.left != null) cur += map.get(root.left);
		if(root.right != null) cur += map.get(root.right);
		map.put(root , cur);
	}

	public int[] findFrequentTreeSum(TreeNode root) {
		if(root == null) return new int[]{};
		HashMap<TreeNode , Integer> map = new HashMap<>();
		dfs(root , map);
		HashMap<Integer,Integer> map2 = new HashMap<>();
		for(TreeNode cur : map.keySet()){
			if(map2.containsKey(map.get(cur)))
				map2.put(map.get(cur) , map2.get(map.get(cur)) + 1);
			else
				map2.put(map.get(cur) , 1);
		}
		int max_value = 0;
		for(Integer cur : map2.keySet()){
			max_value = Math.max(max_value , map2.get(cur));
		}
		int cnt = 0;
		for(Integer cur : map2.keySet()){
			if(map2.get(cur) == max_value)
				cnt ++;
		}

		int ans[] = new int[cnt];
		int idx = 0;
		for(Integer cur : map2.keySet()){
			if(map2.get(cur) == max_value)
				ans[idx ++] = cur;
		}
		return ans;
	}

	public String[] findWords(String[] words) {
		char[][] alphabet_tmp = new char[][]{
				{'q','w','e','r','t','y','u','i','o','p'},
				{'a','s','d','f','g','h','j','k','l'},
				{'z','x','c','v','b','n','m'}
		};
		HashSet<Character>[] alphabet = new HashSet[3];
		for(int i = 0 ; i < 3 ; i ++){
			alphabet[i] = new HashSet<>();
			for(int j = 0 ; j < alphabet_tmp[i].length ; j ++){
				alphabet[i].add(alphabet_tmp[i][j]);
			}
		}
		ArrayList<String> tmp = new ArrayList<>();
		for(int i = 0 ; i < words.length ; i ++){
			for(int j = 0 ; j < 3 ; j ++){
				boolean t = true;
				for(int k = 0 ; k < words[i].length() ; k ++){
					t = t && alphabet[i].contains(Character.toLowerCase(words[i].charAt(k)));
				}
				if(t) {
					tmp.add(words[i]);
					break;
				}
			}
		}
		String[] ans = new String[tmp.size()];
		int idx = 0;
		for(String cur : tmp){
			ans[idx ++] = cur;
		}
		return ans;
	}

	public void run(){
		System.out.println(findMaximizedCapital(1 , 2 , new int[]{1,2,3} , new int[]{1 , 1, 2}));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
