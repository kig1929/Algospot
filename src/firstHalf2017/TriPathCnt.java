package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class TriPathCnt {
	public static int n;
	public static int triangle[][];
	public static int cacheSum[][];
	public static int cacheCnt[][];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("TriPathCnt.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_case = Integer.parseInt(br.readLine().trim());
		while(test_case --> 0){
			n = Integer.parseInt(br.readLine().trim());
			triangle = new int[n][n];
			cacheSum = new int[n][n];
			cacheCnt = new int[n][n];
			for(int i=0;i<triangle.length;i++){
				String temp[] = br.readLine().trim().split(" ");
				for(int j=0;j<temp.length;j++)
					triangle[i][j] = Integer.parseInt(temp[j]);
				Arrays.fill(cacheSum[i], -1);
				Arrays.fill(cacheCnt[i], -1);
			}
			TriPathCnt tpc = new TriPathCnt();
			tpc.getTriSum(0, 0);
			bw.write(tpc.getTriCnt(0, 0) + "\n");
		}
		bw.flush();
	}
	
	public int getTriSum(int y, int x){
		if(y == n) return 0;
		if(cacheSum[y][x] != -1) return cacheSum[y][x];
		int ret = max(getTriSum(y+1, x), getTriSum(y+1, x+1)) + triangle[y][x];
		return cacheSum[y][x] = ret;
	}
	
	public int getTriCnt(int y, int x){
		if(y == n-1) return 1;
		if(cacheCnt[y][x] != -1) return cacheCnt[y][x];
		int ret = 0;
		if((cacheSum[y][x] - triangle[y][x]) == cacheSum[y+1][x]) ret += getTriCnt(y+1, x);
		if((cacheSum[y][x] - triangle[y][x]) == cacheSum[y+1][x+1]) ret += getTriCnt(y+1, x+1);
		return cacheCnt[y][x] = ret;
	}
	
	public int max(int a, int b){
		return a>b?a:b;
	}
}
