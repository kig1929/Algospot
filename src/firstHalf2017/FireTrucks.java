package firstHalf2017;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FireTrucks {
	public static final int INFINITE = (int)Math.pow(10, 30);
	public static int v;
	public static int e;
	public static int[] n;
	public static int[] m;
	public static int[][] adj;
	
	public static void main(String args[]) throws Exception{
//		long start = System.nanoTime();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("FireTrucks.txt")));
		int TC = Integer.parseInt(br.readLine().trim());
		FireTrucks ft = new FireTrucks();
		for(int test_case=0;test_case<TC;test_case++){
			String temp[] = br.readLine().trim().split(" ");
			v = Integer.parseInt(temp[0]);
			e = Integer.parseInt(temp[1]);
			n = new int[Integer.parseInt(temp[2])];
			m = new int[Integer.parseInt(temp[3])];
			adj = new int[v+1][v+1];
			for(int i=0;i<adj.length;i++)
				Arrays.fill(adj[i], INFINITE);
			for(int i=0;i<e;i++){
				temp = br.readLine().trim().split(" ");
				int a = Integer.parseInt(temp[0])-1;
				int b = Integer.parseInt(temp[1])-1;
				adj[a][b] = Integer.parseInt(temp[2]);
				adj[b][a] = adj[a][b];
			}
			
			temp = br.readLine().trim().split(" ");
			for(int i=0;i<n.length;i++)
				n[i] = Integer.parseInt(temp[i])-1;
			temp = br.readLine().trim().split(" ");
			for(int i=0;i<m.length;i++){
				m[i] = Integer.parseInt(temp[i])-1;
				adj[v][m[i]] = 0;
				adj[m[i]][v] = 0;
			}
			
			ft.findShortestPath();
			int result = 0;
			for(int i=0;i<n.length;i++)
				result += dist[n[i]];
			System.out.println(result);
		}
//		long end = System.nanoTime();
//		System.out.println((end-start));
	}
	
	public static int[] dist;
	public void findShortestPath(){
		List<Integer> q = new ArrayList<Integer>();
		dist = new int[v+1];
		Arrays.fill(dist, INFINITE);
		q.add(v);
		dist[v] = 0;
//		for(int fs=0;fs<m.length;fs++){ //소방서
//			q.add(m[fs]);
//			dist[m[fs]] = 0;
//		}
		while(!q.isEmpty()){
			int here = Collections.min(q);
			q.remove(q.indexOf(here));
			for(int there=0;there<v;there++){
//				if(adj[here][there] >= 1){ //인접한 장소
				if(adj[here][there] < INFINITE){ //인접한 장소
					int next = dist[here] + adj[here][there];
					if(dist[there] > next){
						dist[there] = next;
						try{ //there가 q에 있다.
							if(q.get(there) > dist[there])
								q.add(there);
						}catch(IndexOutOfBoundsException e){ //there가 q에 없다.
							q.add(there);
						}
					}
				}
			}
		}
	}
}

//public void findShortestPath(){
////	Map<Integer, Integer> q = new TreeMap<Integer, Integer>();
//	List<Integer> q = new ArrayList<Integer>();
//	dist = new int[v];
//	Arrays.fill(dist, INFINITE);
//	for(int fs=0;fs<m.length;fs++){ //소방서
//		q.add(m[fs]);
////		q.put(m[fs], 0);
//		dist[m[fs]] = 0;
//	}
//	while(!q.isEmpty()){
////		Object[] arr = q.keySet().toArray();
////		int here = (int)arr[0];
////		Collections.sort(q);
////		int here = q.get(0);
////		q.remove(0);
//		int here = Collections.min(q);
//		q.remove(q.indexOf(here));
//		for(int there=0;there<v;there++){
////				if(adj[min(here, there)][max(here,there)] >= 1){ //인접한 장소
//			if(adj[here][there] >= 1){ //인접한 장소
////					int next = dist[here] + adj[min(here, there)][max(here,there)];
//				int next = dist[here] + adj[here][there];
//				if(dist[there] > next){
//					dist[there] = next;
//					try{ //there가 q에 있다.
//						if(q.get(there) > dist[there])
//							q.add(there);
////							q.put(there, dist[there]);
//					}catch(IndexOutOfBoundsException e){ //there가 q에 없다.
//						q.add(there);
////						q.put(there, dist[there]);
//					}
//				}
//			}
//		}
//	}
////	}
//}


//public int max(int a, int b){
//	return a>b?a:b;
//}
//
//public int min(int a, int b){
//	return a>b?b:a;
//}