package firstHalf2017;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hanoi4 {
	public static int n;
	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Hanoi4.txt")));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			n = Integer.parseInt(br.readLine().trim());
			Hanoi4 hni = new Hanoi4();
			int firstState = 0;
			for(int i=0;i<4;i++){
				String[] temp = br.readLine().trim().split(" ");
				firstState |= hni.initState(firstState, i, temp);
			}
			int finalState = (4<<(2*(n-1))) - 1; 
			System.out.print(hni.bidirection(firstState, finalState) + "\n");
		}
	}
	
	public int initState(int state, int pillar, String[] temp){
		for(int i=0;i<Integer.parseInt(temp[0]);i++){
			state |= pillar << (2*(Integer.parseInt(temp[i+1])-1)); 
		}
		return state;
	}
	
	public int setState(int layer, int prePillar, int postPillar, int state){
		return state + ((postPillar-prePillar) << 2*layer);
	}
	
	public int getSng(int num){
		return num>0? 1:-1;
	}
	
	public int incr(int num){
		return getSng(num) * (abs(num)+1);
	}
	
	public int abs(int num){
		return num<0?-1*num:num;
	}
	
	public int getMinLayer(int index, int state){
		return (state >> (2*index)) & 3;
	}
	
	List<Integer> q = new ArrayList<Integer>(); //state를 저장한다
	public int bidirection(int firstState, int finalState){
		int[] len = new int[4<<(2*n)];
		Arrays.fill(len, 0);
		q.add(firstState);
		q.add(finalState);
		len[firstState] = 1;
		len[finalState] = -1;
		while(!q.isEmpty()){
			int state = q.get(0);
			q.remove(0);
			int top[] = {n, n, n, n};
			for(int i=n-1;i>=0;i--)
				top[getMinLayer(i, state)] = i;
			for(int i=0;i<4;i++){ 	//여기서
				if(top[i] != n){
					for(int j=0;j<4;j++){ //여기로 옮기자
						if(i!=j && top[i] < top[j]){ 
							int adjState= setState(top[i], i, j, state);
							if(len[adjState] == 0){ //아직 방문하지 않은
								q.add(adjState);
								len[adjState] = incr(len[state]);
							}
							else if(getSng(len[state]) != getSng(len[adjState])) //끝나는 규칙
								return abs(len[state]) + abs(len[adjState]) - 1;
						}
					}
				}
			}
		}
		return -1;
	}
}

//System.out.println(hni.ids(firstState, finalState));

//for(int i=0;i<top.length;i++)
//top[i] = getMinLayer(i, state);

//	public int getMinLayer(int pillar, int state){
//		int i;
//		for(i=0;i<n;i++){
//			if((state%4 ^ pillar) == 0) break;
//			state >>= 2;
//		}
//		return i;
//	}
	
//	int best;
//	public int ids(int firstState, int finalState){
//		for(int limit=5; limit<30;limit++){
//			best = limit+1;
//			int result = dfs(limit, 0, firstState, finalState);
//			if(result<=limit){	//정답
//				return result;
//			}
//		}
//		return 0;
//	}
//	public int dfs(int limit, int step, int state, int finalState){	//제일 작은 스텝을 반환해라
//		if(step >= best) //방문했거나 한계에 이름
//			return step;
//		if(state == finalState)
//			return step;
//		int layer[] = new int[4];
//		for(int i=0;i<layer.length;i++)
//			layer[i] = getMinLayer(i, state);
//		for(int i=0;i<4;i++){ 	//여기서
//			for(int j=0;j<4;j++){ //여기로 옮기자
//				if(i==j) continue;
//				if(layer[i] < n && layer[i] < layer[j]) 
//					best = Math.min(best, dfs(limit, step+1, setState(layer[i], i, j, state), finalState));
//			}
//		}
//		return best;
//	}
//	public int dfs(int limit, int step, int state, int finalState){	//제일 작은 스텝을 반환해라
//		int result = limit+1;
//		if(step > limit) //방문했거나 한계에 이름
//			return result;
//		if(state == finalState)
//			return step;
//		int layer[] = new int[4];
//		for(int i=0;i<layer.length;i++)
//			layer[i] = getMinLayer(i, state);
//		for(int i=0;i<4;i++){ 	//여기서
//			for(int j=0;j<4;j++){ //여기로 옮기자
//				if(i==j) continue;
//				if(layer[i] < n && layer[i] < layer[j]) 
//					result = Math.min(result, dfs(limit, step+1, setState(layer[i], i, j, state), finalState));
//			}
//		}
//		return result;
//	}
 