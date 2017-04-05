package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Packing {
	public static final int MINUS_INFINITE = Integer.MIN_VALUE/2; 
	public static int n;
	public static int w;
	public static String[] unsorted;
	public static Item[] item;
	public static int[][] chosen;
	public static int[][] cache;
	public static List<Integer> desiredListIdx;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Packing.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_case = Integer.parseInt(br.readLine().trim());
		while(test_case --> 0){
			String temp[] = br.readLine().trim().split(" ");
			n = Integer.parseInt(temp[0]);
			w = Integer.parseInt(temp[1]);
			unsorted = new String[n];
			item = new Item[n];
			for(int i=0;i<item.length;i++){
				temp = br.readLine().trim().split(" ");
				item[i] = new Item(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
				unsorted[i] = item[i].name;
			}
			chosen = new int[w+1][n+1];
			cache = new int[w+1][n+1];
			for(int i=0;i<chosen.length;i++){
				Arrays.fill(chosen[i], -1);
				Arrays.fill(cache[i], -1);
			}
			desiredListIdx = new ArrayList<Integer>();
			desiredListIdx.clear();
			
			Packing pack = new Packing();
//			pack.sortItem();
			int maxDesired = pack.maxDesired(w, -1);
			int cnt = pack.getDesiredList(w, -1);
			bw.write(maxDesired + " " + cnt + "\n");
			for (int idx : desiredListIdx) 
				if(idx>=0) bw.write(item[idx].name + "\n");
		}
		bw.flush();
	}
	
	public int maxDesired(int restW, int idx){
		if(restW == 0) return 0;
		if(restW < 0) return MINUS_INFINITE;
		if(cache[restW][idx+1] != -1) return cache[restW][idx+1];
		int ret = 0, cand = 0;
		for(int next=idx+1;next<n;next++){
			cand = maxDesired(restW - item[next].volume, next) + item[next].desired;
			if(cand > ret){
				ret = cand;
				chosen[restW][idx+1] = next;
			}
		}
		return cache[restW][idx+1] = ret;
	}
	
	public int getDesiredList(int restW, int idx){ //최대 절박도일 때의 물건 개수 반환
		int cnt = 0;
		int next = chosen[restW][idx+1];
		if(next != -1){
			cnt = getDesiredList(restW - item[next].volume, next) + 1;
			desiredListIdx.add(next);
		}
		return cnt;
	}
	
//	public void sortItem(){
//		for(int i=0;i<item.length;i++)
//			for(int j=i+1;j<item.length;j++)
//				if(item[i].prob < item[j].prob)
//					Item.changePos(i, j);
//	}
	
	static class Item{
		String name;
		int volume;
		int desired;
		double prob;
		
		Item(String name, int volume, int desired){
			this.name = name;
			this.volume = volume;
			this.desired = desired;
			this.prob = (double)desired / volume;
		}
		
		public static void changePos(int idx1, int idx2){
			Item temp = new Item(item[idx1].name, item[idx1].volume, item[idx1].desired);

			item[idx1].name = item[idx2].name;
			item[idx1].volume = item[idx2].volume;
			item[idx1].desired = item[idx2].desired;
			item[idx1].prob = item[idx2].prob;
			
			item[idx2].name = temp.name;
			item[idx2].volume = temp.volume;
			item[idx2].desired = temp.desired;
			item[idx2].prob = temp.prob;
		}
	}
}