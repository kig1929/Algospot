package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Pi {
	public static final int INFINITE = Integer.MAX_VALUE/2;
	public static int subCache[][];
	public static int cache[];
	public static String piStr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Pi.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			piStr = br.readLine().trim();
			subCache = new int[piStr.length()][3];
			cache = new int[piStr.length()];
			for(int i=0;i<subCache.length;i++)
				Arrays.fill(subCache[i], -1);
			Arrays.fill(cache, -1);
			Pi pi = new Pi();
			System.out.println(pi.getTotalLevel(0));
		}
	}
	
	public int getLevel(int idx, int length){
		if(subCache[idx][length-3] != -1) return subCache[idx][length-3];
		int[] dif = new int[length-1];
		for(int i=0;i<dif.length;i++)
			dif[i] = piStr.charAt(idx+i+1) - piStr.charAt(idx+i);
		boolean flag = true;
		for(int i=0;i<dif.length;i++)
			if(!(dif[i] == 0)) flag = false;
		if(flag) return subCache[idx][length-3] = 1;
		flag = true;
		for(int i=0;i<dif.length-1;i++)
			if(!(dif[i] == dif[i+1] && (dif[i] == 1 || dif[i] == -1))) flag = false;
		if(flag) return subCache[idx][length-3] = 2;
		flag = true;
		for(int i=0;i<dif.length-1;i++)
			if(!(dif[i] == -1*dif[i+1])) flag = false;
		if(flag) return subCache[idx][length-3] = 4;
		flag = true;
		for(int i=0;i<dif.length-1;i++)
			if(!(dif[i] == dif[i+1])) flag = false;
		if(flag) return subCache[idx][length-3] = 5;
		return subCache[idx][length-3] = 10;
	}
	
	public int getTotalLevel(int idx){
		if(idx == piStr.length()) return 0;
		if(cache[idx] != -1) return cache[idx];
		int ret = INFINITE;
		for(int next=3;next<=5;next++)
			if(idx + next <= piStr.length())
				ret = cache[idx] = min(ret, getTotalLevel(idx + next) + getLevel(idx, next));
		return ret;
	}
	
	public int min(int a, int b){
		return a<b?a:b;
	}
}
