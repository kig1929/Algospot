package secondHalf2016;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Quantization {
	public static final long INFINITE = (long)Math.pow(2, 50);
	public static int[][][] cache;
	public static int n;
	public static int s;
	public static int[] series;
	
	public static void main(String args[]) throws Exception{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("Quantization.txt"));
		
		int C = sc.nextInt();
		for(int TC=0;TC<C;TC++){
			n = sc.nextInt();
			s = sc.nextInt();
			series = new int[n];
			for(int i=0;i<n;i++)
				series[i] = sc.nextInt();
			Arrays.sort(series);
			cache = new int[n][n][s];
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					Arrays.fill(cache[i][j], -1);
			
			Quantization qunatization = new Quantization();
			long result = qunatization.Quantizing(0, n-1, s);
			System.out.println(result==INFINITE?0:result);
		}
	}

	long Quantizing(int start, int end, int sCount){
		if(end-start+1 < sCount || sCount == 0)
			return INFINITE;
		long result = cache[start][end][sCount-1]; 
		if(result != -1) 
			return result;
		if(sCount == 1){
			result = calQuantization(start, end);
		}else{
			for(int i=start;i<end;i++){
				 result = Math.min((result<0?INFINITE:result), Math.min(Quantizing(start, i, 0)+Quantizing(i+1, end, sCount), 
						 	Quantizing(start, i, 1)+Quantizing(i+1, end, sCount-1)));
			}
		}
		cache[start][end][sCount-1] = (int)result;
		return result;
	}
	
	int calQuantization(int start, int end){
		int sum=0;
		for(int i=start;i<=end;i++)
			sum += series[i];
	
		return Math.min(getDifPow(start, end, sum/(end-start+1)), 
				getDifPow(start, end, sum/(end-start+1)+1));
	}
	
	int getDifPow(int start, int end, int avg){
		int difPow = 0;
		for(int i=start;i<=end;i++)
			difPow +=(int)Math.pow(series[i]-avg, 2);
		
		return difPow;
	}
}