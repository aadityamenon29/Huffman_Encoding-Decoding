import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Encoder {
	
	//class variables
	StringBuilder answer = new StringBuilder();
	int sum = 0;
	String[] codeBook = new String[1000000];
	private static final String FILENAME = "A:\\Spring 2017\\ADS\\codebook.txt";
	
	public static void main(String[] args) {
		Scanner scanner;
		fourWayHeap obj = new fourWayHeap();
		int[] freq_table = new int[1000000];
		try {
			scanner = new Scanner(new File(args[0]));
			int temp;
			while(scanner.hasNextInt())
			{
				temp = scanner.nextInt();
				freq_table[temp]++;

			}
			Encoder e = new Encoder();
			e.build(obj.usingFourWayHeap(freq_table));


		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}	

	}

	public void build(TreeNode root)
	{
		StringBuilder sb = new StringBuilder();
		String ch = new String();
		dfs(root,sb,ch);
		System.out.println(sum);		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) 
		{
			bw.write(answer.toString());
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void dfs(TreeNode root,StringBuilder sb,String ch)
	{
		StringBuilder x = new StringBuilder();
		x.append(sb).append(ch);
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
