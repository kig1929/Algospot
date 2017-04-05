package firstHalf2017;

import java.awt.image.RescaleOp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Poly {
	public static final int modNum = 10_000_000;
	public static int n;
	public static int[][] cache;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Poly.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_case = Integer.parseInt(br.readLine().trim());
		while(test_case --> 0){
			n = Integer.parseInt(br.readLine().trim());
			cache = new int[n+1][n+1];
			for(int i=0;i<cache.length;i++)
				Arrays.fill(cache[i], -1);
			Poly poly = new Poly();
			bw.write(poly.runPoly(n) + "\n");
		}
		bw.flush();
	}
	
	public int runPoly(int sqCnt){
		int ret = 0;
		for(int using=1;using<=sqCnt;using++)
			ret = (ret + buildPoly(using, sqCnt-using)) % modNum;
		return ret;
	}
	
	public int buildPoly(int upper, int rest){
		if(rest == 0) return 1;
		if(cache[upper][rest] != -1) return cache[upper][rest];
		int ret = 0;
		for(int using=1;using<=rest;using++)
			ret = (ret + (upper + using - 1) * buildPoly(using, rest-using)) % modNum;
		return cache[upper][rest] = ret;
	}
}
