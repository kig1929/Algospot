package secondHalf2016;
import java.util.ArrayList;
import java.util.List;


public class Packing {
	public static final int Volume = 0;
	public static final int Preference = 1;
	public static final int Pack = 2;
	public static int N;
	public static int W;
	public static String[] itemName;
	public static int[][] item;
	public static int[][] cache;
	public static void main(String[] args) throws Exception{
		List<String> temp = new ArrayList<String>();
		temp.add("hi");
		System.out.println(temp);
//		Scanner sc = new Scanner(new FileInputStream("Packing.txt"));
////		Scanner sc = new Scanner(System.in);
//		
//		int TC = sc.nextInt();
//		for(int m=0;m<TC;m++){
//			N = sc.nextInt();
//			W = sc.nextInt();
//			itemName = new String[N];
//			item = new int[N][3];
//			for(int i=0;i<N;i++){
//				itemName[i] = sc.next();
//				item[i][Volume] = sc.nextInt();
//				item[i][Preference] = sc.nextInt();
//				item[i][Pack] = 0;
//			}
//			cache = new int[W+1][N+1];
//			for(int i=0;i<cache.length;i++)
//				Arrays.fill(cache[i], -1);
//			
//			Packing packing = new Packing();
//			System.out.print(packing.pack(W, 0));
//			packing.backTrackingPack(W, 0);
//			int cnt=0;
//			for(int i=0;i<item.length;i++)
//				if(item[i][Pack]>0)
//					cnt++;
//			System.out.print(" " + cnt);
//			System.out.println();
//			for(int i=0;i<item.length;i++)
//				if(item[i][Pack]>0)
//					System.out.println(itemName[i]);
//		}
	}
	
	public int pack(int capacity, int itemIdx){
		if(itemIdx == N)
			return 0;
		int result = cache[capacity][itemIdx];
		if(result != -1)
			return result;
		int pack=-1; int unpack=-1;
		if(capacity-item[itemIdx][Volume]>=0){
			pack = pack(capacity-item[itemIdx][Volume], itemIdx+1)+item[itemIdx][Preference];
		}
		unpack = pack(capacity,itemIdx+1);
		result = Math.max(pack, unpack);
		cache[capacity][itemIdx] = result;
		return result;
	}
	
	public int backTrackingPack(int capacity, int itemIdx){
		if(itemIdx == N)
			return 0;
		if(pack(capacity,itemIdx) == pack(capacity,itemIdx+1)){	//unpack
			backTrackingPack(capacity, itemIdx+1);
		}else{	//pack
			backTrackingPack(capacity-item[itemIdx][Volume], itemIdx+1);
			item[itemIdx][Pack] = 1;
		}
		return 0;
	}
}
