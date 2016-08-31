package niuke.toutiao_client_2017_intern;

import java.util.Scanner;

/**
 * Created by yanghui on 16/8/31.
 */
public class C {

	private boolean nextPermutate(int data[]) {
		int length = data.length;
		int end = length - 1;
		int swapPoint1 = end, swapPoint2 = end;
		// the actual swap-point is swapPoint1 - 1
		while (swapPoint1 > 0 && data[swapPoint1] <= data[swapPoint1 - 1])
			swapPoint1--;
		if (swapPoint1 == 0)
			return false;
		else {
			while (swapPoint2 > 0 && data[swapPoint2] <= data[swapPoint1 - 1])
				swapPoint2--;
			swap(data, swapPoint1 - 1, swapPoint2);
			reverse(data, swapPoint1, end);
			return true;
		}
	}

	private void swap(int[] data, int left, int right) {
		int temp = data[left];
		data[left] = data[right];
		data[right] = temp;
	}

	private void reverse(int[] data, int left, int right) {
		for (int i = left, j = right; i < j; i++, j--)
			swap(data, i, j);
	}


	public void run(){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		String str[] = new String[n];
		for(int i = 0 ; i < n ; i ++){
			str[i] = scan.next();
		}
		int list[] = new int[n];
		for(int i = 0 ; i < n ; i ++){
			list[i] = i;
		}
		int cnt = 0;
		do {
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < n ; i ++){
				sb.append(str[list[i]]);
			}
			StringBuilder temp = new StringBuilder();
			temp.append(sb);
			temp.append(sb);
			int tcnt = 0;
			for(int i = 1 ; i <= sb.length() ; i ++){
				if(temp.subSequence(i,i+sb.length()).equals(sb.toString()))
					tcnt ++;
			}
			if(tcnt == k)
				cnt ++;
		}while(this.nextPermutate(list));
		System.out.println(cnt);
	}

	public static void main(String args[]){
		new C().run();
	}

}
