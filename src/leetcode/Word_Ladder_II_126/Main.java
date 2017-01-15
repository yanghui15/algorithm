package leetcode.Word_Ladder_II_126;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yanghui on 16/12/15.
 */
public class Main {

	public int ladder_num(HashSet<String> start , HashSet<String> end , Set<String> wordList , int length){
		ArrayList<String> list = new ArrayList<String>();
		for(String cur : start){
			char[] tmp = cur.toCharArray();
			for(int i = 0 ; i < tmp.length ; i ++){
				char back = tmp[i];
				for(char j = 'a' ; j <= 'z' ; j ++){
					if(j == back) continue;
					tmp[i] = j;
					String nxt = new String(tmp);
					if(end.contains(nxt)) return length;
					if(wordList.contains(nxt)){
						wordList.remove(nxt);
						list.add(nxt);
					}
				}
				tmp[i] = back;
			}
		}
		if(list.size() == 0) return 0;
		for(String cur : list){
			start.add(cur);
		}
		if(start.size() < end.size()) return ladder_num(start , end , wordList , length + 1);
		else return ladder_num(end , start , wordList , length + 1);
	}

	public int ladderLength(String beginWord, String endWord, Set<String> wordList){
		HashSet<String> start = new HashSet<>();
		HashSet<String> end = new HashSet<>();
		wordList.remove(beginWord);
		wordList.remove(endWord);
		start.add(beginWord);
		end.add(endWord);
		int ans = ladder_num(start , end , wordList , 2);
		return ans;
	}

	public void dfs(String beginWord , String endWord , List<String> cur , List<List<String>> ans , int length , Set<String> words , HashSet<String> visit){
		if(length == 0) return;
		cur.add(beginWord);
		System.out.println(cur + " " + length + " " + beginWord);
		if(beginWord.equals(endWord)) {
//			System.out.println(cur + " " + length );
			ans.add(new ArrayList<String>(cur));
		}
		char[] tmp = beginWord.toCharArray();
		for(int i = 0 ; i < tmp.length ; i ++) {
			char tmp_i = tmp[i];
			for (char j = 'a'; j <= 'z'; j++) {
				if (j == tmp_i) continue;
				tmp[i] = j;
				String tmp_cur = new String(tmp);
				if(visit.contains(tmp_cur)) {
					continue;
				}
				if(words.contains(tmp_cur)){
					visit.add(tmp_cur);
					dfs(tmp_cur , endWord , cur , ans , length - 1 , words , visit);
					visit.remove(tmp_cur);

				}
			}
			tmp[i] = tmp_i;
		}
		cur.remove(cur.size() - 1);
	}

	public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
		Set<String> words = new HashSet<>(wordList);
		int len = ladderLength(beginWord , endWord , wordList);
		List<List<String>> ans = new ArrayList<>();
		if(len == 0) return ans;
		words.add(endWord);
		System.out.println(len);
		dfs(beginWord , endWord , new ArrayList<String>() , ans , len , words , new HashSet<String>());
		return ans;
	}

	public void run(){
		String[] list = new String[]{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
		Set<String> wordList = new HashSet<>();
		for(String cur : list){
			wordList.add(cur);
		}
		List<List<String>> ans = findLadders("cet" , "ism" , wordList);
		for(List<String> i : ans){
			System.out.println(i);
		}
	}



	public static void main(String args[]){
		new Main().run();
	}
}
