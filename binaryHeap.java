import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class binaryHeap {

	public void usingBinaryHeap(int[] hm)
	{
		ArrayList<TreeNode> al = new ArrayList<TreeNode>();
		TreeNode t1,t2,tc;
		//initialize heap
		for (int i =0;i<hm.length;i++) 
		{
			if(hm[i]!=0)
			{
				al.add(new TreeNode(hm[i],i)); 
			}
		}
		
		build_min_heap(al);
		//keep removing 2 minimums, combining and adding it back to the queue till only one tree is left.
		while(al.size()!=1)
		{
			t1 = remove_min(al);
			t2 = remove_min(al);
			tc = combine(t1,t2);
			insert(al,tc);
		}
		//level_order_print(al.get(0));
	}

	public void min_heapify(ArrayList<TreeNode> A, int i)
	{
		int smallest;
		int left_child = (2*i)+1;
		int right_child = (2*i)+2;
		//find smallest amongst children and parent
		if(left_child<A.size() && (A.get(left_child)).val<=(A.get(i)).val)
			smallest = left_child;
		else smallest = i;
		if(right_child<A.size() && (A.get(right_child)).val<(A.get(smallest)).val)
			smallest = right_child;
		
		if(smallest!=i)
		{
			//get smallest to the parent
			Collections.swap(A, i, smallest);
			min_heapify(A, smallest);
		}
	}

	public void build_min_heap(ArrayList<TreeNode> A)
	{
		for(int i = (A.size()/2)-1;i>=0;i--)
		{
			min_heapify(A, i);
		}
	}

	public TreeNode remove_min(ArrayList<TreeNode> A)
	{
		//replace root with last node, delete last node, heapify root
		TreeNode min = A.get(0);
		A.set(0, A.get(A.size()-1));
		A.remove(A.size()-1);
		min_heapify(A, 0);
		return min;

	}

	public void insert(ArrayList<TreeNode> A,TreeNode node)
	{
		//insert new node at the end. keep swapping with parent until condition break
		A.add(node);
		int i = A.size()-1;
		while(i>0 && A.get((i-1)/2).val>A.get(i).val)
		{
			Collections.swap(A, i, (i-1)/2);
			i = (i-1)/2;
		}
	}

	public TreeNode combine(TreeNode t1, TreeNode t2)
	{
		//create new node and set its value (frequency) to the sum of the children
		TreeNode newNode = new TreeNode(t1.val+t2.val);
		newNode.left = t1;
		newNode.right = t2;
		return newNode;
	}

	//utility function to check if huffman tree created is correct by checking the level order traversal.
	public void level_order_print(TreeNode root)
	{
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		TreeNode temp;
		int size;
		int j = 0;
		while(!q.isEmpty())
		{
			size = q.size();
			j=0;
			while(j<size){
				temp = q.poll();
				if(temp!=null){
					System.out.print("frequency is : "+temp.element);
					q.add(temp.left);
					q.add(temp.right);}
				else
					System.out.print("null");
				j++;
			}
			System.out.println();

		}
	}
	
	////utility function to check if huffman tree created is correct by checking the level order traversal.
	public void level_order_print(pairingHeapNode root)
	{
		Queue<pairingHeapNode> q = new LinkedList<pairingHeapNode>();
		q.add(root);
		pairingHeapNode temp;
		int size;
		int j = 0;
		while(!q.isEmpty())
		{
			size = q.size();
			j=0;
			while(j<size){
				temp = q.poll();
				if(temp!=null){
					System.out.print("frequency is : "+temp.val);
					q.add(temp.left);
					q.add(temp.right);}
				else
					System.out.print("null");
				j++;
			}
			System.out.println();

		}
	}
}
