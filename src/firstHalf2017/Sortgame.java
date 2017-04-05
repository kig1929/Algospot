package firstHalf2017;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sortgame {
	public static int n;
	public static int[] nums;
	public static void main(String args[]) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Sortgame.txt")));
		int TC = Integer.parseInt(br.readLine().trim());
		Sortgame sg = new Sortgame();
		for(int test_case=0;test_case<TC;test_case++){
			n = Integer.parseInt(br.readLine().trim());
			nums = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
			sg.preCalc(n);
			System.out.println(sg.distance.get(sg.normalization()));
		}
	}
	
	Map<Integer, Integer> distance = new HashMap<Integer, Integer>();
	List<Integer> q = new ArrayList<Integer>();
	
	public void preCalc(int len){
		int ordered = 0;
		for(int i=1;i<=len;i++)
			ordered = ordered * 10 + i;
		q.add(ordered);
		distance.put(ordered, 0);
		while(!q.isEmpty()){
			for(int i=0;i<len;i++){
				for(int j=i+1;j<len;j++){
					//reverse
					int reversed=reverse(q.get(0), len, i, j);
					if(distance.get(reversed) == null){
						q.add(reversed);
						distance.put(reversed, distance.get(q.get(0))+1);
					}
				}
			}
			q.remove(0);
		}
	}
	
	public int reverse(int num, int len, int begin, int end){
		int subRev = 0;
		int res = (int)(num%Math.pow(10, len-end-1))
				+ (int)((int)(num/Math.pow(10, len-begin))*Math.pow(10, len-begin));
		num /= Math.pow(10, len-end-1);
		for(int i=0;i<=end-begin;i++){
			subRev = subRev * 10 + num % 10;
			num /= 10;
		}
		subRev *= Math.pow(10, len-end-1);
		return res + subRev;
	}
	
	public int normalization(){
		int arr[] = new int[n];
		Arrays.fill(arr, -1);
		int larger = nums.length;
		for(int j=0;j<nums.length;j++){
			int max = 0;
			int maxIdx = -1;
			for(int i=0;i<nums.length;i++){
				if(arr[i] == -1 && Math.max(max, nums[i]) == nums[i]){
					max = nums[i];
					maxIdx = i;
				}
			}
			arr[maxIdx] = larger--;
			nums[maxIdx] = 0;
		}
		int res = 0;
		for(int i=0;i<n;i++)
			res = res*10 + arr[i];
		return res;
	}
}
