package leetcode.Word_Ladder_II_126;

import java.util.*;

/**
 * Created by yanghui on 16/10/5.
 */
public class Solution {

	class Node{
		int idx;
		String str;
		public List<Node> next;
		public List<Node> last;
		public Node(String str , int idx){
			this.str = str;
			this.idx = idx;
			next = new ArrayList<Node>();
			last = new ArrayList<Node>();
		}

		@Override
		public String toString() {
			return "Node{" +
					"idx=" + idx +
					", str='" + str + '\'' +
					'}';
		}
	}

	public int cal(Node a , Node b){
		int len = a.str.length();
		int ans = 0;
		for(int i = 0 ; i < len ; i ++){
			if(a.str.charAt(i) != b.str.charAt(i))
				ans ++;
		}
		return ans;
	}

	public void dfs(Node end , List<List<String>> result , List<String> cur , Node start){
		cur.add(end.str);
		if(start == end){
			List<String> temp = new ArrayList<String>();
			for(int i = cur.size() - 1 ; i >= 0 ; i --){
				temp.add(cur.get(i));
			}
			result.add(temp);
			return;
		}
		for(Node i : end.last){
			dfs(i , result , cur , start);
		}
		cur.remove(cur.size() - 1);
	}

	public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
		int n = wordList.size();
		Node node[] = new Node[n+2];
		int idx = 0;
		for(String cur : wordList){
			node[idx] = new Node(cur , idx ++);
		}
		node[idx] = new Node(beginWord , idx ++);
		node[idx] = new Node(endWord , idx ++);
		for(int i = 0 ; i < idx ; i ++){
			for(int j = idx - 1 ; j >= 0 ; j --){
				if(cal(node[i] , node[j]) == 1 && j != i) {
					node[i].next.add(node[j]);
				}
			}
		}
		boolean flag[] = new boolean[n+2];
		Arrays.fill(flag , false);
		List<List<String>> result = new ArrayList<List<String>>();
		Queue<Node> q = new LinkedList<Node>();
		q.add(node[n]);
		while(!q.isEmpty()){
			Node top = q.poll();
			flag[top.idx] = true;
			if(top == node[n+1]){
				break;
			}
			for(Node cur : top.next){
				if(!flag[cur.idx]){
					cur.last.add(top);
					q.add(cur);
				}
			}
		}
		List<String> cur = new ArrayList<String>();
		dfs(node[n+1] , result , cur , node[n]);
//		for(int i = 0 ; i < node.length ; i ++){
//			System.out.println(node[i].str + " "+node[i].idx + " "+node[i].next + " "+node[i].last);
//		}
		return result;
	}

	public void run(){
		Scanner scan = new Scanner(System.in);
		String start = scan.next();
		String end = scan.next();
		int n = scan.nextInt();
		Set<String> set = new HashSet<String>();
		for(int i = 0 ; i < n ; i ++){
			set.add(scan.next());
		}
		List<List<String>> result = findLadders(start , end , set);
		for(List<String> cur : result){
			System.out.println(cur);
		}
	}

	public static void main(String args[]){
		new Solution().run();
	}

}