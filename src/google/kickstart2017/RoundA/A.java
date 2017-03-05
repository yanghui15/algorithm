package google.kickstart2017.RoundA;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;

public class A {

	FastIO scan = new FastIO(new File("in"),new File("result"));


	public long fastpow(long x , long pow , long mod){
		long ans = 1;
		while(pow > 0){
			if((pow & 1) > 0){
				ans = ans * x % mod;
			}
			pow = pow / 2;
			x = x * x % mod;
		}
		return ans % mod;
	}

	public int get_ans(long R , long C){
		if(R < C){
			return get_ans(C , R);
		}
		long mod = 1000000007L;
		long ans = (2 * (R * C % mod) % mod - C * C % mod + mod + (2 * R) % mod  - C % mod + mod) % mod;
		ans = ans * (C - 1) % mod;
		ans = ans * C % mod;
		ans = (ans * fastpow(12 , mod - 2 , mod)) % mod;
		return (int)ans;
	}

	public void solve(int times){
		int R = scan.nextInt();
		int C = scan.nextInt();
		int  ans = get_ans((long)R , (long)C);
		scan.println("Case #"+ times +": " + ans);
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

