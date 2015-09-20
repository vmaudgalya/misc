// Given a sorted linked list, delete all
// duplicates such that each element appear only once.

// For example,
// Given 1->1->2, return 1->2.
// Given 1->1->2->3->3, return 1->2->3.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/************
* Time: O(n)
* Space: O(1)
*************/
public class RemoveDuplicates {
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return head;
    }
    ListNode current = head;
    while (current != null) {
      ListNode next = current.next;
      while (next != null && current.val == next.val) {
        next = next.next;
      }
      current.next = next;
      current = current.next;
    }
    return head;
  }
}