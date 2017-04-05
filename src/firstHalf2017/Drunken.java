package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Drunken {
	public static final int INFINITE = Integer.MAX_VALUE/4;
	public static int v;
	public static int e;
	public static int[][] adj;
	public static int[][] W;
	public static int s;
	public static int d;
	public static void main(String args[]) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Drunken.txt")));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String temp[] = br.readLine().trim().split(" ");
		v = Integer.parseInt(temp[0]);
		e = Integer.parseInt(temp[1]);
		adj = new int[v][v];
		W = new int[v][v];
		for(int i=0;i<adj.length;i++){
			Arrays.fill(adj[i], INFINITE);
			Arrays.fill(W[i], INFINITE);
		}
		temp = br.readLine().trim().split(" ");
		Map<Integer, Integer> delay = new HashMap<Integer, Integer>();
		for(int i=0;i<v;i++)
			delay.put(i, Integer.parseInt(temp[i]));
		for(int i=0;i<e;i++){
			temp = br.readLine().trim().split(" ");
			int a = Integer.parseInt(temp[0]) - 1;
			int b = Integer.parseInt(temp[1]) - 1;
			adj[a][b] = adj[b][a] = W[a][b] = W[b][a] = Integer.parseInt(temp[2]);
		}
		Drunken drunken = new Drunken();
		drunken.floyd(delay);
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			temp = br.readLine().trim().split(" ");
			s = Integer.parseInt(temp[0]) - 1;
			d = Integer.parseInt(temp[1]) - 1;
			bw.write(W[s][d] + "\n");
			bw.flush();
		}
	}
	
	public void floyd(Map<Integer, Integer> unsortedDelay){
//		HashMap<Integer, Integer> delay = sortByValues(unsortedDelay);
		HashMap<Integer, Integer> delay = (HashMap<Integer, Integer>) unsortedDelay;
		Object[] idx = delay.keySet().toArray();
		for(int i=0;i<v;i++)
			W[i][i] = 0;
		System.out.println(unsortedDelay);
		for(int k=0;k<v;k++){
			int w = (int)idx[k];
			for(int i=0;i<v;i++){
				for(int j=0;j<v;j++){
					adj[i][j] = min(adj[i][j], adj[i][w] + adj[w][j]);
					W[i][j] = min(W[i][j], adj[i][w] + delay.get(w) + adj[w][j]);
				}
			}
		}
	}	
	
	public int min(int a, int b){
		return a<b?a:b;
	}
	
	public int max(int a, int b){
		return a>b?a:b;
	}
	
	private static HashMap sortByValues(Map map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
}

////kAdj
//for(int k=0;k<v.length;k++)
//	for(int i=0;i<v.length;i++)
//		for(int j=0;j<v.length;j++){
//			if(k==i || k==j) continue;
//			if(kAdj[i][j] > adj[i][k] + adj[k][j] + v[k])
//				kAdj[i][j] = adj[i][k] + adj[k][j] + v[k];
//		}
//for(int i=0;i<v.length;i++)
//	kAdj[i][i] = 0;
//}



//package firstHalf2017;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.plaf.ListUI;
//
//public class Main {
//	public static final int INFINITE = Integer.MAX_VALUE/2;
//	public static int[] v;
//	public static int e;
//	public static int[][] adj;
//	public static int[][] kAdj;
//	public static int s;
//	public static int d;
//	public static int[][] via;
//	public static void main(String args[]) throws Exception{
////		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Drunken.txt")));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		String temp[] = br.readLine().trim().split(" ");
//		v = new int[Integer.parseInt(temp[0])];
//		e = Integer.parseInt(temp[1]);
//		adj = new int[v.length][v.length];
//		kAdj = new int[v.length][v.length];
//		via = new int[v.length][v.length];
//		for(int i=0;i<adj.length;i++){
//			Arrays.fill(adj[i], INFINITE);
//			Arrays.fill(kAdj[i], INFINITE);
//			Arrays.fill(via[i], -1);
//		}
//		temp = br.readLine().trim().split(" ");
//		for(int i=0;i<v.length;i++)
//			v[i] = Integer.parseInt(temp[i]);
//		for(int i=0;i<e;i++){
//			temp = br.readLine().trim().split(" ");
//			int a = Integer.parseInt(temp[0]) - 1;
//			int b = Integer.parseInt(temp[1]) - 1;
//			adj[a][b] = adj[b][a] = kAdj[a][b] = kAdj[b][a] = Integer.parseInt(temp[2]);
//		}
//		Main drunken = new Main();
//		drunken.floyd();
//		int TC = Integer.parseInt(br.readLine().trim());
//		for(int test_case=0;test_case<TC;test_case++){
//			temp = br.readLine().trim().split(" ");
//			s = Integer.parseInt(temp[0]) - 1;
//			d = Integer.parseInt(temp[1]) - 1;
//			bw.write(drunken.calcPath(s, d) + "\n");
//			bw.flush();
//		}
//	}
//	
//	public void floyd(){
//		//adj 찾기 - 단속 없음
//		for(int k=0;k<v.length;k++)
//			for(int i=0;i<v.length;i++)
//				for(int j=0;j<v.length;j++)
//					if(adj[i][j] > adj[i][k] + adj[k][j]){
//						adj[i][j] = adj[i][k] + adj[k][j];
//						via[i][j] = k;
//					}
//	}	
//	
//	public ArrayList<Integer> searchPath(int src, int dest, ArrayList<Integer> path){
//		if(via[src][dest] == -1){
//			path.add(src);
//			if(src != dest) path.add(dest);
//		}else{
//			int w = via[src][dest];
//			searchPath(src, w, path);
//			path.remove(path.size()-1);
//			searchPath(w, dest, path);
//		}
//		return path;
//	}
//	
//	public int calcPath(int src, int dest){
//		Map<ArrayList<Integer>, Integer> pathMap = new HashMap<ArrayList<Integer>, Integer>();
//		for(int k=0;k<v.length;k++){
//			if(k==src || k==dest) continue;
//			ArrayList<Integer> path = new ArrayList<Integer>();
//			path = searchPath(src, k, path);
//			path.remove(path.size()-1);
//			path = searchPath(k, dest, path);
//			if(pathMap.get(path) == null){ //처음 가는 경로
//				pathMap.put(path, adj[src][k] + adj[k][dest] + v[k]);
//			}else{ //다른 경유점을 통해 가본 경로
//				pathMap.put(path, max(pathMap.get(path), adj[src][k] + adj[k][dest] + v[k]));
//			}
//			int a=0;
//			a++;
//		}
//		return Collections.min(pathMap.values());
//	}
//	
//	public int min(int a, int b){
//		return a<b?a:b;
//	}
//	
//	public int max(int a, int b){
//		return a>b?a:b;
//	}
//}
//
//////kAdj
////for(int k=0;k<v.length;k++)
////	for(int i=0;i<v.length;i++)
////		for(int j=0;j<v.length;j++){
////			if(k==i || k==j) continue;
////			if(kAdj[i][j] > adj[i][k] + adj[k][j] + v[k])
////				kAdj[i][j] = adj[i][k] + adj[k][j] + v[k];
////		}
////for(int i=0;i<v.length;i++)
////	kAdj[i][i] = 0;
////}
//
