package secondHalf2016;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;



public class JLIS {
	public static long MINUS_INFINITY = -1*((long)Math.pow(2,31)+1);
	public static int n;
	public static int m;
	public static int[] A;
	public static int[] B;
	public static int[][] cache;
	
	public static void main(String[] args) throws Exception{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("JLIS.txt"));
		
		int TC = sc.nextInt();
		for(int k=0;k<TC;k++){
			n = sc.nextInt();
			m = sc.nextInt();
			A = new int[n];
			B = new int[m];
			for(int i=0;i<n;i++)
				A[i] = sc.nextInt();
			for(int i=0;i<m;i++)
				B[i] = sc.nextInt();
			cache = new int[n+1][m+1];
			for(int i=0;i<n+1;i++)
				Arrays.fill(cache[i], -1);
			
			JLIS jLIS = new JLIS();
			System.out.println(jLIS.jlis(-1,-1)-2);
		}
	}
	
	public int jlis(int idxA, int idxB){
		int result = cache[idxA+1][idxB+1];
		if(result != -1)
			return result;
		long valA = (idxA==-1)? MINUS_INFINITY:A[idxA];
		long valB = (idxB==-1)? MINUS_INFINITY:B[idxB];
		long comp = Math.max(valA, valB);
		result = 2;
		for(int nextA=idxA+1;nextA<n;nextA++)
			if(comp < A[nextA])
				result = Math.max(result, jlis(nextA, idxB)+1);
		for(int nextB=idxB+1;nextB<m;nextB++)
			if(comp < B[nextB])
				result = Math.max(result, jlis(idxA, nextB)+1);
		
		cache[idxA+1][idxB+1] = result;
		return result;
	}
	
}
