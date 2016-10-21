package leetcode.Contest_1016;

import java.util.*;

/**
 * Created by yanghui on 16/10/9.
 */
public class Solution {

	class TrieNode{
		char cur;
		TrieNode[] next;
		boolean flag = false;
		ArrayList<Integer> list;
		public TrieNode(char cur) {
			this.cur = cur;
			this.next = new TrieNode[26];
			this.flag = false;
			list = new ArrayList<Integer>();
		}

		@Override
		public String toString() {
			return "TrieNode{" +
					"cur=" + cur +
					", next=" + Arrays.toString(next) +
					", flag=" + flag +
					'}';
		}
	}

	public void add(TrieNode root , String str , int pos , int idx){
		if(pos >= str.length()) return;
		if(root.next[str.charAt(pos) - 'a'] == null){
			root.next[str.charAt(pos) - 'a'] = new TrieNode(str.charAt(pos));
		}
		root.next[str.charAt(pos) - 'a'].list.add(idx);
		if(pos == str.length() - 1){
			root.next[str.charAt(pos) - 'a'].flag = true;
		}else
			add(root.next[str.charAt(pos) - 'a'] , str , pos + 1 , idx);
	}

	public void dfs(TrieNode root , TrieNode cur , List<List<String>> result , List<String> tmp , int len , String[] words){

		if(tmp.size() > len) return;
		for(int i = 0 ; i < 26 ; i ++){
			if(cur.next[i] != null){
				for(Integer idx : cur.next[i].list){
					tmp.add(words[idx]);
					if(tmp.size() == len){
						ArrayList<String> a = new ArrayList<>();
						a.addAll(tmp);
						result.add(a);
						tmp.remove(tmp.size() - 1);
						continue;
					}
					TrieNode rt = root;
					boolean flag = true;
					for(int k = 0 ; k < tmp.size() ; k ++){
						if(rt.next[tmp.get(k).charAt(tmp.size()) - 'a'] == null){
							flag = false;
							break;
						}else{
							rt = rt.next[tmp.get(k).charAt(tmp.size()) - 'a'];
						}
					}

					if(flag){
						dfs(root , rt , result , tmp , len , words);
						tmp.remove(tmp.size() - 1);
					}else{
						tmp.remove(tmp.size() - 1);
					}
				}
			}
		}
	}

	public List<List<String>> wordSquares(String[] words) {
		List<List<String>> result = new ArrayList<List<String>>();
		if(words.length == 0) return result;
		TrieNode root = new TrieNode('.');
		for(int i = 0 ; i < words.length ; i ++){
			add(root , words[i] , 0 , i);
		}
		List<String> tmp = new ArrayList<String>();
		TrieNode cur = root;
		dfs(root , cur , result , tmp , words[0].length() , words);
		return result;
	}

	public int solve(String s , char cur , int k){
		int n = s.length();
		int idx = 0;
		int cnt = 0;
		int ans = 0;
		for(int i = 0 ; i < n ; i ++){
			if(s.charAt(i) == cur){
				cnt ++;
			}else{
				while(i - idx + 1 > cnt + k){
					if(s.charAt(idx) == cur){
						cnt --;
						idx ++;
					}else{
						idx ++;
					}
				}
			}
			ans = Math.max(ans , i - idx + 1);
		}
		return ans;
	}

	public int characterReplacement(String s, int k) {
		int ans = 0;
		for(int i = 0 ; i < 26 ; i ++){
			ans = Math.max(ans , solve(s , (char)('A'+i) , k));
		}
		return ans;
	}

	public void cal(int cnt[] , String str , char cur){
		for(int i = 0 ; i < str.length() ; i ++){
			if(str.charAt(i) != cur)
				cnt[str.charAt(i) - 'a'] -= cnt[cur - 'a'];
		}
		cnt[cur - 'a'] = 0;
	}

	public String originalDigits(String s) {
		int cnt[] = new int[26];
		for(int i = 0 ; i < s.length() ; i ++){
			cnt[s.charAt(i) - 'a'] ++;
		}
		String a[] = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};
		int num[] = new int[10];
		num[0] = cnt['z' - 'a'];
		cal(cnt , a[0] , 'z');

		num[6] = cnt['x' - 'a'];
		cal(cnt , a[6] , 'x');

		num[8] = cnt['g' - 'a'];
		cal(cnt , a[8] , 'g');

		num[2] = cnt['w' - 'a'];
		cal(cnt , a[2] , 'w');

		num[3] = cnt['h' - 'a'];
		cal(cnt , a[3] , 'h');

		num[4] = cnt['r' - 'a'];
		cal(cnt , a[4] , 'r');

		num[1] = cnt['o' - 'a'];
		cal(cnt , a[1] , 'o');

