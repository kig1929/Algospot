package firstHalf2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BookThief {
	public static final int MINUS_INFINITE = Integer.MIN_VALUE/2;
	public static int n;
	public static int v;
	public static Book[] book;
	public static Map<Integer, Double> ratio;
	public static Object[] ratioIdx;
	public static int[][] cache;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("BookThief.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int test_case=0;test_case<TC;test_case++){
			String[] temp = br.readLine().trim().split(" ");
			n = Integer.parseInt(temp[0]);
			v = Integer.parseInt(temp[1]);
			book = new Book[n];
			for(int i=0;i<book.length;i++)
				book[i] = new Book(br.readLine().trim().split(" "));
			cache = new int[n+1][v+1];
			for(int i=0;i<cache.length;i++)
				Arrays.fill(cache[i], -1);
			
			BookThief bk = new BookThief();
			bk.sortAsRatio();
			ratioIdx = ratio.keySet().toArray();
			System.out.println(bk.maxCost(0, v));
		}
	}
	
	public int maxCost(int ofIdx, int restV){
		if(ofIdx == n || restV == 0) return 0;
		int idx = (int)ratioIdx[ofIdx];
		if(cache[idx][restV] != -1) return cache[idx][restV];
		int ret = 0;
		for(int i=0;i<=book[idx].n;i++){
			if(restV - i*book[idx].v >= 0)
				ret = cache[idx][restV] 
						= max(ret, maxCost(ofIdx+1, restV - i*book[idx].v) + i*book[idx].c);
		}
		return ret;
	}
	
	public int max(int a, int b){
		return a>b?a:b;
	}
	
	public void sortAsRatio(){
		ratio = new HashMap<Integer, Double>();
		for(int i=0;i<book.length;i++)
			ratio.put(i, (double)book[i].c/book[i].v);
		ratio = sortByValue(ratio);
	}
	
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ){
        List<Map.Entry<K, V>> list =
            new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (-1) * (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}


class Book{
	int v;
	int c;
	int n;
	public Book(){
		v = c = n = 0;
	}
	public Book(int value, int cost, int number){
		this.v = value;
		this.c = cost;
		this.n = number;
	}
	public Book(String[] info){
		this.v = Integer.parseInt(info[0]);
		this.c = Integer.parseInt(info[1]);
		this.n = Integer.parseInt(info[2]);
	}
}