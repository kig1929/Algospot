package firstHalf2017;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wordchain {
	public static int n;
	public static String[] word;
	public static int[][] adj = new int[26][26];
	public static String[] result;
	public static int idx;
	public static Map<String, List<String>> wordState;
	
	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Wordchain.txt")));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			n = Integer.parseInt(br.readLine().trim());
			word = new String[n];
			for(int i=0;i<n;i++)
				word[i] = br.readLine().trim();
			result = new String[n];
			for(int i=0;i<26;i++)
				Arrays.fill(adj[i], 0);
			wordState = new HashMap<String, List<String>>();
			idx = 0;
			
			Wordchain wc = new Wordchain();
			wc.makeGraph();
			String flag = wc.runDfs();
			if(flag == "POSSIBLE")
				for(int i=idx-1;i>=0;i--)
					System.out.print(result[i] + " ");
			else
				System.out.print(flag);
			System.out.println();
		}
	}
	
	public void makeGraph(){
		for(int i=0;i<n;i++){
			adj[word[i].charAt(0)-'a'][word[i].charAt(word[i].length()-1)-'a']++; 
			String key = word[i].charAt(0) + "" + word[i].charAt(word[i].length()-1);
			if(wordState.get(key) == null){
				List<String> l = new ArrayList<String>();
				l.add(word[i]);
				wordState.put(key, l);
			}else{
				wordState.get(key).add(word[i]);
			}
		}
	}
	
	public void dfs(int k){
		for(int i=0;i<26;i++){
			if(adj[k][i] > 0){
				int preAdj = --adj[k][i];
				dfs(i);
				result[idx++] = wordState.get((char)(k+'a') + "" + (char)(i+'a')).get(preAdj);
			}
		}
		
	}
	
	public String runDfs(){
		int start = -1;
		for(int i=0;i<26;i++)
			if(getRowSum(i) - getColSum(i) == 1)
				start = i;
		if(start >= 0)
			dfs(start);
		else{
			for(int i=0;i<26;i++)
				if(getRowSum(i)>0){
					dfs(i);
					break;
				}
		}
		
		if(result[n-1] == null)
			return "IMPOSSIBLE";
		else
			return "POSSIBLE";
	}
	
	public int getRowSum(int k){ //k th row
		int sum = 0;
		for(int i=0;i<26;i++)
			sum += adj[k][i]; 
		return sum;
	}
	
	public int getColSum(int k){ //k th row
		int sum = 0;
		for(int i=0;i<26;i++)
			sum += adj[i][k]; 
		return sum;
	}
}
