package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Tiling2 {
	public static final int modNum = 1000000007;
	public static int n;
	public static int cache[];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Tiling2.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			n = Integer.parseInt(br.readLine().trim());
			cache = new int[n];
			Arrays.fill(cache, -1);
			Tiling2 t2 = new Tiling2();
			bw.write(t2.setTile(0) + "\n");
			bw.flush();
		}
	}
	
	public int setTile(int idx){
		if(idx >= n-1) return 1;
		if(cache[idx] != -1) return cache[idx];
		return cache[idx] = (setTile(idx+1) + setTile(idx+2)) % modNum;
	}
}