package secondHalf2016;
import java.io.FileInputStream;
import java.util.Scanner;


public class EscapeDrDunibal {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		EscapeDrDunibal escapeDrDunibal = new EscapeDrDunibal();
		
//		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for(int i =0;i<TC;i++){
			//input
			int N = sc.nextInt();
			int D = sc.nextInt();
			int P = sc.nextInt();
			int[][] arr = new int[N][N+1];
			double[] pos = new double[N];
			for(int j=0;j<N;j++){
				int sum = 0;
				for(int k=0;k<N;k++){
					arr[j][k] = sc.nextInt();
					sum += arr[j][k];
				}
				pos[j] = 1/(double)sum;
			}
			int T = sc.nextInt();
			int[] Q = new int[T];
			for(int j=0;j<T;j++) Q[j] = sc.nextInt();
			
			double[] node = new double[N];
			double[] newNode = new double[N];
			for(int j=0;j<N;j++){
				node[j] = 0;
				newNode[j] = 0;
			}
			node[P] = 1;
			
			//algorithm
			for(int j=0;j<D;j++){
				escapeDrDunibal.calPos(arr, pos, node, newNode, N);
				int c=1;
				c++;
			}
			for(int j=0;j<T;j++) System.out.print(newNode[Q[j]]+" ");
			System.out.println();
		}
	}
	
	void calPos(int[][] arr, double[] pos, double[] node, double[] newNode, int N){
		for(int i=0;i<N;i++){
			newNode[i] = 0;
			for(int j=0;j<N;j++){
				newNode[i] += pos[j]*arr[i][j]*node[j];				
			}
		}
		System.arraycopy(newNode, 0, node, 0, N);
	}
}
