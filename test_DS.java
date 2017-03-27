import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class test_DS {
	public static void main(String[] args) {
		binaryHeap obj = new binaryHeap();
		fourWayHeap obj2 = new fourWayHeap();
		pairingHeap obj3 = new pairingHeap();
		Scanner scanner;
		int[] freq_table = new int[1000000];

		try {
			scanner = new Scanner(new File("A:\\Spring 2017\\ADS\\sample1\\sample_input_small.txt"));
			int temp;
			while(scanner.hasNextInt())
			{
				temp = scanner.nextInt();
				freq_table[temp]++;

			}
			long start_time = System.currentTimeMillis();
			for(int i =0;i<10;i++)
				obj.usingBinaryHeap(freq_table);
			System.out.println((((System.currentTimeMillis()-start_time))/10)+" = time to make 100 huffman trees using binary heap ");
			start_time = System.currentTimeMillis();
			for(int i =0;i<1;i++)			
				obj2.usingFourWayHeap(freq_table);
			System.out.println((((System.currentTimeMillis()-start_time))/10)+" = time to make 100 huffman trees using 4-way heap");
			start_time = System.currentTimeMillis();
			for(int i =0;i<10;i++)	{
				//System.out.println(i);
				obj3.usingPairingHeap(freq_table);
			}
			System.out.println((((System.currentTimeMillis()-start_time))/10)+" = time to make 100 huffman trees using pairing heap");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
}

