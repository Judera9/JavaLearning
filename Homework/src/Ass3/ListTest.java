package Ass3;

import org.junit.Test;

import org.junit.Before;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ListTest {
    List list1;

    @Before
    public void creatList1(){
        ListNode node = new ListNode(11);
        list1 = new List(node);
        //System.out.println(list1);
    }

    @Test(timeout = 1000)
    public void test1() {
        ListNode node2 = new ListNode(8);
        ListNode node3 = new ListNode(9);
        list1.addNode(node2);
        list1.addNode(0,node3);
        ListNode node4 = new ListNode(7);
        list1.addNode(3,node4);
        System.out.println(list1);
//        "[9, 11, 8, 7]"
        assertEquals(35, list1.sum());
        assertEquals(4, list1.size());
        list1.reverse();
        int[] temp = {9, 11, 8, 7};
        for (int i = 1; i <= 4; i++) {
            assertEquals(temp[i-1], list1.kthToLast(i));
        }
//        "[7, 8, 11, 9]"
        Arrays.sort(temp);
        list1.sort();
        assertEquals(1, list1.sorted());
        for (int i = 1; i <= 4; i++) {
            assertEquals(temp[4-i], list1.kthToLast(i));
        }
//        "[7, 8, 9, 11]"
        ListNode node5 = new ListNode(10);
        list1.addNodeSorted(node5);
        assertEquals(10, list1.kthToLast(2));
//        "[7, 8, 9, 10, 11]"

        boolean ok = list1.deleteNode(node5);
        assertEquals(4, list1.size());
        assertTrue(ok);
        for (int i = 1; i <= 4; i++) {
            assertEquals(temp[4-i], list1.kthToLast(i));
        }
//        "[7, 8, 9, 11]"

        boolean wrong = list1.deleteNode(0);
        assertFalse(wrong);
        ok = list1.deleteNode(7);
        assertTrue(ok);
        ok = list1.deleteNode(11);
        assertTrue(ok);
//        "[8, 9]"

        ListNode node6 = new ListNode(8);
        list1.addNode(node6);
        ListNode node7 = new ListNode(9);
        list1.addNode(node7);
        list1.deleteDuplicates();
        assertEquals(2, list1.size());
        assertEquals(17, list1.sum());
        list1.sort();
//        "[8, 9]"

        ListNode node11 = new ListNode(1);
        List list2 = new List(node11);
        ListNode node12 = new ListNode(9);
        list2.addNode(node12);
        list2.sort();
        list1.mergeSortedList(list2);
//        "[1, 8, 9, 9]"
        assertEquals(1, list1.sorted());
    }

    @Test(timeout = 1000)
    public void test2() {
        ListNode node = new ListNode(8);
        list1 = new List(node);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        list1.addNode(node2);
        list1.addNode(node3);
        assertEquals(3, list1.size());
        assertEquals(17, list1.sum());
//        "[8, 4, 5]"

        ListNode node4 = new ListNode(8);
        ListNode node5 = new ListNode(7);
        list1.addNode(1,node4);
        assertEquals(8, list1.kthToLast(4));
//        "[8, 8, 4, 5]"

        list1.sort();
        assertEquals(1, list1.sorted());
        assertEquals(5, list1.kthToLast(3));
//        "[4, 5, 8, 8]"

        list1.reverse();
//        "[8, 8, 5, 4]"
        assertEquals(-1, list1.sorted());
        int[] temp = {4, 5, 8, 8};
        for (int i = 1; i <= 4; i++) {
            assertEquals(temp[i-1], list1.kthToLast(i));
        }

        list1.addNodeSorted(node5);
//        "[8, 8, 7, 5, 4]"
        assertEquals(-1, list1.sorted());
        assertEquals(7, list1.kthToLast(3));

        ListNode node6 = new ListNode(8);
        boolean wrong = list1.deleteNode(node6);
        assertEquals(5, list1.size());
        assertFalse(wrong);

        wrong = list1.deleteNode(-1);
        assertEquals(5, list1.size());
        assertFalse(wrong);
        assertEquals(32, list1.sum());

        list1.deleteDuplicates();
        assertEquals(4, list1.size());
//        "[8, 7, 5, 4]"
        temp[2] = 7;
        for (int i = 1; i <= 4; i++) {
            assertEquals(temp[i-1], list1.kthToLast(i));
        }
        assertEquals(-1, list1.sorted());

        ListNode node11 = new ListNode(1);
        List list2 = new List(node11);
        ListNode node12 = new ListNode(9);
        list2.addNode(node12);
        list2.sort();
        list2.reverse();

        list1.mergeSortedList(list2);
//        "[9, 8, 7, 5, 4, 1]"
        assertEquals(6, list1.size());
        assertEquals(34, list1.sum());
        assertEquals(-1, list1.sorted());
    }
}
