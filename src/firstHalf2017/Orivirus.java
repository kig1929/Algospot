package firstHalf2017;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;


public class Orivirus {
	public static final boolean INFECTED = true;
	public static int n;
	public static int[][] f;
	public static int m;
	public static boolean inf[] = new boolean[101];
	public static int totInf;
	public static int preTotInf;
	
	public static void main(String[] args) throws Exception{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("Orivirus.txt"));
		int TC = sc.nextInt();
		for(int test_case=0;test_case<TC;test_case++){
			n = sc.nextInt();
			f = new int[n+1][n+1];
			for(int i=1;i<=n;i++)
				for(int j=1;j<=n;j++)
					f[i][j] = sc.nextInt();
			m = sc.nextInt();
			for(int i=0;i<m;i++){
				Arrays.fill(inf, false);
				inf[sc.nextInt()] = INFECTED;
				inf[sc.nextInt()] = INFECTED;
				totInf = 2;
				Orivirus ori = new Orivirus();
				ori.orivirus();
			}
			System.out.println();
		}
	}
	
	public void orivirus(){
		preTotInf = 0;
		while(preTotInf < totInf){
			preTotInf = totInf;
			for(int i=1;i<=n;i++){	//each row
				int virusChk = 2;
				if(inf[i] == INFECTED)
					continue;
				else{
					for(int j=1;j<=n;j++){	//each element
						if(i==j)
							continue;
						if(f[i][j] == 1 && inf[j] == INFECTED){
							virusChk--;
							if(virusChk <= 0){
								inf[i] = INFECTED;
								totInf++;
								break;
							}
						}
					}
				}
			}
		}
		System.out.print(totInf + " ");
	}
}
