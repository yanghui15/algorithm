package niuke.toutiao_client_2017_intern;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by yanghui on 16/8/31.
 */
public class D {

	public void run(){
		Scanner scan = new Scanner(System.in);
		long x = scan.nextInt();
		long k = scan.nextInt();

		char[] binary_x = Long.toBinaryString(x).toCharArray();
		char[] binary_k = Long.toBinaryString(k).toCharArray();

		ArrayList<Integer> list = new ArrayList<Integer>();
		int idx = binary_k.length - 1;
		for(int i = binary_x.length - 1 ; i >= 0 ; i --){
			if(binary_x[i] == '1'){
				list.add(0);
			}else{
				if(idx >= 0)
					list.add(binary_k[idx --] - '0');
				else
					list.add(0);
			}
		}
		while(idx >= 0){
			list.add(binary_k[idx --] - '0');
		}
		long result = 0;
		for(int i = 0 ; i < list.size() ; i ++){
			result = result + (long)Math.pow(2 , i) * (long)list.get(i);
		}
		System.out.println(result);
	}

	public static void main(String args[]){
		new D().run();
	}
}
