package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Morse {
	public static int n;
	public static int m;
	public static int k;
	public static List<Character> code = new ArrayList<Character>();
	public static double cache[][];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Morse.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		cache = new double[201][201];
		for(int i=0;i<cache.length;i++)
			Arrays.fill(cache[i], -1);
		int test_case = Integer.parseInt(br.readLine().trim());
		while(test_case --> 0){
			String[] temp = br.readLine().trim().split(" ");
			n = Integer.parseInt(temp[0]); //'-'
			m = Integer.parseInt(temp[1]); //'o'
			k = Integer.parseInt(temp[2]);
			code.clear();
			Morse morse = new Morse();
			morse.route(n+m, n);
			for(int i=0;i<code.size();i++)
				bw.write(code.get(i));
			bw.write("\n");
		}
		bw.flush();
	}
	
	public void route(int idx, int restN){
		if(restN == 0){
			while(idx --> 0)
				code.add('o');
		}else{
			double pivot = comb(idx-1, restN-1);
			if(k > pivot){
				k -= pivot;
				code.add('o');
				route(idx-1, restN);
				
			}else{
				code.add('-');
				route(idx-1, restN-1);
			}
		}
	}
	
	public double comb(int n, int r){
		if(r == 0 || n == r) return 1;
		if(cache[n][r] != -1) return cache[n][r];
		double ret = comb(n-1, r) + comb(n-1, r-1);
		return cache[n][r] = ret;
	}
}
