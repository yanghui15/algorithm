package leetcode.test;

import leetcode.Additive_Number_306.Solution;

import java.util.Scanner;

/**
 * Created by yanghui on 16/9/1.
 */
public class Main {

	public void run(){
		Solution s = new Solution();
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		System.out.println(s.isAdditiveNumber(str));
	}

	public static void main(String args[]){
		new Main().run();
	}

}
