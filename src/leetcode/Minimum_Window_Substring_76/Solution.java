package leetcode.Minimum_Window_Substring_76;

import java.util.HashMap;

/**
 * Created by yanghui on 16/9/4.
 */
public class Solution {

	public boolean Judge(HashMap<Character,Integer> map , HashMap<Character,Integer> map2){
		for(Character cur : map.keySet()){
			if(map2.containsKey(cur) && map2.get(cur) >= map.get(cur)){
				continue;
			}else
				return false;
		}
		return true;
	}

	public String minWindow(String s, String t) {
		if(t.length() == 0)
			return "";
		int len = s.length();
		String result = s + s;
		int idx = len - 1;
		HashMap<Character,Integer> map = new HashMap<Character, Integer>();
//		System.out.println(t);
		for(int i = 0 ; i < t.length() ; i ++){
			if(map.containsKey(t.charAt(i))){
				map.put(t.charAt(i) , map.get(t.charAt(i)) + 1);
			}else{
				map.put(t.charAt(i) , 1);
			}
		}
		HashMap<Character,Integer> map2 = new HashMap<Character, Integer>();
		for(int i = len - 1 ; i >=0 ; i --){
			if(map.containsKey(s.charAt(i))){
				if(map2.containsKey(s.charAt(i))){
					map2.put(s.charAt(i) , map2.get(s.charAt(i)) + 1);
				}else{
					map2.put(s.charAt(i) , 1);
				}
			}
			while(idx >= i && Judge(map , map2)){
				if(map2.containsKey(s.charAt(idx))){
					map2.put(s.charAt(idx) , map2.get(s.charAt(idx)) - 1);
				}
				idx --;
			}
			if(idx < len - 1) {
				idx++;
				if(map2.containsKey(s.charAt(idx))){
					map2.put(s.charAt(idx) , map2.get(s.charAt(idx)) + 1);
				}
			}
//			System.out.println(i +" "+idx);
//			System.out.println(map);
//			System.out.println(map2);
			if(Judge(map,map2) && idx - i + 1 < result.length()){
				result = s.substring(i,idx+1);
			}
		}
		return result.length() > s.length() ? "" : result;
	}
}
