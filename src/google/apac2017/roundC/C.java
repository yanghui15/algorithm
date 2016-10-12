package google.apac2017.roundC;

/**
 * Created by yanghui on 16/9/18.
 */

import java.io.File;
import java.util.*;

public class C {

	FastIO scan = new FastIO(new File("in"),new File("result"));


	public String trans(String str){
		int len = str.length();
		for(int i = 0 ; i < len ; i ++){
			if(str.charAt(i) == '('){
				return str.substring(i + 1 , len - 1);
			}
		}
		return null;
	}

	public class Node {
		String str;
		ArrayList<String> next;
		public Node(String str){
			this.str = str;
			next = new ArrayList<String>();
		}

		public void add(String str){
			this.next.add(str);
		}
	}

	public void solve(int times){

		int n = scan.nextInt();
		boolean flag = true;

		Node[] node = new Node[n];
		HashMap<String , Integer> map = new HashMap<String , Integer>();

		for(int i = 0 ; i < n ; i ++){
			String str = scan.next();
			String a[] = str.split("=");
			node[i] = new Node(a[0]);
			map.put(a[0] , i);
			a[1] = trans(a[1]);
			if(a[1].length() == 0) continue;
			String b[] = a[1].split(",");
			for(int j = 0 ; j < b.length ; j ++){
				node[i].add(b[j]);
				if(b[j].equals(a[0])){
					flag = false;
				}
			}
		}

		if(!flag){
			scan.println("Case #" + times + ": BAD");
			return;
		}

		Node[] re = new Node[n];
		int in[] = new int[n];
		boolean fl[] = new boolean[n];

		for(int i = 0 ; i < n ; i ++){
			re[i] = new Node(node[i].str);
		}

		for(int i = 0 ; i < n ; i ++){
			for(String cur : node[i].next){
				if(map.containsKey(cur)){
					re[map.get(cur)].next.add(re[i].str);
					in[i] ++;
				}else{
					scan.println("Case #" + times + ": BAD");
					return;
				}
			}
		}

		Queue<Node> q = new LinkedList<Node>();

		for(int i = 0 ; i < n ; i ++){
			if(in[i] == 0){
				q.add(re[i]);
				fl[i] = true;
			}
		}

		while(!q.isEmpty()){
			Node cur = q.poll();
			for(String str : cur.next){
				in[map.get(str)] --;
			}
			for(int i = 0 ; i < n ; i ++){
				if(in[i] == 0 && !fl[i]){
					q.add(re[i]);
					fl[i] = true;
				}
			}
		}

		int cnt = 0;
		for(int i = 0 ; i < n ; i ++){
			if(fl[i])
				cnt ++;
		}
		if(cnt == n)
			scan.println("Case #" + times + ": GOOD");
		else
			scan.println("Case #" + times + ": BAD");
	}

	public void run(){
		int T = scan.nextInt();
		for(int i = 1 ; i <= T ; i ++){
			solve(i);
		}
	}


	public static void main(String args[]){
		new C().run();
	}

}

