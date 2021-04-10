// https://leetcode-cn.com/problems/insertion-sort-list/
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1))));
    }

    public ListNode insertionSortList(ListNode head) {
        if (head.next == null){
            return head;
        }

        ListNode cur = head.next;
        head.next = null;
        ListNode curNext = cur.next; //cur is the node waiting to be sorted
        cur.next = null;
        while (cur != null) {
            ListNode sortingCur = head;
            ListNode pre = null;
            while (true) {
                if (sortingCur.val > cur.val) {
                    cur.next = sortingCur;
                    if (pre != null) {
                        pre.next = cur;
                    } else {
                        head = cur;
                    }
                    break;
                } else if (sortingCur.next == null) {
                    sortingCur.next = cur;
                    break;
                }
                pre = sortingCur;
                sortingCur = sortingCur.next;
            }
            cur = curNext;
            if (curNext != null) {
                curNext = curNext.next;
                cur.next = null;
            }
        }
        return head;
    }
}
