package firstHalf2017;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Gallery {
	public static int g;
	public static int h;
	public static boolean[][] adj;
	public static boolean[] visited;
	public static int camCnt;
	
	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Gallery.txt")));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			String[] temp = br.readLine().trim().split(" ");
			g = Integer.parseInt(temp[0]);
			h = Integer.parseInt(temp[1]);
			adj = new boolean[g][g];
			for(int i=0;i<g;i++)
				Arrays.fill(adj[i], false);
			for(int i=0;i<h;i++){
				temp = br.readLine().trim().split(" ");
				int a = Integer.parseInt(temp[0]);
				int b = Integer.parseInt(temp[1]);
				adj[Math.min(a, b)][Math.max(a, b)] = true;
			}
			visited = new boolean[g];
			Arrays.fill(visited, false);
			camCnt = 0;
			
			Gallery gal = new Gallery();
			System.out.println(gal.runDfs());
		}
	}
	
	public int dfs(int k, int depth){
		//Camera : 1, Leaf : 2, else : 3
		visited[k] = true;
		boolean camFlag = false;
		boolean leafFlag = false;
		for(int i=0;i<g;i++){
			if(!visited[i] && adj[Math.min(k, i)][Math.max(k, i)]){
				int res = dfs(i, depth+1);
				if(res == 1) //child node is leaf node
					camFlag = true;
				else if(res == 2)
					leafFlag = true;	//camera
			}
		}
		
		if(leafFlag){
			camCnt++;
			return 1;	//camera
		}
		else if(camFlag)
			return 3;	//else
		else{
			if(depth == 0)
				camCnt++;
			return 2; 	//leaf
		}
	}
	
	public int runDfs(){
		for(int i=0;i<g;i++)
			if(!visited[i])
				dfs(i, 0);
				
		return camCnt;		
	}
}
