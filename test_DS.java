import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class test_DS {
	public static void main(String[] args) {
		test_DS obj = new test_DS();
		Scanner scanner;
		int i = 0;
		HashMap<Integer,Integer> freq_table = new HashMap<Integer,Integer>();
		try {
			scanner = new Scanner(new File("A:\\Spring 2017\\ADS\\sample1\\sample_input_small.txt"));
			int temp;
			while(scanner.hasNextInt())
			{
				temp = scanner.nextInt();
				if(!freq_table.containsKey(temp))
					freq_table.put(temp, 1);
				else
					freq_table.put(temp, freq_table.get(temp)+1);
			}
			obj.usingBinaryHeap(freq_table);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}

	public void usingBinaryHeap(HashMap<Integer,Integer> hm)
	{
		ArrayList<TreeNode> al = new ArrayList<TreeNode>();
		TreeNode t1,t2,tc;
		for (Map.Entry<Integer, Integer> entry : hm.entrySet()) 
		{
			System.out.println(entry.getKey()+" "+entry.getValue());
			al.add(new TreeNode(entry.getValue(),entry.getKey())); 
		}
		System.out.println();
		build_min_heap(al);
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
		if(left_child<A.size() && (A.get(left_child)).val<=(A.get(i)).val)
			smallest = left_child;
		else smallest = i;
		if(right_child<A.size() && (A.get(right_child)).val<(A.get(smallest)).val)
			smallest = right_child;
		if(smallest!=i)
		{
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
		TreeNode min = A.get(0);
		A.set(0, A.get(A.size()-1));
		A.remove(A.size()-1);
		min_heapify(A, 0);
		return min;

	}

	public void insert(ArrayList<TreeNode> A,TreeNode node)
	{
		A.add(node);
		int i = A.size()-1;
		while(i>0 && A.get(i-1/2).val>A.get(i).val)
		{
			Collections.swap(A, i, (i-1)/2);
			i = (i-1)/2;
		}
	}

	public TreeNode combine(TreeNode t1, TreeNode t2)
	{
		TreeNode newNode = new TreeNode(t1.val+t2.val);
		newNode.left = t1;
		newNode.right = t2;
		return newNode;
	}

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
					System.out.print("frequency is : "+temp.val+"and element is : "+temp.element);
					//if(temp.left!=null)
					q.add(temp.left);
					//if(temp.right!=null)
					q.add(temp.right);}
				else
					System.out.println("null");


				j++;
			}
			System.out.println();

		}
	}
}

