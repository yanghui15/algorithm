package leetcode.test;

import leetcode.H_Index_274.Solution;

import java.util.Scanner;

/**
 * Created by yanghui on 16/9/1.
 */
public class Main {

	public void run(){
		Solution s = new Solution();
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int re[] = new int[n];
		for(int i = 0 ; i < n ; i ++)
			re[i] = scan.nextInt();
		System.out.println(s.hIndex(re));
	}

	public static void main(String args[]){
		new Main().run();
	}

}
