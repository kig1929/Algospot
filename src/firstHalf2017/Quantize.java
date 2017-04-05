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

public class Quantize {
	public static int INFINITE = Integer.MAX_VALUE/2;
	public static int n;
	public static int s;
	public static List<Integer> series = new ArrayList<Integer>();
	public static int cache[][];
	public static int subCache[][];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Quantization.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			String[] temp = br.readLine().trim().split(" ");
			n = Integer.parseInt(temp[0]);
			s = Integer.parseInt(temp[1]);
			series.clear();
			temp = br.readLine().trim().split(" ");
			for(int i=0;i<temp.length;i++)
				series.add(Integer.parseInt(temp[i]));
			Collections.sort(series);
			cache = new int[n+1][s+1];
			for(int i=0;i<cache.length;i++)
				Arrays.fill(cache[i], -1);
			subCache = new int[n+1][n+1];
			for(int i=0;i<subCache.length;i++)
				Arrays.fill(subCache[i], -1);
			Quantize quantize = new Quantize();
			System.out.println(quantize.minSquareSum(0, s));
		}
	}
	
	public int minSquareSum(int idx, int sNum){
		if(idx == series.size() && sNum >= 0) return 0;
		if(sNum <= 0) return INFINITE;
		if(cache[idx][sNum] != -1) return cache[idx][sNum];
		int ret = INFINITE;
		for(int next=idx+1;next<=n;next++)
			ret = cache[idx][sNum] = min(ret, minSquareSum(next, sNum-1) + subSquareSum(idx, next-1));
		return ret;
	}
	
	public int subSquareSum(int start, int end){
		if(subCache[start][end] != -1) return subCache[start][end];
		int sum = 0;
		for(int i=start;i<=end;i++)
			sum += series.get(i);
		int mean = round((double)sum / (end-start+1));
		int ret = 0;
		for(int i=start;i<=end;i++)
			ret += square(series.get(i)-mean);
		return subCache[start][end] = ret;
	}
	
	public int min(int a, int b){
		return a<b?a:b;
	}
	
	public int square(int a){
		return a*a; 
	}
	
	public int round(double a){
		return ((a-(int)a) >= 0.5) ? (int)a+1 : (int)a;
	}
}
