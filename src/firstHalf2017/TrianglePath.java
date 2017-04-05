package firstHalf2017;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TrianglePath {
	public static int n;
	public static int[][] triangle;
	public static int[][] cache;
	public static void main(String args[]) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("TrianglePath.txt")));
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("TrianglePath.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		int TC = Integer.parseInt(br.readLine().trim());
		int TC = sc.nextInt();
		for(int test_case=0;test_case<TC;test_case++){
//			n = Integer.parseInt(br.readLine().trim());
			n = sc.nextInt();
			triangle = new int[n][n];
			cache = new int[n][n];
			for(int i=0;i<n;i++){
				Arrays.fill(cache[i], -1);
				for(int j=0;j<=i;j++)
					triangle[i][j] = sc.nextInt();
//				String[] temp = br.readLine().trim().split(" ");
//				for(int j=0;j<temp.length;j++)
//					triangle[i][j] = Integer.parseInt(temp[j]);
			}
			TrianglePath tp = new TrianglePath();
			bw.write(tp.maxSumPath(0, 0) + "\n");
			bw.flush();
		}
	}
	
	public int maxSumPath(int y, int x){
		int ret = 0;
		if(y<x || x>=n || y>=n) return ret;
		if(cache[y][x] != -1) return cache[y][x];
		return ret = cache[y][x] = triangle[y][x] + max(maxSumPath(y+1, x), maxSumPath(y+1, x+1));
	}
	
	public int max(int a, int b){
		return a>b?a:b;
	}
}
