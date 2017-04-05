package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class DragonCurve {
	public static int n;
	public static int p;
	public static int l;
	public static int qIdx;
	public static List<Integer> q;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("DragonCurve.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_case = Integer.parseInt(br.readLine().trim());
		while(test_case-->0){
			String[] temp = br.readLine().trim().split(" ");
			n = Integer.parseInt(temp[0]);
			p = Integer.parseInt(temp[1]);
			l = Integer.parseInt(temp[2]);
			qIdx = 0;
			q = new ArrayList<Integer>();
			DragonCurve dc = new DragonCurve();
			q.add(+1);
			dc.makeDC(0);
			System.out.println(dc.getDCString());
		}
	}
	
	public void makeDC(int k){ //¼¼´ë
		if(k<n){
			int[] temp = new int[q.size()*2];
			for(int i=0;i<q.size();i++)
				switch (q.get(i)) {
				case +1:
					temp[2*i] = +1;
					temp[2*i+1] = +2;
					break;
				case -1:
					temp[2*i] = -1;
					temp[2*i+1] = +2;
					break;
				case +2:
					temp[2*i] = +1;
					temp[2*i+1] = -2;
					break;
				case -2:
					temp[2*i] = -1;
					temp[2*i+1] = -2;
					break;
				default:
					break;
				}
			q.clear();
			int s = (int) ((p/3)/Math.pow(2, n-k-1));
			int e = (int) (((p+l-1)/3)/Math.pow(2, n-k-1));
			for(int i=s-qIdx*2;i<=e-qIdx*2;i++)
				q.add(temp[i]);
			qIdx = s;
			makeDC(k+1);
		}
	}
	
	public String getDCString(){
		String temp = "";
		for(int i=0;i<q.size();i++){
			switch(q.get(i)){
			case +1:
				temp += "+FX";
				break;
			case -1:
				temp += "-FX";
				break;
			case +2:
				temp += "+YF";
				break;
			case -2:
				temp += "-YF";
				break;
			default:
				break;
			}
		}
		char[] resArr = temp.toCharArray();
		String res = "";
		for(int i=p;i<=p+l-1;i++){
			res += resArr[i-qIdx*3];
		}
		return res;
	}
}
