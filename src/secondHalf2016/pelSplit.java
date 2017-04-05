package secondHalf2016;

public class pelSplit {
	
	public static void main(String[] args){
////		String str = "ono";
//		String str = "onocoderedono";
//		pelSplit pel = new pelSplit();
//		int result = str.length()+1;
//		for(int i=0;i<str.length();i++){
//			result = Math.min(result, pel.split(i, str) + i);
//		}
//		System.out.println(result);
			String temp="123";
//			temp.toCharArray()
//			temp.charAt(0) = "3";
			System.out.println();
	}
	public int split(int curIdx, String str){
		int result = str.length()+1;
		if(curIdx == str.length())
			return 0;
		if(curIdx == str.length()-1)
			return 1;
		for(int i=curIdx+1;i<str.length();i++){
			if(str.charAt(curIdx) == str.charAt(i)){
				if(checkPel(curIdx, i, str)){
					result = Math.min(result, 1 + split(i+1, str));
				}else{
					result = Math.min(result, str.length()+1);
				}
			}
		}
		return result;
	}
	
	public boolean checkPel(int firstIdx, int lastIdx, String str){
		boolean flag=true;
		for(int i=firstIdx;i<(lastIdx-firstIdx+1)/2;i++){
			if(str.charAt(i) == str.charAt(lastIdx-i)){
				;
			}else{
				flag = false;
			}
		}
		return flag;
	}
}
