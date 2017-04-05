package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WildCard {
	public static int n;
	public static String wildCard;
	public static String fileName;
	public static int cache[][];
	public static void main(String args[]) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("WildCard.txt")));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			List<String> fileNameList = new ArrayList<String>();
			wildCard = br.readLine().trim();
			n = Integer.parseInt(br.readLine().trim());
			for(int file_case=0;file_case<n;file_case++){
				cache = new int[101][101];
				for(int i=0;i<cache.length;i++)
					Arrays.fill(cache[i], -1);
				fileName = br.readLine().trim();
				WildCard wc = new WildCard();
				if(wc.matchWildCard(0, 0))
					fileNameList.add(fileName);
					
			}
			Collections.sort(fileNameList);
			for (int i=0;i<fileNameList.size();i++)
				bw.write(fileNameList.get(i) + "\n");
			bw.flush();
		}
	}
	
	public boolean matchWildCard(int fIdx, int wIdx){
		boolean ret = false;
		if(fIdx == fileName.length() && wIdx == wildCard.length()) //기저사례
			return true;
		else if(wIdx == wildCard.length()) //기저사례 //와일드 카드만 끝
			return false;
		else if(cache[fIdx][wIdx] != -1) //cache 있는 경우
			return cache[fIdx][wIdx]>0?true:false; //cache가 1일 때 true, 0일 때 false
		else if(wildCard.charAt(wIdx) == '*'){ //와일드 카드에 *
			for(int i=0;i+fIdx<=fileName.length();i++)
				ret |= matchWildCard(fIdx+i, wIdx+1);
			cache[fIdx][wIdx] = ret==true?1:0;
		}
		else if(fIdx == fileName.length()) //기저사례 //파일이름만 끝
			return false;
		else if(fileName.charAt(fIdx) == wildCard.charAt(wIdx) || wildCard.charAt(wIdx) == '?'){
			ret = matchWildCard(fIdx+1, wIdx+1);
			cache[fIdx][wIdx] = ret==true?1:0;
		}
		return ret;
	}
}