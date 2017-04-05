package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class JumpGame {
	public static int n;
	public static int[][] board;
	public static int[][] cache;
	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Jumpgame.txt")));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			n = Integer.parseInt(br.readLine().trim());
			board = new int[n][n];
			cache = new int[n][n];
			for(int i=0;i<n;i++){
				Arrays.fill(board[i], -1);
				Arrays.fill(cache[i], -1);
			}
			for(int i=0;i<n;i++){
				String[] temp = br.readLine().trim().split(" ");
				for(int j=0;j<n;j++)
					board[i][j] = Integer.parseInt(temp[j]);
			}
			JumpGame jg = new JumpGame();
			jg.jump(0, 0);
			if(cache[n-1][n-1] == 1)
				bw.write("YES\n");
			else
				bw.write("NO\n");
			bw.flush();
		}
	}
	
	public void jump(int x, int y){
		if(x>=n || y>=n) ; //범위초과
		else if(x==n-1 && y==n-1) cache[x][y] = 1; //목적지 도달
		else if(cache[x][y] != -1) ;
		else{
			cache[x][y] = 1;
			jump(x+board[x][y], y);
			jump(x, y+board[x][y]);
		}
	}
}
