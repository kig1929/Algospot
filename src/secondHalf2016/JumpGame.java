package secondHalf2016;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;


public class JumpGame {
	public static final int possible = 0;
	public static int N;
	public static int[][] gamePad;
	public static int[][] cache;
	public static  int cnt=0;
	public static void main(String[] args) throws Exception{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("JumpGame.txt"));
		int TC = sc.nextInt();
		
		for(int test_case=0;test_case<TC;test_case++){
			N = sc.nextInt();
			gamePad = new int[N][N];
			for(int i=0;i<N;i++)
				for(int j=0;j<N;j++)
					gamePad[i][j] = sc.nextInt();
			cache = new int[N][N];
			for(int i=0;i<N;i++)
				Arrays.fill(cache[i], -1);
			JumpGame jumpGame = new JumpGame();
//			if(jumpGame.possEval() == possible){
				int result = jumpGame.moveNext(0, 0);
				if(result>0)
					System.out.println("YES");
				else
					System.out.println("NO");
//			}else{
//				System.out.println("NO");
//			}
		}
	}
	
//	public int possEval(){
//		int poss = 1;
//		int min = (N-10)>0?N-10:0;
//		for(int i=min;i<N-1;i++){
//			 poss *= ((N-1)-i)==gamePad[N-1][i]?0:1;
//			 poss *= ((N-1)-i)==gamePad[i][N-1]?0:1;
//		}
//		return poss;
//	}
	
	public int moveNext(int y, int x){
		if(y==N-1 && x==N-1)
			return 1;
		if(y>=N || x>=N)
			return 0;
		int result = cache[y][x];
		if(result!=-1)
			return result;
		result = Math.max(moveNext(y+gamePad[y][x], x), moveNext(y, x+gamePad[y][x]));
		cache[y][x] = result;
		return result;
	}
}
