package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Klis {
	public static int n;
	public static double k;
	public static int[] arr;
	public static int lCache[]; 
	public static double cCache[];
	public static List<Integer> seq;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Klis.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_case = Integer.parseInt(br.readLine().trim());
		while(test_case --> 0){
			String temp[] = br.readLine().trim().split(" ");
			n = Integer.parseInt(temp[0]);
			k = Double.parseDouble(temp[1]);
			temp = br.readLine().trim().split(" ");
			arr = new int[n+1];
			arr[0] = -1;
			for(int i=0;i<temp.length;i++)
				arr[i+1] = Integer.parseInt(temp[i]);
			lCache = new int[n+1];
			cCache = new double[n+1];
			Arrays.fill(lCache, -1);
			Arrays.fill(cCache, -1);
			
			Klis lis = new Klis();
			int maxLength = lis.lisLength(0);
			double maxCnt = lis.lisCnt(0);
			seq = new ArrayList<Integer>();
			lis.searchLis(0, maxLength);
			bw.write((maxLength-1) + "\n");
			for(int i=1;i<seq.size();i++)
				bw.write(seq.get(i) + " ");
			bw.write("\n");
		}
		bw.flush();
	}
	
	
	public int lisLength(int idx){
		if(lCache[idx] != -1) return lCache[idx];
		int ret = 1;
		for(int next=idx+1;next<=n;next++)
			if(arr[idx] < arr[next])
				ret = max(ret, lisLength(next)+1);
		return lCache[idx] = ret;
	}
	
	public int max(int a, int b){
		return a>b?a:b;
	}
	
	public double lisCnt(int idx){
		if(cCache[idx] != -1) return cCache[idx];
		int maxLength = lisLength(idx);
		if(maxLength == 1) return 1;
		double ret = 0;
		for(int next=idx+1;next<=n;next++)
			if(arr[idx] < arr[next])
			if(maxLength == lisLength(next)+1)
				ret += lisCnt(next);
		return cCache[idx] = ret;
	}

	
	public void searchLis(int idx, int length){
		List<Integer> q = new ArrayList<Integer>();
		for(int next=idx+1;next<=n;next++)
			if(length == lisLength(next)+1 && arr[idx]<arr[next])
				q.add(next);
		q = sortQ(q);
		int next;
		for(next=0;next<q.size();next++){
			double cnt = lisCnt(q.get(next));
			if(k > cnt) k -= cnt;
			else break;
		}
		seq.add(arr[idx]);
		if(length > 1)
			searchLis(q.get(next), length-1);
	}
	
	public List<Integer> sortQ(List<Integer> q){
		for(int i=q.size();i>0;i--){
			int minIdx=0;
			for(int j=1;j<i;j++){
				minIdx = arr[q.get(minIdx)]<arr[q.get(j)] ? minIdx:j;
			}
			int temp = q.get(minIdx);
			q.remove(minIdx);
			q.add(temp);
		}
		return q;
	}
}
