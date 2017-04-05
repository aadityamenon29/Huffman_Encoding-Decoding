import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class encoder {
	
	/*encoder uses four way heap to build huffman tree and traverses the tree to 
	 * generate code_table.txt and encoded.bin
	 * size of the encoded.bin file : 24051 kb :: size of the code_table.txt : 27,222kb
	 */
	
	
	//class variables
	StringBuilder answer = new StringBuilder();
	int sum = 0;
	static String[] codeBook = new String[1000000];
	
	public static void main(String[] args) {
		
		StringBuilder encoded_string = new StringBuilder();
		Scanner scanner,scanner2;
		fourWayHeap obj = new fourWayHeap();
		int[] freq_table = new int[1000000];
		try {
			scanner = new Scanner(new File(args[0]));
			int temp;
			//build frequency table using array acting as a direct access table
			while(scanner.hasNextInt())
			{
				temp = scanner.nextInt();
				freq_table[temp]++;

			}
			encoder e = new encoder();
			e.build(obj.usingFourWayHeap(freq_table));
			scanner2 = new Scanner(new File(args[0]));
			//read integers line by line, find its code in codeBook and append the code a string
			while(scanner2.hasNextInt())
			{
				temp = scanner2.nextInt();
				encoded_string.append(codeBook[temp]);		
			}
			
			BitSet bitSet = new BitSet(encoded_string.length());
			int bitcounter = encoded_string.length()-1;
			//convert string to bitset
			for(Character c : encoded_string.toString().toCharArray()) {
			    if(c.equals('1')) {
			        bitSet.set(bitcounter);
			    }
			    bitcounter--;
			}
			//convert bitset to byte array
			byte[] encoded_byte_array = bitSet.toByteArray();
			int p = 0;
			int q = encoded_byte_array.length-1;
			byte byte_temp = 0;
			//reverse bytes for proper alignment 
			while(p<q)
			{
				byte_temp = encoded_byte_array[p];
				encoded_byte_array[p] = encoded_byte_array[q];
				encoded_byte_array[q] = byte_temp;
				p++;
				q--;
			}
			
			FileOutputStream fos = new FileOutputStream("encoded.bin");
			//write final byte array to file
			fos.write(encoded_byte_array);
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	

	}

	public void build(TreeNode root)
	{
		StringBuilder sb = new StringBuilder();
		String ch = new String();
		dfs(root,sb,ch);		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("code_table.txt"))) 
		{
			//write final answer which contains the final code_table
			bw.write(answer.toString());
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void dfs(TreeNode root,StringBuilder sb,String ch)
	{
		//each node will have its own code prefix till that node i.e x
		StringBuilder x = new StringBuilder();
		x.append(sb).append(ch);
		//if leaf reached i.e element is -1, add string to codebook and append to final answer
		if(root.element!=-1){
			sum = sum + (x.length()*(root.val));
			codeBook[root.element] = x.toString();
			answer.append(root.element+""+" "+x.toString()+"\n");
		}

		else{
			dfs(root.left,x,"0");
			dfs(root.right,x,"1");
		}
	}
}
