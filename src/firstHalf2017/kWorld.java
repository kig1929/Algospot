package firstHalf2017;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/* ����ϴ� Ŭ�������� Solution �̾�� �ϸ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� ������ �� �� �ֽ��ϴ�. */

class kWorld {
	public static int n;
	public static int l;
	public static boolean[][] adj;
	public static boolean visited[];
	public static List<Integer> path;
	public static void main(String args[]) throws Exception	{
		/* �Ʒ� �޼ҵ� ȣ���� ������ ǥ���Է�(Ű����) ��� sample_input.txt ���Ϸ� ���� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�.
		   ���� ������ PC ���� �׽�Ʈ �� ����, �Է°��� sample_input.txt�� ������ �� �� �ڵ带 ù �κп� ����ϸ�,
		   ǥ���Է� ��� sample_input.txt ���Ϸ� ���� �Է°��� �о� �� �� �ֽ��ϴ�.
		   ����, ���� PC���� �Ʒ� �޼ҵ带 ������� �ʰ� ǥ���Է��� ����Ͽ� �׽�Ʈ�ϼŵ� �����մϴ�.
		   ��, Codeground �ý��ۿ��� "�����ϱ�" �� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ�(//) ó�� �ϼž� �մϴ�. */
		Scanner sc = new Scanner(new FileInputStream("kWorld.txt"));
//		Scanner sc = new Scanner(System.in);

		int T;
		int test_case;

		T = sc.nextInt();        
		for(test_case = 1; test_case <= T; test_case++) {
			// �� �κп��� �˰��� ���α׷��� �ۼ��Ͻʽÿ�. �⺻ ������ �ڵ带 ���� �Ǵ� �����ϰ� ������ �ڵ带 ����ϼŵ� �˴ϴ�.
			n = sc.nextInt();
			l = sc.nextInt();
			adj = new boolean[n+1][n+1];
			for(int i=0;i<adj.length;i++)
				Arrays.fill(adj[i], false);
			for(int i=0;i<l;i++){
				int x = sc.nextInt();
				int y = sc.nextInt();
				adj[x][y] = adj[y][x] = true;
			}
			visited = new boolean[n+1];
			Arrays.fill(visited, false);
			path = new ArrayList<Integer>();
			// �� �κп��� ������ ����Ͻʽÿ�.
			kWorld kWorld = new kWorld();
			System.out.println("Case #" + test_case);
			System.out.println(kWorld.dfs(1)?1:0);
		}
	}
	
	public boolean dfs(int idx){ 
		if(visited[idx]) return checkLoop(idx);
		else{
			visited[idx] = true;
			path.add(idx);
			boolean ret = true;
			for(int next=1;next<=n;next++)
				if(adj[idx][next])
					if(idx == next) continue;
					else ret &= dfs(next);
			path.remove(path.size()-1);
			return ret;
		}
	}
	
	public boolean checkLoop(int node){ //path���� ���� ���̿� ��� ��ΰ�
		int rep;
		for(rep=0;rep<path.size();rep++)
			if(path.get(rep) == node) break;
		return (path.size()-rep)%2 == 0 ? true : false;
	}
}