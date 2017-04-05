import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class decoder {

	public static void main(String[] args) {

		PrintWriter writer = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(args[0]));	//encoded.bin
			BufferedReader br = new BufferedReader(new FileReader(args[1]));	//code_table.txt
			TreeNode root = new TreeNode(-1,-1);
			TreeNode tempNode,parentNode;
			String line;
			String[] splited;
			char temp;
			//read string from codebook, split it using delimiter space
			while ((line = br.readLine()) != null) {
			    splited = line.split("\\s+");
			    tempNode = root;
			    parentNode = root;
			    //take each character and build code tree 
			    for (int i = 0; i < splited[1].length(); i++) {
			    	temp = splited[1].charAt(i);
			    	if(temp=='0'){
			    		parentNode = tempNode;			    		
			    		tempNode = tempNode.left;
			    		//insert tree
			    		if(tempNode==null)
			    		{
			    			tempNode = new TreeNode(-1,-1);
			    			parentNode.left=tempNode;
			    			
			    		}
			    	}
			    	else{
			    		parentNode = tempNode;
			    		tempNode = tempNode.right;	
			    		//insert node
			    		if(tempNode==null)
			    		{
			    			tempNode = new TreeNode(-1,-1);
			    			parentNode.right=tempNode;
			    			
			    		}
			    	}
				}
			    //set node's element to what was extracted from codebook
			    tempNode.element = Integer.parseInt(splited[0]);
			    tempNode = root;	
			}
			writer = new PrintWriter(new BufferedWriter(new FileWriter("decoded.txt")));
			TreeNode tempNode2 = root;
			binaryHeap ob2 = new binaryHeap();
			//ob2.level_order_print(root);
			int data = 0;
			//read byte and store in int data
			while((data  = bis.read())!=-1)
			{
				//bit manipulation to extract bits one by one and traverse the tree
				for(int i=7;i>=0;i--)
				{
					if(((data&(1<<i))>>i)==1)
						tempNode2 = tempNode2.right;
					else if(((data&(1<<i))>>i)==0)
						tempNode2 = tempNode2.left;
					//when leaf node reached, write the equivalent element to decode.txt
					if(tempNode2.right==null && tempNode2.left==null)
					{
						writer.println(tempNode2.element);;
						tempNode2 = root;
					}	
				}
			}
			writer.close();

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
