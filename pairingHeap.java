import java.util.ArrayList;
public class pairingHeap {
	pairingHeapNode min = null;
	public void usingPairingHeap(int[] hm) {
		binaryHeap object = new binaryHeap();
		pairingHeapNode t1, t2, tc;
		int flag = 1;
		// initialize pairing heap
		for (int i = 0; i < hm.length; i++) {
			if (hm[i] != 0) {
				if (flag == 1) {
					min = new pairingHeapNode(hm[i]);
					flag = 0;
				} else {
					pairingHeapNode temp = new pairingHeapNode(hm[i]);
					insert(temp);
				}
			}
		}
		// build huffman tree
		while (min.children.size() != 0) {
			t1 = min;
			removeMin();
			t2 = min;
			removeMin();
			tc = combine(t1, t2);
			insert(tc);
			for (int j = 0; j < min.children.size(); j++) {
			}
		}
		// object.level_order_print(min);
	}
	public void insert(pairingHeapNode temp) {
		if (min == null)
			min = temp;
		else if (temp.val <= min.val) {
			temp.children.add(min);
			min = temp;
		} else
			min.children.add(temp);
	}
	public void removeMin() {
		int no_of_children = min.children.size();
		pairingHeapNode extra;
		// pairwise combine
		if (no_of_children == 0)
			min = null;
		if (no_of_children == 1)
			min = min.children.get(0);
		else if (no_of_children == 2) {
			if (min.children.get(0).val < min.children.get(1).val) {
				extra = min.children.get(1);
				min = min.children.get(0);
				min.children.add(extra);
			} else {
				extra = min.children.get(0);
				min = min.children.get(1);
				min.children.add(extra);
			}
		} else if (no_of_children > 2) {
			pairingHeapNode a, b;
			int size = min.children.size();
			ArrayList<pairingHeapNode> list = new ArrayList<pairingHeapNode>();
			int counter = 0;
			while (counter < size) {
				a = min.children.get(0);
				b = min.children.get(1);
				min.children.remove(0);
				counter++;
				if (counter == size) {
					min.children.add(a);
				} else {
					min.children.remove(0);
					counter++;
					insert(a, b);
				}
			}
			list = min.children;
			min = list.get(0);
			for (int i = 1; i < list.size(); i++) {
				insert(list.get(i));
			}
		}
	}
	public void insert(pairingHeapNode a, pairingHeapNode b) {
		if (a.val <= b.val) {
			a.children.add(b);
			min.children.add(a);
		}
		else {
			b.children.add(a);
			min.children.add(b);
		}
	}
	public pairingHeapNode combine(pairingHeapNode temp1, pairingHeapNode temp2) {
		pairingHeapNode newNode = new pairingHeapNode(temp1.val + temp2.val);
		newNode.left = temp1;
		newNode.right = temp2;
		return newNode;
	}
}
