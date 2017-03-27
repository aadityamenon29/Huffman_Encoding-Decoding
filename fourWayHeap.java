import java.util.*;
public class fourWayHeap {
	
	public void usingFourWayHeap(int[] hm)
	{
		binaryHeap object = new binaryHeap();
		ArrayList<TreeNode> al = new ArrayList<TreeNode>();
		al.add(null);
		al.add(null);
		al.add(null);

		TreeNode t1,t2,tc;
		for (int i =0;i<hm.length;i++) 
		{
			if(hm[i]!=0)
			{
				al.add(new TreeNode(hm[i],i)); 
			}
		}
		
		build_min_fourHeap(al);
		//keep removing 2 minimums, combining and adding it back to the queue till only one tree is left.
		while(al.size()!=4)
		{
			t1 = remove_min(al);
			t2 = remove_min(al);
			tc = combine(t1,t2);
			insert(al,tc);
		}
		//object.level_order_print(al.get(3));
	}

	public void min_heapify(ArrayList<TreeNode> A, int i)
	{
		int smallest;
		int first_child = (4*(i-3))+4;
		int second_child = (4*(i-3))+5;
		int third_child = (4*(i-3))+6;
		int fourth_child = (4*(i-3))+7;
		if(first_child<A.size() && (A.get(first_child)).val<=(A.get(i)).val)
			smallest = first_child;
		else smallest = i;
		if(second_child<A.size() && (A.get(second_child)).val<(A.get(smallest)).val)
			smallest = second_child;
		if(third_child<A.size() && (A.get(third_child)).val<(A.get(smallest)).val)
			smallest = third_child;
		if(fourth_child<A.size() && (A.get(fourth_child)).val<(A.get(smallest)).val)
			smallest = fourth_child;
		if(smallest!=i)
		{
			Collections.swap(A, i, smallest);
			min_heapify(A, smallest);
		}
	}

	public void build_min_fourHeap(ArrayList<TreeNode> A)
	{
		for(int i = ((A.size()/4)-1)+3;i>=3;i--)
		{
			min_heapify(A, i);
		}
	}

	public TreeNode remove_min(ArrayList<TreeNode> A)
	{
				
		TreeNode min = A.get(3);
		A.set(3, A.get(A.size()-1));
		A.remove(A.size()-1);
		min_heapify(A, 3);
		return min;

	}

	public void insert(ArrayList<TreeNode> A,TreeNode node)
	{
		A.add(node);
		int i = A.size()-1;
		while(i>0 && A.get(((i-4)/4)+3).val>A.get(i).val)
		{
			Collections.swap(A, i, ((i-4)/4)+3);
			i = ((i-4)/4)+3;
		}
	}

	public TreeNode combine(TreeNode t1, TreeNode t2)
	{
		TreeNode newNode = new TreeNode(t1.val+t2.val);
		newNode.left = t1;
		newNode.right = t2;
		return newNode;
	}
}
