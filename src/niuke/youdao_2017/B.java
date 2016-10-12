package niuke.youdao_2017;

import java.util.*;

/**
 * Created by yanghui on 16/10/7.
 */
public class B {

	class Node implements Comparable<Node>{
		int val;
		int idx;
		public Node(int idx , int val){
			this.idx = idx;
			this.val = val;
		}

		@Override
		public int compareTo(Node n) {
			return Integer.compare(this.idx , n.idx);
		}
	}

	public void run(){
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for(int t = 0 ; t < T ; t ++){
			int n = scan.nextInt();
			Queue<Node> q = new LinkedList<Node>();
			Node node[] = new Node[n];
			for(int i = 0 ; i < n ; i ++){
				node[i] = new Node(i , i);
			}
			for(int i = 0 ; i < n ; i++){
				q.add(node[i]);
			}
			int idx = 1;
			while(!q.isEmpty()){
				q.add(q.poll());
				Node top = q.poll();
				top.val = idx ++;
			}
			Arrays.sort(node);
			for(int i = 0 ; i < n ; i ++){
				if(i == n - 1){
					System.out.println(node[i].val);
				}else{
					System.out.print(node[i].val + " ");
				}
			}
		}
	}

	public static void main(String args[]){
		new B().run();
	}
}
