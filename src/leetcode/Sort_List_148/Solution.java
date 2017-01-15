package leetcode.Sort_List_148;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by yanghui on 16/12/11.
 */
public class Solution {

	class ListNode{
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	public ListNode merge(ListNode left , ListNode right){
		ListNode left_idx = left;
		ListNode right_idx = right;
		ListNode ans = new ListNode(0);
		ListNode cur = ans;
		while(left_idx != null || right_idx != null){
			if(left_idx == null){
				cur.next = right_idx;
				break;
			}else if(right_idx == null){
				cur.next = left_idx;
				break;
			}
			else if(left_idx.val < right_idx.val){
				cur.next = left_idx;
				left_idx = left_idx.next;
				cur = cur.next;
				cur.next = null;
			}else{
				cur.next = right_idx;
				right_idx = right_idx.next;
				cur = cur.next;
				cur.next = null;
			}
		}
		return ans.next;
	}

	public ListNode MergeSort(ListNode head){
		if(head == null || head.next == null) return head;
		if(head.next.next == null){
			ListNode right = head.next;
			head.next = null;
			return merge(head , right);
		}
		ListNode slow = head;
		ListNode fast = head;
		while(fast != null && fast.next != null){
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode right = slow.next;
		slow.next = null;
		head = MergeSort(head);
		right = MergeSort(right);
		return merge(head , right);
	}

	public ListNode[] partition(ListNode head){
		if(head == null || head.next == null) return new ListNode[]{head , null};
		ListNode right = new ListNode(0);
		ListNode left = head;
		ListNode cur = head.next;
		ListNode left_idx = left;
		ListNode right_idx = right;
		while(cur != null){
			if(cur.val <= head.val){
				left_idx.next = cur;
				left_idx = left_idx.next;
			}else{
				right_idx.next = cur;
				right_idx = right_idx.next;
			}
			cur = cur.next;
		}
		left_idx.next = null;
		right_idx.next = null;
		return new ListNode[]{left,right.next};
	}

	public ListNode QuickSort(ListNode head){
		if(head == null || head.next == null) return head;
		ListNode[] result = partition(head);
		ListNode cur = null;
		ListNode ans = null;
		if(result[0].next != null){
			ListNode tmp = result[0].next;
			result[0].next = null;
			tmp = QuickSort(tmp);
			ans = tmp;
			cur = tmp;
			while(cur.next != null){
				cur = cur.next;
			}
			cur.next = result[0];
			cur = cur.next;
		}else{
			cur = result[0];
			ans = result[0];
		}
		if(result[1] != null){
			result[1] = QuickSort(result[1]);
		}
		cur.next = result[1];
		return ans;
	}

	public ArrayList<Integer> debug(ListNode head){
		ArrayList<Integer> ans = new ArrayList<>();
		ListNode cur = head;
		while(cur != null){
			ans.add(cur.val);
			cur = cur.next;
		}
		return ans;
	}

	public boolean valid(ArrayList<Integer> a , ArrayList<Integer> b){
		for(int i = 0 ; i < a.size() ; i ++){
			if(a.get(i) - b.get(i) != 0) {
				return false;
			}
		}
		return true;
	}

	public void run(){
		ArrayList<Integer> cur = new ArrayList<Integer>();
		int n = 1000000;
		Random r = new Random();
		for(int i = 0 ; i < n ; i ++){
			cur.add(r.nextInt());
		}
		ListNode head = new ListNode(0);
		ListNode idx = head;
		for(int i = 0 ; i < n ; i ++){
			idx.next = new ListNode(cur.get(i));
			idx = idx.next;
		}
		long start = System.currentTimeMillis();
		ListNode ans  = MergeSort(head.next);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		ArrayList<Integer> result = debug(ans);
		start = System.currentTimeMillis();
		Collections.sort(cur);
		end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(valid(cur , result));
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
