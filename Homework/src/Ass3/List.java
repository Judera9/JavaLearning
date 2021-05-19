package Ass3;

public class List {
    public ListNode headListNode;
    private int size;
    private int sorted; // note: need to check sorted carefully

    public List() {
        headListNode = null;
        size = 0;
        sorted = 0;
    }

    public List(ListNode node) {
        headListNode = node;
        size = 1;
        sorted = 0;
    }

    public int size() {
        return size;
    }

    public int sorted() {
        return sorted; // 0-unsorted, 1-ascending, -1-descending
    }

    // note: New methods to insert below

    // sort the list ascending. Any sorting algorithm is fine.
    // attribute sorted should be changed to 1
    public void sort() {
        sortList(headListNode);
        this.sorted = 1;
    }

    // use merge sort to solve the sorting problem
    // run in O(NlgN) and space cost is O(lgN)
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // first step recursively cut the list
        ListNode newHead = cutToHalf(head);
        ListNode sortedFirstHead = sortList(head);
        ListNode sortedSecondHead = sortList(newHead);

        // second step sort and merge them together
        return merge(sortedFirstHead, sortedSecondHead);
    }

    // use fast-slow pointer to find the middle point of a list
    // return the head of the second list
    public ListNode cutToHalf(ListNode head) {
        // if (head == null) {
        // throw new Exception(">> cutToHalf has null input");
        // }
        if (head.next == null) {
            return head;
        }

        ListNode slowPoint = head;
        ListNode fastPoint = head.next;

        // length is even, this is the point at length / 2
        // length is odd, this is the point at (length + 1) / 2
        while (true) {
            if (fastPoint == null || fastPoint.next == null) {
                ListNode secondHead = slowPoint.next;
                slowPoint.next = null;
                return secondHead;
            }

            fastPoint = fastPoint.next.next;
            slowPoint = slowPoint.next;
        }
    }

    // merge the sorted sublist together
    // return the head of the new list
    public ListNode merge(ListNode head, ListNode secondHead) {
        if (head == null || secondHead == null) {
            System.out.println(">> merge has null input");
        }

        ListNode firstPoint = head;
        ListNode secondPoint = secondHead;
        ListNode newHead = null;
        ListNode cur = null;

        while (true) {
            if (firstPoint == null) {
                assert cur != null;
                cur.next = secondPoint; // the left nodes of the next sublist are sorted
                headListNode = newHead;
                return newHead;
            } else if (secondPoint == null) {
                assert cur != null;
                cur.next = firstPoint;
                headListNode = newHead;
                return newHead;
            }

            // using merge sort
            // sort and merge the two list together
            if (firstPoint.val <= secondPoint.val) { // WARN: ascending, might be reversing
                if (newHead == null) {
                    newHead = firstPoint;
                    cur = firstPoint;
                } else {
                    cur.next = firstPoint;
                    cur = cur.next;
                }
                // firstPoint.next = null;
                firstPoint = firstPoint.next;
            } else {
                if (newHead == null) {
                    newHead = secondPoint;
                    cur = secondPoint;
                } else {
                    cur.next = secondPoint;
                    cur = cur.next;
                }
                // secondPoint.next = null;
                secondPoint = secondPoint.next;
            }
        }
    }

    // reverse the order of nodes of list
    // attribute “sorted” should be changed if the list is sorted before
    public void reverse() {
        if (headListNode == null) {
            return;
        }
        if (headListNode.next == null) {
            sorted = 1; // default to be ascending ?
            return;
        }

        reverseList(headListNode);
        if (sorted != 0) {
            this.sorted = (-1) * sorted;
        }
    }

    public void reverseList(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }

        ListNode prev = head; // hold the pervious node
        ListNode holder = head.next.next; // cut the relationship with the next node
        ListNode buffer = head.next;

        while (true) {
            if (holder == null) {
                if (prev == head) {
                    prev.next = null;
                }
                buffer.next = prev;
                prev = buffer;
                break;
            } else if (prev == head) {
                prev.next = null; // cutoff relationship with the left nodes
                buffer.next = prev; // make it to the head of out new list
                prev = buffer;
                buffer = holder; // move buffer to next nodes
                holder = holder.next;
            } else {
                buffer.next = prev; // make it to the head of out new list
                prev = buffer;
                buffer = holder; // move buffer to next nodes
                holder = holder.next;
            }
        }
        headListNode = prev;
    }

    // add node to the tail of the list – basic method
    public void addNode(ListNode node) { // WARN: wait for test
        if (node == null) {
            return;
        }

        ListNode tempNode = headListNode;
        while (tempNode.next != null) { // find the last node
            tempNode = tempNode.next;
        }
        tempNode.next = node;

        // determine that if the list become unsorted
        if ((sorted() == 1 && tempNode.val > node.val) || (sorted() == -1 && tempNode.val < node.val)) {
            sorted = 0;
        }
        this.size++;
    }

    // add node to sorted list and keep list still sorted
    // node should add to the position according to the value
    public void addNodeSorted(ListNode node) {
        if (node == null) {
            return;
        }

        if (sorted == 0)
            System.out.println("List not sorted, method run wrong!");

        ListNode tempNode = headListNode;
        ListNode holder = tempNode.next; // tempNode is the previous node, holder is the following node
        switch (sorted()) {
            case 1: // ascending
                while (true) {
                    if (holder == null) {
                        tempNode.next = node;
                        break;
                    }
                    if (tempNode == headListNode && node.val <= tempNode.val) {
                        node.next = tempNode;
                        headListNode = node;
                        break;
                    }
                    if (node.val >= tempNode.val && node.val < holder.val) {
                        tempNode.next = node;
                        node.next = holder;
                        break;
                    }
                    tempNode = holder;
                    holder = holder.next;
                }
                break;
            case -1: // descending
                while (true) {
                    if (holder == null) {
                        tempNode.next = node;
                        break;
                    }
                    if (tempNode == headListNode && node.val >= tempNode.val) {
                        node.next = tempNode;
                        headListNode = node;
                        break;
                    }
                    if (node.val <= tempNode.val && node.val > holder.val) {
                        tempNode.next = node;
                        node.next = holder;
                        break;
                    }
                    tempNode = holder;
                    holder = holder.next;
                }
                break;
        }
        this.size++;
    }

    // add node to position of index, which is from 0;
    // return true if succeeded, false if failed
    public boolean addNode(int index, ListNode node) {
        if (node == null) {
            return false;
        }

        if (index < 0 || size() < index) {
            return false;
        }

        if (index == 0) {
            node.next = headListNode;
            headListNode = node;
            size++;
            return true;
        }

        ListNode tempNode = headListNode;
        for (int i = 0; i < index - 1; i++) {
            tempNode = tempNode.next;
        }

        ListNode holder = tempNode.next; // not lose the remaining list
        tempNode.next = node;
        node.next = holder;

        // determine that if the list become unsorted
        switch (sorted()) {
            case 0:
                break;
            case 1:
                if (tempNode.val > node.val || node.val > holder.val)
                    sorted = 0;
                break;
            case -1:
                if (tempNode.val < node.val || node.val < holder.val)
                    sorted = 0;
                break;
        }
        size++;
        return true;
    }

    // delete node, return true if succeeded, false if failed
    public boolean deleteNode(ListNode node) {
        ListNode prevNode = null;
        ListNode judgingNode = headListNode;
        while (true) {
            int status = this.delete(prevNode, judgingNode, node);
            if (status == 0) {
                prevNode = judgingNode;
                judgingNode = judgingNode.next;
            } else {
                return status == 1;
            }
        }
    }

    // delete node whose “val” equals to value, return true if succeeded, false if
    // failed
    public boolean deleteNode(int value) {
        return removeElements(headListNode, value);
    }

    public boolean removeElements(ListNode head, int val) {
        if (head == null) {
            return false;
        }
        boolean isDeleted = false;
        ListNode cur = head;
        while (true) {
            if (head.next == null) { // note: 1 node condition
                if (head.val == val) {
                    ListNode newHead = null;
                    head.next = null;
                    head = null;
                    headListNode = null;
                    isDeleted = true;
                    size--;
                    break;
                } else {
//                    isDeleted = false;
                    break;
                }
            }
            if (cur.next == null) {
                break;
            }
            if (head.val == val) { // note: assert cur = null
                cur = cur.next;
                head.next = null;
                head = null;
                headListNode = cur;
                head = cur;
                isDeleted = true;
                size--;
                continue;
            }
            if (cur.next.val == val) {
                ListNode deleteNode = cur.next;
                cur.next = deleteNode.next;
                deleteNode = null;
                isDeleted = true;
                size--;
            } else {
                cur = cur.next;
            }
        }
        return isDeleted;
    }

    // delete duplicated nodes from unsorted list
    // tired, use brute force to solve this...
    public void deleteDuplicates() {
        ListNode cur = headListNode;
        while (cur != null) {
            ListNode temp = cur.next;
            ListNode holder = cur;
            while (temp != null) {
                if (cur.val == temp.val) {
                    ListNode foll = temp.next;
                    temp.next = null;
                    temp = null;
                    holder.next = foll;
                    temp = foll;
                    size--;
                    continue;
                }

                temp = temp.next;
                holder = holder.next;
            }
            cur = cur.next;
        }
    }

    // return the value of the k-th node from the bottom
    // note: k start from 1
    public int kthToLast(int k) {
        assert headListNode != null;
        if (headListNode.next == null) { // only one node
            if (k == 1) {
                return headListNode.val;
            }
        }
        ListNode prev = headListNode;
        ListNode foll = headListNode;
        for (int i = 0; i < k; i++) {
            foll = foll.next; // let there be a space of k between two pointers
        }

        while (true) {
            if (foll == null) {
                return prev.val;
            } else {
                prev = prev.next;
                foll = foll.next;
            }
        }
    }

    // return the sum of the linked list
    public int sum() {
        int sum = 0;
        ListNode cur = headListNode;
        while (cur != null) {
            sum += cur.val;
            cur = cur.next;
        }
        return sum;
    }

    // merge two sorted lists and keep new list still sorted
    public void mergeSortedList(List listToMerge) {
        merge(this.headListNode, listToMerge.headListNode); // WARN: hope the merge work here
        size += listToMerge.size;
    }

    /*
     * **self-method** my thought is to consider three conditions
     * 1.<null, notNull> the node to delete is the head
     * 2.<notNull, judgingNode.next == null> return null
     * 3.<notNull, notNull> delete it
     *
     * returned value: 1-true 0-false -1 stop loop
     */
    public int delete(ListNode prevNode, ListNode judgingNode, ListNode targetNode) {
        if (prevNode == null) { // this is for the 1st condition
            if (judgingNode == targetNode) {
                headListNode = judgingNode.next;
                targetNode = null;
                judgingNode = null;
                size--;
                return 1;
            } else {
                return 0;
            }
        } else if (judgingNode.next == null) { // for 2nd condition
            if (judgingNode == targetNode) {
                prevNode.next = null;
                targetNode = null;
                judgingNode = null;
                size--;
                return 1;
            } else {
                return -1;
            }
        } else { // for 3rd condition
            if (judgingNode == targetNode) {
                prevNode.next = judgingNode.next;
                judgingNode = null;
                targetNode = null;
                size--;
                return 1;
            } else {
                return 0;
            }
        }
    }
}
