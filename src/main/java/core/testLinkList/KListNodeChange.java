package core.testLinkList;

import java.awt.event.HierarchyEvent;

/**
 * KListNodeChange
 *
 * @author liulian
 * @date 2018/5/14
 **/
public class KListNodeChange {
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null || k == 1) {
			return head;
		}
		ListNode pre = new ListNode(0);
		pre.next = head;
		ListNode cur = head;
		ListNode start = null;
		ListNode end = null;
		ListNode next = null;
		int count = 0;
		while (cur != null) {
			++count;
			if (count % k == 0) {
				next = cur.next;
				start = pre.next;
				end = cur;
				// 打断链表
				end.next = null;
				pre.next = null;
				cur = start.next;
				end = start;
				start.next = null;
				while (cur != null) {
					ListNode curNext = cur.next;
					cur.next = start;
					start = cur;
					cur = curNext;
				}
				pre.next = start;
				if (count == k) {
					head = start;
				}
				end.next = next;
				cur = next;
				pre = end;
			} else {
				cur = cur.next;
			}

		}
		return head;
	}

	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		ListNode e = new ListNode(5);
		ListNode f = new ListNode(6);
		ListNode g = new ListNode(7);
		ListNode h = new ListNode(8);
		ListNode i = new ListNode(9);
		ListNode j = new ListNode(10);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = f;
		f.next = g;
		g.next = h;
		h.next = i;
		i.next = j;
		KListNodeChange kListNodeChange = new KListNodeChange();
		ListNode result = kListNodeChange.reverseKGroup(a, 4);
		while (result != null) {
			System.out.println(result.val);
			result = result.next;
		}

	}

}
