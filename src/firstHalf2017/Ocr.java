package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ocr {
	public static final double MINUS_INFINITE = -1 * Math.pow(2, 30);
	public static int m;
	public static int q;
	public static String[] word;
	public static double[] B;
	public static double[][] T;
	public static double[][] M;
	public static String[] recog;
	public static int[][] chosen;
	public static double[][] cache;
	public static List<Integer> calcWord;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Ocr.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] temp = br.readLine().trim().split(" ");
		m = Integer.parseInt(temp[0]);
		q = Integer.parseInt(temp[1]);
		word = br.readLine().trim().split(" ");
		temp = br.readLine().trim().split(" ");
		B = new double[m];
		T = new double[m][m];
		M = new double[m][m];
		for(int i=0;i<temp.length;i++)
			B[i] = Double.parseDouble(temp[i]);
		for(int i=0;i<m;i++){
			temp = br.readLine().trim().split(" ");
			for(int j=0;j<temp.length;j++)
				T[i][j] = Double.parseDouble(temp[j]);
		}
		for(int i=0;i<m;i++){
			temp = br.readLine().trim().split(" ");
			for(int j=0;j<temp.length;j++)
				M[i][j] = Double.parseDouble(temp[j]);
		}
		while(q --> 0){
			temp = br.readLine().trim().split(" ");
			recog = new String[Integer.parseInt(temp[0])];
			for(int i=0;i<recog.length;i++)
				recog[i] = temp[i+1];
			chosen = new int[m][m];
			cache = new double[m][m];
			for(int i=0;i<chosen.length;i++){
				Arrays.fill(chosen[i], -1);
				Arrays.fill(cache[i], -1);
			}
			Ocr ocr = new Ocr();
			ocr.getProb(0, 0);
			calcWord = new ArrayList<Integer>();
			ocr.reconstruct(0, 0);
			for (int idx : calcWord) {
				bw.write(word[idx] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
	}

	//로그를 취한다
	public double getProb(int preWordIdx, int sentIdx){ //preWordIdx : 이전 단어의 idx, curIdx : 문장 내 몇번째 단어
		if(sentIdx == recog.length) return 0;
		if(cache[preWordIdx][sentIdx] != -1) return cache[preWordIdx][sentIdx]; 
		double cand = MINUS_INFINITE, ret = MINUS_INFINITE;
		int recogWordIdx = getWordIdx(sentIdx); //인식된 단어의 idx
		for(int chosenWordIdx=0;chosenWordIdx<m;chosenWordIdx++){ //모든 단어에 대해
			if(sentIdx == 0)
				cand = Math.log(B[chosenWordIdx]) + Math.log(M[chosenWordIdx][recogWordIdx]) 
						+ getProb(chosenWordIdx, sentIdx+1);
			else
				cand = Math.log(T[preWordIdx][chosenWordIdx]) + Math.log(M[chosenWordIdx][recogWordIdx]) 
						+ getProb(chosenWordIdx, sentIdx+1);
			if(cand > ret){
				ret = cand;
				chosen[preWordIdx][sentIdx] = chosenWordIdx;
			}
		}
		return cache[preWordIdx][sentIdx] = ret;
	}
	
	public int getWordIdx(int sentIdx){
		int i;
		for(i=0;i<m;i++)
			if(word[i].equals(recog[sentIdx]))
				break;
		return i; 
	}
	
	public void reconstruct(int preWordIdx, int sentIdx){
		int next = chosen[preWordIdx][sentIdx];
		if(next != -1){
			calcWord.add(next);
			if(sentIdx+1 < m) reconstruct(next, sentIdx+1);
		}
	}
}
