package firstHalf2017;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Dictionary {
	public static int n;
	public static String[] word;
	public static boolean[][] adj = new boolean[26][26];
	public static boolean[] visited = new boolean[26];
	public static int idx;
	public static Map<Integer, Integer> order;
	
	public static void main(String[] args) throws Exception{
//		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("Dictionary.txt"));
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Dictionary.txt")));
//		int TC = sc.nextInt();
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
//			n=sc.nextInt();
			n=Integer.parseInt(br.readLine().trim());
			word = new String[n];
			for(int i=0;i<n;i++)
				word[i] = br.readLine().trim();
//				word[i] = sc.next();
			for(int i=0;i<26;i++)
				Arrays.fill(adj[i], false);
			Arrays.fill(visited, false);
			order = new HashMap<Integer, Integer>();
			idx=25;
			
			Dictionary dic = new Dictionary();
			dic.makeGraph();
			System.out.println(dic.runDfs());
		}
	}
	
	public void makeGraph(){
		for(int i=1;i<n;i++){	//´Ü¾î
			for(int j=0;j<Math.min(word[i-1].length(), word[i].length());j++){
				if(word[i-1].charAt(j) != word[i].charAt(j)){
					adj[word[i-1].charAt(j)-'a'][word[i].charAt(j)-'a']=true;
					break;
				}
			}
		}
	}
	
	public void dfs(int k){
		visited[k] = true;
		for(int i=0;i<adj[k].length;i++){
			if(!visited[i] && adj[k][i])
				dfs(i);
		}
		order.put(idx--, k);
	}
	
	public String runDfs(){
		for(int i=0;i<26;i++){
			if(!visited[i]) dfs(i);
		}
		Map<Integer, Integer> reversedOrder = new TreeMap<Integer, Integer>(order);
		for(int i=0;i<26;i++){
			for(int j=i+1;j<26;j++)
				if(adj[reversedOrder.get(j)][reversedOrder.get(i)])
					return "INVALID HYPOTHESIS";
		}
		String res="";
		for(int i=0;i<26;i++)
			res += (char)(reversedOrder.get(i)+'a');
		return res;
	}
}
