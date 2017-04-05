package secondHalf2016;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;


public class Dragon {
	public static int n;
	public static int p;
	public static int l;
	public static String[][] cache;
	
	public static void main(String[] args) throws Exception{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("Dragon.txt"));
		int TC = sc.nextInt();
		for(int test_case=0;test_case<TC;test_case++){
			n = sc.nextInt();
			p = sc.nextInt();
			l = sc.nextInt();
//			cache = new String[p+l+1][n+1];
//			for(int i=0;i<p;i++)
//				Arrays.fill(cache[i], null);
			
			Dragon dragon = new Dragon();
			int firstIdx = p/3;
			int lastIdx = (p+l-1)/3;
			for(int i = p;i<p+l;i++){
				String result = dragon.findVal(i/3, n);
				System.out.print(result.charAt(i%3));
			}
			System.out.println();
		}
	}
	
	public String findVal(int idx, int exp){
		String result; //= cache[idx][exp]; 
//		if(result != null)
//			return result;
		if(exp==0)
			return "+FX";
		result = findVal(idx/2, exp-1);
		if(idx%2 == 0){	//Â¦¼ö¹øÂ°
			if(result == "+FX" || result == "+YF"){
				result = "+FX";
			}else{
				result = "-FX";
			}
		}else{	//È¦¼ö¹øÂ°
			if(result == "+FX" || result == "-FX"){
				result = "+YF";
			}else{
				result = "-YF";
			}
		}
//		cache[idx][exp] = result;
		return result;
	}
}
