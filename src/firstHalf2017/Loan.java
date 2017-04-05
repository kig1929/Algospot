package firstHalf2017;

import java.io.FileInputStream;
import java.util.Scanner;

public class Loan {
	public static double n;
	public static int m;
	public static double p;
	
	public static void main(String[] args) throws Exception{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("Loan.txt"));
		int TC = sc.nextInt();
		for(int test_case=0;test_case<TC;test_case++){
			n = sc.nextDouble();
			m = sc.nextInt();
			p = sc.nextDouble();
			
			Loan lo = new Loan();
			lo.loan();
		}
	}
	
	public void loan(){
		double lower=0;
		double upper=(25.0/24.0)*n;
		double preRes=-1;
		while(true){
			double res = calcDeposit(lower, upper);
			if(Math.abs(preRes-res)<=Math.pow(10, -7)){
				System.out.println((lower+upper)/2);
				break;
			}
			if(res<=0)
				upper = (lower+upper)/2;
			else
				lower = (lower+upper)/2;
			preRes=res;
		}
	}
	
	public double calcDeposit(double lower, double upper){
		double c = (lower+upper)/2;
		double pre=0;
		double cur=n;
		for(int i=0;i<m;i++){
			pre=cur;
			cur=pre*(1+p/1200)-c;
		}
		return cur;
	}
}
