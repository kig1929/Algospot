package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Snail {
	public static int n;
	public static int m;
	public static double cache[][];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Snail.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_case = Integer.parseInt(br.readLine().trim());
		while(test_case --> 0){
			String[] temp = br.readLine().trim().split(" ");
			n = Integer.parseInt(temp[0]);
			m = Integer.parseInt(temp[1]);
			cache = new double[n+1][m+1];
			for(int i=0;i<cache.length;i++)
				Arrays.fill(cache[i], -1);
			Snail sp = new Snail();
			DecimalFormat form = new DecimalFormat("0.00000000");
			bw.write(form.format(sp.snailPath(n, m)) + "\n");
		}
		bw.flush();
	}
	
	public double snailPath(int dis, int day){
		if(day < 0) return 0;
		if(dis <= 0) return 1;
		if(cache[dis][day] != -1) return cache[dis][day];
		return cache[dis][day] = 0.75*snailPath(dis-2, day-1) + 0.25*snailPath(dis-1, day-1); 
	}
}
