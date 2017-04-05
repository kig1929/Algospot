package firstHalf2017;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Anagram {
	public static int maxLength = 100;
	public static void main(String args[]) throws Exception{
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("Anagram.txt"));
		int TC = sc.nextInt();
		for(int test_case=0;test_case<TC;test_case++) {
			String serialStr = sc.next();
			String passwordStr = sc.next();
			char[] serialArr = serialStr.toCharArray();
			char[] passwordArr = passwordStr.toCharArray();
			Arrays.sort(serialArr);
			Arrays.sort(passwordArr);
			String sortedSerialStr = new String(serialArr);
			String sortedPasswordStr = new String(passwordArr);
			if(!serialStr.equals(passwordStr) && sortedSerialStr.equals(sortedPasswordStr))
				System.out.println("Yes");
			else
				System.out.println("No.");
		}
	}
}
