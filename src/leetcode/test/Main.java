package leetcode.test;

import leetcode.Minimum_Window_Substring_76.Solution;

import java.util.Scanner;

/**
 * Created by yanghui on 16/9/1.
 */
public class Main {

	public void run(){
		Solution s = new Solution();
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		String t = scan.next();
		System.out.println(s.minWindow(str , t));
	}

	public static void main(String args[]){
		new Main().run();
	}

}
