package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Jlis {
	public static final long MINUS_INFINITE = Long.MIN_VALUE/2; 
	public static final long INFINITE = Long.MAX_VALUE/2;
	public static long[] n;
	public static long[] m;
	public static int[][] cache;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("JLIS.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			String[] temp = br.readLine().trim().split(" ");
			n = new long[Integer.parseInt(temp[0])+1];
			m = new long[Integer.parseInt(temp[1])+1];
			n[0] = m[0] = MINUS_INFINITE;
			temp = br.readLine().trim().split(" ");
			for(int i=1;i<n.length;i++)
				n[i] = Integer.parseInt(temp[i-1]);
			temp = br.readLine().trim().split(" ");
			for(int i=1;i<m.length;i++)
				m[i] = Integer.parseInt(temp[i-1]);
			cache = new int[n.length+1][m.length+1];
			for(int i=0;i<cache.length;i++)
				Arrays.fill(cache[i], -1);
			Jlis jlis = new Jlis();
			bw.write((jlis.calcJlis(0, 0)-2) + "\n");
			bw.flush();
		}
	}
	
	public int calcJlis(int nIdx, int mIdx){
		if(cache[nIdx][mIdx] != -1) return cache[nIdx][mIdx];
		int ret=2;
		long comp = n[nIdx]>m[mIdx] ? n[nIdx] : m[mIdx];
		for(int nNext=nIdx+1;nNext<n.length;nNext++)
			if(comp < n[nNext])
				ret = cache[nIdx][mIdx] = max(ret, calcJlis(nNext, mIdx) + 1);
		for(int mNext=mIdx+1;mNext<m.length;mNext++)
			if(comp < m[mNext])
				ret = cache[nIdx][mIdx] = max(ret, calcJlis(nIdx, mNext) + 1); 
		return ret;
	}
	
	public int max(int a, int b){
		return a>b?a:b;
	}
}
