package secondHalf2016;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;


public class SetCCTV {
	public static final int LEAF = 1;
	public static final int NON_LEAF = 0;
	public static final int UNVISITED = 0;
	public static final int VISITED = 1;
	public static final int CAMERA = 2;
	public static final boolean ISOLATED = false;
	public static final boolean CONNECTED = true;
	public static int G;
	public static int camera;
	public static int[] visited;
	
	public static void main(String[] args) throws Exception{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("SetCCTV.txt"));
		
		int TC = sc.nextInt();
		for(int m=0;m<TC;m++){
			G = sc.nextInt();
			int H = sc.nextInt();
			
			int[][] hMtx = new int[G][G]; 
			for(int i=0;i<G;i++)
				Arrays.fill(hMtx[i], 0);
			for(int i=0;i<H;i++){
				int row = sc.nextInt();
				int col = sc.nextInt();
				hMtx[row][col] = 1;
				hMtx[col][row] = 1;
			}
			SetCCTV setCCTV = new SetCCTV();
			camera = 0;
			visited = new int[G];
			Arrays.fill(visited, UNVISITED);
			for(int i=0;i<G;i++){
				if(visited[i]==UNVISITED)
					setCCTV.searchTree(hMtx, i);
			}
			for(int i=0;i<G;i++)
				if(visited[i] == CAMERA)
					camera++;
			System.out.println(camera);
		}
	}
	
	public void searchTree(int[][] hMtx, int node){
		if(getLeavesCount(hMtx, node) == ISOLATED){
			visited[node] = CAMERA;
		}else{	//not isolated
			if(DFS(hMtx, node) == LEAF){
				visited[node] = CAMERA;
			};
		}
	}
	
	public int DFS(int[][] hMtx, int node){
		visited[node] = VISITED;
		int sum=0;
		for(int i=0;i<G;i++)
			if(visited[i]==UNVISITED && hMtx[node][i]>0)	//UNVISITED && Connected
				switch(DFS(hMtx, i)){
				case LEAF: 
					visited[node] = CAMERA;
					break;
				case CAMERA:
					sum++;
					break;
				case NON_LEAF:
					break;
				}
		if(visited[node] == CAMERA)
			return CAMERA;
		else{
			if(sum>0)
				return NON_LEAF;
			else
				return LEAF;
		}
	}
	
	public boolean getLeavesCount(int[][] hMtx, int node){
		int sum = 0;
		for(int i=0;i<G;i++) 
			sum += hMtx[node][i];
		return sum>0? CONNECTED:ISOLATED;
	}
}
	