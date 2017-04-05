package secondHalf2016;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;


public class Poly {
	public static int n;
	public static int[][] cache;
	public static final int BASE = 10000000;
	public static void main(String[] args) throws Exception{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("Poly.txt"));
		
		int C = sc.nextInt();
		for(int TC=0;TC<C;TC++){
			n = sc.nextInt();
			cache = new int[n+1][n+1];
			for(int i=0;i<n;i++)
				Arrays.fill(cache[i], 0);
			Poly poly = new Poly();
			int result = 0;
			for(int i=1;i<=n;i++)
				result += poly.calPoly(i, n-i);
			System.out.println(result%BASE);
		}
	}
	
	public int calPoly(int fstRow, int below){	//경우의 수를 반환
		int result = cache[fstRow][below];
		if(result != 0)
			return result;
		if(below == 0)
			return 1;
		for(int i=1;i<=below;i++)
			result = (result + (fstRow+i-1)*calPoly(i, below-i))%BASE;
		cache[fstRow][below] = result;
		return result;
	}
}
