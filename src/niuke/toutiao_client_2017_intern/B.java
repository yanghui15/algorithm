package niuke.toutiao_client_2017_intern;

import java.util.Scanner;

/**
 * Created by yanghui on 16/8/31.
 */
public class B {

	public void run(){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int s = scan.nextInt();
		int l = scan.nextInt();

		int x = (l + 1) / (s + 1);
		if(x % 13 == 0)
			x --;
		int num = n / x;
		if(n % x != 0){
			int temp = n % x;
			if(temp % 13 == 0){
				if(num == 0){
					num +=2;
				}
				else if(x % 13 == 1 && temp + 2 > x){
					num += 2;
				}else{
					num ++;
				}
			}else{
				num ++;
			}
		}
		System.out.println(num);

	}

	public static void main(String args[]){
		new B().run();
	}

}
