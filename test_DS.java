import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class test_DS {
	public static void main(String[] args) {
		binaryHeap obj = new binaryHeap();
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
			obj.usingBinaryHeap(freq_table);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
}

