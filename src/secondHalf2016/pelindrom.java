package secondHalf2016;

public class pelindrom {
	
	public static void main(String[] args){
		String str = "onocoderedono";
		pelSplit pel = new pelSplit();
		int result = str.length()+1;
		for(int i=0;i<str.length();i++){
			result = Math.min(result, pel.split(i, str) + i+1);
		}
		System.out.println(result);
	}
	public int split(int curIdx, String str){
		int result = str.length()+1;
		for(int i=curIdx+1;i<str.length();i++){
			if(str.charAt(curIdx) == str.charAt(i)){
				if(checkPel(curIdx, i, str)){
					result = Math.min(result, 1+ + split(i+1, str));
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
//	public static void main(String[] args){
//		String str = "oncn";
//		pelindrom pel = new pelindrom();
//		int idx=0;
//		int result=1;
//		for(int i=0;i<str.length();i++){
//			String newStr;
//			newStr = str;
//			for(int j=0;j<i;j++)
//				newStr += str.charAt(i-j-1);
//			result = pel.checkPel(newStr);
//			if(result>0)
//				break;
//			else
//				idx++;
//		}
////		System.out.println(result);
//	}
//	public int checkPel(String str){
//		int cnt=0;
//		for(int i=0;i<str.length()/2;i++){
//			if(str.charAt(i) == str.charAt(str.length()-i-1)){
//				cnt++;
//			}else{
//				return 0;
//			}
//		}
//		return str.length();
//	}
}
