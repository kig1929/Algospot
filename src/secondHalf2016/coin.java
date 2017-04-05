package secondHalf2016;

public class coin {
	public static int[] coinTypes = {1,2};
	public static int money = 1000;
	public static int[] maxCnt;
	public static void main(String args[]){
		maxCnt = new int[coinTypes.length];
		for(int i=0;i<coinTypes.length;i++)
			maxCnt[i] = money/coinTypes[i];
		coin co = new coin();
		int result = co.coin(0,money);
		System.out.println(result);
	}
	
	public int coin(int idx, int restMoney){	//³²Àº ÀÜ¾× return
		if(restMoney==0)
			return 1;
		if(restMoney<0)
			return 0;
		if(idx == coinTypes.length)
			return 0;
		int result=0;
		for(int i=0;i<=maxCnt[idx];i++){
			result += coin(idx+1, restMoney-i*coinTypes[idx]);
		}
		return result;
	}
}
