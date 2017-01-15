package leetcode.Word_Ladder_127;

import java.util.*;

/**
 * Created by yanghui on 16/12/7.
 */
public class Solution {

	public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		HashSet<String> visit = new HashSet<>();
		HashMap<String,Integer> map = new HashMap<>();
		map.put(beginWord , 1);
		Queue<String> queue = new LinkedList<String>();
		queue.add(beginWord);
		while(!queue.isEmpty()){
			String top = queue.poll();
			if(visit.contains(top)) continue;
			if(top.equals(endWord)) return map.get(top);
			visit.add(top);
			char[] tmp = top.toCharArray();
			for(int i = 0 ; i < tmp.length ; i ++){
				char cur_i = tmp[i];
				for(int j = 0 ; j < 26 ; j ++){
					if((char)(j + 'a') == cur_i) continue;
					tmp[i] = (char)(j + 'a');
					if(wordList.contains(new String(tmp))){
						if(visit.contains(new String(tmp)) || map.containsKey(new String(tmp))) continue;
//						System.out.println(top + " " + new String(tmp));
						map.put(new String(tmp) , map.get(top) + 1);
						queue.add(new String(tmp));
					}
				}
				tmp[i] = cur_i;
			}
		}
		return 0;
	}

	public void run(){
		String[] list = new String[]{"hot","dog","dot"};
		Set<String> wordList = new HashSet<>();
		for(String cur : list){
			wordList.add(cur);
		}
		System.out.println(ladderLength("hot" , "dog" , wordList));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
