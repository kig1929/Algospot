package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Numb3rs {
	public static int n;
	public static int d;
	public static int p;
	public static int[][] adj;
	public static int[] q;
	public static double[][] cache;
	public static double[] prob;
	public static void main(String args[]) throws Exception{
		DecimalFormat form = new DecimalFormat("0.00000000");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Numb3rs.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_case = Integer.parseInt(br.readLine().trim());
		while(test_case --> 0){
			String[] temp = br.readLine().trim().split(" ");
			n = Integer.parseInt(temp[0]);
			d = Integer.parseInt(temp[1]);
			p = Integer.parseInt(temp[2]);
			adj = new int[n][n];
			for(int i=0;i<n;i++){
				temp = br.readLine().trim().split(" ");
				for(int j=0;j<n;j++)
					adj[i][j] = Integer.parseInt(temp[j]);
			}
			q = new int[Integer.parseInt(br.readLine().trim())];
			temp = br.readLine().trim().split(" ");
			for(int i=0;i<q.length;i++)
				q[i] = Integer.parseInt(temp[i]);
			cache = new double[d+1][n+1];
			for(int i=0;i<cache.length;i++)
				Arrays.fill(cache[i], -1);
			Numb3rs n3 = new Numb3rs();
			n3.setProb();
			
			for(int i=0;i<q.length;i++)
				bw.write(form.format(n3.escape(d, q[i])) + " ");
			bw.write("\n");
		}
		bw.flush();
	}
	
	public void setProb(){
		prob = new double[n+1];
		int sum;
		for(int i=0;i<adj.length;i++){
			sum = 0;
			for(int j=0;j<adj[i].length;j++)
				sum += adj[i][j];
			prob[i] = (double)1/sum;
		}
	}
	
	public double escape(int day, int curP){
		if(day == 0) return curP==p ? 1 : 0;
		if(cache[day][curP] != -1) return cache[day][curP];
		double ret = 0;
		for(int preP=0;preP<adj[curP].length;preP++)
			if(adj[curP][preP] > 0)
				ret += prob[preP] * escape(day-1, preP);
		return cache[day][curP] = ret;
	}
}
