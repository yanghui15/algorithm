package leetcode.Insertion_Sort_List_147;

/**
 * Created by yanghui on 16/10/20.
 */
public class Solution {

	public class ListNode{
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	public ListNode insertionSortList(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode idx = head.next;
		ListNode cur = head;
		ListNode last_cur = head;
		while(true){
			if(idx == null) break;
			ListNode idy = cur;
			ListNode temp = idx.next;
			ListNode last = null;
			while(true){
				if(idy == null || idy == idx) {
					last_cur = idx;
					break;
				}
				if(idx.val >= idy.val){
					last = idy;
					idy = idy.next;
				}else{
					last_cur.next = idx.next;
					if(last == null){
						cur = idx;
						idx.next = idy;
					}else{
						idx.next = idy;
						last.next = idx;
					}
					break;
				}
			}
			idx = temp;
		}
		return cur;
	}

	public void print(ListNode head){
		ListNode cur = head;
		while(cur != null){
			System.out.print(cur.val + " ");
			cur = cur.next;
		}

		System.out.println();
	}

	public void run(){
		int arr[] = new int[]{3,4,1};
		ListNode head = new ListNode(arr[0]);
		ListNode cur = head;
		int idx = 1;
		while(idx < arr.length){
			ListNode temp = new ListNode(arr[idx]);
			cur.next = temp;
			cur = cur.next;
			idx ++;
		}
		print(head);
		ListNode result = insertionSortList(head);
		print(result);
	}

	public static void main(String args[]){
		new Solution().run();
	}

}
