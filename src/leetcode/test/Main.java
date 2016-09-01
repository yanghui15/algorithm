package leetcode.test;

import leetcode.Ugly_Number_II_264.Solution;

import java.util.Scanner;

/**
 * Created by yanghui on 16/9/1.
 */
public class Main {

	public void run(){
		Solution s = new Solution();
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		System.out.println(s.nthUglyNumber(n));
	}

	public static void main(String args[]){
		new Main().run();
	}

}
