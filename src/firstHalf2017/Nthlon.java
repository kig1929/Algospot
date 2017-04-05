package firstHalf2017;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nthlon {
	public static int INFINITE = (int)Math.pow(2, 31);
	public static int m[][];
	public static int shift = 199;
	public static int min;
	public static int max;
	public static void main(String args[]) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Nthlon.txt")));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			min = 1;
			max = -1;
			m = new int[Integer.parseInt(br.readLine().trim())][2];
			for(int i=0;i<m.length;i++){
				String[] temp = br.readLine().trim().split(" ");
				m[i][0] = Integer.parseInt(temp[0]);
				m[i][1] = Integer.parseInt(temp[1]);
				if(min > m[i][0]-m[i][1])
					min = m[i][0]-m[i][1];
				if(max < m[i][0]-m[i][1])
					max = m[i][0]-m[i][1];
			}
			if(min*max>0)
				System.out.println("IMPOSSIBLE");
			else{
				Nthlon nthlon = new Nthlon();
				nthlon.makeGraph();
				System.out.println(nthlon.searchGraph());
			}
		}
	}
	
	public int adj[][]; //[state][state] = 종목 번호
	public boolean visited[];
	public void makeGraph(){
		adj = new int[399][399];
		visited = new boolean[399];
		for(int i=0;i<adj.length;i++)
			Arrays.fill(adj[i], -1);
		Arrays.fill(visited, false);
		List<Integer> q = new ArrayList<Integer>();
		q.add(0+shift);
		while(!q.isEmpty()){
			int here = q.get(0); //현재의 state//A-B
			q.remove(0);
			if(visited[here]) continue;
			for(int i=0;i<m.length;i++){
				int there = here + m[i][0] - m[i][1];
				if(there>max+shift || there<min+shift || 
						(adj[here][there] != -1 && m[adj[here][there]][0] < m[i][0])) continue;
				adj[here][there] = i;
				if(!visited[there]) q.add(there);
			}
			visited[here] = true;
		}
	}
	
	public int time[]; //state까지 걸린 최소 시간
	public int searchGraph(){
		time = new int[adj.length];
		Arrays.fill(time, INFINITE);
		Map<Integer, Integer> q = new HashMap<Integer, Integer>();
		q.put(0+shift,0);
		while(!q.isEmpty()){
			int here = getMinIdx(q);
			int cost = q.get(here);
			q.remove(here);
//			if(time[0+shift] != INFINITE && time[0+shift] > 0) break;
			if(time[here] < cost) continue;
			for(int i=0;i<adj.length;i++){
				int there = i;
				if(adj[here][there] >= 0){ //인접할 경우
					int nextCost = cost + m[adj[here][there]][0];
					if(nextCost < time[there]){
						try{
							if(q.get(there) > nextCost){
								time[there] = nextCost; //A의 총 경과시간
								q.put(there, time[there]);
							}
						}catch(NullPointerException e){
							time[there] = nextCost; //A의 총 경과시간
							q.put(there, time[there]);
						}
					}
				}
			}
		}
		return time[0+shift];
	}
	
	public int getMinIdx(Map<Integer, Integer> q){
		int minIdx=0;
		Object[] key = q.keySet().toArray();
		for(int i=0;i<key.length;i++)
			if(q.get(key[minIdx]) > q.get(key[i]))
				minIdx = i;
		return (int)key[minIdx];
	}
}
