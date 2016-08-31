package niuke.toutiao_client_2017_intern;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by yanghui on 16/8/31.
 */
public class A {

	public void run(){
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		StringBuilder sb = new StringBuilder(str);
		int n = scan.nextInt();
		for(int i = 0 ; i < n ; i ++){
			int left = scan.nextInt();
			int l = scan.nextInt();
			int right = left + l - 1;
			StringBuilder temp = new StringBuilder();
			for(int j = right ; j >= left ; j --){
				temp.append(sb.charAt(j));
			}
			sb.insert(right+1,temp);
		}
		System.out.println(sb);

	}

	public static void main(String args[]){
		new A().run();
	}

}
