package firstHalf2017;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Adjacency{
	Map<String, Double> adj;
	public Adjacency(){
		adj = new HashMap<String, Double>();
	}
	
	public double getAdj(int a, int b){
		return adj.get(getStr(a, b));
	}
	
	public void putAdj(int a, int b, double c){
		try{
			double temp = getAdj(a, b); 
			adj.put(getStr(a, b), min(temp,c)); //원래 있음
		}catch(NullPointerException e){ 
			adj.put(getStr(a, b), c); //처음
		}
	}
	
	public int max(int a, int b){
		return a>b?a:b;
	}
	
	public int min(int a, int b){
		return a>b?b:a;
	}
	
	public double min(double a, double b){
		return a>b?b:a;
	}
	
	public String getStr(int a, int b){
		int min = min(a,b);
		int max = max(a,b);
		return min+","+max;
	}
}
public class Routing {
	public static final double infinite = Math.pow(10, 300);
	public static int n;
	public static int m;
	public static Map<Integer, HashMap<Integer, Double>> adj = new HashMap<Integer, HashMap<Integer, Double>>();
	public static void main(String args[]) throws Exception{
//		long start = System.nanoTime();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Routing.txt")));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			Routing rt = new Routing();
			String temp[] = br.readLine().trim().split(" ");
			n = Integer.parseInt(temp[0]);
			m = Integer.parseInt(temp[1]);
//			Adjacency adj = new Adjacency();
			for(int i=0;i<m;i++){
				temp = br.readLine().trim().split(" ");
				int a = Integer.parseInt(temp[0]);
				int b = Integer.parseInt(temp[1]);
				double c = Double.parseDouble(temp[2]);
//				adj.putAdj(a, b, c);
				for(int j=0;j<2;j++){
					if(adj.get(a) == null){
						HashMap<Integer, Double> tt = new HashMap<Integer, Double>();
						tt.put(b, c);
						adj.put(a, tt);
					}else if(adj.get(a).get(b) == null){
						adj.get(a).put(b, c);
					}else{
						adj.get(a).put(b, rt.min(adj.get(a).get(b), c));
					}
					int t=a;
					a=b; b=t;
				}
			}
			
			System.out.format("%.10f\n", rt.dijkstra());
//			System.out.format("%.10f\n", 1.0);
//			long end = System.nanoTime();
//			System.out.println(end-start);
		}
	}
	
	
	public double dijkstra(){
		double weight[] = new double[n];
		Arrays.fill(weight, infinite);
		boolean visited[] = new boolean[n];
		Arrays.fill(visited, false);
		weight[0] = 1.0;
		visited[0] = false;
		while(true){
			int here=-1;
			double closest = infinite;
			for(int i=0;i<n;i++){
				if(weight[i]<closest && !visited[i]){
					here = i;
					closest = weight[i];
				}
			}
			if(closest == infinite) break;
			visited[here] = true;
			Object[] keySet = adj.get(here).keySet().toArray();
			for(Object obj : keySet){
				int there = (int)obj;
				if(visited[there]) continue;
				double next = weight[here]*adj.get(here).get((int)obj);
				weight[there] = min(next, weight[there]);
			}
		}
		return weight[n-1];
	}
	
	public int max(int a, int b){
		return a>b?a:b;
	}
	
	public int min(int a, int b){
		return a>b?b:a;
	}
	
	public double min(double a, double b){
		return a>b?b:a;
	}
}

//public int arrMinIdx(List<Integer> q, double[] weight){ //q 안의 값
//	int minIdx = -1;
//	double minVal = infinite;
//	for(int i=0;i<q.size();i++){
//		if(minVal > weight[q.get(i)]){
//			minIdx = q.get(i);
//			minVal = weight[q.get(i)];
//		}
//	}
//	return minIdx;
//}
//List<Integer> q = new ArrayList<Integer>();
//q.add(0);
//weight[0] = 1.0;
//
//while(!q.isEmpty()){
//	int idx = arrMinIdx(q, weight); //weight가 제일 작은 노드
//	q.remove(q.indexOf(idx));
//	Object[] keySet = adj.get(idx).keySet().toArray();
//	for (Object obj : keySet) {
//		double next = weight[idx]*adj.get(idx).get((int)obj); 
//		if(next < weight[(int)obj]){
//			weight[(int)obj] = next;
//			q.add((int)obj);
//		}
//	}
//}



//public static Map<String, Double> adjc;
////public static double adj[][];
//public static void main(String args[]) throws Exception{
////	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Routing.txt")));
//	int TC = Integer.parseInt(br.readLine().trim());
//	for(int test_case=0;test_case<TC;test_case++){
//		Routing rt = new Routing();
//		String temp[] = br.readLine().trim().split(" ");
//		n = Integer.parseInt(temp[0]);
//		m = Integer.parseInt(temp[1]);
////		adj = new double[n][n];
//		adjc = new TreeMap<String, Double>();
////		for(int i=0;i<n;i++)
////			Arrays.fill(adj[i], infinite);
//		for(int i=0;i<m;i++){
//			temp = br.readLine().trim().split(" ");
//			int a = Integer.parseInt(temp[0]);
//			int b = Integer.parseInt(temp[1]);
//			String str = rt.min(a, b) + "/" + rt.max(a, b);
//			try{
//				adjc.get(str);
//				adjc.put(str, rt.min(adjc.get(str), Double.parseDouble(temp[2])));
//			}catch(Exception e){
//				adjc.put(str, Double.parseDouble(temp[2]));
//			}
////			adj[rt.min(a,b)][rt.max(a,b)] = 
////					rt.min(adj[rt.min(a,b)][rt.max(a, b)], Double.parseDouble(temp[2])); //adj[min][max]
//		}
//		
//		System.out.format("%.10f\n", rt.dijkstra());
////		System.out.format("%.10f\n", 1.0);
//	}
//}
//
//
//public double dijkstra(){
//	List<Integer> q = new ArrayList<Integer>();
//	double weight[] = new double[n];
//	Arrays.fill(weight, infinite);
//	q.add(0);
//	weight[0] = 1.0;
//	while(!q.isEmpty()){
//		int idx = arrMinIdx(q, weight); //weight가 제일 작은 노드
//		q.remove(q.indexOf(idx));
//		for(int i=0;i<n;i++){//인접한 노드 모두 찾기
//			int min = min(idx,i);
//			int max = max(idx,i);
//			String str = min + "/" + max;
////			if(adj[min][max]>=1){
//			try{
//				if(adjc.get(str)>=1){
//					double next = weight[idx]*adjc.get(str); 
//					if(next < weight[i]){
//						weight[i] = next;
//						q.add(i);
//					}
//				}
//			}catch(Exception e){
//				
//			}
//			
//		}
//	}
//	
//	return weight[n-1];
//}
