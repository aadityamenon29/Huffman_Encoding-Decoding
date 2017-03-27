import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class codebook {
	
	String[] codeBook = new String[1000000];
	StringBuilder answer = new StringBuilder();
	private static final String FILENAME = "A:\\Spring 2017\\ADS\\answer1.txt";
	public void build(TreeNode root)
	{
		StringBuilder sb = new StringBuilder();
		String ch = new String();
		dfs(root,sb,ch);
		System.out.println(answer);
		
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
			//System.out.println(x+" f is : "+root.element);	
			codeBook[root.element] = x.toString();
			answer.append(root.element+""+" "+x.toString()+"\n");
		}
		
		else{
			dfs(root.left,x,"0");
			dfs(root.right,x,"1");
		}
	}

}
