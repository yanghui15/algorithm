package leetcode.test;

import leetcode.Perfect_Rectangle_391.Solution;

import java.util.Scanner;

/**
 * Created by yanghui on 16/9/1.
 */
public class Main {

	public void run(){
		Solution s = new Solution();
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int re[][] = new int[n][4];
		for(int i  = 0 ; i < n ; i ++){
			re[i][0] = scan.nextInt();
			re[i][1] = scan.nextInt();
			re[i][2] = scan.nextInt();
			re[i][3] = scan.nextInt();
		}
		System.out.println(s.isRectangleCover(re));
	}

	public static void main(String args[]){
		new Main().run();
	}

}
