package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Lis {
	public static int n;
	public static int[] series;
	public static int[] cache;
	public static void main(String args[]) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Lis.txt")));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			n = Integer.parseInt(br.readLine().trim());
			String[] temp = br.readLine().trim().split(" ");
			series = new int[temp.length];
			cache = new int[n];
			for(int i=0;i<temp.length;i++){
				series[i] = Integer.parseInt(temp[i]);
				Arrays.fill(cache, -1);
			}
			Lis lis = new Lis();
			int ret=0;
			for(int i=0;i<n;i++)
				ret = lis.max(ret, lis.getLis(i));
			bw.write(ret + "\n");
			bw.flush();
		}
	}
	
	public int getLis(int pivot){
		int ret=0;
		if(cache[pivot] != -1) return cache[pivot];
		for(int j=pivot+1;j<n;j++)
			if(series[pivot] < series[j])
				ret = max(ret, getLis(j));
		return cache[pivot] = ret+1;
	}
	
	public static int max(int a, int b){
		return a>b?a:b;
	}
}
