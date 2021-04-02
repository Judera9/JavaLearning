package Ass3;

public class List {

    /*
     * Given attributes and methods
     */

    public ListNode headListNode;
    private int size;
    private int sorted;

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

    /*
     * New methods to insert
     */

    // sort the list ascending. Any sorting algorithm is fine.
    // attribute sorted should be changed to 1
    public void sort() {

    }


    // reverse the order of nodes of list
    // attribute “sorted” should be changed if the list is sorted before
    public void reverse() {

    }

    // add node to the tail of the list – basic method
    public void addNode(ListNode node) {
        ListNode tempNode = headListNode;
        while (tempNode.next != null) { // find the last node
            tempNode = tempNode.next;
        }
        tempNode.next = node;

        // determine that if the list become unsorted
        if ((sorted() == 1 && tempNode.val > node.val) || (sorted() == -1 && tempNode.val < node.val)) {
            sorted = 0;
        }
    }

    // add node to sorted list and keep list still sorted
    // node should add to the position according to the value
    public void addNodeSorted(ListNode node) {
        if (sorted == 0)
            System.out.println("List not sorted, method run wrong!");

        ListNode tempNode = headListNode;
        ListNode holder = tempNode.next; // tempNode is the previous node, holder is the following node
        switch (sorted()) {
            case 1: // ascending
                while (!(node.val < tempNode.val || node.val > holder.val)) {
                    tempNode.next = node;
                    node.next = holder;
                }
                break;
            case -1: // descending
                while (!(node.val > tempNode.val || node.val < holder.val)) {
                    tempNode.next = node;
                    node.next = holder;
                }
                break;
        }
    }

    // add node to position of index, which is from 0;
    // return true if succeeded, false if failed
    public boolean addNode(int index, ListNode node) {
        if (index < 0 && size() <= index) {
            return false;
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

        return true;
    }

    //delete node, return true if succeeded, false if failed
    public boolean deleteNode(ListNode node) {
        ListNode prevNode = null;
        ListNode judgingNode = headListNode;
        while (true) {
            int status = this.delete(prevNode, judgingNode, node);
            if (status == 0) {
                prevNode = judgingNode;
                judgingNode = judgingNode.next;
            } else return status == 1;
        }
    }

    //delete node whose “val” equals to value, return true if succeeded, false if failed
    public boolean deleteNode(int value) {
        ListNode tempNode = headListNode;

        while (tempNode.next != null) {
            if (tempNode.next.val != value) {
                tempNode = tempNode.next;
            } else {
                ListNode holder = tempNode.next.next; // hold the following node to prevent missing list
                tempNode.next = null; // delete it thoroughly
                tempNode.next = holder;
            }
        }
    }

    //delete duplicated nodes from unsorted list
    public void deleteDuplicates()

    // return the value of the k-th node from the bottom
    public int kthToLast(int k)

    // return the sum of the linked list
    public int sum()

    // merge two sorted lists and keep new list still sorted
    public void mergeSortedList(List listToMerge)


    /* **self-method**
      my thought is to consider three conditions
     * 1.<null, notNull> the node to delete is the head
     * 2.<notNull, judgingNode.next == null> return null
     * 3.<notNull, notNull> delete it

     returned value:
     1-true 0-false -1 stop loop 2-wrong
     */
    public int delete(ListNode prevNode, ListNode judgingNode, ListNode targetNode) {
        if (prevNode == null) { // this is for the 1st condition
            if (judgingNode == targetNode) {
                headListNode = judgingNode.next;
                targetNode = null;
                judgingNode = null;
                return 1;
            } else {
                return 0;
            }
        } else if (judgingNode.next == null) { // for 2nd condition
            if (judgingNode == targetNode) {
                prevNode.next = null;
                targetNode = null;
                judgingNode = null;
                return 1;
            } else {
                return -1;
            }
        } else { // for 3rd condition
            if (judgingNode == targetNode) {
                prevNode.next = judgingNode.next;
                judgingNode = null;
                targetNode = null;
                return 1;
            } else {
                return 0;
            }
        }
    }
}
