package firstHalf2017;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChildrenDay {
	public static String d;
	public static int n;
	public static int m;
	public static List<Integer> type;
	public static int[] parent;	//parent vertex
	public static int[] choice;	//edge
	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("ChildrenDay.txt")));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			String[] temp = br.readLine().trim().split(" ");
			d = temp[0];
			n = Integer.parseInt(temp[1]);
			m = Integer.parseInt(temp[2]);
			parent = new int[2*n];
			choice = new int[2*n];
			Arrays.fill(parent, -1);
			Arrays.fill(choice, -1);
			type = new ArrayList<Integer>();
			ChildrenDay cd = new ChildrenDay();
			cd.getType();
			System.out.println(cd.runBfs());
		}
	}
	
	public void getType(){
		for(int i=0;i<d.length();i++)
			type.add(d.charAt(i)-'0');
		Collections.sort(type);
	}
	
	public List<Integer> q = new ArrayList<Integer>();
	public String runBfs(){
		parent[0] = 0;
		q.add(0);
		while(!q.isEmpty()){
			int here = q.get(0);
			q.remove(0);
			for(int i=0;i<type.size();i++){
				int there = append(here, type.get(i));
				if(parent[there] == -1){
					parent[there] = here;
					choice[there] = type.get(i);
					q.add(there);
				}
			}
		}
		
		if(parent[n+m] == -1) return "IMPOSSIBLE";
		List<Integer> result = new ArrayList<Integer>();
		int here = n+m;
		while(parent[here] != here){
			result.add(choice[here]);
			here = parent[here];
		}
		String ret = "";
		for(int i=result.size()-1;i>=0;i--)
			ret += result.get(i);
		return ret;
	}
	
	public int append(int here, int edge){
		int there = here*10 + edge;
		if(there>=n+m) return n + there%n;
		else return there%n;
	}
}
