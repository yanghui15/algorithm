package leetcode.contest_2017_0108;

import java.util.*;

/**
 * Created by yanghui on 17/1/8.
 */
public class Solution {

	int min_size = 0;
	int max_size = 0;

	public void add(TreeMap<Integer,Integer> map , int number){
		if(map.containsKey(number)) map.put(number , map.get(number) + 1);
		else map.put(number , 1);
	}

	public void remove(TreeMap<Integer,Integer> map , int number){
		if(map.get(number) == 1)
			map.remove(number);
		else
			map.put(number , map.get(number) - 1);
	}

	public void mintomax(TreeMap<Integer,Integer> min , TreeMap<Integer,Integer> max , int num){
		Integer mn = min.lastKey();
		remove(min , mn);
		add(min , num);
		add(max , mn);
		max_size ++;
	}

	public void mintomax(TreeMap<Integer,Integer> min , TreeMap<Integer,Integer> max){
		Integer mn = min.lastKey();
		remove(min , mn);
		add(max , mn);
		min_size --;
		max_size ++;
	}

	public void maxtomin(TreeMap<Integer,Integer> min , TreeMap<Integer,Integer> max , int num){
		Integer mx = max.firstKey();
		remove(max , mx);
		add(max , num);
		add(min , mx);
		min_size ++;
	}

	public void maxtomin(TreeMap<Integer,Integer> min , TreeMap<Integer,Integer> max){
		Integer mx = max.firstKey();
		remove(max , mx);
		add(min , mx);
		max_size --;
		min_size ++;
	}

	public void add(TreeMap<Integer,Integer> min , TreeMap<Integer,Integer> max , int number){
		if(min_size == 0){
			if(max_size > 0){
				maxtomin(min , max , number);
			}else{
				add(max , number);
				max_size ++;
			}
			return;
		}
		Integer mn = min.lastKey();
		if(min_size == max_size){
			if(number > mn){
				add(max , number);
				max_size ++;
			}else{
				mintomax(min , max , number);
			}
		}else{
			if(number > mn){
				maxtomin(min , max , number);
			}else{
				add(min , number);
				min_size ++;
			}
		}
	}

	public void remove(TreeMap<Integer,Integer> min , TreeMap<Integer,Integer> max , int number){

		if(min.containsKey(number) && !max.containsKey(number)){
			remove(min , number);
			min_size --;
			if(max_size - min_size >= 2)
				maxtomin(min , max);
		}else if(max.containsKey(number) && !min.containsKey(number)){
			remove(max , number);
			max_size --;
			if(max_size < min_size){
				mintomax(min , max);
			}
		}else{
			remove(min , number);
			min_size --;
			if(max_size - min_size >= 2)
				maxtomin(min , max);
		}
	}

	public double get(TreeMap<Integer,Integer> min , TreeMap<Integer,Integer> max){
		if(min_size == max_size){
			return ((double) min.lastKey() + (double) max.firstKey()) / 2.0d;
		}else if(min_size > max_size){
			return (double) min.lastKey();
		}else {
			return (double) max.firstKey();
		}
	}

	public double[] medianSlidingWindow(int[] nums, int k) {

		TreeMap<Integer,Integer> max = new TreeMap<>();
		TreeMap<Integer,Integer> min = new TreeMap<>();

		int len = nums.length;
		double[] result = new double[len - k + 1];
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0 ; i < k ; i ++){
			list.add(nums[i]);
		}
		Collections.sort(list);
		if(k % 2 == 0){
			result[0] = ((double)list.get(k/2) + (double)list.get(k/2 - 1)) / 2.0d;
		}else{
			result[0] = (double)list.get(k/2);
		}
		for(int i = 0 ; i < k/2 ; i ++){
			add(min , list.get(i));
			min_size ++;
		}
		for(int i = k/2 ; i < k ; i ++){
			add(max , list.get(i));
			max_size ++;
		}
		for(int i = k ; i < len ; i ++){
			remove(min , max , nums[i - k]);
			add(min , max , nums[i]);
			result[i - k + 1] = get(min , max);
		}
//		for(int i = 0 ; i < result.length ; i ++){
//			System.out.print(result[i] + " ");
//		}
//		System.out.println();
		return result;
	}

	public String licenseKeyFormatting(String S, int K) {
		StringBuilder sb = new StringBuilder();
		for(char cur : S.toCharArray()){
			if(cur == '-') continue;
			if((cur >= '0' && cur <= '9') || (cur >= 'A' && cur <= 'Z')) sb.append(cur);
			else{
				sb.append(Character.toUpperCase(cur));
			}
		}
		int len = sb.length();
		char[] re = sb.toString().toCharArray();
		StringBuilder result = new StringBuilder();
		int idx = 0;
		if(len % K != 0){
			for( ; idx < len % K ; idx ++){
				result.append(re[idx]);
			}
			if(idx < len){
				result.append('-');
			}
		}
		int cnt = 0;
		for(; idx < len ; idx ++){
			result.append(re[idx]);
			cnt ++;
			if(cnt == K){
				if(idx != len - 1) result.append('-');
				cnt = 0;
			}
		}
		return result.toString();
	}

	public int findComplement(int num) {
		char[] cur = Integer.toBinaryString(num).toCharArray();
		for(int i = 0 ; i < cur.length ; i ++){
			if(cur[i] == '0')  cur[i] = '1';
			else cur[i] = '0';
		}
		int ans = 0;
		for(int i = 0 ; i < cur.length ; i ++){
			ans += (int)Math.pow(2,cur.length - i - 1) * (cur[i] - '0');
		}
		return ans;
	}

	public void run(){
		System.out.println(findComplement(Integer.MAX_VALUE - 100000));
		LinkedHashMap<Integer,Integer> map = new LinkedHashMap<>();
		LinkedHashSet<Integer> set = new LinkedHashSet<>();
		set.iterator();
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
