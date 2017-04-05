package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class AsymTiling {
	public static final int modNum = 1_000_000_007; 
	public static int n;
	public static int cache[];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Asymtiling.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_case = Integer.parseInt(br.readLine().trim());
		while(test_case --> 0){
			n = Integer.parseInt(br.readLine().trim());
			cache = new int[n];
			Arrays.fill(cache, -1);
			AsymTiling at = new AsymTiling();
			int ret = at.asymTiling(0);
			if(n%2 == 0) ret -= (at.asymTiling(n/2) + at.asymTiling(n/2+1)) % modNum;
			else ret -= at.asymTiling(n/2+1);
			ret = (ret+modNum)%modNum;
			bw.write(ret + "\n");
		}
		bw.flush();
	}
	
	public int asymTiling(int idx){
		if(idx >= n-1) return 1;
		if(cache[idx] != -1) return cache[idx];
		return cache[idx] = (asymTiling(idx+1) + asymTiling(idx+2)) % modNum;
	}
}