		num[5] = cnt['f' - 'a'];
		cal(cnt , a[5] , 'f');
		num[7] = cnt['v' - 'a'];
		cal(cnt , a[7] , 'v');
		num[9] = cnt['i' - 'a'];
		cal(cnt , a[9] , 'i');
		int sum = 0;
		for(int i = 0 ; i < 26 ; i ++){
			sum += cnt[i];
		}
		if(sum == 0){
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < 9 ; i ++){
				if(num[i] > 0)
					for(int j = 0 ; j < num[i] ; j ++){
						sb.append(i);
					}
			}
			return sb.toString();
		}else{
			return "false";
		}

	}

	public boolean validWordSquare(List<String> words) {
		for(int i = 0 ; i < words.size(); i ++){
			StringBuilder sb = new StringBuilder();
			for(int j = 0 ; j < words.size() ; j ++){
				if(i < words.get(j).length()){
					sb.append(words.get(j).charAt(i));
				}
			}
			if(!sb.toString().equals(words.get(i))) return false;
		}
		return true;
	}

	public void run(){
		Scanner scan = new Scanner(System.in);
		long a = System.currentTimeMillis();
		System.out.println(wordSquares(new String[]{"baffs","sloop","octad","brens","gammy","sones","perdy","myope","burbs","doeth","yirds","bolls","veils","pause","niton","kibbi","salal","redid","gaped","anise","derby","oinks","dormy","wagon","soupy","ephah","goody","foals","rusks","memos","pareo","levee","prosy","earls","gator","urged","welly","boils","wells","gecko","cline","gamps","shaft","jiver","eyers","packs","eased","adman","ackee","recce","defog","cadis","jives","germy","umbos","gypsy","miler","swive","clubs","plots","fille","dodos","sylph","indol","lodes","chore","flute","tauts","chams","poilu","folds","jimpy","yodhs","swang","thymi","spite","equal","wrack","roses","degas","etyma","yecch","phyle","ducks","scape","tepas","flams","nidal","sells","geest","talus","bilgy","weets","dorps","wolfs","bilks","spurn","rinse","serfs","iodid","hying","boast","leben","fussy","stour","eches","bored","cones","tiled","forms","purda","pants","whops","udder","coles","apeak","nippy","skimp","jolty","ilium","ordos","shews","title","grate","kibes","pryer","bleep","utile","orles","lamia","selva","meant","frump","gaurs","defat","ormer","cramp","cured","sloyd","bates","bowed","samek","knave","jupon","exons","plica","uncus","murrs","sowed","fishy","halid","nisus","fives","spays","quoth","rants","justs","sewed","orzos","click","kings","repay","grope","bring","teach","expos","supra","dight","lines","shred","chide","biked","fugue","manna","bruin","mouch","syces","sophs","retax","alary","litai","unrig","dwell","jells","thuja","ovals","snare","snide","abate","femme","numbs","fount","undid","pyxes","warms","fines","ansae","fards","nabis","garni","false","naric","hales","tores","kilty","rewon","grail","synth","vowel","sagas","progs","snags","genii","swath","pross","exine","jaups","desex","acmes","pilei","treed","genic","weeks","hosed","chela","suave","duple","anger","cohos","dahls","essay","grans","tarty","vines","shoal","north","hertz","pecks","fairs","hylas","ochry","parge","witan","shawm","nylon","plier","kolas","umiaq","zoeal","apple","abaca","miked","meths","karma","yummy","thorn","tuyer","nappe","duels","shame","rowdy","plush","toyos","batts","emirs","braxy","quilt","voles","ileus","husks","kinos","vying","segue","toker","wynds","scaly","tardy","preed","wonts","prune","ikons","asset","evoke","rosin","omasa","fados","jehad","vises","roues","laris","snack","bhuts","edify","cloys","newts","sonic","teams","kempt","peeve","biffs","cisco","begot","knout","pesos","soldi","appal","unwon","axles","shins","decay","betta","hence","gowan","froes","usury","early","sugar","sotol","boggy","roads","exult","cheap","shivs","stiff","whigs","ousel","livre","flush","jaunt","clued","venue","green","promo","gybes","vimen","amici","dumky","kalpa","oboli","chafe","parch","shrub","raped","magus","cozey","braze","ikats","skats","jupes","irate","gibed","pecky","evict","synod","plums","puffs","drupe","rumor","scrip","sappy","yells","shiel","beaux","apses","damps","lamed","gally","wines","skids","laked","blimp","spoil","beech","plage","mothy","bally","gager","flora","latke","secco","salve","peony","overt","tofus","yogic","finer","kinin","rakis","tunas","lolly","trash","boart","fordo","longs","lacer","momus","moory","picul","ament","cocci","taper","luged","scuff","azure","gaffe","tubby","opsin","twits","veldt","forgo","lotah","banes","murid","venin","sooty","bhang","allow","scour","lours","chubs","ureal","basts","argot","rehem","ichor","kerne","shear","copse","bathe","perky","onion","stade","bises","dopes","dexie","peens","letch","gamer","passe","sudds","prahu","yerks","hippy","oxeye","rills","gulpy","dulse","hokey","tammy","paler","cutin","kafir","tarps","zowie","eruct","upbow","armed","viced","rosed","strap","shuls","harps","whizz","epics","manos","elver","kefir","noter","bowls","kicks","pewee","fugus","hiker","vexil","tides","manes","crepy","relay","acres","snobs","kecks","vogie","dowel","theft","doozy","daffy","joked","pecan","zineb","chias","grain","point","spate","shalt","rayed","kolos","jurat","mooed","grows","scuba","skees","exams","tutti","aliya","wiles","pilot","netty","nabes","berme","poind","zones","coyed","abaci","panty","moved","peise","sughs","llano","volte","peppy","shies","mixed","whale","whity","bandy","layup","nodus","vigas","fifes","oared","daddy","waspy","dales","crony","pikes","reifs","kaiak","coted","hyper","monks","bravi","diner","slung","rishi","bawdy","steps","cusks","wiver","trips","slink","ambit","genie","dikes","faker","quags","savin","amids","desks","spook","gonof","whisk","hotel","brags","carny","queer","poler","pawns","toner","nowts","truce","pekes","reeds","baals","ouzel","dairy","hoers","sepal","butle","petit","mucky","liege","rajah","cades","bogie","esses","juicy","kelly","bogus","silly","dotes","direr","tucks","aglee","miffy","freak","dildo","maids","words","cakes","ocker","lurid","tophs","event","drams","leash","clags","plank","butts","ruffe","sends","actor","dials","mesas","caput","yearn","suite","yucca","naevi","swots","namer","sojas","along","crams","eyrie","brins","comer","blocs","spade","hexad","steel","moony","nyala","lathe","grego","titty","gleys","melic","aunty","sural","shool","avers","rides","getup","barbe","bluey","saute","globs","foods","amiga","triol","qaids","deign","leone","gawks","heigh","dexes","shits","lodge","dives","obits","ileum","whets","power","duked","helix","yogas","skeps","sluff","bleak","chess","spend","force","haver","snubs","punts","reuse","debye","cubit","buret","pepla","ziram","route","pined","still","spica","alifs","gazoo","balsa","papal","twier","didos","carns","gulls","bakes","brows","gulch","cadgy","jakes","ethic","tuffs","alley","ossia","faery","glial","tawny","carpi","smaze","champ","gated","quest","dines","middy","guilt","vitta","sicko","kaury","yills","soled","flics","tache","avgas","sages","belay","arils","erect","omits","mopes","weedy","spode","glove","stack","omens","years","roven","tythe","zloty","chirr","keeps","ridgy","mover","range","sutta","eight","misdo","issue","bebop","juked","turbo","tough","emits","prude","hogan","infer","quash","reefy","toros","annul","muser","turds","flans","clomp","fails","equip","oasis","covey","iodic","tense","chimp","whaup","lores","spake","sasin","those","lough","lacey","skegs","heist","loved","times","sulks","beige","potto","zerks","dryer","towed","dined","cerci","flung","fluke","elemi","schmo","phons","dropt","bales","argle","carom","whort","gloze","hymns","twirl","amiss","atmas","cates","pukes","craps","sirup","balks","domes","retag","dorky","septa","spiks","shads","gemma","beets","inlet","ropes","aroma","chits","thuds","peels","fogie","stela","mythy","drier","thrip","burst","salad","rainy","bilge","woful","prick","props","stock","ofays","hosel","areic","clang","sexes","pyxie","haled","nawab","boche","fanos","aurum","tours","funky","dynes","raias","hexyl","welch","shyly","hemic","shade","netts","fiefs","topis","muton","nicol","updos","jubes","blite","xebec","swear","moult","pagod","cecum","rewet","nopal","holds","ruana","meany","juice","cobbs","mated","helms","dared","muley","bails","tench","blimy","tepoy","duroc","helps","quasi","stake","galas","mizen","spics","scudi","stook","pleas","hooly","gowks","hocks","hooey","octan","modal","minny","gaudy","neats","calfs","troop","amaze","zebra","jemmy","berms","shent","spasm","batty","menad","porks","jests","miaou","busty","buran","endow","juror","arias","stall","carry","tarre","colog","grout","ixias","alecs","resee","jowly","lochs","nines","yangs","byssi","sixty","gyros","tools","every","penes","dinks","storm","arles","chiro","waler","masts","gluts","siver","antre","treat","flair","lurks","overs","ratal","audit","tipis","vroom","spank","moors","paves","vinos","ghost","opine","toras","cauld","mokes","antsy","liman","outer","mufti","gores","baled","urine","minds","sorbs","hefty","cozen","musky","booed","wrier","jibes","reify","block","rebus","atoms","risus","zetas","dauby","rayas","razee","sagos","roset","yarer","fetes","award","filth","credo","gutsy","inter","yards","obeah","waist","oyers"}));
//		System.out.println(wordSquares(new String[]{"abat","baba","atan","atal"}));
		long b = System.currentTimeMillis();
		System.out.println( b - a );
	}

	public static void main(String args[]){
		new Solution().run();
	}
}
