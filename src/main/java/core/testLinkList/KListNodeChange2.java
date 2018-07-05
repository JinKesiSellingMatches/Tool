package core.testLinkList;

/**
 * KListNodeChange
 *
 * @author liulian
 * @date 2018/5/14
 **/
public class KListNodeChange2 {

	private static ListNode head;// 头节点

	private ListNode headTemp;

	private static ListNode newNode;// 新的

	private int size;

	public KListNodeChange2() {
		size = 0;
		head = null;
	}

	private class ListNode {
		private int val;
		private ListNode next;

		ListNode(int val) {
			this.val = val;
		}
	}

	public ListNode reverseKGroup(int k) {

		if (head == null || head.next == null || k == 1) {
			newNode=head;
			return newNode;
		}
		// 判断可以颠倒的次数
		int count = 0;
		int countK = size / k;
		int sizeTemp=size;

		while (sizeTemp > 0) {
			if (countK > 0) {
				headTemp = addHead(headTemp, head.val, false);
				head = head.next;
				sizeTemp--;
				count++;
				if (count == k) {
					int tempK = k;
					while (tempK > 0) {
						newNode = addHead(newNode, headTemp.val, false);
						headTemp = headTemp.next;
						tempK--;
					}
					countK--;
					count = 0;
					headTemp = null;
				}
			}else{
				newNode = addHead(newNode, head.val, false);
				head = head.next;
				sizeTemp--;
				count++;
			}
		}
		reverseLinkList();
		return newNode;
	}

	// 在链表头添加元素
	public ListNode addHead(ListNode listNode, int obj, boolean updateSize) {

		ListNode newHead = new ListNode(obj);
		if (size == 0) {
			listNode = newHead;
		} else {
			newHead.next = listNode;
			listNode = newHead;
		}
		if (updateSize) {
			size++;
		}
		return listNode;
	}

	public void reverseLinkList() { 
		if (newNode == null || newNode.next == null) { 
			return;
		} else {
			ListNode p = newNode;
			ListNode q = newNode.next;
			p.next = null;
			while (q != null) {
				ListNode temp = q.next;
				q.next = p;
				p = q;
				q = temp;
			}
			if (q == null) {
				newNode = p;
				q = null;
			}
			
		}
	}
	
	//显示节点信息
    public void display(ListNode newNode){
        if(size >0){
        	ListNode node = newNode;
            int tempSize = size;
            if(tempSize == 1){//当前链表只有一个节点
                System.out.println("["+node.val+"]");
                return;
            }
            while(tempSize>0){
                if(node.equals(head)){
                    System.out.print("["+node.val+"->");
                }else if(node.next == null){
                    System.out.print(node.val+"]");
                }else{
                    System.out.print(node.val+"->");
                }
                node = node.next;
                tempSize--;
            }
            System.out.println();
        }else{//如果链表一个节点都没有，直接打印[]
            System.out.println("[]");
        }
    }

	public static KListNodeChange2 initData() {
		KListNodeChange2 listNodeChange2 = new KListNodeChange2();
		for (int i = 0; i < 500000; i++) {
			head = listNodeChange2.addHead(head, i, true);
		}
		return listNodeChange2;
	}

	public static void main(String[] args) {

		KListNodeChange2 kListNodeChange = initData();
		kListNodeChange.display(head);
		kListNodeChange.reverseKGroup(2);
		kListNodeChange.display(newNode);
	}

}
