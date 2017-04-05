package secondHalf2016;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;


public class fix {
	public static int N;
	public static int arr[];
	public static int cpArr[];
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("Fix.txt"));
		
		int TC = sc.nextInt();
		for(int m=0;m<TC;m++){
			int cnt=0;
			N = sc.nextInt();
			arr = new int[N];
			for(int i=0;i<N;i++)
				arr[i] = sc.nextInt();
			cpArr = arr.clone();
			Arrays.sort(arr);
			for(int i=0;i<N;i++)
				if(cpArr[i] == arr[i])
					cnt++;
			System.out.println(cnt);
		}
	}

}
