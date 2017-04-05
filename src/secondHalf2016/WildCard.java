package secondHalf2016;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;


public class WildCard {
	
	public static String W;
	public static int N;
	public static String[] file;
	public static boolean[] checkFile; 
	public static void main(String[] args) throws Exception{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("WildCard.txt"));
		
		int TC = sc.nextInt();
		for(int test_case=0;test_case<TC;test_case++){
			W = sc.next();
			N = sc.nextInt();
			file = new String[N];
			for(int i=0;i<N;i++)
				file[i] = sc.next();
			Arrays.sort(file);
			checkFile = new boolean[N];
			
			WildCard wildCard = new WildCard();
			for(int i=0;i<N;i++)
				checkFile[i] = wildCard.checkWildCard(i, 0, 0);
			for(int i=0;i<N;i++)
				if(checkFile[i])
					System.out.println(file[i]);
		}
	}
	
	public boolean checkWildCard(int fileNum, int wIdx, int fIdx){
		if(fIdx == file[fileNum].length()){
			for(int i=wIdx;i<W.length();i++)
				if(W.charAt(i) != '*')
					return false;
			return true;
		}
		if(wIdx == W.length())
			return false;
		
		boolean result = false;
		if(W.charAt(wIdx) == '?'){
			return checkWildCard(fileNum, wIdx+1, fIdx+1);
		}else if(W.charAt(wIdx) == '*'){
			if(wIdx == W.length()-1)	//마지막 문자가 '*'
				return true;
			if(W.charAt(wIdx+1) == '*')
				return checkWildCard(fileNum, wIdx+1, fIdx);
			for(int i=fIdx;i<file[fileNum].length();i++){
				if(W.charAt(wIdx+1) == file[fileNum].charAt(i) || W.charAt(wIdx+1) == '?')
					result = result || checkWildCard(fileNum, wIdx+1, i);
			}
		}else{
			if(W.charAt(wIdx) == file[fileNum].charAt(fIdx)){
				return checkWildCard(fileNum, wIdx+1, fIdx+1);
			}else
				return false;
		}
		return result;
	}
}
