package google.apac2017.roundE;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;

public class B {

	FastIO scan = new FastIO(new File("in"),new File("result"));


	public int gcd(int a , int b){
		return (a == 0) ? b : gcd(b% a , a);
	}

	public long cal(int len , long n){
		long left = 2l;
		long right = (long) Math.pow(n , 1.0/(len - 1));
		while(left <= right){
			long mid = (left + right) / 2;
			long sum = 0;
			long base = 1;
			boolean flag = false;
			for(int i = 0 ; i < len ; i ++){
				long temp = sum + base;
				if(temp - base == sum){
					sum += base;
				}else{
					flag = true;
					break;
				}
				base *= mid;
			}
			if(flag){
				right = mid - 1;
			}else if(sum == n) return mid;
			else{
				left = mid + 1;
			}
		}
		return -1;
	}



	public void solve(int times){

		long n = scan.nextLong();
		String cur = Long.toBinaryString(n);
		int len = cur.length();
		long ans = n - 1;

		for(int i = len ; i > 2 ; i --){
			long result = cal(i , n);
			if(result == -1) continue;
			else{
				ans = result;
				break;
			}
		}

		scan.println("Case #" + times + ": " + ans);
	}

	public void run(){
		int T = scan.nextInt();
		for(int i = 1 ; i <= T ; i ++){
			solve(i);
		}
	}


	public static void main(String args[]){
		new B().run();
	}

}

