package firstHalf2017;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TimeTrip {
	public static final int NONE = 1001;
	public static final int N_NONE = -1001;
	public static final long INFINITE = (long)Math.pow(2, 30);
	public static int v;
	public static int w;
	public static void main(String args[]) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("TimeTrip.txt")));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			TimeTrip tt = new TimeTrip();
			String temp[] = br.readLine().trim().split(" ");
			v = Integer.parseInt(temp[0]);
			w = Integer.parseInt(temp[1]);
			int[][] minAdj = new int[v][v];
			int[][] maxAdj = new int[v][v];
			for(int i=0;i<minAdj.length;i++){
				Arrays.fill(minAdj[i], NONE);
				Arrays.fill(maxAdj[i], N_NONE);
			}
			for(int i=0;i<w;i++){
				temp = br.readLine().trim().split(" ");
				int a = Integer.parseInt(temp[0]);
				int b = Integer.parseInt(temp[1]);
				int c = Integer.parseInt(temp[2]);
				minAdj[a][b] = tt.min(minAdj[a][b], c);
				maxAdj[a][b] = tt.max(maxAdj[a][b], c);
			}
			
			String res = tt.BellmanFord(1, minAdj);
			if(!res.equals("UNREACHABLE"))
				res += " " + tt.BellmanFord(-1, maxAdj);
			System.out.println(res);
		}
	}
	
	public int min(int a, int b){
		return a<b?a:b;
	}
	public int max(int a, int b){
		return a>b?a:b;
	}
	
	public static long dist[];
	public String BellmanFord(int sign, int[][] adj){
		dist = new long[v];
		Arrays.fill(dist, INFINITE);
		dist[0] = 0; //init
		for(int iter=0;iter<v-1;iter++){ //노드 순서가 역순일 경우 V-1회 수행해야 전체 값을 알 수 있다.
			for(int here = 0;here<v;here++){
				for(int there=0;there<v;there++){
					if(sign*adj[here][there] != NONE && 
							dist[here] != INFINITE && dist[there] > (dist[here] + sign*adj[here][there])){
						dist[there] = dist[here] + sign*adj[here][there];
					}
				}
			}
		}
		
		if(dist[1] >= INFINITE)
			return "UNREACHABLE"; 
			
		long preMin = dist[1];
		for(int iter=0;iter<v-1;iter++){ 
			for(int here = 0;here<v;here++){
				for(int there=0;there<v;there++){
					if(sign*adj[here][there] != NONE && 
							dist[here] !=INFINITE && dist[there] > (dist[here] + sign*adj[here][there])){
						dist[there] = dist[here] + sign*adj[here][there];
					}
				}
			}
		}
		if(dist[1] == preMin)
			return sign*dist[1]+"";
		else
			return "INFINITY";
	}
}

//package firstHalf2017;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//
//public class Main {
//	public static final int NONE = 1001;
//	public static final int N_NONE = -1001;
//	public static final long INFINITE = (long)Math.pow(2, 30);
//	public static final int UNREACHABLE = 1;
//	public static final int INFINITY = 2;
//	public static int v;
//	public static int w;
//	public static void main(String args[]) throws Exception{
////		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("TimeTrip.txt")));
//		int TC = Integer.parseInt(br.readLine().trim());
//		for(int test_case=0;test_case<TC;test_case++){
//			Main tt = new Main();
//			String temp[] = br.readLine().trim().split(" ");
//			v = Integer.parseInt(temp[0]);
//			w = Integer.parseInt(temp[1]);
//			int[][] minAdj = new int[v][v];
//			int[][] maxAdj = new int[v][v];
//			for(int i=0;i<minAdj.length;i++){
//				Arrays.fill(minAdj[i], NONE);
//				Arrays.fill(maxAdj[i], N_NONE);
//			}
//			for(int i=0;i<w;i++){
//				temp = br.readLine().trim().split(" ");
//				int a = Integer.parseInt(temp[0]);
//				int b = Integer.parseInt(temp[1]);
//				minAdj[a][b] = tt.min(minAdj[a][b], Integer.parseInt(temp[2]));
//				maxAdj[a][b] = tt.max(maxAdj[a][b], Integer.parseInt(temp[2]));
//			}
//			tt.setReachable(minAdj);
//			String res = tt.BellmanFord(1, minAdj);
//			if(!res.equals("UNREACHABLE"))
//				res += " " + tt.BellmanFord(-1, maxAdj);
//			System.out.println(res);
//		}
//	}
//	
//	public int min(int a, int b){
//		return a<b?a:b;
//	}
//	public int max(int a, int b){
//		return a>b?a:b;
//	}
//	
//	public static long dist[];
//	public static boolean reachable[][];
//	public String BellmanFord(int sign, int[][] adj){
//		dist = new long[v];
//		Arrays.fill(dist, INFINITE);
//		dist[0] = 0; //init
//		for(int iter=0;iter<v-1;iter++){ //노드 순서가 역순일 경우 V-1회 수행해야 전체 값을 알 수 있다.
//			for(int here = 0;here<v;here++){
//				for(int there=0;there<v;there++){
//					if(sign*adj[here][there] != NONE && dist[there] > (dist[here] + sign*adj[here][there])){
//						dist[there] = dist[here] + sign*adj[here][there];
//					}
//				}
//			}
//		}
//		
//		for(int here = 0;here<v;here++){
//			for(int there=0;there<v;there++){
//				if(sign*adj[here][there] != NONE && dist[there] > (dist[here] + sign*adj[here][there])){
//					if(reachable[0][here] && reachable[there][1])
//						return "INFINITY";
//				}
//			}
//		}
//		
//		if(dist[1] >= INFINITE/2)
//			return "UNREACHABLE";
//		else
//			return sign*dist[1]+"";
//	}
//	
//	public void setReachable(int[][] adj){
//		reachable = new boolean[v][v];
//		for(int i=0;i<reachable.length;i++)
//			Arrays.fill(reachable[i], false);
//		for(int here=0;here<v;here++)
//			for(int there=0;there<v;there++)
//				if(adj[here][there] != NONE)
//					reachable[here][there] = true;
//		for(int iter=0;iter<v;iter++)
//			for(int here=0;here<v;here++)
//				for(int there=0;there<v;there++)
//					if(reachable[here][there])
//						for(int far=0;far<v;far++)
//							if(reachable[there][far])
//								reachable[here][far]=true;
//	}
//}

