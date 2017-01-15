package google.apac2017.roundE;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;

public class A {

	FastIO scan = new FastIO(new File("in"),new File("result"));


	public int gcd(int a , int b){
		return (a == 0) ? b : gcd(b% a , a);
	}

	public void solve(int times){



		scan.println("Case #" + times + ": ");
	}

	public void run(){
		int T = scan.nextInt();
		for(int i = 1 ; i <= T ; i ++){
			solve(i);
		}
	}


	public static void main(String args[]){
		new A().run();
	}

}

