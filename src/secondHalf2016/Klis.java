package secondHalf2016;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Klis {
	public static int N;
	public static long K;
	public static int[] series;
	public static int[] cache;
	public static int[] store;
	public static int maxLength;

	public static void main(String[] args) throws Exception{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("Klis.txt"));
		
		int TC = sc.nextInt();
		for(int m=0;m<TC;m++){
			maxLength = 0;
			N = sc.nextInt();
			K = sc.nextLong();
			store = new int[N+1];
			series = new int[N+1];
			series[0] = 0;
			for(int i=1;i<=N;i++)
				series[i] = sc.nextInt();
			cache = new int[N+1];
			Arrays.fill(cache, -1);
			
			Klis klis = new Klis();
			maxLength = klis.lisCount(0)-1;
			
			
		}
	}
	
	public int lisCount(int idx){	//seriesÀÇ idx
		int result = cache[idx];
		if(result!=0)
			return result;
		result = 1;
		for(int i=idx+1;i<N+1;i++)
			if(series[idx] < series[i]){
				result = Math.max(lisCount(i)+1, result);
			}
		cache[idx] = result;
		return result;
	}
	
	
}
