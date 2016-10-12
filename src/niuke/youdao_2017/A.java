package niuke.youdao_2017;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by yanghui on 16/10/7.
 */
public class A {

	public void run(){
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for(int t = 0 ; t < T ; t ++){
			int n = scan.nextInt();
			int K = scan.nextInt();
			ArrayList<Integer> a = new ArrayList<Integer>();
			ArrayList<Integer> b = new ArrayList<Integer>();
			ArrayList<Integer> result = new ArrayList<Integer>();
			for(int i = 0 ; i < n ; i ++){
				a.add(scan.nextInt());
			}
			for(int i = 0 ; i < n ; i ++){
				b.add(scan.nextInt());
			}
			for(int k = 0 ; k < K ; k ++){
				result.clear();
				for(int i = 0 ; i < n; i ++){
					result.add(a.get(i));
					result.add(b.get(i));
				}
				a.clear();
				b.clear();
				for(int i = 0 ; i < n ; i ++){
					a.add(result.get(i));
				}
				for(int i = n ; i < n*2 ; i ++) {
					b.add(result.get(i));
				}
			}
			for(int i = 0 ; i < result.size() ; i ++){
				if(i == result.size() - 1){
					System.out.println(result.get(i));
				}else{
					System.out.print(result.get(i) + " ");
				}
			}
		}
	}

	public static void main(String args[]){
		new A().run();
	}
}
