
import java.util.Deque;
import java.util.LinkedList;

public class StackMin<E extends Comparable<E>> {

  private class Node {
    E data;
    Node next;
    public Node(final E data) {
      this.data = data;
    }
  }

  private Node head;
  private int size;
  private Deque<E> minStack;

  public StackMin() {
    minStack = new LinkedList<E>();
  }

  public void push(final E data) {
    if (data == null) {
      throw new NullPointerException("Nulls are not permitted in StackMin");
    }
    Node top = new Node(data);
    if (this.isEmpty()) {
      head = top;
      minStack.push(data);
    } else {
      top.next = head;
      head = top;
      if (data.compareTo(minStack.peek()) <= 0) {
        minStack.push(data);
      }
    }
  }

  public E pop() {
    if (isEmpty()) {
      return null;
    }
    final E data = head.data;
    head = head.next;
    if (data.compareTo(minStack.peek()) == 0) {
      minStack.pop();
    }
    return data;
  }

  public E min() {
    return minStack.peek();
  }

  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    for (Node current = head; current != null; current = current.next) {
      builder.append(current.data + (current.next == null ? "" : ", "));
    }
    builder.append("]");
    return builder.toString();
  }

  public static void main(String[] args) {
    StackMin<Integer> stack = new StackMin<Integer>();
    stack.push(1);
    for (int i = 10; i > -3; i--) {
      System.out.println("Pushing  " + i);
      stack.push(i);
      System.out.println(stack + "   MIN: " + stack.min());
    }
    for (int i = 0; i < 5; i++) {
      System.out.println("Popped " + stack.pop() + " MIN: " + stack.min());
      System.out.println("stack is " + stack);
    }
  }

}
