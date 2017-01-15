package google.apac2017.roundE;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;

public class C {

	FastIO scan = new FastIO(new File("in"),new File("result2"));


	public int gcd(int a , int b){
		return (a == 0) ? b : gcd(b% a , a);
	}

	public int go(int N, int M)
	{
		if (M*2<N) return 0;
		int l = Math.max(N-M, 0);
		int r = N/2;
		return r-l+1;
	}

	public long cal(int sum , int min){
		long ans = 0;
		for(int i = 1 ; i * min <= sum ; i ++){
			int cur = i * min;
			for(int j = 0 ; cur + j * (min + 1) <= sum ; j ++){
				int temp = cur + j * (min + 1);
				if((sum - temp) % (min + 2) == 0){
					ans ++;
				}
			}
		}
		return ans;
	}

	public void solve(int times){

		int n = scan.nextInt();
		int D = scan.nextInt();

		long ans = 0;
		for(int i = D ; i <= n ; i += D){
			ans += cal(n , i);
		}
		scan.println("Case #" + times + ": " + ans);

	}

	public void run(){
		long start = System.currentTimeMillis();
		int T = scan.nextInt();
		for(int i = 1 ; i <= T ; i ++){
			solve(i);
		}
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000);
	}


	public static void main(String args[]){
		new C().run();
	}

}

